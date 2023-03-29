package com.paymentchain.transaction.controller;

import com.paymentchain.transaction.entities.Transaction;
import com.paymentchain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping()
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable long id) {
        return transactionRepository.findById(id).map(
                ResponseEntity::ok).orElse((ResponseEntity.notFound().build()));
    }

    @GetMapping("/customer/transactions")
    public List<Transaction> get(@RequestParam String ibanAccount) {
        return transactionRepository.findByIbanAccount(ibanAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Transaction input) {
        Transaction find = transactionRepository.findById(id).get();
        if(find != null){
            find.setAmount(input.getAmount());
            find.setChannel(input.getChannel());
            find.setDate(input.getDate());
            find.setDescription(input.getDescription());
            find.setFee(input.getFee());
            find.setIbanAccount(input.getIbanAccount());
            find.setReference(input.getReference());
            find.setStatus(input.getStatus());
        }
        Transaction save = transactionRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transaction input) {
        Transaction save = transactionRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Transaction> findById = transactionRepository.findById(id);
        if(findById.get() != null){
            transactionRepository.delete(findById.get());
        }
        return null;
    }

}
