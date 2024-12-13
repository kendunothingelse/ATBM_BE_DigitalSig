package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Order;
import com.example.main.myproject.service.OrderDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderDAO.getById(id);
    }

    @PostMapping
    public void insertOrder(@RequestBody Order order) {
        orderDAO.insert(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable String id, @RequestBody Order order) {
        orderDAO.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderDAO.delete(id);
    }
}
