package com.project.bakery.controller;


import com.project.bakery.model.Product;
import com.project.bakery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/product")
public class ProductController {

    ProductService service;

    ProductController(ProductService productService){
        this.service = productService;
    }

    @GetMapping("/get/all")
    public List<Product> getAllProducts(){
        return service.getProducts();
    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return service.insertProduct(product);
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam int productId) {
        return service.getProductByProductId(productId);
    }

    @PostMapping("update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

}
