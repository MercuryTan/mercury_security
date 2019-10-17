package com.tx.security.broswer.config;

import com.tx.security.broswer.filter.ValidateImageCodeFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    ValidateImageCodeFilter validateImageCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateImageCodeFilter,UsernamePasswordAuthenticationFilter.class)
             .formLogin()
                .loginPage("/authentication/login")
                .loginProcessingUrl("/login/form")
                .successHandler(mercuryAuthenticationSuccessHandler)
                .failureHandler(mercuryAuthenticationFailureHandler)
            .and()
                .authorizeRequests()
                    .antMatchers("/generate/image-code","/authentication/login",mercuryProperty.getBroswer().getLoginPage()).permitAll()
                    .anyRequest() .authenticated()
            .and()
                .csrf().disable();
    }
}