package com.nijimas.api;

import com.nijimas.api.api.MockTokenVerifyFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MockTokenVerifyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
