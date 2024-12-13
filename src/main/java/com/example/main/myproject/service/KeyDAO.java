package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Key;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeyDAO implements GenericDAO<Key> {
    private final MongoCollection<Document> keyCollection;

    public KeyDAO() {
        this.keyCollection = Connection.getDatabase("test").getCollection("keys");
    }

    @Override
    public List<Key> getAll() {
        List<Key> keys = new ArrayList<>();
        for (Document doc : keyCollection.find()) {
            keys.add(documentToKey(doc));
        }
        return keys;
    }

    @Override
    public Key getById(String id) {
        Document doc = keyCollection.find(Filters.eq("_id", new ObjectId(id))).first();
        return doc != null ? documentToKey(doc) : null;
    }

    @Override
    public void insert(Key entity) {
        Document doc = new Document("userId", entity.getUserId())
                .append("publicKey", entity.getPublicKey())
                .append("createTime", entity.getCreateTime())
                .append("endTime", entity.getEndTime());
        keyCollection.insertOne(doc);
    }

    @Override
    public void update(String id, Key entity) {
        Document doc = new Document("userId", entity.getUserId())
                .append("publicKey", entity.getPublicKey())
                .append("createTime", entity.getCreateTime())
                .append("endTime", entity.getEndTime());
        keyCollection.updateOne(Filters.eq("_id", new ObjectId(id)), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
        keyCollection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    private Key documentToKey(Document doc) {
        Key key = new Key();
        key.setId(doc.getObjectId("_id"));
        key.setUserId(doc.getString("userId"));
        key.setPublicKey(doc.getString("publicKey"));
        key.setCreateTime(doc.getDate("createTime"));
        key.setEndTime(doc.getDate("endTime"));
        return key;
    }
}
