package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.MessageMapper;
import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.domain.Placeholder;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import com.joelgtsantos.nawalkan.repositories.MessageRepository;
import com.joelgtsantos.nawalkan.repositories.PlaceholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.services.MessageServiceImpl}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {
    private final String CHAT_NAME = "new chat";
    private final String MESSAGE = "${Bitcoin}";
    private final String BITCOIN_VALUE = "123";
    private final Long CHAT_ID = 1L;

    @Mock
    MessageRepository messageRepository;

    @Mock
    ContactRepository contactRepository;

    @Mock
    ChatRepository chatRepository;

    @Mock
    PlaceholderRepository placeholderRepository;

    MessageService messageService;


    @BeforeEach
    public void setUp() {
        setupPlaceholder();
        messageService = new MessageServiceImpl(MessageMapper.INSTANCE, messageRepository, contactRepository, chatRepository, placeholderRepository);

    }

    /**
     * This test verifies that Message content is being replace with the right placeholders
     * in addition checks the message is completely saved in the system
     *
     * @throws Exception
     */
    @Test
    public void createNewMessage() throws Exception {
        //given
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setTitle(CHAT_NAME);

        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        chat.setTitle(CHAT_NAME);

        Message message = new Message();
        message.setChat(chat);
        message.setId(CHAT_ID);
        message.setMessage(MESSAGE);
        message.setToContactId(CHAT_ID);
        message.setFromContactId(CHAT_ID);

        MessageDTO messageDTO = MessageMapper.INSTANCE.messageToMessageDTO(message);

        given(messageRepository.save(any(Message.class))).willReturn(message);

        MessageDTO savedMessageDTO = messageService.applyPlaceholders(messageDTO);
        assertEquals(savedMessageDTO.getMessage(), BITCOIN_VALUE);

        //when
        savedMessageDTO = messageService.sendNewMessage(messageDTO);

        //then
        // 'should' defaults to times = 1
        then(messageRepository).should().save(any(Message.class));
        assertThat(savedMessageDTO.getMessageUrl(), containsString("1"));
    }

    private void setupPlaceholder(){
        Placeholder placeholder = new Placeholder();
        placeholder.setId(1L);
        placeholder.setPropertyName("Bitcoin");
        placeholder.setPropertyValue(BITCOIN_VALUE);
        placeholder.setStatus("active");


        doReturn(Arrays.asList(placeholder)).when(placeholderRepository).findAll();
    }
}
