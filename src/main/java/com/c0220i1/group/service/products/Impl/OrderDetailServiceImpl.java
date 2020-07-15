package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.OrderDetail;
import com.c0220i1.group.repository.order.OrderDetailRepository;
import com.c0220i1.group.service.products.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
  public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}