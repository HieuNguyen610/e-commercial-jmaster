package com.mods.accountservice.controller;

import com.mods.accountservice.model.AccountDto;
import com.mods.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable(name = "id") Long id ) {
        AccountDto accountDto = accountService.findById(id);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id ) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/accounts/${id}")
    public void update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        accountDto.setId(id);
        accountService.update(accountDto);
    }
}
