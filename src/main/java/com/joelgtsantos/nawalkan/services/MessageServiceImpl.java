package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.MessageMapper;
import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.controllers.MessageController;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import com.joelgtsantos.nawalkan.repositories.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.services
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final ContactRepository contactRepository;
    private final ChatRepository chatRepository;
    private final String NEW_MESSAGE = "new";

    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository, ContactRepository contactRepository, ChatRepository chatRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
        this.contactRepository = contactRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public MessageDTO sendNewMessage(MessageDTO messageDTO) {
        log.info("Sending message");
        Chat chat = chatRepository.getById(messageDTO.getChatId());
        Message message = messageMapper.messageDTOtoMessage(messageDTO);
        message.setChat(chat);
        return saveAndReturnDTO(message);
    }



    private MessageDTO saveAndReturnDTO(Message message){
        message.setStatus(NEW_MESSAGE);
        Message savedMessage = messageRepository.save(message);
        MessageDTO returnMessageDTO = messageMapper.messageToMessageDTO(savedMessage);
        //Set ChatID
        returnMessageDTO.setChatId(savedMessage.getChat().getId());
        returnMessageDTO.setMessageUrl(getMessageUrl(savedMessage.getId()));
        return returnMessageDTO;
    }

    private String getMessageUrl(Long id) {
        return MessageController.BASE_URL + "/" + id;
    }
}
