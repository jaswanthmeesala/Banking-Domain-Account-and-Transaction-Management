package com.ust.bankmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.bankmanagement.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
