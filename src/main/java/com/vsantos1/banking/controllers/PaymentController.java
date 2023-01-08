package com.vsantos1.banking.controllers;


import com.vsantos1.banking.mapper.MapperUtils;
import com.vsantos1.banking.models.Payment;
import com.vsantos1.banking.services.PaymentService;
import com.vsantos1.banking.vo.PaymentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping(value = "/payments/{payment_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentVO> getPayment(@PathVariable("payment_id") UUID id) {

        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findById(id));
    }

    @GetMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PaymentVO>> getAllPayments(@PageableDefault(size = 10, direction = Sort.Direction.ASC, value = 10) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll(pageable));
    }

    @PostMapping(value = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentVO> createPayment(@RequestBody PaymentVO paymentVO) {
        paymentVO.setCreatedAt(new Date());

        Payment payment = new Payment();
        MapperUtils.copyProperties(paymentVO, payment);

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @PutMapping(value = "/payments/{payment_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentVO> updatePayment(@PathVariable("payment_id") UUID id, @RequestBody PaymentVO paymentVO) {

        Payment payment = new Payment();
        MapperUtils.copyProperties(paymentVO, payment);

        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(id, payment));
    }

    @DeleteMapping(value = "/payments/{payment_id}")
    public ResponseEntity<?> deletePayment(@PathVariable("payment_id") UUID id) {
        paymentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
