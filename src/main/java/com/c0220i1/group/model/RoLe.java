package com.c0220i1.group.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RoLe implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "role")
//    private List<Account> accounts;
    @Override
    public String getAuthority() {
        return this.name;
    }
}
