# ShopSphere Project Contract

**Status:** Requirements and architecture baseline only. No application code or Maven project has been created by this contract.

## Authority and Change Control

The original ShopSphere project guide (`Java_Advanced_ShopSphere_Project_Guide.docx`, also supplied as `SHOPSPHERE_SPECIFICATION.md.txt`) is authoritative for project requirements. This contract records those requirements and the implementation decisions requested where the guide is silent. If this contract or a later implementation choice conflicts with an explicit guide requirement, the guide wins and the conflict must be raised before changing architecture. Do not silently remove features, change technologies, rename established classes, or add unrelated business tables.

**THE SHOPSPHERE PROJECT CONTRACT IS IMMUTABLE UNLESS THE USER EXPLICITLY APPROVES A CHANGE.**

Before each implementation phase, read this contract and inspect the existing project. Continue from the existing state; do not restart. No implementation phase starts until the user explicitly says `START PHASE 1`.

## 1. Project Objective

Build ShopSphere as an RTU Advanced Java capstone: a Java e-commerce system whose primary customer experience is a web store, with related Swing administration, JDBC/MySQL persistence, Socket networking, RMI, serialization, internationalization, and educational syllabus demonstrations. Demonstrate MVC/N-tier design, HTTP, the web container, Servlet lifecycle, sessions, cookies, listeners, filters, JSP, EL, JSTL, tag files, and JSP fragments.

## 2. Specification Requirements

Implement the customer and administrator features, web pages/controllers, database relationships, security behavior, special modules, and deliverables listed in this contract. Keep SQL and persistence in DAOs, business rules in services, request handling in Servlets, and presentation in JSP. The main application must not use JSP scriptlets for application behavior, client-side authoritative totals, or SQL in JSP/Servlet code.

Customer capabilities include registration, login/logout, product browsing/search/filter/sort/details, cart, wishlist, address management, coupons, checkout, mock payment, order placement/history/tracking, profile, reviews, and ratings. Admin capabilities include admin login, dashboard, product/category CRUD, inventory, customer list/management, order management, coupon management, low-stock view, and basic sales summary.

The guide also requires:

- Demonstrations of Servlet lifecycle (`init`, `service`, `doGet`/`doPost`, `destroy`), `ServletConfig`, `ServletContext`, HTTP request/response, sessions, cookies, events, and filters.
- A short Applet history/concept demonstration only; the modern web application must not depend on applet execution.
- Traditional JSP declaration, scriptlet, expression, and comment syntax in a separate syllabus demonstration; main application pages use EL/JSTL.
- JSTL Core (`c:forEach`, `c:if`, `c:choose`, `c:set`), XML product-feed, SQL syllabus, and Functions demonstrations. JSTL SQL remains separate from the main DAO/JDBC application.
- A responsive Bootstrap-based store/admin UI, including navigation/search, product cards with images/discounts/ratings, cart icon and counter, responsive forms/tables, admin dashboard cards, order-status badges/timeline, and success/error alerts.
- RTU syllabus mapping, setup/deployment documentation, diagrams, screenshots, tests, report, and viva preparation.

## 3. Fixed Technology Stack

- Java 17 or later; Maven.
- Apache Tomcat 11; Jakarta Servlet API 6.1.0 and `jakarta.servlet.*` imports only.
- Jakarta JSP, EL, JSTL; HTML5, CSS3, JavaScript, Bootstrap.
- MySQL with JDBC and MySQL Connector/J.
- Java Swing; `java.net.Socket` and `ServerSocket`; Java RMI and RMI Registry; Java object serialization; JNDI; `URL`/`URLConnection`; `Locale`/`ResourceBundle`.
- JUnit 5 for automated tests.
- The deployable web module is a WAR. The Maven root is an aggregator POM; `shopsphere-web` produces the WAR for Tomcat.
- Do not introduce Spring Boot, Spring MVC, Hibernate, JPA, React, Angular, Vue, Node.js backend, PHP, Python backend, another database, or another server framework unless the original specification explicitly requires it.

## 4. Fixed Architecture

```text
Browser
  -> JSP / HTML / JavaScript
  -> Servlet Controller
  -> Service Layer
  -> DAO Layer
  -> JDBC
  -> MySQL
```

