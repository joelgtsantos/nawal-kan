package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO;

/**
 * Contact service interface provides two operations
 *
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 * User: Joel Ajucum
 * Date: 7/8/2021
 */
public interface ContactService {

    /**
     * @return a list of existing contacts wrapped
     * in by the class {@link com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO}
     */
    ContactListDTO getAllContacts();

    /**
     * @param contactDTO expects a contact DTO
     * @return ContactDTO returns a contact DTO but this same already saved
     * in by the class {@link com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO}
     */
    ContactDTO createNewContact(ContactDTO contactDTO);
}
