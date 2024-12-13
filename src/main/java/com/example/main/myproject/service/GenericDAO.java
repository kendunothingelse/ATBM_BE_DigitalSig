package com.example.main.myproject.service;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll();

    T getById(String id);

    //CRUD Operations
    void insert(T entity);

    void update(String id, T entity);

    void delete(String id);
}
