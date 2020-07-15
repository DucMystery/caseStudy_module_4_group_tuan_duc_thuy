package com.c0220i1.group.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RoLe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<Account> accounts;
}
