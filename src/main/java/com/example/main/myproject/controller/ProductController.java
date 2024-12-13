package com.example.main.myproject.controller;

import com.example.main.myproject.dao.model.Product;
import com.example.main.myproject.service.ProductDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productDAO.getById(id);
    }

    @PostMapping
    public void insertProduct(@RequestBody Product product) {
        productDAO.insert(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody Product product) {
        productDAO.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productDAO.delete(id);
    }


}
