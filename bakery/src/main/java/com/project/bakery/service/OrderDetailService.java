package com.project.bakery.service;

import com.project.bakery.model.Address;
import com.project.bakery.model.OrderDetail;
import com.project.bakery.model.OrderDetailResponse;
import com.project.bakery.model.Product;
import com.project.bakery.repository.OrderDetailRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    OrderDetailRepositoy repository;

    public OrderDetailService(OrderDetailRepositoy repository) {
        this.repository = repository;
    }

    public List<OrderDetail> getOrderDetail(String orderId){
        return  repository.getOrderDetail(orderId);
    }

    public void saveOrderDetail(OrderDetail orderDetail){
        repository.save(orderDetail);
    }

    public List<OrderDetailResponse> getProducts(String orderId) {
        return repository.getProducts(orderId);
    }
}
