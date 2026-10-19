package com.shopsphere.network;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import org.junit.jupiter.api.Test;

class SocketInventoryTest {
    @Test
    void socketClientReceivesServerResponse() throws Exception {
        try (SocketInventoryServer server = new SocketInventoryServer(0, command -> (Serializable) ("OK|" + command))) {
            server.start();
            Serializable response = new SocketInventoryClient("127.0.0.1", server.getPort()).request("GET_STOCK|3");
            assertEquals("OK|GET_STOCK|3", response);
        }
    }
}
