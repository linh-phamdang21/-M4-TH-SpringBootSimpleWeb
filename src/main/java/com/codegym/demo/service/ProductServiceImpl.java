package com.codegym.demo.service;

import com.codegym.demo.model.Product;
import com.codegym.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

//    @Override
//    public Iterable<Product> findAll() {
//        Iterable<Product> productPage = productRepository.findAll();
//        return productPage;
//    }

    @Override
    public Page<Product> findAllByName(String name, Pageable pageable) {
        return productRepository.findAllByName(name, pageable);
    }

//    @Override
//    public Iterable<Product> findAllByName(String name) {
//        return productRepository.findAllByName(name);
//    }

    @Override
    public Product findById(Long id) {
        Optional<Product> customerOptional = productRepository.findById(id);
        if (customerOptional.isPresent()){
            return customerOptional.get();
        }
        throw new RuntimeException("Not found customer with ID: " + id);
    }

    @Override
    public void remove(Long id) {
            productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
