package com.auryadov.userservice.service;

import com.auryadov.userservice.dto.TransactionRequestDto;
import com.auryadov.userservice.dto.TransactionResponseDto;
import com.auryadov.userservice.dto.TransactionStatus;
import com.auryadov.userservice.entity.UserTransaction;
import com.auryadov.userservice.repository.UserRepository;
import com.auryadov.userservice.repository.UserTransactionRepository;
import com.auryadov.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
        return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(this.transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getTransactionByUserId(final int userId) {
        return this.transactionRepository
                .findAllByUserId(userId);
    }
}
