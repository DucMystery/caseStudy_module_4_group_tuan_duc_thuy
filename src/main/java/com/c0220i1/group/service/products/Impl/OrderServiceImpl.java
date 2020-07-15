package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.Orders;
import com.c0220i1.group.repository.order.OrderRepository;
import com.c0220i1.group.service.products.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

      @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Orders order) {
        orderRepository.save(order);
    }
}