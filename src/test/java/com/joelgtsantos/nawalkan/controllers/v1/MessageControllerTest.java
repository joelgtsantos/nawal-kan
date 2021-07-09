package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.controllers.ChatController;
import com.joelgtsantos.nawalkan.controllers.MessageController;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.joelgtsantos.nawalkan.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  Unit test for {@link com.joelgtsantos.nawalkan.controllers.MessageController}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers.v1
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@WebMvcTest(controllers = {MessageController.class})
public class MessageControllerTest {

    @MockBean
    MessageService messageService;

    @Autowired
    MockMvc mockMvc;

    MessageDTO messageDTO_1;
    Chat chat;


    @BeforeEach
    public void setUp() throws Exception {
        //Chat
        chat = new Chat();
        chat.setTitle("New chat");

        //Message
        messageDTO_1 = new MessageDTO(1L, 1L, "hello", "", 1L,  ChatController.BASE_URL + "/1");
    }

    /**
     * This test verifies the endpoints retrieves the exact number of records
     * saved in the system when calling it
     * @throws Exception
     */
    @Test
    public void createNewMessage() throws Exception{
        given(messageService.sendNewMessage(messageDTO_1)).willReturn(messageDTO_1);

        mockMvc.perform(post(MessageController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(messageDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", equalTo(messageDTO_1.getMessage())));
    }
}
