package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Admin;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminDAO implements GenericDAO<Admin> {
    private final MongoCollection<Document> adminCollection;

    public AdminDAO() {
        this.adminCollection = Connection.getDatabase("test").getCollection("admins");
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        for (Document doc : adminCollection.find()) {
            admins.add(documentToAdmin(doc));
        }
        return admins;
    }

    @Override
    public Admin getById(String id) {
        Document doc = adminCollection.find(Filters.eq("_id", new ObjectId(id))).first();
        return doc != null ? documentToAdmin(doc) : null;
    }

    @Override
    public void insert(Admin entity) {
        Document doc = new Document("fullName", entity.getFullName())
                .append("email", entity.getEmail())
                .append("password", entity.getPassword())
                .append("token", entity.getToken())
                .append("__v", entity.get__v());
        adminCollection.insertOne(doc);

    }

    @Override
    public void update(String id, Admin entity) {
        Document doc = new Document("fullName", entity.getFullName())
                .append("email", entity.getEmail())
                .append("password", entity.getPassword())
                .append("token", entity.getToken())
                .append("__v", entity.get__v());
        adminCollection.updateOne(Filters.eq("_id", new ObjectId(id)), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
    adminCollection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    private Admin documentToAdmin(Document doc) {
        Admin admin = new Admin();
        admin.setId(doc.getObjectId("_id"));
        admin.setFullName(doc.getString("fullName"));
        admin.setEmail(doc.getString("email"));
        admin.setPassword(doc.getString("password"));
        admin.setToken(doc.getString("token"));
        admin.set__v(doc.getInteger("__v"));
        return admin;
    }
}
