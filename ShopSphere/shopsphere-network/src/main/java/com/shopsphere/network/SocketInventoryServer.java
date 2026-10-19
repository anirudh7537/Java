package com.shopsphere.network;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class SocketInventoryServer implements AutoCloseable {
    private final int port;
    private final Function<String, Serializable> handler;
    private final ExecutorService clients = Executors.newCachedThreadPool();
    private volatile ServerSocket serverSocket;

    public SocketInventoryServer(int port, Function<String, Serializable> handler) { this.port = port; this.handler = handler; }
    public int getPort() { return serverSocket == null ? port : serverSocket.getLocalPort(); }
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        Thread acceptor = new Thread(this::acceptClients, "shopsphere-socket-acceptor");
        acceptor.setDaemon(true);
        acceptor.start();
    }
    private void acceptClients() {
        while (!Thread.currentThread().isInterrupted() && serverSocket != null && !serverSocket.isClosed()) {
            try { Socket client = serverSocket.accept(); clients.submit(() -> handle(client)); }
            catch (SocketException ex) { if (serverSocket != null && !serverSocket.isClosed()) throw new IllegalStateException("Socket server failed.", ex); }
            catch (IOException ex) { throw new IllegalStateException("Socket server failed.", ex); }
        }
    }
    private void handle(Socket socket) {
        try (socket; ObjectInputStream input = new ObjectInputStream(socket.getInputStream()); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
            input.setObjectInputFilter(info -> {
                if (info.depth() > 2 || info.references() > 8 || info.streamBytes() > 4096) return java.io.ObjectInputFilter.Status.REJECTED;
                Class<?> type = info.serialClass();
                return type == null || type == String.class ? java.io.ObjectInputFilter.Status.ALLOWED : java.io.ObjectInputFilter.Status.REJECTED;
            });
            Object command = input.readObject();
            Serializable result = command instanceof String text ? handler.apply(text) : "ERROR|A text command is required";
            output.writeObject(result); output.flush();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }
    @Override public void close() throws IOException {
        if (serverSocket != null) serverSocket.close();
        clients.shutdownNow();
    }
}
