package com.tx.security.broswer.config;

import com.tx.security.broswer.pojo.MercuryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler mercuryAuthenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;

    @Autowired
    MercuryProperty mercuryProperty;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/login")
                .loginProcessingUrl("/login/form")
                .successHandler(mercuryAuthenticationSuccessHandler)
                .failureHandler(mercuryAuthenticationFailureHandler)
            .and()
                .authorizeRequests()
                    .antMatchers("/authentication/login",mercuryProperty.getBroswer().getLoginPage()).permitAll()
                    .anyRequest() .authenticated()
            .and()
                .csrf().disable();
    }
}