package com.nijimas.api.config;

import com.nijimas.api.middleware.TokenVerifyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenVerifyFilter> authFilter() {
        FilterRegistrationBean<TokenVerifyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenVerifyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
