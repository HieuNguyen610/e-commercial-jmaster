package com.mods.accountservice.service;

import com.mods.accountservice.model.AccountDto;

import java.util.List;

public interface AccountService {
    void add(AccountDto accountDto);

    void update(AccountDto accountDto);

    void updatePassword(AccountDto accountDto);

    void delete(Long id);

    List<AccountDto> findAll();

    AccountDto findById(Long id);
}
