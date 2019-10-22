package com.tx.security.domain;

/**
 * @author ：tx
 * @description：security常量表
 * @modified By：
 * @version:
 */
public class SecurityConstants {
    /**
     * 没有权限后，跳转的url
    **/
    public static final String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/login";

    /**
     * 登录表单中的action
     **/
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/login/form";

    /**
     * 默认remember-me中cookie的有效时间
     **/
    public static final int DEFAULT_LOGIN_REMEMBER_ME_TIME =  3600;

    /**
     * 验证码生成controller中的前缀
     **/
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/generate";

    /**
     * 默认的登录主页
     **/
    public static final String DEFAULT_LOGIN_PAGE_URL = " /login/login-user.html";


    //~~验证码
    /**
     * 验证码长度，默认4位
     **/
    public static final int CODE_LENGTH =  4;

    /**
     * 验证码有效时间，默认1小时
     **/
    public static final int CODE_EXPIRE_TIME =  3600;
}
