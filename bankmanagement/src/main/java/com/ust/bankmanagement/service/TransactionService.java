package com.ust.bankmanagement.service;


import com.ust.bankmanagement.entity.Account;
import com.ust.bankmanagement.entity.Transaction;
import com.ust.bankmanagement.exception.InsufficientBalanceException;
import com.ust.bankmanagement.exception.ResourceNotFoundException;
import com.ust.bankmanagement.repository.AccountRepository;
import com.ust.bankmanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction processTransaction(Transaction transaction) {
        Account account = transaction.getAccount();

        if ("WITHDRAWAL".equals(transaction.getTransactionType()) && account.getBalance() < transaction.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }

        double updatedBalance = "DEPOSIT".equals(transaction.getTransactionType())
                ? account.getBalance() + transaction.getAmount()
                : account.getBalance() - transaction.getAmount();

        account.setBalance(updatedBalance);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = getTransactionById(id);
        transactionRepository.delete(transaction);
    }

    public Account getAccount(Long accountId) {
        return accountService.getAccountById(accountId); 
    }
}
