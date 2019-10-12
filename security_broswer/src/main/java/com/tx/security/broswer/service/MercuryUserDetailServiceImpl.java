package com.tx.security.broswer.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class MercuryUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //**根据用户名查找数据库
        //下面的密码"123456" 就是从数据库中获得到的，比如 user.getPwd();
        return new User(userName, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
