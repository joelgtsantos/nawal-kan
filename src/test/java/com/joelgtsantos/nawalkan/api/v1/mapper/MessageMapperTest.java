package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.domain.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link com.joelgtsantos.nawalkan.api.v1.mapper.MessageMapper}
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

public class MessageMapperTest {
    public static final Long FROM = 1L;
    public static final Long TO = 1L;
    public static final String MESSAGE = "new message";
    public static final String STATUS = "new";

    MessageMapper messageMapper = MessageMapper.INSTANCE;

    /**
     * This test verifies the exact conversion of DTO to an Entity
     *
     * @throws Exception
     */
    @Test
    public void messageToMessageDTO() throws Exception {
        //given
        Message message = new Message();
        message.setFromContactId(FROM);
        message.setToContactId(TO);
        message.setMessage(MESSAGE);
        message.setStatus(STATUS);

        //when
        MessageDTO messageDTO = messageMapper.messageToMessageDTO(message);

        //then
        assertEquals(FROM, messageDTO.getFromContactId());
        assertEquals(TO, messageDTO.getToContactId());
        assertEquals(MESSAGE, messageDTO.getMessage());
        assertEquals(STATUS, messageDTO.getStatus());
    }
}
