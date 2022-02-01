package com.project.bakery.service;

import com.project.bakery.entity.Product;
import com.project.bakery.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository repository;

    ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

}
