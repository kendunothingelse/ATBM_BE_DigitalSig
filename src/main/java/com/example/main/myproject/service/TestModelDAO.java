package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.*;

import java.util.List;


public class TestModelDAO {
    public static void main(String[] args) {
//        //User
//        UserDAO userDAO = new UserDAO();
//        List<User> users = userDAO.getAll();
//        System.out.println("All Users:");
//        for (User user : users) {
//            System.out.println(user);
//        }
//        //Admin
//        AdminDAO adminDAO = new AdminDAO();
//        List<Admin> admins = adminDAO.getAll();
//        System.out.println("All Admins:");
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }
//        //Conversation
//        ConversationDAO conversationDAO = new ConversationDAO();
//        List<Conversation> conversations = conversationDAO.getAll();
//        System.out.println("All Conversations:");
//        for (Conversation conversation : conversations) {
//            System.out.println(conversation);
//        }
//        //Message
//        MessageDAO messageDAO = new MessageDAO();
//        List<Message> messages = messageDAO.getAll();
//        System.out.println("All Messages:");
//        for (Message message : messages) {
//            System.out.println(message);
//        }
//        //Orders
//        OrderDAO orderDAO = new OrderDAO();
//        List<Order> orders = orderDAO.getAll();
//        System.out.println("All Orders:");
//        for (Order order : orders) {
//            System.out.println(order);
//        }
        //Product
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAll();
        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
        }
        //close connection
        Connection.closeConnection();

    }
}
