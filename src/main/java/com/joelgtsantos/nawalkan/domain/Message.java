package com.joelgtsantos.nawalkan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * The message entity stores the messages a channel may have
 * and the relationship with the owner and recipient contacts
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.domain
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */

@Data
@Entity
@EqualsAndHashCode(exclude = {"chat"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromContactId;

    private Long toContactId;

    private String message;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private Chat chat;
}
