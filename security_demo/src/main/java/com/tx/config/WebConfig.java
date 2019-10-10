package com.tx.config;

import com.tx.web.filter.TimeFilter;
import com.tx.web.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：tx
 * @description：由于第三方的filter不会存在@Component,因此需要用以下方式，把第三方的filter加入到过滤器链中
 * @modified By：
 * @version:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TimeInterceptor());
//    }


    /**
     * @author tx
     * @description  把自定义filter加入到过滤器链中，并且指定，哪些url会被过滤
     * @param  * @Param:
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
    **/
   /* @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistration.setFilter(timeFilter);

        List urls = new ArrayList();
        urls.add("/*");
        filterRegistration.setUrlPatterns(urls);


        return filterRegistration;
    }*/


}
