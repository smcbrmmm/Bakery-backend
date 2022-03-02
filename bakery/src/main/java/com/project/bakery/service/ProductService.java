package com.project.bakery.service;

import com.project.bakery.model.Product;
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

    public Product insertProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductByProductId(int productId) {
        return repository.findByProductId(productId);
    }

    public Product updateProduct(Product product) {
        return repository.update(product);
    }
}
