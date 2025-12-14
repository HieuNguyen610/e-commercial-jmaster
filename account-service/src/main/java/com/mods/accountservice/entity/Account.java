package com.mods.accountservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String username;

    private String password;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role")
    private List<String> roles;
}
