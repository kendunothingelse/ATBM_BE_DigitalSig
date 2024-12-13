package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Message;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDAO implements GenericDAO<Message> {
    private final MongoCollection<Document> messCollection;

    public MessageDAO() {
        this.messCollection = Connection.getDatabase("test").getCollection("messages");
    }
    @Override
    public List<Message> getAll() {
        List<Message> m = new ArrayList<>();
        for (Document doc : messCollection.find()) {
            m.add(documentToMessage(doc));
        }
        return m;
    }

    @Override
    public Message getById(String id) {
        Document doc = messCollection.find(new Document("_id", id)).first();
        return doc != null ? documentToMessage(doc) : null;
    }

    @Override
    public void insert(Message entity) {
    Document doc = new Document("conversationId", entity.getConversationId())
            .append("senderId", entity.getSenderId())
            .append("message", entity.getMessage())
            .append("__v", entity.get__v());
    messCollection.insertOne(doc);
    }

    @Override
    public void update(String id, Message entity) {
        Document doc = new Document("conversationId", entity.getConversationId())
                .append("senderId", entity.getSenderId())
                .append("message", entity.getMessage())
                .append("__v", entity.get__v());
        messCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
        messCollection.deleteOne(Filters.eq("_id", id));
    }

    private Message documentToMessage(Document doc) {
        Message message = new Message();
        message.setId(doc.getObjectId("_id"));
        message.setConversationId(doc.getString("conversationId"));
        message.setSenderId(doc.getString("senderId"));
        message.setMessage(doc.getString("message"));
        message.set__v(doc.getInteger("__v"));
        return message;
    }
}
