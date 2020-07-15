package com.c0220i1.group.controller;

import com.c0220i1.group.service.products.OrderDetailService;
import com.c0220i1.group.service.products.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
  public class OrderController {
 @Autowired
  private OrderService orderService;
 @Autowired
  private OrderDetailService orderDetailService;
 @Autowired
  private HttpSession httpSession;

}