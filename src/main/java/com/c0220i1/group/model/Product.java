package com.c0220i1.group.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Long price;

    @Column(nullable = false)
    @Min(0)
    private Long quantity;

    @Column(nullable = false)
    private String image;

    @Min(0)
    private Long like;

    @Min(0)
    private double evaluate;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categorySet = new HashSet<>();

}
