package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Key;
import com.example.main.myproject.service.KeyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keys")
public class KeyController {
    private final KeyDAO keyDAO;

    @Autowired
    public KeyController(KeyDAO keyDAO) {
        this.keyDAO = keyDAO;
    }

    @GetMapping
    public List<Key> getAllKeys() {
        return keyDAO.getAll();
    }

    @GetMapping("/{id}")
    public Key getKeyById(@PathVariable String id) {
        return keyDAO.getById(id);
    }

    @PostMapping
    public void insertKey(@RequestBody Key key) {
        keyDAO.insert(key);
    }

    @PutMapping("/{id}")
    public void updateKey(@PathVariable String id, @RequestBody Key key) {
        keyDAO.update(id, key);
    }

    @DeleteMapping("/{id}")
    public void deleteKey(@PathVariable String id) {
        keyDAO.delete(id);
    }
    

}
