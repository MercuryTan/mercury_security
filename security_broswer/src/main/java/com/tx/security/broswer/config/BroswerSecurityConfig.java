package com.tx.security.broswer.config;

import com.tx.security.config.AbstractChannelSecurityConfig;
import com.tx.security.domain.SecurityConstants;
import com.tx.security.properties.MercuryProperty;
import com.tx.security.config.ValidateCodeConfig;
import com.tx.security.validate.image.ImageCodeFilter;
import com.tx.security.validate.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class BroswerSecurityConfig extends AbstractChannelSecurityConfig {

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
    ValidateCodeConfig validateCodeConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * @author tx
     * @description  security初始化token到数据库中，需要的dao层类
     * @param  * @Param:
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
    **/
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setCreateTableOnStartup(true); //在启动时，创建表，所以需要在第一次创建时才开启
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * @author tx
     * @description  【 broswer端 】 【 配置信息 】
     * @param  * @Param: http
     * @return void
    **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录设置
        applyPasswordAuthenticationConfig(http);

        /**
         * 引入【验证码】配置信息
        **/
        http.apply(validateCodeConfig)
             .and()
            /**
             * 记住我
             **/
             .rememberMe()
                .tokenValiditySeconds(mercuryProperty.getBroswer().getCookieTime())
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
             .and()
            /**
             * url权限判断
             **/
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                        mercuryProperty.getBroswer().getLoginPage()
                ).permitAll()
                .anyRequest().authenticated()
            .and()
                .csrf().disable()

            ;
    }
}