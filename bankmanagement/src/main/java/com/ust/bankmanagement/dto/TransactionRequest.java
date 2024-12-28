package com.ust.bankmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class TransactionRequest {
    @NotNull(message = "Account ID is required.")
    private Long accountId;

    @Min(value = 1, message = "Transaction amount must be greater than zero.")
    private Double amount;

    @Pattern(regexp = "DEPOSIT|WITHDRAWAL", message = "Transaction type must be either 'DEPOSIT' or 'WITHDRAWAL'.")
    private String transactionType;
}
