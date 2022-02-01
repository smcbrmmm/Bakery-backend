package com.project.bakery.controller;


import com.project.bakery.entity.Product;
import com.project.bakery.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/products")
public class ProductController {

    ProductService service;

    ProductController(ProductService productService){
        this.service = productService;
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts(){
        return service.getProducts();
    }

}
