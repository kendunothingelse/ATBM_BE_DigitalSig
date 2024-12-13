package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Message;
import com.example.main.myproject.service.MessageDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageDAO messageDAO;

    public MessageController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageDAO.getAll();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable String id) {
        return messageDAO.getById(id);
    }

    @PostMapping
    public void insertMessage(@RequestBody Message message) {
        messageDAO.insert(message);
    }

    @PutMapping("/{id}")
    public void updateMessage(@PathVariable String id, @RequestBody Message message) {
        messageDAO.update(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable String id) {
        messageDAO.delete(id);
    }

}
