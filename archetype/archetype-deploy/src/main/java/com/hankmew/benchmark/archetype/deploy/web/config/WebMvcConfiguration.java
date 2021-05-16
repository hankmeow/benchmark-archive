package com.hankmew.benchmark.archetype.deploy.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private ThreadLocalInterceptor threadLocalInterceptor;
    @Resource
    private LocaleInterceptor localeInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowCredentials(true)
            .allowedHeaders("*")
            .maxAge(3600);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter () {
        //重写Json解析器，这里可以获得未序列化前的返回值，还可以自定义返回的一些行为。
        //最好写成子类实现的形式，便于共享。
        return new MappingJackson2HttpMessageConverter () {
            @Override
            protected void writeInternal (Object object,
                                          HttpOutputMessage outputMessage) throws IOException,
                                                                                  HttpMessageNotWritableException {
                super.writeInternal(object, outputMessage);
            }
        };
    }
    @Override
    public void configureMessageConverters (List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(threadLocalInterceptor).addPathPatterns("/**");
        registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
    }
}
