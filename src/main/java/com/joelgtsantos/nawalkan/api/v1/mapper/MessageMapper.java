package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.domain.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO messageToMessageDTO(Message message);

    Message messageDTOtoMessage(MessageDTO messageDTO);
}
