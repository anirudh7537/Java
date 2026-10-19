# Student Registration --- Java and Database

## Overview

This project contains a Java student-registration application with
separate connection and application classes, plus a SQL script for the
database.

The files visible in the project are `DBConnection.java`,
`StudentRegistration.java`, and `student_db.sql`.

## Objectives

-   Connect a Java program to a relational database.
-   Separate database connection setup from application logic.
-   Work with student-related records using SQL.
-   Practice handling data persistence from Java.

## Technologies

-   Java / JDK
-   JDBC, if used by `DBConnection.java`
-   A relational database compatible with `student_db.sql`

Check the imports and connection URL in the source code to confirm the
database driver and engine.

## Project structure

``` text
StudentRegistration/
├── DBConnection.java
├── StudentRegistration.java
├── student_db.sql
├── .gitignore
└── README.md
```

## Prerequisites

1.  Install a JDK.
2.  Install and start the database engine expected by `student_db.sql`.
3.  Inspect `student_db.sql` and execute it in a development database if
    it creates the schema needed by the program.
4.  Configure database host, port, database name, username, and password
    in the appropriate source/configuration.
5.  Add the required JDBC driver to the classpath.

## Setup

1.  Read `student_db.sql` before executing it.
2.  Create a dedicated local database for this project.
3.  Execute the SQL script using your database client.
4.  Update the connection settings used by `DBConnection.java`.
5.  Do not upload real passwords or personal student data to a public
    repository.

## Compile and run

The exact command depends on the database driver and class names. If
both Java files are in the default package and no external driver is
needed at compile time:

``` powershell
javac DBConnection.java StudentRegistration.java
```

If JDBC classes are referenced, the driver usually needs to be available
at runtime. For a driver JAR at `lib\driver.jar`, a Windows PowerShell
classpath may look like:

``` powershell
java -cp ".;lib\driver.jar" StudentRegistration
```

Adjust the JAR path, main class, and commands to match the actual
project. Run the class containing
`public static void main(String[] args)`.

## How the parts fit together

-   **`DBConnection.java`:** expected to centralize database connection
    creation.
-   **`StudentRegistration.java`:** expected to contain the program flow
    and registration-related operations.
-   **`student_db.sql`:** contains database setup or SQL statements used
    by the project.

Inspect the code to confirm the precise fields, validations, and
database operations.

## Troubleshooting

-   Check that the database server is running.
-   Verify JDBC URL and credentials.
-   Confirm the schema and table names match the SQL used by the Java
    code.
-   Ensure the JDBC driver is present on the runtime classpath.
-   If records are not saved, inspect SQL exceptions and
    transaction/commit behavior.

## Future improvements

-   Validate inputs before sending SQL.
-   Use `PreparedStatement` for user-supplied values.
-   Add clear handling for duplicate student identifiers.
-   Separate input/UI logic from database operations.
-   Add tests for validation and database access.

## Learning outcome

This project demonstrates the basic structure of a Java application that
stores student-related information in a database.
