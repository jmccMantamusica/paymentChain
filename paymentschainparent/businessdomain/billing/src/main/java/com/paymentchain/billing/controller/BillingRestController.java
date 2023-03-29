package com.paymentchain.billing.controller;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingRestController {

    @Autowired
    BillingRepository billingRepository;

    @GetMapping()
    public List<Invoice> findAll() {
        return billingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Invoice input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Invoice input) {
        Invoice save = billingRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
