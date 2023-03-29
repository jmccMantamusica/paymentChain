package com.paymentchain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return productRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Product input) {
        Product find = productRepository.findById(id).get();
        if(find != null){
            find.setCode(input.getCode());
            find.setName(input.getName());
        }
        Product save = productRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) {
        Product save = productRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Product> findById = productRepository.findById(id);
        if(findById.get() != null){
            productRepository.delete(findById.get());
        }
        return null;
    }

}
