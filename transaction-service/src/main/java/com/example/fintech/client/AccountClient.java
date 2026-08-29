package com.example.fintech.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "account-service")
public interface AccountClient {
    @GetMapping("/api/accounts/{id}/balance")
    BigDecimal getBalance(@PathVariable("id") String id);

    @GetMapping("/api/accounts/{id}/currency")
    String getCurrency(@PathVariable("id") String id);
    
    @PutMapping("/api/accounts/{id}/update-balance")
    void updateBalance(@PathVariable("id") String id, @RequestParam("amount") BigDecimal amount);
}
