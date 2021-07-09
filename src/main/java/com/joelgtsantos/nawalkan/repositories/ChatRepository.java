package com.joelgtsantos.nawalkan.repositories;

import com.joelgtsantos.nawalkan.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Chat JPA repository to access the local resources
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.repositories
 * User: Joel Ajucum
 * Date: 7/8/2021
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
