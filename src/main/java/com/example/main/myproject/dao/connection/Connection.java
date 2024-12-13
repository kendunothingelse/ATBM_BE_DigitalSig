package com.example.main.myproject.dao.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connection {
    private static final String CONNECTION_STRING = "mongodb+srv://DawgCheeseShop:khoa123456@dawgcheeseshop.ciepk.mongodb.net/";
    private static MongoClient mongoClient = null;

    private Connection() {
        // Private constructor to prevent instantiation
    }

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(dbName);
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
