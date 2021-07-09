package com.joelgtsantos.nawalkan.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContactDTO represents the Contact JPA entity but only exposing the necessary properties
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.model
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private String name;
    private String email;
    private String telephone;
    private Integer age;
    @JsonProperty("contact_url")
    private String contactUrl;
}
