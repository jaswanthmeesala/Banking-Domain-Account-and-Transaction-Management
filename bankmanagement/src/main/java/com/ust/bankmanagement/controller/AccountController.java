package com.ust.bankmanagement.controller;


import com.ust.bankmanagement.dto.AccountRequest;
import com.ust.bankmanagement.entity.Account;
import com.ust.bankmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = new Account();
        account.setName(accountRequest.getName());
        account.setBalance(accountRequest.getBalance());
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @Valid @RequestBody AccountRequest accountRequest) {
        Account account = new Account();
        account.setName(accountRequest.getName());
        account.setBalance(accountRequest.getBalance());
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "Account with ID " + id + " has been deleted successfully.";
    }
}
