package com.project.bakery.controller;

import com.project.bakery.model.Payment;
import com.project.bakery.model.UploadSlipRequest;
import com.project.bakery.service.PaymentService;
import com.project.bakery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("https://bakery-frontend-react.vercel.app/")
@CrossOrigin
@RestController()
@RequestMapping("/api/payment")
public class PaymentController {

    PaymentService service;

    PaymentController(PaymentService paymentService){
        this.service = paymentService;
    }

    @GetMapping("/getPayment/{orderId}")
    public List<Payment> getPayment(@PathVariable("orderId") String orderId){
        return service.getPayment(orderId);
    }

    @PostMapping("/upload")
    public void uploadSlip(@RequestBody UploadSlipRequest request){
        service.upload(request);
    }



}
