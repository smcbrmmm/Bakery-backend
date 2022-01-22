package com.project.bakery.Controller;

import com.project.bakery.Model.Customer;
import com.project.bakery.Repository.CustomerRepository;
import com.project.bakery.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @RequestMapping("/customer")
    public String getAllCustomers(){
        return "customers";
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        Customer record = new Customer(customer.getId(),customer.getName(),customer.getPin());
        service.createCustomer(record);
        return record;
    }
}

