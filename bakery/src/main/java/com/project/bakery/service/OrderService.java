package com.project.bakery.service;

import com.project.bakery.model.Order;
import com.project.bakery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getOrder(){
        return repository.findAll();
    }

    public void save(Order order){
        repository.save(order);
    }

}
