package com.tx.security.validate.basic;

import com.tx.security.domain.SecurityConstants;
import com.tx.security.enumation.ValidateCodeType;
import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.image.ImageCodeException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ：tx
 * @description：公共校验过滤器
 * @modified By：
 * @version:
 */
//@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    AuthenticationFailureHandler mercuryAuthenticationFailureHandler;

    @Autowired
    MercuryProperty mercuryProperty;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 所有需要过滤的url  <String,                 url字符串
     *                    ValidateCodeType>       当前url属于哪种验证码
    **/
    private Map<String, ValidateCodeType> urlMaps = new HashMap<>();

    /**
     * 包含所有需校验 验证码的url
     */
    Set<String> urlSet = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String smsUrls = mercuryProperty.getCode().getSms().getUrls();
        addUrls2Map(smsUrls,ValidateCodeType.SMS);
        addUrls2Map(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM_MOBILE,ValidateCodeType.SMS);

        String imageUrls = mercuryProperty.getCode().getImageCode().getUrls();
        addUrls2Map(imageUrls,ValidateCodeType.IMAGE);
        addUrls2Map(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,ValidateCodeType.IMAGE);
    }

    /**
     * 添加url到map中
    **/
    private void addUrls2Map(String urls,ValidateCodeType type) {
        if (StringUtils.isEmpty(urls)){
            return;
        }
        String [] urlsArray = urls.split(",");
        for (String url : urlsArray) {
            urlMaps.put(url,type);
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String targetUrl = request.getRequestURI();
        //TODO:请添加注释.(修改好了把TODO删除)tx add. 抽出公共部分
        boolean isExist = false;
        for (String s : urlSet) {
            if(pathMatcher.match(targetUrl,s)){
                isExist = true;
            }
        }
        if (isExist) {
            //判断用户输入的验证码和session中生成的是否一致
            try {
//                validateCode(request);
            } catch (ImageCodeException e) {
                mercuryAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        //调用下一个过滤器
        filterChain.doFilter(request, response);
    }

}
