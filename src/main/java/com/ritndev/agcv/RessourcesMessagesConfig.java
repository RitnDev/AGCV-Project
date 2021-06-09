package com.ritndev.agcv;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 *
 * @author Ritn
 */
@Configuration
public class RessourcesMessagesConfig {
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/messages/typeReponses"//,
                //"classpath:/messages/api_response_messages"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    
}
