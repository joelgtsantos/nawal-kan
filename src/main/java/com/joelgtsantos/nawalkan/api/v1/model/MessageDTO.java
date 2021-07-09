package com.joelgtsantos.nawalkan.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joelgtsantos.nawalkan.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.model
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long fromContactId;

    private Long toContactId;

    private String message;

    private String status;

    private Long chatId;

    @JsonProperty("message_url")
    private String messageUrl;
}
