/*package com.myrest.example.application.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class InMemorySecurity extends WebSecurityConfigurerAdapter {

  @Override
   protected void configure(HttpSecurity http) throws Exception {
       super.configure(http);
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.inMemoryAuthentication().withUser("paudel").password("paudel").roles("USER")
               .and()
               .withUser("pandey").password("pandey").roles("USER");
   }


   @Override
   public void configure(WebSecurity web) throws Exception {
       super.configure(web);
   }

   @Override
   public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
       super.setAuthenticationConfiguration(authenticationConfiguration);
   }


   @Bean
   public PasswordEncoder getPasswordEncoder(){
       return NoOpPasswordEncoder.getInstance();
   }
}*/
