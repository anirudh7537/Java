# Java Servlet Fundamentals

## Overview

This project contains a Maven-based Java Servlet application with
examples of common servlet concepts. The source tree includes servlets
for requests, sessions, configuration/context, lifecycle, logout, and
student form handling, as well as a filter and a context listener.

It is best treated as a learning project for understanding how a servlet
container manages web requests and application lifecycle events.

## Concepts covered by the visible source files

-   Handling HTTP requests and responses
-   Servlet lifecycle
-   Servlet configuration and application context
-   Session handling and logout
-   Filters for request processing
-   Context listeners
-   Form submission and request parameters

The precise behavior of each example should be confirmed by reading the
corresponding Java file and `pom.xml`.

## Technologies

-   Java / JDK
-   Maven (`pom.xml` is present)
-   Java Servlet API
-   HTML form pages
-   A compatible servlet container for deployment

Check `pom.xml` for the required Java and Servlet API versions, and
select a container that supports that API.

## Project structure

``` text
servlet/
├── pom.xml
└── src/main/
    ├── java/com/servlet/
    │   ├── AppContextListener.java
    │   ├── ConfigServlet.java
    │   ├── ContextServlet.java
    │   ├── HelloServlet.java
    │   ├── LifecycleServlet.java
    │   ├── LoggingFilter.java
    │   ├── LogoutServlet.java
    │   ├── Main.java
    │   ├── RequestResponseServlet.java
    │   ├── SessionServlet.java
    │   └── StudentPostServlet.java
    └── webapp/
        ├── index.html
        └── student-form.html
```

## Prerequisites

1.  Install a JDK compatible with `pom.xml`.
2.  Install Maven.
3.  Install a servlet container compatible with the Servlet API version
    in the POM.
4.  Review servlet mappings and configuration in the Java source,
    annotations, and/or deployment descriptor.

## Build

From this directory:

``` powershell
mvn clean package
```

If the build succeeds, inspect the Maven output for the generated WAR
file and its location. Do not commit generated `target/` files; they can
be recreated by the build.

## Deploy and run

1.  Build the project with Maven.
2.  Locate the generated WAR in the build output (commonly under
    `target/`).
3.  Deploy it to the configured compatible servlet container.
4.  Start the container.
5.  Open the deployed context path in a browser.
6.  Use `index.html` and `student-form.html` to test the routes and form
    behavior, adjusting the URL to the servlet mappings used in the
    project.

Exact URLs depend on the servlet mappings and deployment context; check
the source before assuming a path.

## Concepts in brief

-   **Servlet:** receives a request and produces a response.
-   **Request/response:** the request carries input such as parameters;
    the response carries output and status information.
-   **Session:** stores per-user state across multiple HTTP requests.
-   **Filter:** can inspect or modify requests/responses before or after
    a servlet executes.
-   **Context listener:** responds to application-context lifecycle
    events.
-   **Servlet configuration/context:** provides initialization
    parameters and application-wide information.

## Troubleshooting

-   **404:** confirm the WAR context path and servlet mappings.
-   **Unsupported class version:** use a JDK compatible with the build
    target.
-   **Servlet API errors:** use a container compatible with the
    dependency declared in `pom.xml`.
-   **Form does not reach servlet:** check form `action`, HTTP method,
    field names, and servlet mapping.
-   **Changes do not appear:** rebuild and redeploy the WAR, then
    restart/reload the container if required.

## Future improvements

-   Add a setup guide with exact container version and URLs.
-   Validate form inputs and encode output safely.
-   Avoid storing sensitive information directly in session attributes
    without a clear need.
-   Add tests for servlet behavior.
-   Keep generated `target/` artifacts out of source control.

## Learning outcome

This project offers practical examples of the Servlet API and the
request lifecycle in a Java web application.
