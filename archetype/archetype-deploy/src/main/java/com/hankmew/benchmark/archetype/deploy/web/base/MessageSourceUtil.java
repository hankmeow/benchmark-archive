package com.hankmew.benchmark.archetype.deploy.web.base;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MessageSourceUtil {
    private static MessageSource messageSource;
    @Resource
    public void init(MessageSource messageSource) {
        MessageSourceUtil.messageSource = messageSource;
    }

    public static String generateMessage(Integer code) {
        return messageSource.getMessage(code.toString(), null, LocaleContextHolder.getLocale());
    }
}
