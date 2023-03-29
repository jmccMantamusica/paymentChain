package com.paymentchain.customer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String iban;
    private String name;
    private String surname;
    private String phone;
    private String address;
    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <CustomerProduct> products;
    @Transient
    private List<?> transaction;

}
