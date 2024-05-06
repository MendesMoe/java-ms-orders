package com.postech.msorders.usecase;

import com.postech.msorders.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class OrderUseCaseTest {
    @Test
    void testValidarInsertOrder_ValidInput() {
//        // Arrange
//        Order order = new Order();
//        when(OrderUseCase.validateInsertOrder(order).thenReturn(order);
//
//        // Act & Assert
//        assertDoesNotThrow(() -> OrderUseCase.validateInsertOrder(order));
    }

    @Test
    void testValidateInsertOrder_ClientNotFound_ThrowsException() {
        // Arrange
        Order order = new Order();

        // Act & Assert
        assertThrows(HttpClientErrorException.class, () -> OrderUseCase.validateInsertOrder(order));
    }

}
