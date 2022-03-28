package com.project.bakery.controller;

import com.project.bakery.model.Order;
import com.project.bakery.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://bakery-frontend-react.vercel.app/")
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

    @GetMapping("/getOrderByDate/{date}")
    public List<Order> getOrderByDate(@PathVariable("date") String date){
        return orderService.getOrderByDate(date);
    }

    @GetMapping("/getOrderByDateAndOrderId/{date}/{orderId}")
    public List<Order> getOrderByDateAndOrderId(@PathVariable("date") String date , @PathVariable("orderId") String orderId){
        return orderService.getOrderByDateAndOrderId(date , orderId);
    }

    @GetMapping("/getOrderForReport/{dateTo}/{dateFrom}")
    public List<Order> getOrderForReport(@PathVariable("dateTo") String dateTo , @PathVariable("dateFrom") String dateFrom){
        return orderService.getOrderForReport(dateTo , dateFrom);
    }

    @GetMapping("/getTotalPrice/{dateTo}/{dateFrom}")
    public int getTotalPrice(@PathVariable("dateTo") String dateTo , @PathVariable("dateFrom") String dateFrom){
        return orderService.getTotalPrice(dateTo , dateFrom);
    }

    @PostMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable("orderId") String orderId){
        orderService.cancel(orderId);
    }

    @PostMapping("/update/status/confirm/{orderId}")
    public void updateStatusConfirm(@PathVariable("orderId") String orderId){
        orderService.updateStatusConfirm(orderId);
    }

    @PostMapping("/update/status/shipping/{orderId}/{trackingNo}")
    public void updateStatusShipping(@PathVariable("orderId") String orderId , @PathVariable("trackingNo") String trackingNo ){
        orderService.updateStatusShipping(orderId , trackingNo);
    }

    @PostMapping("/update/status/success/{orderId}")
    public void updateStatusSuccess(@PathVariable("orderId") String orderId ){
        orderService.updateStatusSuccess(orderId);
    }

}