package com.project.bakery.controller;


import com.project.bakery.model.Product;
import com.project.bakery.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Produces;
import javax.xml.ws.Response;
import java.util.List;

@CrossOrigin("https://bakery-frontend-react.vercel.app/")
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

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return service.insertProduct(product);
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam int productId) {
        return service.getProductByProductId(productId);
    }

    @PostMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        System.out.println(product);
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody Product product){
        service.deleteProduct(product.getId());
    }

    @GetMapping("/getProductQty/{productId}")
    public int getProductQty(@PathVariable("productId") int productId){
        return service.getProductQty(productId);
    }

}
