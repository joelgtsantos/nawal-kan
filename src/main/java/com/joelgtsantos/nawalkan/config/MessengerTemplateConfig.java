package com.joelgtsantos.nawalkan.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This bean provides the capability of consuming an external service
 * It's intended to be a channel of communication with other messaging platforms
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.config
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@Configuration
public class MessengerTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
