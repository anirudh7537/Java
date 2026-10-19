package com.shopsphere.service;

public final class ShopSphereServices {
    private final AuthenticationService authentication = new AuthenticationService();
    private final ProductService products = new ProductService();
    private final CategoryService categories = new CategoryService();
    private final CartService cart = new CartService();
    private final WishlistService wishlist = new WishlistService();
    private final AddressService addresses = new AddressService();
    private final OrderService orders = new OrderService();
    private final CheckoutService checkout = new CheckoutService();
    private final PaymentService payments = new PaymentService();
    private final InventoryService inventory = new InventoryService();
    private final CouponService coupons = new CouponService();
    private final ReviewService reviews = new ReviewService();
    private final AdminService admin = new AdminService();
    public AuthenticationService authentication() { return authentication; }
    public ProductService products() { return products; }
    public CategoryService categories() { return categories; }
    public CartService cart() { return cart; }
    public WishlistService wishlist() { return wishlist; }
    public AddressService addresses() { return addresses; }
    public OrderService orders() { return orders; }
    public CheckoutService checkout() { return checkout; }
    public PaymentService payments() { return payments; }
    public InventoryService inventory() { return inventory; }
    public CouponService coupons() { return coupons; }
    public ReviewService reviews() { return reviews; }
    public AdminService admin() { return admin; }
}