The Swing application, Socket server, and RMI server reuse the common models and service/business layer where practical. They do not duplicate persistence or business rules. RMI is a separate remote inventory interface; JNDI and URL/URLConnection remain educational demonstrations, not dependencies of the shopping workflow. No Spring Boot, Spring MVC, JPA/Hibernate, React, or Angular replacement is permitted.

## 5. Fixed Maven Modules

The fixed root organization is:

```text
ShopSphere/
|-- pom.xml
|-- README.md
|-- SHOPSPHERE_PROJECT_CONTRACT.md
|-- CHANGELOG.md
|-- database/shopsphere.sql
|-- docs/
|-- shopsphere-common/
|-- shopsphere-dao/
|-- shopsphere-service/
|-- shopsphere-web/
|-- shopsphere-swing-admin/
|-- shopsphere-network/
|-- shopsphere-rmi-api/
|-- shopsphere-rmi-server/
|-- shopsphere-rmi-client/
`-- shopsphere-demos/
```

Required Maven modules:

```text
shopsphere-common/
shopsphere-dao/
shopsphere-service/
shopsphere-web/
shopsphere-swing-admin/
shopsphere-network/
shopsphere-rmi-api/
shopsphere-rmi-server/
shopsphere-rmi-client/
shopsphere-demos/
```

Responsibilities and dependency direction:

- `shopsphere-common`: shared domain models and shared non-persistence value/configuration types.
- `shopsphere-dao`: JDBC connection/configuration support and DAO implementations; depends on common.
- `shopsphere-service`: named business services; depends on common and DAO.
- `shopsphere-web`: Servlet controllers, filters, listeners, JSP/JSTL pages, fragments and tags; WAR; depends on service/common and Jakarta web APIs.
- `shopsphere-swing-admin`: Swing MVC application; reuses common/service.
- `shopsphere-network`: Socket client/server and serialization demonstrations; uses common/service.
- `shopsphere-rmi-api`: `InventoryService` remote contract; shares serializable common models.
- `shopsphere-rmi-server`: remote implementation and server/registry startup; reuses service.
- `shopsphere-rmi-client`: registry lookup and remote calls.
- `shopsphere-demos`: standalone syllabus demonstrations, including Applet concept, JNDI and URL/URLConnection. JSP/JSTL demonstrations run in the web module because they require a JSP container.

No module may be merged, removed, or replaced without explicit approval. Keep dependencies acyclic. Root-level `database/shopsphere.sql` and project documentation are outside the Maven modules.

## 6. Fixed Database

Database name: `shopsphere`. The only business tables are the twelve listed in Section 7. Do not add unrelated tables. Centralize database configuration in one changeable mechanism; use environment variables or configuration outside source control as appropriate. Never scatter credentials or expose them in source/JSP. A container-managed JNDI resource may be demonstrated separately, but must not unnecessarily complicate core database access.

## 7. Database Tables

The guide's stated columns are the schema baseline:

| Table | Stated columns |
|---|---|
| `users` | `user_id`, `name`, `email`, `password`, `mobile`, `role`, `status`, `created_at` |
| `addresses` | `address_id`, `user_id`, `address_line`, `city`, `state`, `pincode`, `address_type` |
| `categories` | `category_id`, `name`, `description`, `status` |
| `products` | `product_id`, `category_id`, `name`, `brand`, `description`, `price`, `discount`, `stock`, `image_url`, `status`, `created_at` |
| `cart` | `cart_id`, `user_id`, `product_id`, `quantity` |
| `wishlist` | `wishlist_id`, `user_id`, `product_id`, `created_at` |
| `orders` | `order_id`, `user_id`, `address_id`, `total_amount`, `discount`, `payment_method`, `payment_status`, `order_status`, `created_at` |
| `order_items` | `order_item_id`, `order_id`, `product_id`, `quantity`, `price` |
| `payments` | `payment_id`, `order_id`, `payment_method`, `transaction_reference`, `amount`, `payment_status`, `created_at` |
| `reviews` | `review_id`, `user_id`, `product_id`, `rating`, `review_text`, `created_at` |
| `coupons` | `coupon_id`, `code`, `discount_type`, `discount_value`, `minimum_order`, `maximum_discount`, `expiry_date`, `status` |
| `coupon_usage` | `usage_id`, `coupon_id`, `user_id`, `order_id`, `used_at` |

Relationships: users own addresses, cart rows, wishlist rows, orders, and reviews; products are referenced by cart, wishlist, order items, and reviews; categories contain products; orders contain order items and payment records; coupons have usage records. The SQL script creates database `shopsphere` and defines keys, constraints, and indexes consistent with these relationships.

## 8. Fixed Models

`User`, `Address`, `Category`, `Product`, `CartItem`, `WishlistItem`, `Order`, `OrderItem`, `Payment`, `Review`, `Coupon`.

Use encapsulated fields and ordinary constructors/accessors as needed by the application. `Product` and `Order` must support serialization, with `serialVersionUID`; nested serialized values must also be serializable. Never expose password hashes to JSP. Monetary calculations use `BigDecimal`, not binary floating-point.

## 9. Fixed DAOs

`UserDAO`, `AddressDAO`, `CategoryDAO`, `ProductDAO`, `CartDAO`, `WishlistDAO`, `OrderDAO`, `OrderItemDAO`, `PaymentDAO`, `ReviewDAO`, `CouponDAO`.

DAOs contain persistence logic only. Use JDBC `Connection`, `PreparedStatement`, `ResultSet`, `DriverManager`/centralized connection configuration, `SQLException`, and try-with-resources. Bind all user-controlled values; never concatenate them into SQL.

## 10. Fixed Services

`AuthenticationService`, `ProductService`, `CategoryService`, `CartService`, `WishlistService`, `AddressService`, `OrderService`, `CheckoutService`, `PaymentService`, `InventoryService`, `CouponService`, `ReviewService`, `AdminService`.

Services perform validation and business rules; they coordinate DAOs as needed. Checkout coordinates cart, address, coupon, order, payment, and inventory work. These names are fixed; do not create duplicate manager/service classes or rename them. Additional services may be introduced only where genuinely necessary to preserve clean architecture and satisfy the specification.

## 11. Fixed Web Controllers

Customer controllers: `LoginServlet`, `RegisterServlet`, `LogoutServlet`, `ProductServlet`, `CartServlet`, `CheckoutServlet`, `OrderServlet`, `WishlistServlet`, `ReviewServlet`.

Admin controllers: `AdminLoginServlet`, `AdminProductServlet`, `AdminCategoryServlet`, `AdminOrderServlet`, `AdminUserServlet`.

Servlets receive/validate requests, call services, manage session state, and forward or redirect. They contain no SQL or major business rules. Additional Servlets may be introduced only where genuinely required by the specification.

## 12. Fixed Filters

- `AuthenticationFilter`: protects authenticated customer operations/pages; unauthenticated protected requests go to login.
- `AdminFilter`: protects `/admin/*` and permits only authenticated ADMIN users; deny customer access.

Home, login, registration, and public product browsing/details remain accessible without login. Exact URL mappings are an implementation decision and must preserve these behaviors.

## 13. Fixed Listeners

Implement `ServletContextListener` for application lifecycle and `HttpSessionListener` for session events. Use application context for application-wide configuration and servlet config for servlet-specific configuration. Logout invalidates the session. Session state carries authentication and active cart context; database cart rows remain the persistent cart record.

## 14. Customer JSP Pages

- `index.jsp`: home page.
- `login.jsp`: customer/admin login entry.
- `register.jsp`: registration.
- `products.jsp`: listing, search, filtering, sorting.
- `product-details.jsp`: product details.
- `cart.jsp`: cart and quantities.
- `checkout.jsp`: address, coupon, payment.
- `order-success.jsp`: confirmation.
- `orders.jsp`: order history.
- `order-details.jsp`: details and tracking.
- `profile.jsp`: profile.
- `wishlist.jsp`: wishlist.

Use EL/JSTL in normal application JSPs. No scriptlets or SQL in these pages. Keep traditional JSP declaration/scriptlet/expression/comment syntax in a separate syllabus demonstration.

Required fragments: `fragments/header.jsp`, `fragments/navbar.jsp`, `fragments/footer.jsp`, `fragments/sidebar.jsp`. Required tag files: `WEB-INF/tags/productCard.tag` and `WEB-INF/tags/rating.tag`. Demonstrate reusable product/rating tag handlers. Keep the JSP syntax and JSTL XML/SQL syllabus demonstrations separate from normal shopping pages; JSTL SQL must not replace DAO/JDBC in the application.

## 15. Admin Pages

`admin/dashboard.jsp`, `admin/products.jsp`, `admin/categories.jsp`, `admin/orders.jsp`, `admin/users.jsp`.

The UI provides dashboard summaries, product/category management, order/customer administration, inventory/low-stock information, coupon management, and status feedback through the controller/service paths.

## 16. Swing Module

Required screens: `LoginFrame`, `AdminDashboard`, `ProductFrame`, `CategoryFrame`, `CustomerFrame`, `OrderFrame`, `InventoryFrame`. Use Swing MVC; required example controllers are `ProductController` and `LoginController`. Use `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`, `JCheckBox`, `JRadioButton`, `JComboBox`, `JTable`, `JScrollPane`, and `JOptionPane`. Apply the system Look and Feel. Reuse common models/services where practical.

## 17. Socket Module

Implement an inventory server using `ServerSocket` and clients using `Socket`. Support `GET_PRODUCT`, `GET_STOCK`, and `SEARCH_PRODUCT`. Route business access through existing services; do not duplicate DAO/business logic. Demonstrate actual client/server communication.

## 18. RMI Module

`shopsphere-rmi-api` defines `InventoryService extends Remote`, with `getProduct`, `getStock`, and `updateStock` methods declaring `RemoteException`. `shopsphere-rmi-server` binds an implementation in an RMI Registry and delegates to the existing service layer. `shopsphere-rmi-client` looks up the service and makes remote calls. Product results use the shared serializable model.

## 19. Serialization

Demonstrate `ObjectOutputStream` and `ObjectInputStream` by transferring `Product` and `Order` objects in the networking demonstration. Use `serialVersionUID`. Do not accept untrusted Java-serialized data from arbitrary clients as a general-purpose public protocol.

## 20. JNDI

Provide a separate educational resource-lookup demonstration. It does not become a dependency of core shopping flows. Any configured lookup resource is externalized and documented.

## 21. URL/URLConnection

Provide separate educational demonstrations of `URL` and `URLConnection` content/protocol handling. They remain outside core shopping flows and do not require an external service for ordinary application startup.

## 22. Internationalization

Provide `messages_en.properties` and `messages_hi.properties`. Demonstrate `Locale`, `ResourceBundle`, and localized text plus appropriate price/date formatting. Keep currency/formatting decisions centralized rather than embedded throughout JSPs.

## 23. Security

- Hash passwords before database storage; use salted password hashing. Never put passwords or hashes in cookies or URLs.
- Use `HttpSession` for login state; enforce role-based admin access with `AdminFilter`.
- Validate input server-side and use `PreparedStatement` for database access.
- Calculate cart/order totals and discounts server-side; never trust browser-submitted prices or totals. Validate coupon state/expiry/minimum and maximum discount; validate stock server-side.
- Checkout uses a database transaction so order creation, item rows, payment record, cart clearing, and stock changes cannot partially succeed.
- Payment is mock-only; do not integrate a real payment gateway.
- Cookies may be used for non-sensitive convenience features such as recently viewed products; never store passwords in cookies.

## 24. Testing

Use JUnit 5. Cover registration and duplicate email; valid/invalid login and session/authentication; product browsing/search/filter/sort/details; cart and wishlist; coupons; checkout totals and rollback; order creation/history/status; inventory validation/updates; reviews/ratings; admin authorization; service behavior; Socket client/server; and RMI calls. Add integration tests where useful.

Acceptance behaviors from the guide: valid registration creates a user; duplicate email reports validation/error; valid login creates a session; invalid login reports an error; unauthenticated cart access redirects to login; search returns matching products; cart addition and quantity changes update cart/totals; order placement creates order and items; admin login opens dashboard; customer `/admin` access is denied; admin order updates are visible to customer; logout invalidates session; RMI and Socket calls receive responses.

## 25. Implementation Phases

Follow the guide's phase order; the user's earlier alternate Phase 1 ordering does not override it:

1. Requirements and UI design: use cases, screens, module list.
2. Database: SQL script and ER diagram.
3. Models and JDBC: POJOs and DAO CRUD.
4. MVC/service structure.
5. Authentication: registration, login, logout, session.
6. Products: listing, search, filtering, details.
7. Cart: add, update, remove.
8. Checkout: address, coupon, order.
9. Admin: products, categories, inventory, orders.
10. Servlet/JSP: complete web interface.
11. Filters/listeners: security and events.
12. JSTL/tags: dynamic JSP and reusable UI.
13. Socket networking.
14. RMI.
15. Swing.
16. Testing.
17. Report, documentation, screenshots, and viva.

The guide's minimum-working-version ordering is only for time-limited delivery; it does not remove any final project requirement. No phase implementation begins before explicit user authorization with `START PHASE 1`.

## 26. Decisions Where the Specification Was Silent

These are stable implementation choices, not claims that the guide specifies their exact details:

- Java 17+, Maven, JUnit 5, and the ten fixed Maven modules follow the user's fixed decisions. The root is an aggregator POM; only the web child is packaged as WAR. The guide's different directory layout is a recommendation, not a reason to replace these fixed modules.
- Store money as SQL `DECIMAL` and Java `BigDecimal`. Use generated primary keys, foreign keys, required-field `NOT NULL`, unique user email and coupon code, unique user/product pairs for cart and wishlist, and indexes on common foreign-key/search columns. Use conservative deletion rules for order/payment history; deactivate catalog records rather than deleting referenced records. No unrelated tables are added.
- Use a centralized JDBC configuration sourced from environment variables or an external configuration file. Do not commit credentials.
- Use salted PBKDF2 password hashes with per-user random salts and constant-time verification, using Java standard APIs. The stored database `password` value is the encoded hash, never plaintext.
- Roles are `CUSTOMER` and `ADMIN`. Product/category/coupon status is checked before customer use. Public browsing does not require authentication; cart mutation, checkout, orders, profile, wishlist, and review submission require an authenticated customer. Admin operations require `ADMIN`.
- Cart rows persist in MySQL; the session tracks the authenticated user and active cart context/count. Changes are persisted through `CartDAO` and the displayed count is refreshed from authoritative state.
- Checkout recalculates product prices, discounts, coupon eligibility, and totals from database state inside one transaction. Lock/recheck stock while placing the order. On failure, roll back all checkout writes. A mock payment records a local mock transaction reference/status only; no external payment call occurs.
- Coupon validation checks active status, expiry, minimum subtotal, discount type/value, and maximum discount. Apply at most one coupon per order and never allow a discount below zero payable total. `coupon_usage` records successful application; no additional reuse quota is assumed without a requirement.
- Review ratings are integers from 1 through 5. Invalid quantities, prices, coupon values, IDs, or role values are rejected server-side.
- `InventoryService.updateStock(productId, quantity)` sets the target on-hand quantity; reject negative values and unknown products. Socket commands use a documented simple line-oriented request/response protocol; object serialization is demonstrated separately over streams in the same network module.
- Order/payment status values are a small documented lifecycle suitable for pending/confirmed/processing/shipped/delivered/cancelled orders and pending/success/failed mock payments. Transitions are validated in services; exact UI wording may be localized.
- The `shopsphere-demos` module holds standalone Java syllabus demos. JSP/JSTL demos are separate routes/pages in `shopsphere-web`, because they require the JSP container. The JNDI demo uses an explicitly configured educational resource; it is not required for ordinary JDBC startup.
- Document environment setup, database configuration, module run commands, test commands, Tomcat deployment, diagrams, RTU mapping, report, and viva steps. Maintain additional `README.md`/`CHANGELOG.md` files only as documentation deliverables; neither changes this contract or the guide's authority.

Before creating or modifying any project file, inspect the existing structure and check whether the component already exists. Reuse correct existing code, avoid duplicate or competing implementations, and do not overwrite working code unnecessarily. If an ambiguity would require changing architecture, technology, module structure, database structure, a required feature/class, or a syllabus component, stop and ask the user before proceeding.
