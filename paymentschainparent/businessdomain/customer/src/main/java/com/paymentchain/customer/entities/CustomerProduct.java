package com.paymentchain.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    @Transient
    private String productName;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;
}
