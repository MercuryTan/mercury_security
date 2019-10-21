package com.tx.security.validate.sms;

import com.tx.security.domain.SmsCode;
import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.basic.IValidateCodeGenerate;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("smsCodeGenerate")
public class SmsCodeGenerate implements IValidateCodeGenerate {

    @Autowired
    MercuryProperty mercuryProperty;

    @Override
    public SmsCode generateCode() {
        //生成4位短信验证码
        String code = RandomStringUtils.randomNumeric(4);
        return new SmsCode(code,3600);
    }

}