package com.example.main.myproject.service;

import com.example.main.myproject.dao.connection.Connection;
import com.example.main.myproject.dao.model.Product;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO implements GenericDAO<Product> {
    private final MongoCollection<Document> productCollection;

    public ProductDAO() {
        this.productCollection = Connection.getDatabase("test").getCollection("products");
    }
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        for (Document doc : productCollection.find()) {
            products.add(documentToProduct(doc));
        }
        return products;
    }

    @Override
    public Product getById(String id) {
        Document doc = productCollection.find(new Document("_id", id)).first();
        return doc != null ? documentToProduct(doc) : null;
    }

    @Override
    public void insert(Product entity) {
        Document doc = new Document("name", entity.getName())
                .append("price", entity.getPrice())
                .append("category", entity.getCategory())
                .append("__v", entity.get__v());
        productCollection.insertOne(doc);
    }

    @Override
    public void update(String id, Product entity) {
        Document doc = new Document("name", entity.getName())
                .append("price", entity.getPrice())
                .append("category", entity.getCategory())
                .append("__v", entity.get__v());
        productCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    @Override
    public void delete(String id) {
        productCollection.deleteOne(Filters.eq("_id", id));
    }
    private Product documentToProduct(Document doc) {
        Product product = new Product();
        product.setId(doc.getObjectId("_id"));
        product.setName(doc.getString("name"));
        product.setPrice(doc.getInteger("price"));
        product.setCategory(doc.getString("category"));
        product.set__v(doc.getInteger("__v"));
        return product;
    }
}
