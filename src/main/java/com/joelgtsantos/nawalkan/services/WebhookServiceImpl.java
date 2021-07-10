package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@Slf4j
@Service
public class WebhookServiceImpl implements WebhookService{

    private RestTemplate restTemplate;
    private final String api_url;

    public WebhookServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public void messengerWebhook(Message message) {
        log.info("Sending message by webhook");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url+"/sendMessage");
    }
}
