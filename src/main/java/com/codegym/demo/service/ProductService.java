package com.codegym.demo.service;

import com.codegym.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Page<Product> findAll(Pageable pageable);
//    public Iterable<Product> findAll();

    Page<Product> findAllByName(String name, Pageable pageable);
//    public Iterable<Product> findAllByName(String name);

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);
}
