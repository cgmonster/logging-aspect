package com.airtel.loggingaspect;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Data data(){
        return new Data(2,3);
    }
}
