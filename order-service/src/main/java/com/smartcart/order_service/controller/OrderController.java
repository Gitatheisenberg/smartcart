package com.smartcart.order_service.controller;

import com.smartcart.order_service.dto.Order;
import com.smartcart.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }
    @PostMapping
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }
    @GetMapping("user/{userId}")
    public List<Order> getOrderByUser(@PathVariable Long userId){
        return orderService.getOrderByUser(userId);
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
}
