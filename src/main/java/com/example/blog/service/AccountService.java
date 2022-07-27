package com.example.blog.service;

import com.example.blog.domain.Account;
import com.example.blog.domain.AccountRepository;
import com.example.blog.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public Boolean login(AccountRequestDto requestDto) {
        if (accountRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            Account test = accountRepository.findByUsername(requestDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException());

            if (test.getPassword() == requestDto.getPassword()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Transactional
    public Boolean create(AccountRequestDto requestDto) {
        if (accountRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            return false;
        }
        Account account = new Account(requestDto.getUsername(), requestDto.getPassword());
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public void updatePassword(AccountRequestDto requestDto) {
        Account account = accountRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException());

        account.updatePassword(requestDto.getPassword());
    }

    @Transactional
    public void delete(AccountRequestDto requestDto) {
        Account account = accountRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException());
        accountRepository.delete(account);
    }
}
