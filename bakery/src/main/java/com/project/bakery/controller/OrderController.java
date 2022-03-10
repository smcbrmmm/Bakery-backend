package com.project.bakery.controller;

import com.project.bakery.model.Order;
import com.project.bakery.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public List<Order> getOrder(){
        return orderService.getOrder();
    }

    @PostMapping("/save")
    public void saveOrder(@RequestBody Order order) {
        orderService.save(order);
    }

    @GetMapping("/getSumPrice/{orderId}")
    public List<Integer>  getSumPrice(@PathVariable("orderId") String orderId ){
        return orderService.getSumPrice(orderId);
    }


}