package com.example.avtorizationserver.component;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageByLang {
    final MessageSource messageSource;

    public MessageByLang(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessageByKey(String key){
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }
}
