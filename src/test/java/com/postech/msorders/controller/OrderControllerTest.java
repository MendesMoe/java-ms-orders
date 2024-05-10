package com.postech.msorders.controller;

import com.postech.msorders.dto.OrderDTO;
import com.postech.msorders.entity.Item;
import com.postech.msorders.entity.Order;
import com.postech.msorders.gateway.OrderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreatetOrder {
        @Test
        void devePermitirRegistrarPedido() throws Exception {
            // Arrange
            String idCustomer = "c0390cca-aba3-4c91-ac44-29ec5615f381";
            List<Item> items = new ArrayList<>();
            OrderDTO orderDTO = new OrderDTO(idCustomer, items);
            orderDTO.setIdCustomer(idCustomer);
            orderDTO.setItens(orderDTO.getItens());

            when(orderGateway.createOrder(any())).thenReturn(new Order());

            // Act
            ResponseEntity<?> response = orderController.createOrder(orderDTO);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertThat(orderDTO.getIdCustomer()).isEqualTo(idCustomer);
            assertThat(orderDTO.getItens()).isEqualTo(items);
        }

        @Test
        void deveGerarExcecaoQuandoRegistrarPedidoCampoNulo() throws Exception {
            // Arrange
            OrderDTO orderDTO = new OrderDTO();

            // Act
            ResponseEntity<?> response = orderController.createOrder(orderDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        void deveGerarExcecaoQuandoCustsomerNaoEncontrado() throws Exception {
            // Arrange
            String idCustomer = "c0390cca-aba3-4c91-ac44-29ec5615f381";
            List<Item> items = new ArrayList<>();
            items.add(0,new Item(1, 1));
            OrderDTO validOrderDTO = new OrderDTO(idCustomer, items);

            when(orderGateway.createOrder(any())).thenThrow(HttpClientErrorException.class);

            // Act
            ResponseEntity<?> response = orderController.createOrder(validOrderDTO);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    class ReadOrder {
        @Test
        void devePermitirPesquisarUmPedido() throws Exception {
            // Arrange
            String orderId = "123";
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            List<Item> items = new ArrayList<>();
            items.add(0,new Item(1, 1));
            order.setItens(items);
            when(orderGateway.findOrder(orderId)).thenReturn(order);

            // Act
            ResponseEntity<?> response = orderController.findOrder(orderId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(order, response.getBody());
            assertThat(order.getItens()).isEqualTo(items);
        }

        @Test
        void devePermitirListarTodosPedidos() throws Exception {
            // Arrange
            List<Order> orders = new ArrayList<>();
            orders.add(new Order());
            orders.add(new Order());

            when(orderGateway.listAllOrders()).thenReturn(orders);

            // Act
            ResponseEntity<List<Order>> response = orderController.listAllOrders();

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(orders, response.getBody());
        }

        @Test
        void deveGerarExcecaoSeNaoEncontrarPedido() throws Exception {
            // Arrange
            String invalidId = "999";
            when(orderGateway.findOrder(invalidId)).thenReturn(null);

            // Act
            ResponseEntity<?> response = orderController.findOrder(invalidId);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Pedido não encontrado.", response.getBody());
        }
    }
}

