package com.shopsphere.service;

import com.shopsphere.common.model.User;
import com.shopsphere.dao.UserDAO;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

public class AuthenticationService {
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private final UserDAO userDAO;

    public AuthenticationService() { this(new UserDAO()); }
    public AuthenticationService(UserDAO userDAO) { this.userDAO = userDAO; }

    public User register(String name, String email, char[] password, String mobile) throws SQLException {
        String cleanName = requireText(name, "Name is required.");
        String cleanEmail = requireText(email, "Email is required.").toLowerCase(Locale.ROOT);
        if (!EMAIL.matcher(cleanEmail).matches() || cleanEmail.length() > 254) throw new IllegalArgumentException("Enter a valid email address.");
        if (password == null || password.length < 8 || password.length > 128) throw new IllegalArgumentException("Password must contain 8 to 128 characters.");
        if (userDAO.findByEmail(cleanEmail).isPresent()) throw new IllegalArgumentException("An account with this email already exists.");
        User user = new User(); user.setName(cleanName); user.setEmail(cleanEmail); user.setPasswordHash(PasswordHasher.hash(password)); user.setMobile(mobile == null ? null : mobile.trim()); user.setRole("CUSTOMER"); user.setActive(true);
        user.setUserId(userDAO.create(user));
        user.setPasswordHash(null);
        return user;
    }

    public Optional<User> authenticate(String email, char[] password) throws SQLException {
        if (email == null || password == null) return Optional.empty();
        Optional<User> found = userDAO.findByEmail(email.trim().toLowerCase(Locale.ROOT));
        if (found.isEmpty()) return Optional.empty();
        User stored = found.get();
        if (!stored.isActive() || !PasswordHasher.verify(password, stored.getPasswordHash())) return Optional.empty();
        stored.setPasswordHash(null);
        return Optional.of(stored);
    }

    public boolean updateProfile(long userId, String name, String mobile) throws SQLException {
        User user = new User(); user.setUserId(userId); user.setName(requireText(name, "Name is required.")); user.setMobile(mobile == null ? null : mobile.trim()); return userDAO.updateProfile(user);
    }

    private String requireText(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
        return value.trim();
    }
}
