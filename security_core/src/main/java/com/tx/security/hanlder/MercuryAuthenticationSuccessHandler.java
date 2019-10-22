package com.tx.security.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.security.enumation.JsonType;
import com.tx.security.properties.MercuryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：tx
 * @description： 登录成功后的处理器
 * @modified By：
 * @version:
 */
@Component("mercuryAuthenticationSuccessHandler")
public class MercuryAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    MercuryProperty mercuryProperty;

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取当前系统中配置的type
        JsonType jsonType = mercuryProperty.getBroswer().getJsonType();
        if(JsonType.JSON.equals(jsonType)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(mapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
