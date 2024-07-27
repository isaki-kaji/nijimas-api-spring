package com.nijimas.api.config;

import com.nijimas.api.api.TokenVerifyFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenVerifyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
