package com.shopsphere.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class SocketInventoryClient {
    private final String host;
    private final int port;
    public SocketInventoryClient(String host, int port) { this.host = host; this.port = port; }
    public Serializable request(String command) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket(host, port); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
            output.writeObject(command); output.flush();
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                input.setObjectInputFilter(java.io.ObjectInputFilter.Config.createFilter("maxdepth=12;maxrefs=2000;maxbytes=2000000;java.base/*;java.util.*;com.shopsphere.common.model.*;!*"));
                Object result = input.readObject();
                if (!(result instanceof Serializable serializable)) throw new IOException("Server returned an unsupported response.");
                return serializable;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        String command = args.length == 0 ? "GET_STOCK|1" : String.join("|", args);
        Serializable result = new SocketInventoryClient("127.0.0.1", 5050).request(command);
        System.out.println(result);
    }
}
