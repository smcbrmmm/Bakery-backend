package com.project.bakery.service;

import com.project.bakery.model.Cart;
import com.project.bakery.model.OrderDetail;
import com.project.bakery.model.OrderDetailResponse;
import com.project.bakery.repository.CartRepository;
import com.project.bakery.repository.OrderDetailRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public List<Cart> inCart(String userId){
        return repository.inCart(userId);
    }


    public void insert(Cart cart) {
        repository.insert(cart);
    }

    public void deleteItemInCart(Cart cart) {
        repository.deleteItemInCart(cart);
    }

    public void deleteAllItemInCart(Cart cart) {
        repository.deleteAllItemInCart(cart);
    }
}
