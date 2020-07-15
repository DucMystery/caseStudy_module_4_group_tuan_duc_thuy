package com.c0220i1.group.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
  public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date dateOrder;
  private double shipFee;
  @OneToOne
  private Account account;
  @OneToMany
  List<OrderDetail> details;

}