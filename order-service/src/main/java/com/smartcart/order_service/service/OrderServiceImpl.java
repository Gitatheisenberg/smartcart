package com.smartcart.order_service.service;

import com.smartcart.order_service.dto.Order;
import com.smartcart.order_service.entity.OrderEntity;
import com.smartcart.order_service.entity.OrderItem;
import com.smartcart.order_service.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository,ModelMapper modelMapper){
        this.orderRepository=orderRepository;
        this.modelMapper=modelMapper;

    }

    @Override
    public Order placeOrder(Order orderdto) {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setUserId(orderdto.getUserId());
        orderEntity.setOrderDate(LocalDateTime.now());

       List<OrderItem> orderItemsList=orderdto.getItems()
        .stream().map(itemDto->{
           OrderItem item= modelMapper.map(itemDto,OrderItem.class);
           item.setOrder(orderEntity);
           return item;
        }).collect(Collectors.toList());


        orderEntity.setItems(orderItemsList);

        orderEntity.setTotalAmount(orderItemsList.stream()
                .mapToDouble(i->i.getPrice()*i.getQuantity())
                        .sum()
                );
        return  modelMapper.map(orderRepository.save(orderEntity), Order.class);
    }

    @Override
    public List<Order> getOrderByUser(Long userId) {
        return orderRepository.findById(userId).stream()
                .map(order->modelMapper.map(order,Order.class))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long id) {
       return orderRepository.findById(id)
               .map(order->modelMapper.map(order,Order.class))
               .orElseThrow(()->new RuntimeException("Order Not Found"));
    }
}
