package com.samyak.controller;

import com.samyak.dto.PaymentRequest;
import com.samyak.dto.PaymentResponse;
import com.samyak.repository.PaymentRepository;
import com.samyak.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/do-payment")
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequest paymentRequest){
        paymentService.doPayment(paymentRequest);
        return new ResponseEntity<String>("Payment Successfully completed",HttpStatus.OK);
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId){
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<PaymentResponse>(paymentResponse,HttpStatus.OK);
    }

}
