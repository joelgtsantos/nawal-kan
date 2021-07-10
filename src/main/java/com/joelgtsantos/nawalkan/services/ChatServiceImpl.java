package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.ChatMapper;
import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ChatListDTO;
import com.joelgtsantos.nawalkan.controllers.v1.ChatController;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ChatService implementation
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@Service
public class ChatServiceImpl implements ChatService{

    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatMapper chatMapper, ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
    }

    @Override
    public ChatDTO createNewChat(ChatDTO chatDTO) {
        return saveAndReturnDTO(chatMapper.chatDTOtoChat(chatDTO));
    }

    /**
     * This function first retrieves all the chats then traverses the list to set the ChatURL
     * for each record
     *
     * @param fromId the Contact ID
     * @return
     */
    @Override
    public ChatListDTO getAllChatsByContactId(Long fromId) {
        List<ChatDTO> chatDTOS = chatRepository
                .findByFromContact(fromId)
                .stream()
                .map(chat -> {
                    ChatDTO chatDTO = chatMapper.chatToChatDTO(chat);
                    chatDTO.setChatUrl(getChatUrl(fromId));

                    return chatDTO;
                })
                .collect(Collectors.toList());

        return new ChatListDTO(chatDTOS);
    }

    /**
     * This method persists a {@link com.joelgtsantos.nawalkan.domain.Chat} first then
     * it will convert the result to a {@link com.joelgtsantos.nawalkan.api.v1.model.ContactDTO}
     * @param chat this the JPA entity that is going to be persisted
     * @return it will return a save ContactDTO
     */
    private ChatDTO saveAndReturnDTO(Chat chat){
        Chat savedChat = chatRepository.save(chat);

        ChatDTO returnChatDTO = chatMapper.chatToChatDTO(savedChat);

        returnChatDTO.setChatUrl(getChatUrl(savedChat.getId()));
        return returnChatDTO;
    }

    private String getChatUrl(Long id) {
        return ChatController.BASE_URL + "/" + id;
    }
}