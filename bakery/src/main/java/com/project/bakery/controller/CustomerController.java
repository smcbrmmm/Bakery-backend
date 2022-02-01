package com.project.bakery.controller;

import com.project.bakery.entity.Customer;
import com.project.bakery.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController()
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @CrossOrigin
    @GetMapping
    public HashMap<String, Object> get() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("results", "samut");
        return map;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        Customer record = new Customer(customer.getId(),customer.getName(),customer.getPin());
        service.createCustomer(record);
        return record;
    }
}

