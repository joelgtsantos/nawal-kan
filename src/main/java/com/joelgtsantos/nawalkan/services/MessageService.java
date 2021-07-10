package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;

/**
 * Message interface adds a new message
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 * User: Joel Ajucum
 * Date: 7/9/2021
 */
public interface MessageService {

    /**
     * This function saves a new message into the system
     * @param messageDTO expects a MessageDTO with the ChatID where the message belongs to
     * @return returns the MessageDTO saved
     */
    MessageDTO sendNewMessage(MessageDTO messageDTO) throws ResourceNotFoundException;

    /**
     * This function replaces the placeholders accordingly with the values stored
     * @param messageDTO
     * @return
     */
    MessageDTO applyPlaceholders(MessageDTO messageDTO);
}
