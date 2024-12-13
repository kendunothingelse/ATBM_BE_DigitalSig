package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.User;
import com.example.main.myproject.service.UserDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users ")
public class UserController {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userDAO.getById(id);
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        userDAO.insert(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        userDAO.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userDAO.delete(id);
    }


}
