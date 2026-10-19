# Java Projects Portfolio

**Author:** Anirudh Gandhi\
**Repository:** [Java --- GitHub](https://github.com/anirudh7537/Java)

## About This Repository

This repository brings together Java programming projects and practical
exercises in one place. The projects cover core Java, database
connectivity, client-server networking, MVC architecture, Java RMI, and
Java web development using Servlets/JSP. Each project is maintained in
its own folder so that its source code and supporting files remain
organized.

> These are separate projects, not one application that runs with a
> single command. Follow the setup instructions inside each project
> before building or running it.

## Projects

  --------------------------------------------------------------------------------------------
  Project                                      Description             Main concepts
  -------------------------------------------- ----------------------- -----------------------
  [Java TCP                                    Demonstrates            `Socket`,
  Client--Server](./java-tcp-client-server/)   communication between a `ServerSocket`,
                                               Java client and server  streams, networking
                                               using TCP sockets.      

  [JDBC](./JDBC/)                              Demonstrates Java       JDBC driver, database
                                               Database Connectivity   connection, SQL
                                               for connecting Java     
                                               code to a relational    
                                               database.               

  [JSP-Project](./JSP-Project/)                Java web project        JSP/Servlet web
                                               associated with the     development, Maven
                                               Servlet folder and      
                                               Maven configuration.    
                                               Review its project      
                                               README and source for   
                                               the exact implemented   
                                               functionality.          

  [MVC Calculator](./MVCCalculator/)           Organizes a calculator  MVC pattern, separation
                                               using separate model,   of concerns
                                               view, and controller    
                                               classes.                

  [RMI](./RMI/)                                Demonstrates remote     Remote interfaces, RMI
                                               method invocation using server/client
                                               Java client/server      
                                               classes.                

  [Servlet](./servlet/)                        Maven-based servlet     HTTP
                                               examples covering       requests/responses,
                                               request handling and    sessions, filters,
                                               common Servlet API      listeners
                                               concepts.               

  [Servlet Lab](./servlet-lab/)                A separate servlet lab  Servlet routing, web
                                               project with its own    application structure
                                               Maven configuration and 
                                               source tree.            

  [ShopSphere](./ShopSphere/)                  A multi-module Java     Modular design,
                                               project with common,    persistence,
                                               DAO, service, network,  networking, desktop/web
                                               RMI, Swing-admin, and   components
                                               web modules.            

  [Student                                     Java                    Java, JDBC/database
  Registration](./StudentRegistration/)        student-registration    setup, SQL
                                               project with database   
                                               connection code and an  
                                               SQL script.             
  --------------------------------------------------------------------------------------------

Descriptions above summarize the visible folder names and files. Confirm
exact features and supported operations by reviewing each project's
source code.

## Repository Structure

``` text
Java/
├── java-tcp-client-server/
├── JDBC/
├── JSP-Project/
│   ├── Servlet/
│   ├── jsp-project-README.md
│   └── pom.xml
├── MVCCalculator/
├── RMI/
├── servlet/
├── servlet-lab/
├── ShopSphere/
└── StudentRegistration/
```

The tree is simplified to show the main project folders; each folder
contains its own source code and configuration files.

## Technologies and Concepts

Depending on the project, this repository provides practice with:

-   Java classes, objects, and application entry points
-   Model--View--Controller (MVC) organization
-   JDBC and SQL database connectivity
-   TCP socket-based client-server communication
-   Java Remote Method Invocation (RMI)
-   Java Servlet/JSP web applications
-   Maven project configuration and packaging
-   Modular application structure and separation of responsibilities

Check each project's `pom.xml`, source files, and README for the exact
Java version, dependencies, and configuration required.

## Requirements

Common requirements may include:

-   A compatible Java Development Kit (JDK)
-   Git
-   Apache Maven for projects containing a `pom.xml`
-   A compatible servlet container for web projects
-   A configured database and JDBC driver for database-backed projects

Not every project requires every tool. Review the project-specific
README before installation.

## Getting Started

Clone the repository:

``` bash
git clone https://github.com/anirudh7537/Java.git
cd Java
```

Then:

1.  Choose a project folder.
2.  Read its README and inspect its build/configuration files.
3.  Install only the dependencies required by that project.
4.  Configure local database credentials, ports, or server settings if
    needed.
5.  Build and run that project using its own instructions.

There is no single root-level command that runs every project.

## Academic Purpose and Learning Outcomes

This collection is intended to demonstrate practical learning across
Java programming and application development. It helps illustrate how
Java can be used for standalone programs, database-backed applications,
network communication, remote calls, and web applications.

For an academic review or viva, be prepared to explain each submitted
project's purpose, the role of its main classes, its execution steps,
and any limitations or configuration requirements.

## Documentation

Each project should have its own `README.md` containing:

-   Project overview and objectives
-   Technologies and prerequisites
-   Folder structure
-   Setup/build/run instructions
-   Explanation of the main classes or modules
-   Testing, limitations, and possible improvements

For the JSP project, rename `JSP-Project/jsp-project-README.md` to
`JSP-Project/README.md` if it is intended to be the project's main
documentation file. If `Servlet/` is itself an independent project, add
a separate `README.md` inside that folder too.

## Important Notes

-   Do not commit passwords, API keys, or private database credentials.
-   Generated files such as `.class` files and Maven `target/` output
    usually do not need to be committed; check each project's
    `.gitignore`.
-   Test build and run instructions on your own setup before presenting
    them.
-   The README documents the repository; it does not replace a
    demonstration of the actual working code.

------------------------------------------------------------------------

**Maintained by Anirudh Gandhi**
