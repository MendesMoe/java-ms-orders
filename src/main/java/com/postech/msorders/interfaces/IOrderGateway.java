package com.postech.msorders.interfaces;

import com.postech.msorders.entity.Order;

import java.util.List;

public interface IOrderGateway {
    public Order createOrder(Order order);

    public Order updateOrder(Order order);

    public boolean deleteOrder(String strId);

    public Order findOrder(String strId);

    public List<Order> listAllOrders();
}
