# Java RMI Calculator

## Overview

This project demonstrates **Java Remote Method Invocation (RMI)** using
a calculator example. RMI allows a Java program to invoke methods on an
object hosted by another Java process, subject to the remote interface
and RMI configuration.

The files include a calculator interface/implementation and separate
server/client entry points.

## Objectives

-   Understand remote interfaces and remote object implementations.
-   Learn the roles of an RMI server and client.
-   Register or expose a remote object and look it up from a client.
-   Observe remote method calls between separate Java processes.

## Technologies

-   Java / JDK
-   Java RMI APIs (`java.rmi`)
-   Two terminal sessions or IDE run configurations for server and
    client

## Project structure

``` text
RMI/
├── Calculator.java
├── CalculatorImplement.java
├── Server.java
├── Client.java
└── .gitignore
```

## Responsibilities

-   **`Calculator.java`:** expected to define the remote calculator
    contract; inspect it for the methods and whether it extends
    `Remote`.
-   **`CalculatorImplement.java`:** expected to implement the remote
    calculator operations.
-   **`Server.java`:** starts or registers the remote service.
-   **`Client.java`:** looks up the remote service and calls its
    methods.

These descriptions are based on the filenames; the implementation
details and binding name should be confirmed in the source code.

## Prerequisites

-   Install a JDK.
-   Run both client and server on the same computer for a basic test,
    unless the source code is configured for separate hosts.
-   Ensure the hostname, port, and RMI binding name used by the client
    match the server configuration.

## Compile and run

If the files use the default package and have no external dependencies:

``` powershell
javac *.java
```

Start the server first:

``` powershell
java Server
```

Open a second terminal in the same directory and start the client:

``` powershell
java Client
```

If the program expects a separately started RMI registry, start it using
the port and setup described in the source code before launching the
server. If the files declare a package, use the appropriate
package-aware compile and run commands.

## How RMI works

1.  The server creates or exports an implementation of a remote
    interface.
2.  The server makes the remote object available through an RMI registry
    or another configured lookup mechanism.
3.  The client looks up the remote reference.
4.  The client invokes methods on that reference.
5.  RMI transports the request and returns the result or an exception.

## Troubleshooting

-   **Connection refused:** ensure the server/registry is running and
    the configured port is reachable.
-   **Not bound / name not found:** check the registry binding name and
    startup order.
-   **Remote exception:** inspect server output, network settings, and
    remote method declarations.
-   **Class or package errors:** compile from the correct directory and
    use the correct fully qualified class names.

## Security note

Do not expose an RMI service to an untrusted network without
understanding the risks and configuring network access appropriately.

## Future improvements

-   Validate inputs and handle remote failures clearly.
-   Add logging on both sides.
-   Document registry settings and supported operations.
-   Add tests for calculator behavior separately from the networking
    layer.

## Learning outcome

This project provides a practical introduction to remote method calls
between Java processes.
