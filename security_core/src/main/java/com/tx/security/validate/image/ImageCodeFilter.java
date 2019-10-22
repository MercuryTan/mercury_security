package com.tx.security.validate.image;

import com.tx.security.domain.ImageCode;
import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.basic.IValidateCodeGenerate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：tx
 * @description：登录校验图片验证码
 * @modified By：
 * @version:
 */
@Component
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;

    @Autowired
    MercuryProperty mercuryProperty;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 包含所有需校验 验证码的url
     */
    Set<String> urlSet = new HashSet<>();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        String urls = mercuryProperty.getCode().getImageCode().getUrls();
        String [] urlsArray = urls.split(",");
        for (String s : urlsArray) {
            urlSet.add(s);
        }

        urlSet.add("/login/form");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String targetUrl = request.getRequestURI();

        boolean isExist = false;
        for (String s : urlSet) {
            if(pathMatcher.match(targetUrl,s)){
                isExist = true;
            }
        }

        if (isExist) {
            //判断用户输入的验证码和session中生成的是否一致
            try {
                validateImageCode(request);
            } catch (ImageCodeException e) {
                mercuryAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                //TODO:请添加注释.(修改好了把TODO删除)tx add. 校验失败的信息
                return;
            }
        }
        //调用下一个过滤器
        filterChain.doFilter(request, response);
    }

    private void validateImageCode(HttpServletRequest request) throws ImageCodeException {
        Object sessionCodeObj = request.getSession().getAttribute(IValidateCodeGenerate.SESSION_CODE_KEY + "IMAGE");
        String inputCode = request.getParameter("imageCode");

        if (ObjectUtils.isEmpty(sessionCodeObj)) {
            throw new ImageCodeException("验证码还未生成，请联系技术人员处理！");
        }

        ImageCode imageCode = (ImageCode) sessionCodeObj;

        if (!dateDiffer(imageCode)){
            request.getSession().removeAttribute(IValidateCodeGenerate.SESSION_CODE_KEY + "IMAGE");
            throw new ImageCodeException("验证码失效了！");
        }

        if (StringUtils.isEmpty(inputCode)) {
            throw new ImageCodeException("请先输入验证码！");
        }


        if (!imageCode.getCode().equals(inputCode)) {
            throw new ImageCodeException("验证码不相符,请重新输入！");
        }
    }

    /**
     * @author tx
     * @description  计算当前时间和验证码过期时间之差，如果失效返回false
     * @param  * @Param: imageCode
     * @return boolean
    **/
    private boolean dateDiffer(ImageCode imageCode) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime expireTime = imageCode.getFailureTime();
        Duration duration = Duration.between(expireTime,nowTime);
        if(duration.getSeconds() > 0){
            return false;
        }
        return true;
    }
}
