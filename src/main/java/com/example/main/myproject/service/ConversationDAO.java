package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Conversation;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConversationDAO implements GenericDAO<Conversation> {
    private final MongoCollection<Document> conversationCollection;

    public ConversationDAO() {
        this.conversationCollection = Connection.getDatabase("test").getCollection("conversations");
    }

    @Override
    public List<Conversation> getAll() {
        List<Conversation> c = new ArrayList<>();
        for (Document doc : conversationCollection.find()) {
            c.add(documentToConversation(doc));
        }
        return c;
    }


    @Override
    public Conversation getById(String id) {
        Document doc = conversationCollection.find(Filters.eq("_id", new ObjectId(id))).first();
        return doc != null ? documentToConversation(doc) : null;
    }

    @Override
    public void insert(Conversation entity) {
        Document doc = new Document("members", entity.getMembers())
                .append("__v", entity.get__v());
        conversationCollection.insertOne(doc);
    }

    @Override
    public void update(String id, Conversation entity) {
        Document doc = new Document("members", entity.getMembers())
                .append("__v", entity.get__v());
        conversationCollection.updateOne(Filters.eq("_id", new ObjectId(id)), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
        conversationCollection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    private Conversation documentToConversation(Document doc) {
        Conversation conversation = new Conversation();
        conversation.setId(doc.getObjectId("_id"));
        conversation.setMembers((List) doc.get("members"));
        conversation.set__v(doc.getInteger("__v"));
        return conversation;
    }
}
