package com.joelgtsantos.nawalkan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This entity allows to configure the placeholders a {@link Message#Message()} might contain
 * which are intended to be substituted after
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.domain
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@Data
@Entity
public class Placeholder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyName;

    private String propertyValue;

    private String status;
}
