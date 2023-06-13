package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.payment.PayPalPaymentMethod;
import com.codecool.elproyectegrande.payment.PaypalPaymentIntent;
import com.codecool.elproyectegrande.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PaymentController {

    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    public ResponseEntity<String> initiatePayment() {
        try {
            Payment payment = paypalService.createPayment(
                    4.00,
                    "USD",
                    PayPalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    PAYPAL_CANCEL_URL,
                    PAYPAL_SUCCESS_URL);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    System.out.println("aici");
                    return ResponseEntity.ok(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPayment() {
        return ResponseEntity.ok("Payment cancelled");
    }

    @GetMapping("/success")
    public ResponseEntity<String> successPayment(@RequestParam("paymentId") String paymentId,
                                                 @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return ResponseEntity.ok("Payment successful");
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
