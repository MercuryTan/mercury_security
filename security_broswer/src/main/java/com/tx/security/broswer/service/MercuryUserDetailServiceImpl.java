package com.tx.security.broswer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class MercuryUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //**根据用户名查找数据库
        //下面的密码"123456" 就是从数据库中获得到的，比如 user.getPwd();
        String encodePwd = passwordEncoder.encode("123456");
        logger.info("数据库的加密密码：" + encodePwd);
        return new User(userName, encodePwd,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
