package com.tx.security.validate.basic;

import com.tx.security.domain.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


/**
 *
 */
public abstract class AbstractValidateProcessor<C extends ValidateCode> implements IValidateCodeProcessor {

    /**
     * 收集系统中所有的 {@link IValidateCodeGenerate} 接口的实现。
     */
    @Autowired
    private Map<String, IValidateCodeGenerate> validateCodeGenerators;


    @Override
    public void create(ServletWebRequest request) throws Exception {
        //创建code
        C code = generateCode(request);
        //保存到session中
        save2Session(request,code);
        //发送
        send(request,code);
    }

    /**
     * 创建验证码
     * @return
     */
    protected  C generateCode(ServletWebRequest request){
        //获得当前使用哪个generator
        String type = getProcessorType(request);
        IValidateCodeGenerate iValidateCodeGenerate = validateCodeGenerators.get(type + "CodeGenerate");
        //生成code
        return (C)iValidateCodeGenerate.generateCode();
    }


    /**
     * 保存验证码到session中
     * @param request
     * @param code
     */
    protected void save2Session(ServletWebRequest request,C code){
        String upperProcessorType = StringUtils.upperCase(getProcessorType(request));
        String key = IValidateCodeGenerate.SESSION_CODE_KEY + upperProcessorType;
        request.getRequest().getSession().setAttribute(key,code);
    }

    /**
     * 发送验证码或者输出验证码到页面上,由子类实现
     */
    protected abstract void send(ServletWebRequest request,C code) throws  Exception;


    /**
     * 根据请求的url获得校验码的类型
     * 如：sms,image
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request){
        String url = request.getRequest().getRequestURI();
        String str = StringUtils.substringAfter(url,"/generate/");
        return StringUtils.substringBefore(str,"-code");
    }

}