package com.example.main.myproject.dao.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Conversation {
    private ObjectId id;
    private List<ObjectId> members;
    private int __v;

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<ObjectId> getMembers() {
        return members;
    }

    public void setMembers(List<ObjectId> members) {
        this.members = members;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", members=" + members +
                ", __v=" + __v +
                '}';
    }
}

