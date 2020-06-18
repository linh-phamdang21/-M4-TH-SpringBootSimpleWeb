package com.codegym.demo.service;

import com.codegym.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    public Page<Product> findAll(Pageable pageable);
//    public Iterable<Product> findAll();

    public Page<Product> findAllByName(String name, Pageable pageable);
//    public Iterable<Product> findAllByName(String name);

    public Product findById(Long id);

    public void save(Product product);

    public void remove(Long id);
}
