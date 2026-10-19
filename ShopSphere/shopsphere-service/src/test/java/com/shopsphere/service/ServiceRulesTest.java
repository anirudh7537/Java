package com.shopsphere.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.shopsphere.common.model.Coupon;
import com.shopsphere.common.model.Product;
import com.shopsphere.common.model.User;
import com.shopsphere.dao.UserDAO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ServiceRulesTest {
    @Test
    void passwordHashUsesSaltAndRejectsWrongPassword() {
        char[] password = "correct horse battery".toCharArray();
        String first = PasswordHasher.hash(password);
        String second = PasswordHasher.hash(password);
        assertTrue(PasswordHasher.verify(password, first));
        assertFalse(PasswordHasher.verify("incorrect password".toCharArray(), first));
        assertFalse(first.equals(second));
    }

    @Test
    void productSalePriceAppliesPercentDiscountWithCurrencyPrecision() {
        Product product = new Product(1, 1, "Lamp", "Brand", "", new BigDecimal("34.50"), new BigDecimal("10.00"), 4, "", true);
        assertEquals(new BigDecimal("31.05"), product.getSalePrice());
    }

    @Test
    void couponDiscountHonorsMinimumAndMaximum() {
        CouponService service = new CouponService();
        Coupon coupon = new Coupon(1, "SAVE", "PERCENTAGE", new BigDecimal("20"), new BigDecimal("50"), new BigDecimal("15"), LocalDateTime.now().plusDays(1), true);
        assertEquals(new BigDecimal("15.00"), service.calculateDiscount(coupon, new BigDecimal("100.00")));
        assertThrows(IllegalArgumentException.class, () -> service.calculateDiscount(coupon, new BigDecimal("40.00")));
    }

    @Test
    void registrationHashesPasswordAndLoginReturnsNoHash() throws Exception {
        MemoryUserDAO users = new MemoryUserDAO();
        AuthenticationService service = new AuthenticationService(users);
        User registered = service.register("Casey Customer", "casey@example.test", "long-test-password".toCharArray(), "5550100");
        assertEquals(null, registered.getPasswordHash());
        assertTrue(service.authenticate("casey@example.test", "long-test-password".toCharArray()).isPresent());
        assertFalse(service.authenticate("casey@example.test", "wrong-password".toCharArray()).isPresent());
        assertThrows(IllegalArgumentException.class, () -> service.register("Casey Customer", "casey@example.test", "long-test-password".toCharArray(), "5550100"));
    }

    private static final class MemoryUserDAO extends UserDAO {
        private final Map<String, User> users = new HashMap<>();
        @Override public Optional<User> findByEmail(String email) { return Optional.ofNullable(users.get(email)).map(MemoryUserDAO::copy); }
        @Override public long create(User user) { user.setUserId(1); users.put(user.getEmail(), copy(user)); return 1; }
        private static User copy(User user) { return new User(user.getUserId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getMobile(), user.getRole(), user.isActive(), user.getCreatedAt()); }
    }
}
