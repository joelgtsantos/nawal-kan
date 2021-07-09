package com.joelgtsantos.nawalkan.repositories;

import com.joelgtsantos.nawalkan.domain.Placeholder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Chat JPA repository to access the local resources
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.repositories
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
public interface PlaceholderRepository extends JpaRepository<Placeholder, Long> {
}
