package com.shopsphere.demos;

import javax.naming.InitialContext;

public final class JndiLookupDemo {
    private JndiLookupDemo() { }
    public static void main(String[] args) throws Exception {
        Object value = new InitialContext().lookup("java:comp/env/shopsphere/appName");
        System.out.println("JNDI resource: " + value);
    }
}
