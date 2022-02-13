package com.auryadov.userservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table("USERS")
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
