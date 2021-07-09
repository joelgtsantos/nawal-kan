package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.ContactMapper;
import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO;
import com.joelgtsantos.nawalkan.controllers.ContactController;
import com.joelgtsantos.nawalkan.domain.Contact;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ContactService} interface
 * It's autowire by its annotation as Service
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */

@Service
public class ContactServiceImpl implements ContactService{

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactMapper contactMapper, ContactRepository contactRepository){
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactListDTO getAllContacts() {
        List<ContactDTO> contactDTOS = contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                    ContactDTO contactDTO = contactMapper.contactToContactDTO(contact);
                    contactDTO.setContactUrl(getContactUrl(contact.getId()));
                    return contactDTO;
                })
                .collect(Collectors.toList());

        return new ContactListDTO(contactDTOS);
    }

    @Override
    public ContactDTO createNewContact(ContactDTO contactDTO) {
        return saveAndReturnDTO(contactMapper.contactDTOtoContact(contactDTO));
    }

    /**
     * This method persists a {@link com.joelgtsantos.nawalkan.domain.Contact} first then
     * it will convert the result to a {@link com.joelgtsantos.nawalkan.api.v1.model.ContactDTO}
     * @param contact this is the JPA entity that is going to be persisted
     * @return it will return a save ContactDTO
     */
    private ContactDTO saveAndReturnDTO(Contact contact){
        Contact savedContact = contactRepository.save(contact);

        ContactDTO returnContactDTO = contactMapper.contactToContactDTO(savedContact);

        returnContactDTO.setContactUrl(getContactUrl(savedContact.getId()));
        return returnContactDTO;
    }

    private String getContactUrl(Long id) {
        return ContactController.BASE_URL + "/" + id;
    }
}
