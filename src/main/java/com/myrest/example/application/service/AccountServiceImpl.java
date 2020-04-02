package com.myrest.example.application.service;

import com.myrest.example.application.data.entity.Account;
import com.myrest.example.application.data.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {


    static int i = 0;

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setId("" + i);
            account.setAccountNumber("123" + i + "123" + i);
            account.setAccountType("saving");
            account.setBalaceAmmount(240);
            account.setDateTime(LocalDateTime.now());
            accountRepository.save(account);

        }


    }

    @Override
    public Account create(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findAll() throws Exception {


        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll(PageRequest.of(i, 20, Sort.unsorted())).forEach(accounts::add);
        i = (i + 1) % 5;
        if (i > 2) {
            throw new Exception("Error while doing pagination");
        }
        return accounts;
    }


    @Override
    public List<Account> findAllAccounts() throws Exception {

        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account update(Account todo) {
        return accountRepository.save(todo);
    }
}