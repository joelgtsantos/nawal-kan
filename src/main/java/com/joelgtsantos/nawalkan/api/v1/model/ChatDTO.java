package com.joelgtsantos.nawalkan.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joelgtsantos.nawalkan.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatDTO represents the Contact JPA entity but only exposing the necessary properties
 *
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
public class ChatDTO {
    private String title;
    private List<Message> messages = new ArrayList<>();
    @JsonProperty("chat_url")
    private String chatUrl;
}
