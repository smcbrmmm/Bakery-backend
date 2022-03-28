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

    public List<Integer>  getSumPrice(String orderId) {
        return repository.getSumPrice(orderId);
    }

    public void cancel(String orderId) {
        repository.cancel(orderId);
    }

    public List<Order> getOrderByDate(String date) {
        return repository.getOrderByDate(date);
    }

    public List<Order> getOrderByDateAndOrderId(String date , String orderId) {
        return repository.getOrderByDateAndOrderId(date , orderId);
    }

    public int getTotalPrice(String dateTo , String dateFrom) {
        return repository.getTotalPrice(dateTo,dateFrom);
    }

    public List<Order> getOrderForReport(String dateTo , String dateFrom) {
        return repository.getOrderForReport(dateTo,dateFrom);
    }

    public void updateStatusConfirm(String orderId) {
        repository.updateStatusConfirm(orderId);
    }

    public void updateStatusShipping(String orderId , String trackingNo) {
        repository.updateStatusShipping(orderId , trackingNo);
    }

    public void updateStatusSuccess(String orderId) {
        repository.updateStatusSuccess(orderId);
    }


}
