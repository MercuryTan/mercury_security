package com.tx.security.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx.security.enumation.JsonType;
import com.tx.security.properties.MercuryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：tx
 * @description： 登录失败后的处理器
 * @modified By：
 * @version:
 */
@Component("mercuryAuthenticationFailureHandler")
public class MercuryAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MercuryProperty mercuryProperty;

    ObjectMapper mapper = new ObjectMapper();

    /**
     * @author tx
     * @description  目前是登录失败直接返回json数据到前台。如果有需要区别是返回json还是页面，可参考MercuryAuthenticationSuccessHandler.java
     * @param  * @Param: request
     * @Param: response
     * @Param: exception
     * @return void
    **/
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
      /*  response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(exception));
*/
        //获取当前系统中配置的type
        JsonType jsonType = mercuryProperty.getBroswer().getJsonType();
        if(JsonType.JSON.equals(jsonType)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(mapper.writeValueAsString(exception.getMessage()));
        }else{
            super.onAuthenticationFailure(request,response,exception);
        }
    }

}
