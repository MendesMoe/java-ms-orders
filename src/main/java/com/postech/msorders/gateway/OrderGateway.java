package com.postech.msorders.gateway;

import com.postech.msorders.entity.Order;
import com.postech.msorders.interfaces.IOrderGateway;
import com.postech.msorders.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderGateway implements IOrderGateway {
    private final OrderRepository orderRepository;

    public OrderGateway(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteOrder(String strId) {
        try {
            UUID uuid = UUID.fromString(strId);
            orderRepository.deleteById(uuid);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Order findOrder(String strId) {
        try {
            UUID uuid = UUID.fromString(strId);
            return orderRepository.findById(uuid).orElseThrow();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }
}
