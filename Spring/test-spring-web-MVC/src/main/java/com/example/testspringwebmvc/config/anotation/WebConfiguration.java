package com.example.testspringwebmvc.config.anotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry regitry){
        regitry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources");
        regitry.addResourceHandler("webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        regitry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        regitry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        regitry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
