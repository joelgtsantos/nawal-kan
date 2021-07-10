package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.services.WebhookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is intended to be dedicated for communicating
 * an external service via webhook with this system
 * the exposed endpoint is the one available to share with the third party
 * messaging service
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@RestController
@RequestMapping(WebhookController.BASE_URL)
public class WebhookController {
    public static final String BASE_URL = "/api/v1/webhook";
    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createNewContact(@RequestBody Message message){
        webhookService.messengerWebhook(message);
    }
}
