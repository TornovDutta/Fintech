package com.example.fintech.dto;

import lombok.Data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotBlank(message = "Source account ID is required")
    private String sourceAccountId;
    
    @NotBlank(message = "Destination account ID is required")
    private String destinationAccountId;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Transfer amount must be greater than zero")
    private BigDecimal amount;
}
