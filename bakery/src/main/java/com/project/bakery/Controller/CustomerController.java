package com.project.bakery.Controller;

import com.project.bakery.Model.Customer;
import com.project.bakery.Repository.CustomerRepository;
import com.project.bakery.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

