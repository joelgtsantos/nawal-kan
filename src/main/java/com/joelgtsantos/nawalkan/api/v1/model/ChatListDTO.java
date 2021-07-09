package com.joelgtsantos.nawalkan.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ChatListDTO wraps up a list of contacts
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.model
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatListDTO {
    List<ChatDTO> chats;
}
