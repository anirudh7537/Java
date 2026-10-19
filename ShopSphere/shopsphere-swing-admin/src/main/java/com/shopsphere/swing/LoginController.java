package com.shopsphere.swing;

import com.shopsphere.common.model.User;
import com.shopsphere.service.AuthenticationService;
import java.util.Optional;

public class LoginController {
    private final AuthenticationService authentication;
    public LoginController() { this(new AuthenticationService()); }
    public LoginController(AuthenticationService authentication) { this.authentication = authentication; }
    public Optional<User> login(String email, char[] password) throws Exception {
        Optional<User> user = authentication.authenticate(email, password);
        if (user.isPresent() && !"ADMIN".equals(user.get().getRole())) throw new IllegalArgumentException("This desktop application is for administrators.");
        return user;
    }
}
