package com.profuno.fingerprint_assistance.utils.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:message.properties")
public class MessageResource {

    @Autowired
    private Environment env;

    public String getDefaultMessage(String key, String... params){
        if(key == null || key.isBlank()) return "";

        String messageValue = env.getProperty(key);
        String result = messageValue;
        if(params != null && params.length != 0 && !Arrays.stream(params).anyMatch((param) -> param == null )){
            result = String.format(messageValue, params);
        }

        return result;
    }

    public String getDefaultMessage(String key){
        String result = env.getProperty(key);
        return result;
    }
}
