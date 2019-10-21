package com.tx.security.validate.basic;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 */
public interface IValidateCodeProcessor {

    /**
     * 生成验证码
     */
    void create(ServletWebRequest request) throws Exception;
}