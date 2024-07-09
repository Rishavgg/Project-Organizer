package com.rishav.controller;

import com.rishav.model.Chat;
import com.rishav.model.Message;
import com.rishav.model.User;
import com.rishav.request.CreateMessageRequest;
import com.rishav.service.MessageService;
import com.rishav.service.ProjectService;
import com.rishav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request
    ) throws Exception {
        User user = userService.findUserById(request.getSenderId());

        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if (chat == null) {
            throw new Exception("Chats not found");
        }

        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(
            @PathVariable Long projectId
    ) throws Exception {
        List<Message> messageList = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messageList);
    }

}
