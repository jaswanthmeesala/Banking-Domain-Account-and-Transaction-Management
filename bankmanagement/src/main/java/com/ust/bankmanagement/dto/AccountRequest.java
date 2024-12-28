package com.ust.bankmanagement.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "Account name cannot be blank.")
    private String name;

    @Min(value = 0, message = "Balance cannot be negative.")
    private Double balance;
}
