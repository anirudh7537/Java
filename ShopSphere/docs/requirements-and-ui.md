# ShopSphere Requirements and UI Map

This screen/use-case map translates the original RTU ShopSphere guide into implementation navigation. It does not replace or narrow the project requirements in the root contract.

## Customer Use Cases

- Guest: browse catalog, search, filter, sort, open product details, register, sign in.
- Customer: manage profile and addresses; add/update/remove cart items; maintain wishlist; validate coupon; checkout using a saved address; complete mock payment; view confirmation, order history, order details and status; submit and read ratings/reviews.
- Administrator: sign in; inspect dashboard/sales/low-stock summaries; create/update/deactivate products and categories; manage inventory, customers, orders, coupons.
- Syllabus demonstrations: inspect traditional JSP syntax and JSTL Core/XML/SQL/Functions; run Socket client/server; run RMI registry/server/client; use Swing administration; run serialization, JNDI, URL/URLConnection, locale/resource-bundle and Applet concept demonstrations.

## Screen Map

| Route/view | User | Main behavior |
|---|---|---|
| `index.jsp` | Guest/customer | Featured catalog and search entry |
| `products.jsp` | Guest/customer | Search, category filter, sort, product cards |
| `product-details.jsp` | Guest/customer | Product details, rating summary, review list |
| `login.jsp` | Guest | Customer/admin login entry |
| `register.jsp` | Guest | Account registration |
| `cart.jsp` | Customer | Quantity update, remove, server-derived totals |
| `wishlist.jsp` | Customer | View/remove wishlist and move an item to cart |
| `checkout.jsp` | Customer | Address selection, coupon, server-calculated order summary, mock payment |
| `order-success.jsp` | Customer | Placed order confirmation |
| `orders.jsp` | Customer | Order history and current status |
| `order-details.jsp` | Customer | Items, amounts, payment/order status |
| `profile.jsp` | Customer | Profile and address CRUD |
| `admin/dashboard.jsp` | Admin | Sales summary, order counts, low-stock list |
| `admin/products.jsp` | Admin | Product CRUD and stock operations |
| `admin/categories.jsp` | Admin | Category CRUD |
| `admin/orders.jsp` | Admin | Order search/details/status updates |
| `admin/users.jsp` | Admin | Customer list and status management |
| Admin coupon view | Admin | Coupon CRUD and usage visibility; route is handled by existing admin controller |

## Navigation and Feedback

Use a responsive Bootstrap navbar with search, cart counter, and account navigation. Catalog cards show real database-backed image URLs, prices, discounts, and review ratings. Forms use server-side validation and redisplay field-level errors. Administrative pages use responsive tables, concise dashboard cards, status badges, alerts, and order-status progression. Public catalog access does not require authentication; cart mutations, checkout, account, wishlist, orders, and review submission do.

## External Dependencies

Bootstrap may be loaded from a pinned CDN URL; no frontend framework or separate backend is introduced. Product images are URLs stored in MySQL, with a local neutral fallback when an image cannot load. No static fixture data substitutes for DAO-backed catalog behavior.
