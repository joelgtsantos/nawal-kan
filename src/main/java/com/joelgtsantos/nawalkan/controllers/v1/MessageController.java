package com.joelgtsantos.nawalkan.controllers.v1;

import com.joelgtsantos.nawalkan.api.v1.model.MessageDTO;
import com.joelgtsantos.nawalkan.services.MessageService;
import com.joelgtsantos.nawalkan.services.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The Contact Controller exposes one operation
 * POST for creating(sending) a new message into the system
 *
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */

@RestController
@RequestMapping(MessageController.BASE_URL)
public class MessageController {
    public static final String BASE_URL = "/api/v1/messages";
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity  sendNewMessage(@RequestBody MessageDTO messageDTO) throws ResourceNotFoundException {
        MessageDTO newMessageDTO = messageService.sendNewMessage(messageDTO);
        return new ResponseEntity<>(newMessageDTO, HttpStatus.CREATED);
        //return messageService.sendNewMessage(messageDTO);
    }
}
