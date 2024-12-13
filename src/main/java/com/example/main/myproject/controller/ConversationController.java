package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Conversation;
import com.example.main.myproject.service.ConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    private final ConversationDAO conversationDAO;

    @Autowired
    public ConversationController(ConversationDAO conversationDAO) {
        this.conversationDAO = conversationDAO;
    }

    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationDAO.getAll();
    }

    @GetMapping("/{id}")
    public Conversation getConversationById(@PathVariable String id) {
        return conversationDAO.getById(id);
    }

    @PostMapping
    public void insertConversation(@RequestBody Conversation conversation) {
        conversationDAO.insert(conversation);
    }

    @PutMapping("/{id}")
    public void updateConversation(@PathVariable String id, @RequestBody Conversation conversation) {
        conversationDAO.update(id, conversation);
    }

    @DeleteMapping("/{id}")
    public void deleteConversation(@PathVariable String id) {
        conversationDAO.delete(id);
    }
}
