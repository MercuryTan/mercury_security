package com.tx.security.validate;

import com.tx.security.validate.basic.IValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ：tx
 * @description： 【验证码生成器】
 *                      生成图片验证码: image
 *                      短信验证码: sms
 * @modified By：
 * @version:
 */
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 收集系统中所有的 {@link IValidateCodeProcessor} 接口的实现。
     */
    @Autowired
    private Map<String, IValidateCodeProcessor> processors;


    @GetMapping("/generate/{type}")
    public void  generateImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
       logger.info("============================准备生成:" + type + "验证码============================");
       IValidateCodeProcessor processor = processors.get(type + "ValidateProcessor");
       processor.create(new ServletWebRequest(request, response));
       logger.info("============================生成:" + type + "验证码完毕============================");
    }

}
