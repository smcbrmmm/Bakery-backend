package com.project.bakery.service;

import com.project.bakery.model.Payment;
import com.project.bakery.model.UploadSlipRequest;
import com.project.bakery.repository.OrderRepository;
import com.project.bakery.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public void upload(UploadSlipRequest request) {
        repository.upload(request);
    }

    public List<Payment> getPayment(String orderId) {
        return repository.getPayment(orderId);
    }
}
