package com.paymentchain.customer.repository;

import com.paymentchain.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("Select c from Customer c Where c.code = ?1")
    public Customer findByCode(String code);

    @Query("Select c from Customer c Where c.iban = ?1")
    public Customer findByAccount(String iban);
}