# JDBC --- Java Database Connectivity

## Overview

This project demonstrates connecting a Java program to a relational
database through **JDBC (Java Database Connectivity)**. JDBC provides
Java APIs for opening database connections, executing SQL statements,
and processing results.

The repository includes `src/App.java` and a `lib` directory containing
a MySQL Connector/J JAR, indicating that the project is set up to use
the MySQL JDBC driver.

## Objectives

-   Understand the role of a JDBC driver.
-   Establish a connection from Java to a database.
-   Execute SQL statements through Java code.
-   Process database results and handle database exceptions.
-   Learn how Java applications interact with persistent data.

## Technologies

-   Java / JDK
-   JDBC API
-   MySQL Connector/J driver (included under `lib`, if this is the
    driver used by `App.java`)
-   MySQL or another compatible database, depending on the connection
    settings in the source code

## Project structure

``` text
JDBC/
├── src/
│   └── App.java
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── .gitignore
└── README.md
```

## Prerequisites

1.  Install a JDK.
2.  Install and start MySQL if the source code connects to MySQL.
3.  Create the database and tables expected by `App.java`.
4.  Check the JDBC URL, database name, username, and password in the
    source code. Use your own local settings; do not publish real
    credentials.

## Compile and run on Windows PowerShell

Run commands from the `JDBC` directory. The following assumes `App.java`
has no package declaration and the bundled driver JAR is used:

``` powershell
javac -cp ".;lib/mysql-connector-j-26.7.0.jar" src/App.java
java -cp ".;src;lib/mysql-connector-j-26.7.0.jar" App
```

If the source declares a package, or if the class name differs, adjust
the command to match the source. You can also run the project from an
IDE by adding the driver JAR to the project's classpath.

## How it works

1.  The application loads or uses the JDBC driver.
2.  It opens a database connection using a JDBC URL and credentials.
3.  It sends SQL through a `Statement` or `PreparedStatement`.
4.  It reads results from a `ResultSet`, when the query returns rows.
5.  It closes resources and reports errors.

The exact operations performed are determined by `src/App.java`; read
that file to confirm the queries and schema used.

## Common troubleshooting

-   **Connection refused:** confirm that the database server is running
    and the host/port in the JDBC URL are correct.
-   **Access denied:** verify the database username, password, and
    permissions.
-   **Driver not found:** ensure the JAR is included in the classpath.
-   **Unknown database/table:** create the database and tables expected
    by the source code.
-   **Class not found:** check whether `App.java` declares a package and
    compile/run from the correct directory.

## Future improvements

-   Use `PreparedStatement` for values supplied by users.
-   Move credentials to environment variables or a local, untracked
    configuration file.
-   Add validation and clear error messages.
-   Separate connection setup, data-access logic, and user interaction
    into different classes.
-   Add tests for database operations.

## Learning outcome

This project provides a starting point for understanding how a Java
program communicates with a relational database using JDBC.
