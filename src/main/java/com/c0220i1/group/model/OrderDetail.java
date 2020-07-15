package com.c0220i1.group.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
  public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private double amount;
    @ManyToOne
    private Product product;

  public OrderDetail(Product product,int quantity,double amount) {
    this.product= product;
    this.quantity=quantity;
    this.amount=amount;
  }
}