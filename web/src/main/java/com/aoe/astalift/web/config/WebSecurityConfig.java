/*
package com.aoe.astalift.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



*/
/**
 * Created by joey on 16-3-7.
 *//*


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("adminpassword")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("userpassword")
                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources*/
/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/resources/index.html#/login")
                    .loginProcessingUrl("/login.json")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .csrf()
                .disable();

    }

    //httpSecurity.authorizeRequests().anyRequest().authenticated().and()
    //.formLogin().loginPage("/resources/index.html#/login").permitAll();
}

*/
