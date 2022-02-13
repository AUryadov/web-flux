package com.auryadov.userservice.util;

import com.auryadov.userservice.dto.TransactionRequestDto;
import com.auryadov.userservice.dto.TransactionResponseDto;
import com.auryadov.userservice.dto.TransactionStatus;
import com.auryadov.userservice.dto.UserDto;
import com.auryadov.userservice.entity.User;
import com.auryadov.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        var dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

     public static User toEntity(UserDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
     }

     public static UserTransaction toEntity(TransactionRequestDto requestDto) {
        var userTransaction = new UserTransaction();
        userTransaction.setUserId(requestDto.getUserId());
        userTransaction.setAmount(requestDto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
     }

     public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
        var responseDto = new TransactionResponseDto();
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
     }
}
