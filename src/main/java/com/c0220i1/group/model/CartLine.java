package com.c0220i1.group.model;

import lombok.Data;

import java.util.Date;

@Data
  public class CartLine {
  private Product product;
  private int quantity;
  private double amount;
  private Date createDate;

  public CartLine() {
  }

  public CartLine(Product product) {
    this.product = product;
  }

  public CartLine(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getAmount() {
    return product.getPrice()*this.quantity;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}