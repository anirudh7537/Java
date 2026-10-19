package com.shopsphere.demos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public final class UrlConnectionDemo {
    private UrlConnectionDemo() { }
    public static void main(String[] args) throws Exception {
        URL resource = UrlConnectionDemo.class.getClassLoader().getResource("com/shopsphere/common/i18n/messages_en.properties");
        if (resource == null) throw new IllegalStateException("ShopSphere message bundle was not found on the classpath.");
        URLConnection connection = resource.openConnection();
        System.out.println("Protocol: " + resource.getProtocol());
        System.out.println("Content type: " + connection.getContentType());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) { System.out.println("First line: " + reader.readLine()); }
    }
}
