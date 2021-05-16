package com.hankmew.benchmark.archetype.deploy.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
@Slf4j
public class LocaleInterceptor extends LocaleChangeInterceptor {
    @Bean
    public LocaleResolver localeResolver() {
        return new LocaleResolver() {

            private static final String PATH_PARAMETER = "lang";

            private static final String PATH_PARAMETER_SPLIT = "_";

            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String lang = request.getHeader(PATH_PARAMETER);
                Locale locale = request.getLocale();
                if (!StringUtils.isEmpty(lang)) {
                    String[] split = lang.split(PATH_PARAMETER_SPLIT);
                    locale = new Locale(split[0], split[1]);
                }
                return locale;
            }

            @Override
            public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

            }
        };
    }
}
