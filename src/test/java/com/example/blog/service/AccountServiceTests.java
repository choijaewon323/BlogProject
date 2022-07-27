package com.example.blog.service;

import com.example.blog.domain.Account;
import com.example.blog.domain.AccountRepository;
import com.example.blog.dto.AccountRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTests {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @AfterEach
    void afterEach() throws Exception {
        accountRepository.deleteAll();
    }

    @Test
    void loginTest() throws Exception {
        Account account = new Account("acc", "pass");
        accountRepository.save(account);

        // test
        AccountRequestDto requestDto = new AccountRequestDto("bcc", "pass");
        assertThat(accountService.login(requestDto)).isFalse();

        AccountRequestDto requestDto1 = new AccountRequestDto("acc", "pbss");
        assertThat(accountService.login(requestDto1)).isFalse();

        AccountRequestDto requestDto2 = new AccountRequestDto("acc", "pass");
        assertThat(accountService.login(requestDto2)).isTrue();
    }

    @Test
    void createTest() throws Exception {
        Account account = new Account("acc", "pass");
        accountRepository.save(account);
        AccountRequestDto requestDto = new AccountRequestDto("acc", "pass");
        AccountRequestDto requestDto1 = new AccountRequestDto("abc", "pass");

        // test
        assertThat(accountService.create(requestDto)).isFalse();
        assertThat(accountService.create(requestDto1)).isTrue();
        assertThat(accountRepository.count()).isEqualTo(2L);
    }

    @Test
    void updatePasswordTest() throws Exception {
        Account account = new Account("acc", "pass");
        accountRepository.save(account);
        AccountRequestDto requestDto = new AccountRequestDto("acc", "pass1");

        // test
        accountService.updatePassword(requestDto);
        assertThat(accountRepository.findAll().get(0).getPassword()).isEqualTo("pass1");
    }

    @Test
    void deleteTest() throws Exception {
        Account account = new Account("acc", "pass");
        accountRepository.save(account);
        AccountRequestDto requestDto = new AccountRequestDto("acc", "pass");

        // test
        accountService.delete(requestDto);
        assertThat(accountRepository.count()).isEqualTo(0L);
    }
}
