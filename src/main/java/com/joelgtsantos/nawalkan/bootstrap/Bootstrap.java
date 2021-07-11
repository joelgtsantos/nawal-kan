package com.joelgtsantos.nawalkan.bootstrap;

import com.joelgtsantos.nawalkan.domain.Chat;
import com.joelgtsantos.nawalkan.domain.Contact;
import com.joelgtsantos.nawalkan.domain.Message;
import com.joelgtsantos.nawalkan.domain.Placeholder;
import com.joelgtsantos.nawalkan.repositories.ChatRepository;
import com.joelgtsantos.nawalkan.repositories.ContactRepository;
import com.joelgtsantos.nawalkan.repositories.MessageRepository;
import com.joelgtsantos.nawalkan.repositories.PlaceholderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.bootstrap
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final ChatRepository chatRepository;
    private final ContactRepository contactRepository;
    private final MessageRepository messageRepository;
    private final PlaceholderRepository placeholderRepository;

    public Bootstrap(ChatRepository chatRepository, ContactRepository contactRepository, MessageRepository messageRepository, PlaceholderRepository placeholderRepository) {
        this.chatRepository = chatRepository;
        this.contactRepository = contactRepository;
        this.messageRepository = messageRepository;
        this.placeholderRepository = placeholderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadContacts();
        loadChats();
        loadPlaceholders();
    }

    private void loadContacts(){
        Contact contact1 = new Contact();
        contact1.setId(1L);
        contact1.setName("Paul Schmidt");
        contact1.setEmail("pschmidt@gmail.com");
        contact1.setTelephone("502 4050 6070");
        contact1.setAge(20);

        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setId(2L);
        contact2.setName("Juan Perez");
        contact2.setEmail("jperez@gmail.com");
        contact2.setTelephone("502 4020 3050");
        contact2.setAge(30);

        contactRepository.save(contact2);

    }

    private void loadChats(){
        Chat chat = new Chat();
        chat.setId(1L);
        chat.setTitle("Test new chat");

        chatRepository.save(chat);

        Chat parent = chatRepository.getById(chat.getId());

        Message message1 = new Message();
        message1.setId(1L);
        message1.setMessage("Message 1");
        message1.setStatus("new");
        message1.setFromContactId(1L);
        message1.setToContactId(2L);
        message1.setChat(parent);
        messageRepository.save(message1);

        Chat parent2 = chatRepository.getById(chat.getId());

        Message message2 = new Message();
        message2.setId(2L);
        message2.setMessage("Message 2");
        message2.setStatus("new");
        message2.setFromContactId(1L);
        message2.setToContactId(2L);
        message1.setChat(parent2);

        messageRepository.save(message2);
    }

    private void loadPlaceholders(){
        Placeholder placeholder1 = new Placeholder();
        placeholder1.setId(1L);
        placeholder1.setPropertyName("bitcoin");
        placeholder1.setPropertyValue("$ 33,779.50");
        placeholder1.setStatus("active");

        placeholderRepository.save(placeholder1);

        Placeholder placeholder2 = new Placeholder();
        placeholder2.setId(2L);
        placeholder2.setPropertyName("name");
        placeholder2.setPropertyValue("contact_attributes");
        placeholder2.setStatus("active");

        placeholderRepository.save(placeholder2);
    }

}
