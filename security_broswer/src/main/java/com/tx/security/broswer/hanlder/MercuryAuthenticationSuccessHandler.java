package com.tx.security.broswer.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
@Component("mercuryAuthenticationSuccessHandler")
public class MercuryAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(authentication));
    }
}
