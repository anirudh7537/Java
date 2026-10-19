# Changelog

## 2026-09-30

- Locked the ShopSphere project requirements and implementation boundaries in `SHOPSPHERE_PROJECT_CONTRACT.md` against the original specification and user-approved implementation decisions.
- Verified Windows 11 ARM64, JDK/Javac 25.0.4, Maven 3.9.16, running MySQL80, and Tomcat 11.0.25 on the ARM64 JVM. Confirmed the Tomcat x64 APR warning is optional and will not be introduced as a project dependency.
- Created the ten-module Maven reactor; `mvn validate` succeeds on the ARM64 JDK.
- Added requirements/UI mapping, architecture diagrams, and the MySQL schema with all twelve required tables and sample catalog rows.
- Implemented all named models, JDBC DAOs, business services, Servlet controllers, filters/listeners, customer/admin JSP views, and the ten module build layout.
- Added Socket request/response and serialization, RMI Registry service/client/server, functional Swing admin panels, locale/JNDI/URL/JSTL demonstrations, and setup/report/viva documentation.
- Current validation: clean full Maven reactor verification succeeds; 7 JUnit tests pass; Tomcat Jasper compiled the complete JSP/tag tree with zero errors; Tomcat route/auth/locale smoke tests pass.
- Started the final WAR on the isolated Tomcat base at port 18080. Database-backed flows remain blocked until an authorized MySQL account initializes/configures the dedicated schema.
- MySQL schema/runtime integration remains pending authorized credentials; no existing database was modified and no completion claim is made for DB-backed workflows until configured.
