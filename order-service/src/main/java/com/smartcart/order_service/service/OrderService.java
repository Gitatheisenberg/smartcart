package com.smartcart.order_service.service;

import com.smartcart.order_service.dto.Order;

import java.util.List;

public interface OrderService  {
    Order placeOrder(Order order);
    List<Order> getOrderByUser(Long userId);
    Order getOrderById(Long id);
}
