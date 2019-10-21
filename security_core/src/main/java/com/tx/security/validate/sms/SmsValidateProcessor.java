package com.tx.security.validate.sms;

import com.tx.security.domain.ImageCode;
import com.tx.security.domain.SmsCode;
import com.tx.security.domain.ValidateCode;
import com.tx.security.validate.basic.AbstractValidateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component("smsValidateProcessor")
public class SmsValidateProcessor extends AbstractValidateProcessor {

    @Autowired
    SmsCodeSend smsCodeSend;

    @Override
    protected void send(ServletWebRequest request, ValidateCode code) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        //给手机号发送短信验证码
        SmsCode smsCode = (SmsCode)code;
        smsCodeSend.send(mobile, smsCode.getCode());
    }
}