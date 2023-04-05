/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.service.BussinesTransaction;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.exception.BussinesRuleException;
import com.paymentchain.customer.respository.CustomerRepository;

import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    BussinesTransaction bt;
    
    @Value("${user.role}")
    private String role;
  
    
    
    @GetMapping("/full")
    public Customer get(@RequestParam  String code) {   
        Customer customer = bt.get(code);       
        return customer;   
    }
    
  
   
     @GetMapping()
    public ResponseEntity<List<Customer>> list() {
        List<Customer> findAll = customerRepository.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
           return ResponseEntity.ok(findAll);
        }
    }
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello your role is: "+ role;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable long id) {        
      return customerRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }  
   
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Customer input) {
        return null;
    }
    
   @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer input) throws BussinesRuleException, UnknownHostException {
        Customer save = bt.save(input);
       return new ResponseEntity<>(save,HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
