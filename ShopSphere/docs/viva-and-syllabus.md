# RTU Viva and Syllabus Mapping

| Topic | ShopSphere demonstration | Explanation to prepare |
|---|---|---|
| Java/J2EE and N-tier | Web module: JSP → Servlet → Service → DAO → JDBC → MySQL | Responsibilities and request/data flow |
| Maven | Root aggregator and ten child modules | Dependency graph and WAR packaging |
| Tomcat/web container | `shopsphere-web` WAR on Tomcat 11 | Deployment, context path, container-managed Servlet/JSP lifecycle |
| HTTP request/response | Customer and admin Servlets | GET/POST, forwarding, redirects, status/error handling |
| Servlet lifecycle | Annotated Servlet and listener code | `init`, `service`, `doGet`/`doPost`, `destroy` |
| ServletConfig/ServletContext | Servlet config and `AppListener` | Per-Servlet versus application-wide state |
| Session/cookies | Login/cart session; recently viewed cookie | Session authentication; cookies hold no credentials |
| Filters | `AuthenticationFilter`, `AdminFilter` | Customer protection and ADMIN role enforcement |
| Events/listeners | `AppListener`, `SessionTrackerListener` | Startup/shutdown and active-session tracking |
| JSP | Customer/admin views and isolated traditional syntax page | JSP lifecycle; classic declaration/scriptlet/expression/comment are syllabus-only |
| EL/JSTL | Main pages and Core/Functions/XML/SQL demos | Data binding, tags, XML feed, isolated SQL tag |
| Fragments/tag files | Header/navbar/footer/sidebar; productCard/rating | Reuse and separation of presentation |
| JDBC/DAO | Eleven JDBC DAOs and `PreparedStatement` | Parameter binding, result mapping, resource closing, transactions |
| Service layer | Named service classes | Validation/business logic and transaction orchestration |
| MySQL | Twelve-table `shopsphere` schema | Keys, relationships, checks/indexes and persistent flows |
| Socket/ServerSocket | Inventory client/server commands | Request/response protocol and service reuse |
| Serialization | Product/Order object stream round-trip | Serializable model versions and stream filtering |
| RMI/Registry | Inventory service contract and client/server | Registry lookup, remote invocation, `RemoteException` |
| Swing/MVC | Login/controller and administration tabs | Components, event handlers, service reuse, system Look and Feel |
| JNDI | `java:comp/env/shopsphere/appName` | Naming lookup and container resource environment |
| URL/URLConnection | Read classpath resource through URLConnection | URL protocol and content metadata/stream |
| Internationalization | ResourceBundles, Locale switch and formatted INR values/dates | Message lookup and locale-aware output |
| Applets | Historical concept page/class | Why modern browsers do not execute applets; not a runtime dependency |

## Typical Demonstration Order

1. Show the architecture/ER diagrams and database relationships.
2. Walk through customer registration/login, catalog search, cart, address/coupon checkout, mock payment, order status, and review.
3. Sign in as ADMIN and show dashboard, products/categories, stock, users, orders, and coupons.
4. Show Servlet filters/listeners and JSP/EL/JSTL fragments/tags.
5. Run Socket server/client and RMI registry/server/client in separate terminals.
6. Demonstrate object serialization, URL/URLConnection, JNDI, English/Hindi messages, and Swing MVC.
7. Explain Maven modules, Tomcat deployment, and JDBC transaction/security choices.
