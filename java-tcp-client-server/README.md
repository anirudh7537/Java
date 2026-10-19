# Java TCP Client--Server

## Overview

This project demonstrates communication between a Java client and server
using TCP sockets. TCP provides a reliable, ordered byte stream between
endpoints; the application defines the messages exchanged over that
stream.

The project includes `src/Client.java` and `src/Server.java`.

## Objectives

-   Understand the client-server model.
-   Practice creating TCP sockets in Java.
-   Learn how a server accepts client connections.
-   Exchange messages over input/output streams.
-   Understand why client and server must agree on a message protocol.

## Technologies

-   Java / JDK
-   TCP sockets (`java.net`)
-   Input/output streams

## Project structure

``` text
java-tcp-client-server/
├── src/
│   ├── Client.java
│   └── Server.java
├── .gitignore
└── README.md
```

## Prerequisites

-   Install a JDK.
-   Inspect `Server.java` to identify the port and expected startup
    behavior.
-   Ensure the chosen port is not already in use and is allowed by local
    firewall rules.

## Compile

Run these commands from the project directory if the files use the
default package:

``` powershell
javac src\Server.java src\Client.java
```

## Run

Start the server first:

``` powershell
java -cp src Server
```

Open a second terminal and start the client:

``` powershell
java -cp src Client
```

If the code uses a package declaration or different port/host settings,
adapt the commands accordingly. The client host and port must match the
server's configuration.

## How it works

1.  The server creates a `ServerSocket` bound to a configured port.
2.  The server waits for an incoming connection.
3.  The client connects using a `Socket`.
4.  Both sides exchange data using streams or another protocol defined
    by the source code.
5.  The application closes sockets and streams when finished.

The precise message format and whether the server supports one or
multiple clients depend on the implementation.

## Troubleshooting

-   **Connection refused:** start the server before the client.
-   **Address already in use:** change the configured port or stop the
    process using it.
-   **Client connects but hangs:** check whether both sides expect a
    line terminator, flush, or a particular message order.
-   **Cannot connect from another computer:** check host configuration,
    firewall, and network reachability.

## Future improvements

-   Handle multiple clients using threads or an executor.
-   Define a clear message protocol and validation.
-   Add timeouts and robust exception handling.
-   Use try-with-resources for sockets and streams.
-   Add logging and tests for disconnects and malformed messages.

## Security note

This is a networking learning project. Plain TCP communication does not
automatically encrypt data or authenticate clients. Do not use it to
transmit sensitive information without appropriate security controls.

## Learning outcome

This project provides hands-on practice with Java networking and the
basic lifecycle of a TCP client-server connection.
