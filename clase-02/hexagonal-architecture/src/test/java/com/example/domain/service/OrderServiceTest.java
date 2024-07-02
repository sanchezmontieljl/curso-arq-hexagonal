package com.example.domain.service;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order.getId());
    }
    
    @Test
    void testAddItemToOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item = new OrderItem("Product 1", 2, new BigDecimal("50.0"));
        orderService.addItemToOrder(order.getId(), item);
        assertNotNull(orderService.findOrderById(order.getId()));
    }
    
    @Test
    void testUpdateOrderStatus(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.findOrderById(order.getId()).getStatus());
                
    }
}
