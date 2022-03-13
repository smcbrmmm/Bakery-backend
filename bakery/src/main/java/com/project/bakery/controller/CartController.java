package com.project.bakery.controller;

import com.project.bakery.model.Address;
import com.project.bakery.model.Cart;
import com.project.bakery.model.Product;
import com.project.bakery.service.AddressService;
import com.project.bakery.service.CartService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/cart")
public class CartController {

    CartService cartService;

    CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/inCart/{userId}")
    public List<Cart> inCart(@PathVariable("userId") String userId){
        List<Cart> response = cartService.inCart(userId);
        return response;
    }

    @PostMapping("/inCart/insert")
    public void insertToCart(@RequestBody Cart cart){
        cartService.insert(cart);
    }

    @DeleteMapping("/inCart/deleteItemInCart")
    public void deleteItemInCart(@RequestBody Cart cart){
        cartService.deleteItemInCart(cart);
    }

    @DeleteMapping("/inCart/deleteAllItem")
    public void deleteAllItemInCart(@RequestBody Cart cart){
        cartService.deleteAllItemInCart(cart);
    }

}