package com.auryadov.userservice.controller;

import com.auryadov.userservice.dto.TransactionRequestDto;
import com.auryadov.userservice.dto.TransactionResponseDto;
import com.auryadov.userservice.entity.UserTransaction;
import com.auryadov.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono.flatMap(this.transactionService::createTransaction);
    }

    @GetMapping("/{userId}")
    public Flux<UserTransaction> getTransactionsByUserId(@PathVariable int userId) {
        return transactionService.getTransactionByUserId(userId);
    }
}
