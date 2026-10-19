# ShopSphere Architecture and Diagrams

## Runtime Architecture

```mermaid
flowchart TD
    Browser[Browser: HTML, CSS, JavaScript, Bootstrap] --> JSP[JSP + EL + JSTL]
    JSP --> Servlet[Jakarta Servlet Controllers]
    Servlet --> Service[Service Layer]
    Service --> DAO[DAO Layer]
    DAO --> JDBC[JDBC / MySQL Connector-J]
    JDBC --> DB[(MySQL: shopsphere)]
    Swing[Swing MVC Admin] --> Service
    Socket[Socket Client/Server] --> Service
    RMIClient[RMI Client] --> Registry[RMI Registry]
    Registry --> RMIServer[RMI Inventory Server]
    RMIServer --> Service
    Demos[Educational Java and JSP demos] -. separate from core workflow .-> JSP
```

## Entity Relationships

```mermaid
erDiagram
    users ||--o{ addresses : owns
    users ||--o{ cart : has
    users ||--o{ wishlist : saves
    users ||--o{ orders : places
    users ||--o{ reviews : writes
    categories ||--o{ products : contains
    products ||--o{ cart : appears_in
    products ||--o{ wishlist : saved_as
    products ||--o{ order_items : purchased_as
    products ||--o{ reviews : receives
    orders ||--|{ order_items : contains
    orders ||--o{ payments : records
    coupons ||--o{ coupon_usage : tracks
    users ||--o{ coupon_usage : redeems
    orders ||--o| coupon_usage : applies
```

## Use Cases

```mermaid
flowchart LR
    Guest((Guest)) --> Catalog[Browse, search, filter, sort, view details]
    Guest --> Register[Register / sign in]
    Customer((Customer)) --> Cart[Manage cart and wishlist]
    Customer --> Address[Manage addresses]
    Customer --> Checkout[Apply coupon and mock checkout]
    Customer --> Orders[View orders and tracking]
    Customer --> Reviews[Write/read reviews and ratings]
    Admin((Administrator)) --> Dashboard[Dashboard and sales summary]
    Admin --> CatalogAdmin[Manage products, categories, inventory]
    Admin --> People[Manage customers and orders]
    Admin --> Coupons[Manage coupons and low stock]
```

## Core Class Responsibilities

```mermaid
classDiagram
    class ServletController
    class Service
    class DAO
    class ConnectionFactory
    class User
    class Product
    class CartItem
    class Order
    class OrderItem
    class Payment
    class Coupon
    ServletController --> Service : handles requests through
    Service --> DAO : applies rules and coordinates
    DAO --> ConnectionFactory : obtains JDBC connection
    DAO --> User
    DAO --> Product
    DAO --> CartItem
    DAO --> Order
    Order *-- OrderItem
    Order --> Payment
    Service --> Coupon
```

## Login Sequence

```mermaid
sequenceDiagram
    actor Customer
    participant Browser
    participant Servlet as LoginServlet
    participant Service as AuthenticationService
    participant DAO as UserDAO
    participant DB as MySQL
    Customer->>Browser: Submit email and password
    Browser->>Servlet: POST /login
    Servlet->>Service: authenticate(email, password)
    Service->>DAO: findByEmail(email)
    DAO->>DB: PreparedStatement query
    DB-->>DAO: user hash and role
    DAO-->>Service: User
    Service-->>Servlet: authenticated user or validation error
    Servlet-->>Browser: create session and redirect, or show error
```

## Checkout Sequence

```mermaid
sequenceDiagram
    actor Customer
    participant Browser
    participant Servlet as CheckoutServlet
    participant Service as CheckoutService
    participant DAO as JDBC DAOs
    participant DB as MySQL
    Customer->>Browser: Confirm address, coupon, mock payment
    Browser->>Servlet: POST checkout data (no authoritative total)
    Servlet->>Service: checkout(userId, addressId, couponCode)
    Service->>DAO: begin transaction and reload cart/products
    DAO->>DB: validate stock and lock cart/product rows
    Service->>Service: calculate prices, coupon and total
    Service->>DAO: create order, items, mock payment, usage; update stock; clear cart
    DAO->>DB: commit transaction
    DB-->>Service: committed order id
    Service-->>Servlet: order result
    Servlet-->>Browser: redirect to confirmation
```
```
