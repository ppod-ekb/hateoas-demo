package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;

@Configuration
//@EnableHypermediaSupport(type= {EnableHypermediaSupport.HypermediaType.HAL})
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class ApplicationConfig {
/*
    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider("ex", new UriTemplate("https://www.example.com/rels/{rel}"));
    }

 */
}
