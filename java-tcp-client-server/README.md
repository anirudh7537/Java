# Java TCP Client-Server

A basic TCP client-server program in Java using `Socket` and `ServerSocket` from the `java.net` package.

## How it works
- The server listens on port 5000 and waits for a client to connect.
- Once connected, the client sends "Hello Server" and the server replies "Hello Client".
- Both sides then close the connection.

## Run it
1. Compile both files: `javac Server.java Client.java`
2. Start the server: `java Server`
3. In a separate terminal, start the client: `java Client`

## Concepts covered
- TCP vs UDP
- Sockets and ports
- Blocking I/O with `accept()`
- `BufferedReader` / `PrintWriter` for stream communication