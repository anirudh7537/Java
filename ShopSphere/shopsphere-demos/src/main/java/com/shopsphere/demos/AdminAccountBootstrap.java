package com.shopsphere.demos;

import com.shopsphere.common.model.User;
import com.shopsphere.dao.UserDAO;
import com.shopsphere.service.PasswordHasher;
import java.util.Arrays;
import java.util.Locale;

public final class AdminAccountBootstrap {
    private AdminAccountBootstrap() { }
    public static void main(String[] args) throws Exception {
        String name = required("SHOPSPHERE_ADMIN_NAME");
        String email = required("SHOPSPHERE_ADMIN_EMAIL").toLowerCase(Locale.ROOT);
        char[] password = required("SHOPSPHERE_ADMIN_PASSWORD").toCharArray();
        try {
            UserDAO users = new UserDAO();
            if (users.findByEmail(email).isPresent()) throw new IllegalArgumentException("An account already exists for that email; no account was changed.");
            User admin = new User(); admin.setName(name); admin.setEmail(email); admin.setPasswordHash(PasswordHasher.hash(password)); admin.setRole("ADMIN"); admin.setActive(true);
            users.create(admin);
            System.out.println("Administrator account created for " + email + ". The password was not displayed.");
        } finally { Arrays.fill(password, '\0'); }
    }
    private static String required(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Set environment variable " + name + " before running this command.");
        return value;
    }
}
