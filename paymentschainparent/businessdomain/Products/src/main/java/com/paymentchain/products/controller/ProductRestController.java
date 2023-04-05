/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.products.controller;

import com.paymentchain.products.respository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.products.entities.Product;
import org.springframework.beans.factory.annotation.Value;
/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {
    
    @Autowired
    ProductRepository productRepository;
         
     @Value("${user.role}")
    private String role;
    
     @GetMapping()
    public ResponseEntity<List<Product>> list() {
         System.out.print("el role es : " +role);
         List<Product> findAll = productRepository.findAll();
         if(findAll == null || findAll.isEmpty()){
             return ResponseEntity.noContent().build();
         }else{
             return ResponseEntity.ok(findAll);
         }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }  
   
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Product input) {
        return null;
    }
    
   @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) {       
        Product save = productRepository.save(input);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
