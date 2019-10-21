package com.tx.security.validate;

import com.tx.security.domain.ImageCode;
import com.tx.security.domain.SmsCode;
import com.tx.security.validate.basic.IValidateCodeGenerate;
import com.tx.security.validate.basic.IValidateCodeProcessor;
import com.tx.security.validate.sms.SmsCodeSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author ：tx
 * @description： 生成验证码
 * @modified By：
 * @version:
 */
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

   public static final String SESSION_IMAGE_CODE_KEY = "IMAGE_CODE_KEY";

    /* @Autowired
    IValidateCodeGenerate imageCodeGenerate;

    @Autowired
    IValidateCodeGenerate smsCodeGenerate;

    @Autowired
    SmsCodeSend smsCodeSend;*/

    /**
     * 收集系统中所有的 {@link IValidateCodeProcessor} 接口的实现。
     */
    @Autowired
    private Map<String, IValidateCodeProcessor> processors;



    @GetMapping("/generate/{type}")
    public void  generateImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
       IValidateCodeProcessor processor = processors.get(type+"ValidateProcessor");
       processor.create(new ServletWebRequest(request,response));
    }



/*
    @GetMapping("/generate/image-code")
    public void  generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        ImageCode imageCode = (ImageCode)imageCodeGenerate.generateCode();
        logger.info("当前生成的验证码是：" + imageCode.getCode());
        //把验证码放到session中
        session.setAttribute(SESSION_IMAGE_CODE_KEY,imageCode);
        //输出到页面上
        ImageIO.write(imageCode.getBufferedImage(),"JPEG",response.getOutputStream());
    }*/

  /*  @GetMapping("/generate/sms-code")
    public void  generateSmsCode(HttpSession session, ServletRequest request) throws Exception {
        //生成验证码
        SmsCode smsCode = (SmsCode) smsCodeGenerate.generateCode();
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        logger.info("当前的短信验证码是：" + smsCode.getCode());
        //把验证码放到session中
        session.setAttribute(SESSION_IMAGE_CODE_KEY, smsCode);
        //给手机号发送短信验证码
        smsCodeSend.send(mobile, smsCode.getCode());
    }*/
}
