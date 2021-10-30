package com.profuno.fingerprint_assistance.utils.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:message.properties")
public class LanguageResource {

    @Autowired
    private Environment env;

    public String getMessage(String key, String language){
        String result = this.env.getProperty(key + "." + language);
        return result;
    }

    public String getDefaultMessage(String key){
        String result = this.env.getProperty(key);
        return result;
    }
}
