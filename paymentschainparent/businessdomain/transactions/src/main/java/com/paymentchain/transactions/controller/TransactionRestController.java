/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.transactions.controller;

import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.respository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/transaction")
public class TransactionRestController {
    
    @Autowired
    TransactionRepository transactionRepository;
    
      
    @GetMapping()
    public ResponseEntity<List<Transaction>> list() {
        List<Transaction> findAll = transactionRepository.findAll();
        if(findAll == null || findAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable long id) {
         return transactionRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> get(@RequestParam String ibanAccount) {
      List<Transaction> findByIbanAccount = transactionRepository.findByIbanAccount(ibanAccount);
        if(findByIbanAccount == null || findByIbanAccount.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findByIbanAccount);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Transaction input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transaction input) {
        Transaction save = transactionRepository.save(input);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
