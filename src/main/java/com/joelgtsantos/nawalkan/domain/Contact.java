package com.joelgtsantos.nawalkan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Contact JPA entity stores the basic contact information
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.domain
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */
@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String telephone;

    private Integer age;

}
