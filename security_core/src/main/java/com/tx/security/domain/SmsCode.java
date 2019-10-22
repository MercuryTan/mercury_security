package com.tx.security.domain;

/**
 *
 */
public class SmsCode extends ValidateCode{
    /**
     * @author tx
     * @description
     * @Param: code  验证码
     * @Param: effectiveTime  失效时间
     */
    public SmsCode(String code, int effectiveTime) {
        super(code, effectiveTime);
    }
}