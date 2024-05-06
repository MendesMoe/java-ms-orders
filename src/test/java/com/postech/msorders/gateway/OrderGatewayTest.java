package com.postech.msorders.gateway;

import com.postech.msorders.entity.Order;
import com.postech.msorders.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class OrderGatewayTest {
    @InjectMocks
    private OrderGateway orderGateway;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder_ValidInput_ReturnsOrder() {
        // Arrange
        Order order = new Order();
        // Mock the behavior of orderRepository
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order result = orderGateway.createOrder(order);

        // Assert
        assertEquals(order, result);
    }

    @Test
    void testUpdateOrder_ValidInput_ReturnsOrder() {
        // Arrange
        Order order = new Order();
        // Mock the behavior of orderRepository
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order result = orderGateway.updateOrder(order);

        // Assert
        assertEquals(order, result);
    }

    @Test
    void testDeleteOrder_ValidInput_ReturnsTrue() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        // Mock the behavior of orderRepository
        doNothing().when(orderRepository).deleteById(orderId);

        // Act
        boolean result = orderGateway.deleteOrder(String.valueOf(orderId));

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteOrder_InvalidInput_ReturnsFalse() {
        // Arrange
        String invalidOrderId = "invalid-id";

        // Act
        boolean result = orderGateway.deleteOrder(invalidOrderId);

        // Assert
        assertFalse(result);
    }

    @Test
    void testFindOrder_ValidInput_ReturnsOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Order expectedOrder = new Order();
        // Mock the behavior of orderRepository
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        Order result = orderGateway.findOrder(String.valueOf(orderId));

        // Assert
        assertEquals(expectedOrder, result);
    }

    @Test
    void testFindOrder_InvalidInput_ReturnsNull() {
        // Arrange
        String invalidOrderId = "invalid-id";

        // Act
        Order result = orderGateway.findOrder(invalidOrderId);

        // Assert
        assertNull(result);
    }

    @Test
    void testListAllOrders_ReturnsListOfOrders() {
        // Arrange
        List<Order> expectedOrders = List.of(new Order(), new Order());
        // Mock the behavior of orderRepository
        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Act
        List<Order> result = orderGateway.listAllOrders();

        // Assert
        assertEquals(expectedOrders, result);
    }
}
