package com.tx.security.validate.basic;

import com.tx.security.validate.image.ImageCodeFilter;
import com.tx.security.validate.sms.SmsCodeFilter;
import com.tx.security.validate.sms.authentication.SmsAuthenticationFilter;
import com.tx.security.validate.sms.authentication.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class CodeValidateConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationSuccessHandler mercuryAuthenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        smsAuthenticationFilter.setAuthenticationSuccessHandler(mercuryAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(mercuryAuthenticationFailureHandler);


        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsAuthenticationProvider)
            .addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
        ;
    }


}
