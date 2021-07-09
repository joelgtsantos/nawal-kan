package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.ChatMapper;
import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ChatListDTO;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.services.ChatServiceImpl}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@ExtendWith(MockitoExtension.class)
public class ChatServiceImplTest {

    private final String CHAT_NAME = "new chat";
    private final Long CHAT_ID = 1L;

    @Mock
    ChatRepository chatRepository;

    ChatService chatService;


    @BeforeEach
    public void setUp() {
        chatService = new ChatServiceImpl(ChatMapper.INSTANCE, chatRepository);
    }

    /**
     * This test verifies {@link com.joelgtsantos.nawalkan.services.ChatServiceImpl#getAllChatsByContactId(Long)}
     * that the number of previous conversations from a user
     * matches what the services returns
     *
     * @throws Exception
     */
    @Test
    public void getAllChatsByContactId() throws Exception {
        //given
        Message message = new Message();
        message.setMessage("new message");
        message.setFromContactId(1L);
        message.setToContactId(1L);

        Chat chat1 = new Chat();
        chat1.setId(CHAT_ID);
        chat1.setTitle(CHAT_NAME);
        chat1.setMessages(new ArrayList<>(Arrays.asList(message)));

        when(chatRepository.findByFromContact(1L)).thenReturn(Arrays.asList(chat1));

        //when
        ChatListDTO chatListDTO = chatService.getAllChatsByContactId(1L);

        //then
        assertEquals(1, chatListDTO.getChats().size());
    }

    /**
     * This test verifies {@link com.joelgtsantos.nawalkan.services.ChatServiceImpl#createNewChat(ChatDTO)}
     * and the ChatDTO is saved into the system
     *
     * @throws Exception
     */
    @Test
    public void createNewChat() throws Exception {
        //given
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setTitle(CHAT_NAME);

        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        chat.setTitle(CHAT_NAME);

        given(chatRepository.save(any(Chat.class))).willReturn(chat);

        //when
        ChatDTO savedChatDTO = chatService.createNewChat(chatDTO);

        //then
        then(chatRepository).should().save(any(Chat.class));
        assertThat(savedChatDTO.getChatUrl(), containsString("1"));

    }
}