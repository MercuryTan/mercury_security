package com.tx.security.broswer.filter;

import com.tx.security.broswer.controller.ImageCodeController;
import com.tx.security.broswer.pojo.ImageCode;
import com.tx.security.exception.ImageCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ：tx
 * @description：校验验证码
 * @modified By：
 * @version:
 */
@Component
public class ValidateImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String targetUrl = request.getRequestURI();
        if(StringUtils.pathEquals("/login/form",targetUrl) && "POST".equals(request.getMethod())){
            //判断用户输入的验证码和session中生成的是否一致
            try {
                validateImageCode(request);
            } catch (ImageCodeException e) {
                mercuryAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                //TODO:请添加注释.(修改好了把TODO删除)tx add. 校验失败的信息
                return;
            }
        }
        //调用下一个过滤器
        filterChain.doFilter(request,response);
    }

    private void validateImageCode(HttpServletRequest request) throws ImageCodeException {
        Object sessionCodeObj = request.getSession().getAttribute(ImageCodeController.SESSION_IMAGE_CODE_KEY);
        String inputCode = request.getParameter("imageCode");

        if(ObjectUtils.isEmpty(sessionCodeObj)){
            throw new ImageCodeException("验证码还未生成，请联系技术人员处理！");
        }

        if(StringUtils.isEmpty(inputCode)){
            throw new ImageCodeException("请先输入验证码！");
        }

        ImageCode imageCode = (ImageCode)sessionCodeObj;

        if(! imageCode.getCode().equals(inputCode)){
            throw new ImageCodeException("验证码不相符,请重新输入！");
        }
    }
}
