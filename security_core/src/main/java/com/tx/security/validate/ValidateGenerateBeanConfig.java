package com.tx.security.validate;

import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.basic.IValidateCodeGenerate;
import com.tx.security.validate.image.ImageCodeGenerate;
import com.tx.security.validate.sms.DefaultSmsCodeSendImpl;
import com.tx.security.validate.sms.SmsCodeGenerate;
import com.tx.security.validate.sms.SmsCodeSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ValidateGenerateBeanConfig {


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerate") //如果应用中没有找到名字是这个的bean,那么就会生成下面这个bena
    public IValidateCodeGenerate imageCodeGenerate(){
        ImageCodeGenerate imageCodeGenerate = new ImageCodeGenerate();
        return imageCodeGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeGenerate.class) //如果应用中没有找到名字是这个的bean,那么就会生成下面这个bena
    public IValidateCodeGenerate smsCodeGenerate(){
        SmsCodeGenerate smsCodeGenerate = new SmsCodeGenerate();
        return smsCodeGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSend.class) //如果应用中没有找到名字是这个的bean,那么就会生成下面这个bena
    public SmsCodeSend smsCodeSend(){
        SmsCodeSend smsCodeSend = new DefaultSmsCodeSendImpl();
        return smsCodeSend;
    }
}