# Testing and Validation

## Automated Tests

Run the full reactor from the project root:

```powershell
mvn clean verify
```

Current JUnit 5 coverage:

- `ServiceRulesTest`: registration, duplicate-email rejection, salted password verification/hash redaction, product sale-price arithmetic, and coupon minimum/maximum behavior.
- `SocketInventoryTest`: actual loopback Socket client/server request and response.
- `SerializationRoundTripTest`: Product and Order object stream round trips, including nested order items.
- `RmiRegistryTest`: create an RMI Registry, bind the remote implementation, look it up through a stub, and call product/stock/update operations.

## Environment-Dependent Tests

DAO and full customer/admin integration tests require an authorized MySQL login and an initialized `shopsphere` schema. Credentials are supplied using `SHOPSPHERE_DB_URL`, `SHOPSPHERE_DB_USER`, and `SHOPSPHERE_DB_PASSWORD`; no credential is committed. The current environment rejected unauthenticated `root` access, so implementation did not execute SQL against the installed server or change any database.

Tomcat Jasper separately compiles the complete WAR JSP/tag tree. The current WAR passed Jasper compilation with zero JSP errors using Tomcat 11.0.25 and JDK 25.0.4 ARM64. Runtime smoke tests returned HTTP 200 for login, registration, admin login, every syllabus demo, English/Hindi locale output, and the JSTL XML demo. Unauthenticated cart/admin requests redirected to their corresponding login routes.

## Manual Acceptance Checklist

- [ ] Apply `database/shopsphere.sql` to the dedicated schema.
- [ ] Bootstrap an ADMIN account via environment variables.
- [ ] Register/login/logout as a customer; confirm invalid/duplicate inputs are rejected.
- [ ] Browse, search, filter, sort, and inspect catalog data from MySQL.
- [ ] Add/update/remove cart and wishlist items; confirm stock and server totals.
- [ ] Add/edit/delete addresses; test coupon valid/expired/minimum/maximum cases.
- [ ] Complete success and declined mock checkout; confirm stock/cart transactional behavior.
- [ ] Verify order history/details/status, reviews, and average ratings.
- [ ] Verify ADMIN dashboard, product/category/coupon CRUD, customer/order actions, low stock and sales summary.
- [ ] Verify guest/customer/admin route access restrictions.
- [ ] Run JSP/JSTL demos, Socket client/server, RMI Registry calls, Swing, serialization, JNDI, URL demos, and English/Hindi locale switching.
- [ ] Deploy the WAR to Tomcat 11 and inspect application logs without exposing SQL credentials.
