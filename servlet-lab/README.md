# Servlet Lab --- Java Web Application

## Overview

This project is a Maven-based Java Servlet lab with examples for a
simple web application. The visible source includes a home servlet,
login/logout and registration servlets, an authentication filter, an
application listener, and a static `index.html`.

The project is useful for learning how HTTP requests are routed through
servlets and filters in a servlet container.

## Objectives

-   Build and package a Java web application with Maven.
-   Handle HTTP requests through servlets.
-   Explore login, logout, and registration request flows.
-   Understand how filters can guard or process requests.
-   Learn the role of application listeners and static web resources.

These are the concepts suggested by the file names; inspect the source
to confirm which workflows are fully implemented.

## Technologies

-   Java / JDK
-   Maven
-   Java Servlet API
-   HTML
-   A servlet container compatible with the Servlet API version declared
    in `pom.xml`

## Project structure

``` text
servlet-lab/
├── pom.xml
├── README.md
├── .gitignore
└── src/main/
    ├── java/com/servlet/
    │   ├── AppListener.java
    │   ├── AuthFilter.java
    │   ├── HelloServlet.java
    │   ├── HomeServlet.java
    │   ├── LoginServlet.java
    │   ├── LogoutServlet.java
    │   └── RegistrationServlet.java
    └── webapp/
        └── index.html
```

## Prerequisites

1.  Install a JDK compatible with the Maven configuration.
2.  Install Maven.
3.  Install a compatible servlet container.
4.  Review `pom.xml` for Java and Servlet API versions.
5.  Inspect servlet mappings, filter mappings, and any
    database/configuration dependencies before running.

## Build

Open a terminal in `servlet-lab` and run:

``` powershell
mvn clean package
```

If Maven reports a successful build, locate the generated WAR file in
the `target` directory.

## Deploy and run

1.  Deploy the generated WAR to a compatible servlet container.
2.  Start the container.
3.  Open the deployed application context in a browser.
4.  Test the home page and each mapped route.
5.  Review server logs if a request returns an error.

The exact context path and endpoint URLs depend on the WAR name and
servlet mappings. Check the source rather than assuming the routes.

## Request flow

A typical protected request may pass through these stages:

1.  The browser sends an HTTP request.
2.  The container applies matching filters, including `AuthFilter` if
    mapped.
3.  The request is routed to a servlet.
4.  The servlet processes input and creates a response or redirects the
    browser.
5.  The container returns the response to the browser.

The actual sequence depends on the filter and servlet mappings
configured in this project.

## Troubleshooting

-   **404:** verify the deployed context path and servlet mappings.
-   **Login/registration does not work:** inspect form field names, HTTP
    methods, and servlet logic.
-   **Protected page is accessible unexpectedly:** review `AuthFilter`
    mapping and authorization checks.
-   **Build fails:** check the JDK version and dependency declarations
    in `pom.xml`.
-   **Changes do not appear:** rebuild and redeploy the WAR.

## Security considerations

A classroom login/registration example should not be treated as
production authentication without review. Check password handling,
session management, CSRF protections, input validation, output encoding,
and storage of user data before using it for real users. Never commit
real credentials or private user data.

## Future improvements

-   Document exact routes and screenshots.
-   Add server-side input validation.
-   Use secure password hashing if credentials are persisted.
-   Add tests for authentication and authorization paths.
-   Add explicit error handling and user-friendly feedback.

## Learning outcome

This lab provides a focused introduction to Java Servlet request routing
and common web application components.
