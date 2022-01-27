package com.project.bakery.Service;

import com.project.bakery.Model.Product;
import com.project.bakery.Model.User;
import com.project.bakery.Repository.ProductRepository;
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
