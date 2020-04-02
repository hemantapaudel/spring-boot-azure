package com.myrest.example.application.data.repos;

import com.myrest.example.application.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}


