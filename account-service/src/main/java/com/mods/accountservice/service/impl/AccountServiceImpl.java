package com.mods.accountservice.service.impl;

import com.mods.accountservice.entity.Account;
import com.mods.accountservice.model.AccountDto;
import com.mods.accountservice.repository.AccountRepository;
import com.mods.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
    }

    @Override
    public void update(AccountDto accountDto) {
        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("Account id is required for update");
        }
        Account existing = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountDto.getId()));

        // Update allowed fields (do not overwrite password here)
        existing.setUsername(accountDto.getUsername());
        existing.setEmail(accountDto.getEmail());
        existing.setRoles(accountDto.getRoles());

        accountRepository.save(existing);
    }

    @Override
    public void updatePassword(AccountDto accountDto) {
        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("Account id is required to update password");
        }
        if (accountDto.getPassword() == null) {
            throw new IllegalArgumentException("New password is required");
        }
        Account existing = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountDto.getId()));

        existing.setPassword(accountDto.getPassword());
        accountRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Long id) {
        return accountRepository.findById(id)
                .map(a -> modelMapper.map(a, AccountDto.class))
                .orElse(null);
    }
}
