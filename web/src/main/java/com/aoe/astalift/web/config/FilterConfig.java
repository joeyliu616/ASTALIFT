package com.aoe.astalift.web.config;

import com.aoe.astalift.web.filter.CORSFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joey on 16-3-28.
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registration(CORSFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(true);
        return registration;
    }
}
