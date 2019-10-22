package com.tx.security.broswer.config;

import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.basic.CodeValidateConfig;
import com.tx.security.validate.image.ImageCodeFilter;
import com.tx.security.validate.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    ImageCodeFilter validateImageCodeFilter;

    @Autowired
    SmsCodeFilter validateSmsCodeFilter;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CodeValidateConfig codeValidateConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setCreateTableOnStartup(true); //在启动时，创建表，所以需要在第一次创建时才开启
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateImageCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateSmsCodeFilter,UsernamePasswordAuthenticationFilter.class)
             .formLogin()
                .loginPage("/authentication/login")
                .loginProcessingUrl("/login/form")
                .successHandler(mercuryAuthenticationSuccessHandler)
                .failureHandler(mercuryAuthenticationFailureHandler)
                .and()
             .rememberMe()
                .tokenValiditySeconds(mercuryProperty.getBroswer().getCookieTime())
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                    .antMatchers("/generate/*","/authentication/login",mercuryProperty.getBroswer().getLoginPage()).permitAll()
                    .anyRequest() .authenticated()
            .and()
                .csrf().disable()
            .apply(codeValidateConfig)
            ;
    }
}