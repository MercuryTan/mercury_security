package com.tx.security.config;

import com.tx.security.domain.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthenticationSuccessHandler mercuryAuthenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                /**
                 * 未授权后，跳转的url
                 **/
               .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                /**
                 * 表单登录中，action的url
                 **/
               .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                /**
                 * 登录成功后，跳转到的处理器
                 **/
                .successHandler(mercuryAuthenticationSuccessHandler)
                /**
                 * 登录失败后，跳转到的处理器
                 **/
                .failureHandler(mercuryAuthenticationFailureHandler);
    }
}
