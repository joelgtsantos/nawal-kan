package com.joelgtsantos.nawalkan.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ContactListDTO wraps a list of contacts
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.model
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactListDTO {

    List<ContactDTO> contacts;
}
