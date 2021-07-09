package com.joelgtsantos.nawalkan.repositories;

import com.joelgtsantos.nawalkan.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Contact JPA repository to access the local resources
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.repositories
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}