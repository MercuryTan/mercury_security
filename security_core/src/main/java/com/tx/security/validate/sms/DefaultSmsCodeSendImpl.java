package com.tx.security.validate.sms;

/**
 *
 */
public class DefaultSmsCodeSendImpl implements SmsCodeSend {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号：" + mobile + " 发送了验证码：" + code);
    }
}