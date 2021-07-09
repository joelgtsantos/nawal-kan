package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.domain.Chat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.api.v1.mapper.ChatMapper}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

public class ChatMapperTest {
    public static final String TITLE = "Jhon Smith";
    ChatMapper chatMapper = ChatMapper.INSTANCE;

    /**
     * This test verifies the exact conversion of DTO to an Entity
     *
     * @throws Exception
     */
    @Test
    public void chatToChatDTO() throws Exception {
        //given
        Chat chat = new Chat();
        chat.setTitle(TITLE);

        //when
        ChatDTO chatDTO = chatMapper.chatToChatDTO(chat);

        //then
        assertEquals(TITLE, chatDTO.getTitle());
    }
}
