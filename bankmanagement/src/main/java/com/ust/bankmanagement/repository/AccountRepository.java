package com.ust.bankmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.bankmanagement.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

