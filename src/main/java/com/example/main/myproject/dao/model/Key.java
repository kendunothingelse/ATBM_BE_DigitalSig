package com.example.main.myproject.dao.model;

import org.bson.types.ObjectId;

import java.util.Date;

public class Key {
    private ObjectId id;
    private String userId; // Liên kết với User
    private String publicKey;
    private Date createTime;
    private Date endTime;

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                '}';
    }
}
