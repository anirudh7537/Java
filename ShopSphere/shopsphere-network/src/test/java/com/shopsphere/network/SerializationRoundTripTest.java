package com.shopsphere.network;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shopsphere.common.model.Order;
import com.shopsphere.common.model.OrderItem;
import com.shopsphere.common.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class SerializationRoundTripTest {
    @Test
    void productAndOrderSurviveObjectStreamRoundTrip() throws Exception {
        Product product = new Product(4, 2, "Desk Lamp", "Northlight", "", new BigDecimal("34.50"), new BigDecimal("10"), 18, "", true);
        Product receivedProduct = SerializationRoundTrip.copy(product, Product.class);
        assertEquals(product.getName(), receivedProduct.getName());
        Order order = new Order(7, 3, 9, new BigDecimal("31.05"), new BigDecimal("3.45"), "MOCK", "SUCCESS", "CONFIRMED", LocalDateTime.now());
        order.setItems(List.of(new OrderItem(1, 7, 4, "Desk Lamp", 1, new BigDecimal("31.05"))));
        Order receivedOrder = SerializationRoundTrip.copy(order, Order.class);
        assertEquals(order.getOrderId(), receivedOrder.getOrderId());
        assertEquals("Desk Lamp", receivedOrder.getItems().get(0).getProductName());
    }
}
