package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Order;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAO implements GenericDAO<Order>{
    private final MongoCollection<Document> orderCollection;

    public OrderDAO() {
        this.orderCollection = Connection.getDatabase("test").getCollection("orders");
    }
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        for (Document doc : orderCollection.find()) {
            orders.add(documentToOrder(doc));
        }
        return orders;
    }



    @Override
    public Order getById(String id) {
        Document doc = orderCollection.find(new Document("_id", id)).first();
        return doc != null ? documentToOrder(doc) : null;
    }

    @Override
    public void insert(Order entity) {
        Document doc = new Document("userId", entity.getUserId())
                .append("items", entity.getItems())
                .append("totalPrice", entity.getTotalPrice())
                .append("address", entity.getAddress())
                .append("customerName", entity.getCustomerName())
                .append("customerPhone", entity.getCustomerPhone())
                .append("status", entity.getStatus())
                .append("createdAt", entity.getCreatedAt())
                .append("__v", entity.get__v());
        orderCollection.insertOne(doc);

    }

    @Override
    public void update(String id, Order entity) {
        Document doc = new Document("userId", entity.getUserId())
                .append("items", entity.getItems())
                .append("totalPrice", entity.getTotalPrice())
                .append("address", entity.getAddress())
                .append("customerName", entity.getCustomerName())
                .append("customerPhone", entity.getCustomerPhone())
                .append("status", entity.getStatus())
                .append("createdAt", entity.getCreatedAt())
                .append("__v", entity.get__v());
        orderCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
        orderCollection.deleteOne(Filters.eq("_id", id));
    }
    private Order documentToOrder(Document doc) {
        Order order = new Order();
        order.setId(doc.getObjectId("_id"));
        order.setUserId(doc.getObjectId("userId"));
        order.setItems((List) doc.get("items"));
        order.setTotalPrice(doc.getInteger("totalPrice"));
        order.setAddress(doc.getString("address"));
        order.setCustomerName(doc.getString("customerName"));
        order.setCustomerPhone(doc.getString("customerPhone"));
        order.setStatus(doc.getString("status"));
        order.setCreatedAt(doc.getDate("createdAt"));
        order.set__v(doc.getInteger("__v"));
        return order;

    }
}
