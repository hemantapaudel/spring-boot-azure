package com.myrest.example.application.service;

import com.myrest.example.application.data.entity.User;
import com.myrest.example.application.data.model.MyUserDetails;
import com.myrest.example.application.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;

        for (int i = 0; i < 10; i++) {

            User user = new User(0, "user" + i, "password" + i, "ROLE_USER,ROLE_ADMIN",
                    true,
                    true,
                    true,
                    true
            );
            userRepository.save(user);
        }
        for (int i = 10; i < 20; i++) {

            User user = new User(0, "user" + i, "password" + i, "ROLE_USER",
                    true,
                    true,
                    true,
                    true
            );
            userRepository.save(user);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("No user details found:" + userName));

        return user.map(MyUserDetails::new).get();
    }
}
