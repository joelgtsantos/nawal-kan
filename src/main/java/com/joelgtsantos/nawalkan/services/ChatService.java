package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ChatListDTO;

/**
 * Chat service interface that provides two operations
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 * User: Joel Ajucum
 * Date: 7/9/2021
 */

public interface ChatService {

    /**
     * This function creates a new chat
     *
     * @param chatDTO this is a ChatDTO that may or not include messages
     * @return the same chatDTO is returned but already saved into the system
     */
    ChatDTO createNewChat(ChatDTO chatDTO);

    /**
     * This function searches between the chats to find messages an user has sent
     * and returns those chats and messages
     *
     * @param contactId is the Contact ID who sent the messages
     * @return a list of previous conversations where the Contact has participated
     */
    ChatListDTO getAllChatsByContactId(Long contactId);
}
