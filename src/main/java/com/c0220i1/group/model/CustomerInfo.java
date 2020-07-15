package com.c0220i1.group.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Component
@Entity
@Data
@Getter
@Setter
public class CustomerInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Address can not be empty")
    private String address;
    @NotEmpty(message = "Address can not be empty")
    private String name;
    @Pattern(regexp = "(^$|[0-9]*$)",message = "Phone number not validation")
    private String phone;
    @Email(message = "valid email form")
    private String email;
    @OneToOne
    private Account account;

    public CustomerInfo() {
    }
}
