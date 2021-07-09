package com.joelgtsantos.nawalkan.controllers;

import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO;
import com.joelgtsantos.nawalkan.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The Contact Controller exposes two endpoints
 * GET for retrieving the list of all contacts
 * POST for creating a new contact into the system
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers
 *
 * @author: Joel Ajucum
 * @since: 7/8/2021
 * @version: 0.1
 */


@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
    public static final String BASE_URL = "/api/v1/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContactListDTO getContactList(){
        return contactService.getAllContacts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createNewContact(@RequestBody ContactDTO contactDTO){
        return contactService.createNewContact(contactDTO);
    }

}
