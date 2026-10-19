package com.shopsphere.network;

import com.shopsphere.common.model.Order;
import com.shopsphere.common.model.OrderItem;
import com.shopsphere.common.model.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.List;

public final class SerializationRoundTrip {
    private SerializationRoundTrip() { }
    public static <T> T copy(T value, Class<T> type) throws Exception {
        byte[] bytes;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream(); ObjectOutputStream output = new ObjectOutputStream(buffer)) { output.writeObject(value); bytes = buffer.toByteArray(); }
        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes))) { input.setObjectInputFilter(java.io.ObjectInputFilter.Config.createFilter("maxdepth=16;maxrefs=1000;com.shopsphere.common.model.*;java.base/*;!*")); return type.cast(input.readObject()); }
    }
    public static void main(String[] args) throws Exception {
        Product product = new Product(7, 2, "Serialized product", "ShopSphere", "Serialization demonstration", new BigDecimal("10.00"), BigDecimal.ZERO, 3, "", true);
        Order order = new Order(12, 4, 9, new BigDecimal("20.00"), BigDecimal.ZERO, "MOCK", "SUCCESS", "CONFIRMED", java.time.LocalDateTime.now());
        order.setItems(List.of(new OrderItem(1, 12, 7, product.getName(), 2, product.getPrice())));
        System.out.println("Product round trip: " + copy(product, Product.class));
        System.out.println("Order round trip: " + copy(order, Order.class));
    }
}
