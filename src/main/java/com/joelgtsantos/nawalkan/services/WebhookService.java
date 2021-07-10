package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.domain.Message;

/**
 * This interface is for receiving  a message from an external source
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
public interface WebhookService {
    public void messengerWebhook(Message message);
}
