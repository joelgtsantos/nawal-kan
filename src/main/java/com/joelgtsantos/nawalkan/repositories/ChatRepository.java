package com.joelgtsantos.nawalkan.repositories;

import com.joelgtsantos.nawalkan.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

/**
 * Chat JPA repository to access the local resources
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.repositories
 * User: Joel Ajucum
 * Date: 7/8/2021
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where c.id = (select distinct(m.chat.id) from Message m where m.fromContactId = ?1 )")
    List<Chat> findByFromContact(Long fromId);
}
