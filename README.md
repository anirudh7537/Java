# Java Projects Portfolio

A collection of Java projects covering core Java programming,
client-server networking, JDBC database connectivity, Java RMI, MVC
design, and Java Servlet web applications.

**Repository:** [anirudh7537/Java](https://github.com/anirudh7537/Java)

## Projects

  --------------------------------------------------------------------------------------------------
  Project                                                        Main learning areas
  -------------------------------------------------------------- -----------------------------------
  [JDBC](./JDBC/README.md)                                       Java Database Connectivity, SQL
                                                                 database access

  [MVCCalculator](./MVCCalculator/README.md)                     Model--View--Controller (MVC),
                                                                 separation of concerns

  [RMI](./RMI/README.md)                                         Java Remote Method Invocation,
                                                                 remote client-server calls

  [ShopSphere](./ShopSphere/README.md)                           Multi-module Java e-commerce
                                                                 application, persistence, services,
                                                                 networking and web UI

  [StudentRegistration](./StudentRegistration/README.md)         Java application with database
                                                                 connection and student records

  [java-tcp-client-server](./java-tcp-client-server/README.md)   TCP sockets, client-server
                                                                 communication

  [servlet](./servlet/README.md)                                 Java Servlet API, request/response
                                                                 handling, sessions, filters and
                                                                 listeners

  [servlet-lab](./servlet-lab/README.md)                         Servlet-based web application with
                                                                 login/registration examples
  --------------------------------------------------------------------------------------------------

## Repository structure

``` text
Java/
├── JDBC/
├── MVCCalculator/
├── RMI/
├── ShopSphere/
├── StudentRegistration/
├── java-tcp-client-server/
├── servlet/
└── servlet-lab/
```

Each folder is an independent learning project. Build and run projects
from their own directories; dependencies and execution steps are not
shared across the entire repository.

## General requirements

-   Install a JDK compatible with each project's source code and build
    configuration.
-   Install Git to clone the repository.
-   Install Maven for projects that contain a `pom.xml`.
-   Install and configure a supported SQL database for database-backed
    projects.
-   For servlet projects, use a servlet container compatible with the
    Servlet API version declared by the project's `pom.xml`.

Check each project's README and build files before running it. Database
names, credentials, ports, and server settings may need to be configured
for your own computer.

## Clone the repository

``` bash
git clone https://github.com/anirudh7537/Java.git
cd Java
```

## How to use this repository

1.  Open the folder of the project you want to study.
2.  Read that project's README and inspect its source files.
3.  Configure any database or server dependency locally.
4.  Compile or build the project using the instructions in its README.
5.  Do not commit database passwords, private configuration, generated
    build output, or IDE-specific files.

## Learning outcomes

This portfolio demonstrates practice with Java classes and interfaces,
separation of responsibilities, database access, network communication,
remote calls, web request handling, and modular application
organization.

## Notes

This repository groups several independent projects; it is not one
application that starts with a single command. Some projects may be
coursework or demonstrations rather than production-ready software.
Review and test each project before using it in a real environment.
