package com.ust.bankmanagement.controller;

import com.ust.bankmanagement.dto.TransactionRequest;
import com.ust.bankmanagement.entity.Transaction;
import com.ust.bankmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction processTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAccount(transactionService.getAccount(transactionRequest.getAccountId()));
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        return transactionService.processTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "Transaction with ID " + id + " has been deleted successfully.";
    }
}
