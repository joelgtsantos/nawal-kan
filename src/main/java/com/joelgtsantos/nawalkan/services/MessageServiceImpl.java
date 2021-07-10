package com.joelgtsantos.nawalkan.services;

import com.joelgtsantos.nawalkan.api.v1.mapper.MessageMapper;
import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.controllers.v1.MessageController;
import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Contact;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.domain.Placeholder;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import com.joelgtsantos.nawalkan.repositories.MessageRepository;
import com.joelgtsantos.nawalkan.repositories.PlaceholderRepository;
import org.apache.commons.beanutils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Implementation of the {@link MessageService} interface
 * It's autowire by its annotation as Service
 *
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
    private final PlaceholderRepository placeholderRepository;
    private final String NEW_MESSAGE = "new";

    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository, ContactRepository contactRepository, ChatRepository chatRepository, PlaceholderRepository placeholderRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
        this.contactRepository = contactRepository;
        this.chatRepository = chatRepository;
        this.placeholderRepository = placeholderRepository;
    }

    /**
     * This method allows to save(send) the entire MessageDTO
     * @param messageDTO expects a MessageDTO with the ChatID where the message belongs to
     * @return
     */
    @Override
    public MessageDTO sendNewMessage(MessageDTO messageDTO){
        log.info("Sending message");
        if(!chatRepository.existsById(messageDTO.getChatId())){
            throw new ResourceNotFoundException("The chat does not exist yet");
        }

        Chat chat = chatRepository.getById(messageDTO.getChatId());

        //Apply placeholders
        MessageDTO newMessageDTO = applyPlaceholders(messageDTO);

        Message message = messageMapper.messageDTOtoMessage(newMessageDTO);
        message.setChat(chat);
        return saveAndReturnDTO(message);
    }

    /**
     * This method applies the placeholders configured in the Entity
     * It has two types of placeholders the fist one is directly a Key-Value configuration
     * The second one has in the {@link Placeholder#getPropertyValue()} the word 'contact-data'
     * so that the functions knows this corresponds to one of Contact's attributes
     *
     * @param messageDTO
     * @return
     */
    @Override
    public MessageDTO applyPlaceholders(MessageDTO messageDTO){
        // Populates first the Contact information in case is needed
        Contact contact = contactRepository.getById(messageDTO.getToContactId());

        if(placeholderRepository.findAll().size() > 0) {
            log.info("Get placeholders");

            placeholderRepository
                    .findAll()
                    .stream()
                    .map(placeholder -> {
                        if(placeholder.getStatus().contentEquals("active")) {
                            /**
                             * Decides based on the type of placeholder where to get the value from
                             * if the PropertyValue is 'contact_attributes' gets the value from the Contact entity
                             * by the PropertyName specified
                             * else it gets the value directly from PropertyValue
                             */
                            if(placeholder.getPropertyValue().contentEquals("contact_attributes")){
                                String property = placeholder.getPropertyName();

                                // Dynamically gets the property value from the Contact
                                try {
                                    property = PropertyUtils.getProperty(contact, placeholder.getPropertyName()).toString();
                                } catch (Exception e) {
                                    log.error("Error trying to access property " + e.getMessage());
                                }

                                messageDTO.setMessage(messageDTO.getMessage().replaceAll("\\$\\{" + placeholder.getPropertyName() + "\\}", property));
                            }else {
                                messageDTO.setMessage(messageDTO.getMessage().replaceAll("\\$\\{" + placeholder.getPropertyName() + "\\}", placeholder.getPropertyValue()));
                            }
                        }
                        return messageDTO;
                    }).collect(Collectors.toList());
        }

        log.info(messageDTO.getMessage());

        return messageDTO;
    }

    /**
     * This method saves the Message into the system
     * @param message entity that is going to be save with the placeholders already applied
     * @return
     */
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
