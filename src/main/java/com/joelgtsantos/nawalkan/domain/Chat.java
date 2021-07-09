package com.joelgtsantos.nawalkan.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The Chat Entity stores the different chats a user may have
 * and One to Many relationship with the messages
 *
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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "chat")
    private Set<Message> messages = new HashSet<>();
}
