package com.joelgtsantos.nawalkan.api.v1.mapper;

import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.domain.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The ChatMapper provides the functions to convert to ChatDTO a Chat Entity
 * and vice versa a Chat Entity to ChatDTO
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.api.v1.mapper
 * User: Joel Ajucum
 * Date: 7/9/2021
 */

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatDTO chatToChatDTO(Chat chat);

    Chat chatDTOtoChat(ChatDTO chatDTO);
}
