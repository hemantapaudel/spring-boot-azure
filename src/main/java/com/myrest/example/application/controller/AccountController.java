package com.myrest.example.application.controller;

import com.myrest.example.application.data.entity.Account;
import com.myrest.example.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/accountservice/getAccounts"}, produces = {"application/json"},
            method = {RequestMethod.GET})
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = accountService.findAll();
        return accounts;
    }

    @RequestMapping(value = {"/accountservice/getAccount/{id}"}, produces = {"application/json"},
            method = {RequestMethod.GET})
    public Account getAccount(@PathVariable("id") String id) {
        return accountService.findById(id);
    }


    @RequestMapping(value = {"/accountservice/createAccount"}, produces = {
            "application/json", "application/xml"}, method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Account createAccount(@RequestBody Account account) {
        Account accounts = accountService.create(account);

        return accounts;
    }

    @RequestMapping(value = "/accountservice/deleteAccount/id/{id}", consumes = {"application/json"},
            method = {RequestMethod.DELETE})
    public void deleteAccount(@PathVariable(value = "id") String id) {
        accountService.delete(id);
    }

    @RequestMapping(value = "/accountservice/updateAccount", produces = {"application/xml"}, method = {RequestMethod.PUT})
    @ResponseBody
    public Account updateAccount(@RequestBody Account account) {
        return accountService.update(account);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void handleTodoNotFound(NoSuchElementException ex) {
    }

}
