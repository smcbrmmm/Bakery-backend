package com.project.bakery.controller;

import com.project.bakery.model.OrderDetail;
import com.project.bakery.service.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/api/orderDetail")
public class OderDetailController {

    OrderDetailService service;

    public OderDetailController(OrderDetailService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public void saveOrderDetail(@RequestBody OrderDetail orderDetail){
        service.saveOrderDetail(orderDetail);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getOrderDetail(@PathVariable(value="orderId") String orderId){
        return service.getOrderDetail(orderId);
    }
}
