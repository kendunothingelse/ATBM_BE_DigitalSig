package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Admin;
import com.example.main.myproject.service.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminDAO adminDAO;

    @Autowired
    public AdminController(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminDAO.getAll();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return adminDAO.getById(id);
    }

    @PostMapping
    public void insertAdmin(@RequestBody Admin admin) {
        adminDAO.insert(admin);
    }

    @PutMapping("/{id}")
    public void updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        adminDAO.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminDAO.delete(id);
    }
}
