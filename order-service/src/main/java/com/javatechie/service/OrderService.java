package com.javatechie.service;

import com.javatechie.common.Payment;
import com.javatechie.common.TransactionRequest;
import com.javatechie.common.TransactionResponse;
import com.javatechie.entity.Order;
import com.javatechie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
            // rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success")?"payment processing success and order placed":"there is a failure in payment api, order added to cart";

        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);

    }
}
