package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.domain.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The ContactMapper provides the functions to convert to ContactDTO a Contact Entity
 * and vice versa a Contact Entity to ContactDTO
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDTO(Contact contact);

    Contact contactDTOtoContact(ContactDTO contactDTO);
}
