package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.api.v1.model.ChatDTO;
import com.joelgtsantos.nawalkan.api.v1.model.ChatListDTO;
import com.joelgtsantos.nawalkan.services.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *  The Chat Controller exposes two endpoints
 *  GET for retrieving the list of all Chat by Contact ID
 *  POST for creating a new chat channel into the system
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
@RestController
@RequestMapping(ChatController.BASE_URL)
public class ChatController {
    public static final String BASE_URL = "/api/v1/chats";
    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatDTO createNewChat(@RequestBody ChatDTO chatDTO){
        return chatService.createNewChat(chatDTO);
    }


    @GetMapping("/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public ChatListDTO getAllPreviousChats(@PathVariable Long contactId){
        return chatService.getAllChatsByContactId(contactId);
    }
}
