package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ChatListDTO;
import com.joelgtsantos.nawalkan.controllers.ChatController;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.services.ChatService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.joelgtsantos.nawalkan.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.controllers.ChatController}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers.v1
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@WebMvcTest(controllers = {ChatController.class})
public class ChatControllerTest {

    @MockBean
    ChatService chatService;

    @InjectMocks
    ChatController chatController;

    @Autowired
    MockMvc mockMvc;

    /**
     * This test verifies the {@link com.joelgtsantos.nawalkan.controllers.ChatController#getAllPreviousChats(Long)}
     * Based on the ChatID expects the exact number of messages a user has
     *
     * @throws Exception
     */
    @Test
    public void testListChats() throws Exception {

        //given

        //Chat
        Chat chat = new Chat();
        chat.setId(1L);
        chat.setTitle("New chat");

        //Messages
        Message message1 = new Message();
        message1.setChat(chat);

        Message message2 = new Message();
        message2.setChat(chat);

        List<Message> messages = Arrays.asList(message1, message2);

        //ChatDTO
        ChatDTO chatDTO = new ChatDTO("", messages, ChatController.BASE_URL+"/1");
        ChatListDTO chatListDTO = new ChatListDTO(Arrays.asList(chatDTO));

        when(chatService.getAllChatsByContactId(1L)).thenReturn(chatListDTO);

        mockMvc.perform(get(ChatController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chats", hasSize(1)));
    }

    /**
     * This test verifies the {@link com.joelgtsantos.nawalkan.controllers.ChatController#createNewChat(ChatDTO)} (Long)}
     * expects ChatDTO previously saved in the system
     *
     * @throws Exception
     */
    @Test
    public void createNewChat() throws Exception{
        ChatDTO chatDTO_1 = new ChatDTO("new chat", new ArrayList<Message>(),ChatController.BASE_URL+"/1" );

        given(chatService.createNewChat(chatDTO_1)).willReturn(chatDTO_1);

        mockMvc.perform(post(ChatController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(chatDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo(chatDTO_1.getTitle())));
    }

}
