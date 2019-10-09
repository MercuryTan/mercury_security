package com.tx.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
//@Component //该注解不写，是模拟第三方filter,就需要用FilterRegistrationBean来注册
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--- filter init ---");
    }

    /**
     * @author tx
     * @description   filter 不能够获取spring的一些信息，比如@controller等
    **/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long beginTime = System.currentTimeMillis();
        System.out.println("--- filter invoke begin --- :" );

        //把请求转发给另外一个filter
        filterChain.doFilter(servletRequest,servletResponse);

        long endTime = System.currentTimeMillis();
        System.out.println("--- filter invoke end --- 耗时：" + (endTime - beginTime));

    }

    @Override
    public void destroy() {
        System.out.println("--- filter destroy ---");
    }
}
