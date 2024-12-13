package com.example.main.myproject;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBTest {
    public static void main(String[] args) {
        // Thay URI kết nối của bạn tại đây
        String mongoUri = "mongodb+srv://DawgCheeseShop:khoa123456@dawgcheeseshop.ciepk.mongodb.net/";

        // Tạo kết nối MongoDB
        try (MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .build())) {

            // Lấy tất cả database trong MongoDB
            MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();

            System.out.println("Danh sách Collection trong MongoDB Atlas:");
            for (String dbName : databaseNames) {
                System.out.println("Database: " + dbName);
                MongoDatabase database = mongoClient.getDatabase(dbName);

                // Liệt kê các collection trong database
                MongoIterable<String> collections = database.listCollectionNames();
                for (String collectionName : collections) {
                    System.out.println("  - Collection: " + collectionName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        MongoClient client = MongoClients.create(mongoUri);
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("orders");

        // Retrieve and display all documents in the collection
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson()); // Print the document in JSON format
        }

        // Đóng kết nối
        client.close();

    }
}
