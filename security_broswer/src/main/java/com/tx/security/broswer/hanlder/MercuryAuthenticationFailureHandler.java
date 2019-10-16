package com.tx.security.broswer.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@Component("mercuryAuthenticationFailureHandler")
public class MercuryAuthenticationFailureHandler implements AuthenticationFailureHandler {

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
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(exception));
    }

}
