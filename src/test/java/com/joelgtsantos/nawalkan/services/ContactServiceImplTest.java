package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.ContactMapper;
import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO;
import com.joelgtsantos.nawalkan.domain.Contact;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.services.ContactServiceImpl}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    public static final String NAME_1 = "John Smith";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "Juan Perez";
    public static final long ID_2 = 1L;

    @Mock
    ContactRepository contactRepository;

    ContactService contactService;


    @BeforeEach
    public void setUp() {
        contactService = new ContactServiceImpl(ContactMapper.INSTANCE, contactRepository);
    }

    /**
     * This test verifies the service is retrieving the exact
     * number of records saved in the system
     * @throws Exception
     */
    @Test
    public void getAllContacts() throws Exception {
        //given
        List<Contact> contacts = Arrays.asList(getContact1(), getContact2());
        //ABC
        given(contactRepository.findAll()).willReturn(contacts);

        //when
        ContactListDTO contactListDTO = contactService.getAllContacts();

        //then
        then(contactRepository).should(times(1)).findAll();
        assertThat(contactListDTO.getContacts().size(), is(equalTo(2)));
    }

    /**
     * This test is mocking the repository and verify when calling the Save method
     * the entity is persisted in the system
     * @throws Exception
     */
    @Test
    public void createNewContact() throws Exception {
        //given
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(NAME_1);

        Contact contact = getContact1();

        given(contactRepository.save(any(Contact.class))).willReturn(contact);

        //when
        ContactDTO savedVendorDTO = contactService.createNewContact(contactDTO);

        //then
        // 'should' defaults to times = 1
        then(contactRepository).should().save(any(Contact.class));
        assertThat(savedVendorDTO.getContactUrl(), containsString("1"));

    }

    private Contact getContact1() {
        Contact contact = new Contact();
        contact.setName(NAME_1);
        contact.setId(ID_1);
        return contact;
    }

    private Contact getContact2() {
        Contact contact = new Contact();
        contact.setName(NAME_2);
        contact.setId(ID_2);
        return contact;
    }
}
