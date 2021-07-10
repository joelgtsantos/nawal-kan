package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.api.v1.model.ContactDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ContactListDTO;
import com.joelgtsantos.nawalkan.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.joelgtsantos.nawalkan.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for {@link ContactController}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers.v1
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@WebMvcTest(controllers = {ContactController.class})
public class ContactControllerTest {

    @MockBean
    ContactService contactService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    ContactDTO contactDTO_1;
    ContactDTO contactDTO_2;

    @BeforeEach
    public void setUp() throws Exception {
        contactDTO_1 = new ContactDTO("Contact 1", "email","2", 20, ContactController.BASE_URL + "/1");
        contactDTO_2 = new ContactDTO("Contact 2", "email", "3", 30, ContactController.BASE_URL + "/2");
    }

    /**
     * This test verifies the endpoints retrieves the exact number of records
     * saved in the system when calling it
     * @throws Exception
     */
    @Test
    public void getContactList() throws Exception {
        ContactListDTO contactListDTO = new ContactListDTO(Arrays.asList(contactDTO_1, contactDTO_2));

        given(contactService.getAllContacts()).willReturn(contactListDTO);

        mockMvc.perform(get(ContactController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts", hasSize(2)));
    }

    /**
     * This test verifies the correct response of the controller when creating a new Contact
     * @throws Exception
     */
    @Test
    public void createNewContact() throws Exception{
        given(contactService.createNewContact(contactDTO_1)).willReturn(contactDTO_1);

        mockMvc.perform(post(ContactController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contactDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(contactDTO_1.getName())));
    }

}
