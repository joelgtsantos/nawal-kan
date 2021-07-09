package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.domain.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.api.v1.mapper.ContactMapper}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
public class ContactMapperTest {

    public static final String NAME = "Jhon Smith";
    public static final String EMAIL = "jsmith@gmail.com";
    public static final String TELEPHONE = "502 2030 4050";
    public static final Integer AGE = 20;
    ContactMapper contactMapper = ContactMapper.INSTANCE;

    /**
     * This test verifies the exact conversion of DTO to an Entity
     * @throws Exception
     */
    @Test
    public void contactToContactDTO() throws Exception {
        //given
        Contact contact = new Contact();
        contact.setName(NAME);
        contact.setEmail(EMAIL);
        contact.setTelephone(TELEPHONE);
        contact.setAge(AGE);

        //when
        ContactDTO contactDTO = contactMapper.contactToContactDTO(contact);

        //then
        assertEquals(NAME, contactDTO.getName());
        assertEquals(EMAIL, contactDTO.getEmail());
        assertEquals(TELEPHONE, contactDTO.getTelephone());
        assertEquals(AGE, contactDTO.getAge());

    }
}

