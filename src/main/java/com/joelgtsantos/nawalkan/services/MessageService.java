package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 * User: Joel Ajucum
 * Date: 7/9/2021
 */
public interface MessageService {

    MessageDTO sendNewMessage(MessageDTO messageDTO);

}
