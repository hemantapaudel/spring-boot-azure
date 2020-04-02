package com.myrest.example.application.service;


import com.myrest.example.application.data.entity.Account;

import java.util.List;

public interface AccountService {

    Account create(Account account);

    void delete(String id);

    List<Account> findAll() throws Exception;

    List<Account> findAllAccounts() throws Exception;

    Account findById(String id);

    Account update(Account todo);
}