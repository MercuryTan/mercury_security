package com.tx.security.broswer.controller;

import com.tx.security.broswer.pojo.LoginErrorMsg;
import com.tx.security.broswer.pojo.MercuryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@RestController
public class LoginController {


    /**
     * security缓存的信息，里面存有用户一开始的请求url
    **/
    HttpSessionRequestCache sessionCache = new HttpSessionRequestCache();

    @Autowired
    MercuryProperty mercuryProperty;

    /**
     * @author tx
     * @description  在访问url时，未登录或权限不够等，返回到登录页面
     *                  网页：返回到html页面
     *                  app/ajax等：返回json数据，包括状态码和错误信息
     * @param  * @Param:
     * @return java.lang.String
    **/
    @GetMapping("/authentication/login")
    public LoginErrorMsg login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(mercuryProperty);
        String targetUrl = sessionCache.getRequest(request,response).getRedirectUrl();
        if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.sendRedirect(mercuryProperty.getBroswer().getLoginPage());
        }
        return new LoginErrorMsg("当前登录错误，请联系相关人员处理消息");
    }
}
