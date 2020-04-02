package com.myrest.example.application.data.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {


    @Id
    @Column(name = "ACCOUNT_ID")
    //@GeneratedValue(strategy = GenerationType.AUTO)
            String id;

    String accountType;
    String accountNumber;
    double balaceAmmount;
    LocalDateTime dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalaceAmmount() {
        return balaceAmmount;
    }

    public void setBalaceAmmount(double balaceAmmount) {
        this.balaceAmmount = balaceAmmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
