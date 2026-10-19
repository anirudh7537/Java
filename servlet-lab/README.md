# Servlet Lab — Student Login, Session & Filter Demo

A small Jakarta Servlet (Tomcat 11) web app demonstrating the core Servlet API concepts: HTTP GET/POST handling, session management, listeners, and filters.

## Features
- `HelloServlet` — basic GET/POST demo (`/hello`)
- `LoginServlet` — validates credentials and creates an `HttpSession` (`/login`)
- `AuthFilter` — protects `/home`, redirects unauthenticated users to the login page
- `HomeServlet` — reads the session attribute for the logged-in user (`/home`)
- `LogoutServlet` — invalidates the session (`/logout`)
- `AppListener` — logs application startup/shutdown via `ServletContextListener`

## Tech
- Jakarta Servlet API 6.1.0
- Maven (WAR packaging)
- Apache Tomcat 11

## Project structure
```
servlet-lab/
├── pom.xml
└── src/main/
    ├── java/com/servlet/
    │   ├── HelloServlet.java
    │   ├── LoginServlet.java
    │   ├── HomeServlet.java
    │   ├── LogoutServlet.java
    │   ├── AuthFilter.java
    │   └── AppListener.java
    └── webapp/
        └── index.html
```

## Build
```
mvn package
```
This produces `target/servlet.war`.

## Deploy
1. Copy `target/servlet.war` into `<TOMCAT_HOME>/webapps/`.
2. Start Tomcat (`startup.bat` on Windows).
3. Open `http://localhost:8080/servlet/`.

## Demo login
- Username: `admin`
- Password: `1234`

## Notes
Demo credentials are hardcoded for teaching purposes only — not for production use.
