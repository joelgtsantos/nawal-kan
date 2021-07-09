package com.joelgtsantos.nawalkan.repositories;

import com.joelgtsantos.nawalkan.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Message JPA repository to access the local resources
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.repositories
 * User: Joel Ajucum
 * Date: 7/8/2021
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
