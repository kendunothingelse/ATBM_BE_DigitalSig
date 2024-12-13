package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements GenericDAO<User> {
    private final MongoCollection<Document> userCollection;

    public UserDAO() {
        this.userCollection = Connection.getDatabase("test").getCollection("users");
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        for (Document doc : userCollection.find()) {
            users.add(documentToUser(doc));
        }
        return users;
    }

    @Override
    public User getById(String id) {
        Document doc = userCollection.find(Filters.eq("_id", new ObjectId(id))).first();
        return doc != null ? documentToUser(doc) : null;
    }

    @Override
    public void insert(User user) {
        Document doc = new Document("fullName", user.getFullName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("token", user.getToken())
                .append("__v", user.get__v());
        userCollection.insertOne(doc);
    }

    @Override
    public void update(String id, User user) {
        Document updatedDoc = new Document("fullName", user.getFullName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("token", user.getToken())
                .append("__v", user.get__v());
        userCollection.updateOne(Filters.eq("_id", new ObjectId(id)), new Document("$set", updatedDoc));
    }

    @Override
    public void delete(String id) {
        userCollection.deleteOne(Filters.eq("_id", new ObjectId(id)));

    }

    private User documentToUser(Document doc) {
        User user = new User();
        user.setId(doc.getObjectId("_id"));
        user.setFullName(doc.getString("fullName"));
        user.setEmail(doc.getString("email"));
        user.setPassword(doc.getString("password"));
        user.setToken(doc.getString("token"));
        user.set__v(doc.getInteger("__v"));
        return user;
    }
}
