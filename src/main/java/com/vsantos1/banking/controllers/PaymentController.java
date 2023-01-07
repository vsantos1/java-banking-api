package com.vsantos1.banking.controllers;


import com.vsantos1.banking.services.PaymentService;
import com.vsantos1.banking.vo.PaymentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping(value = "/payments",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PaymentVO> getAllPayments(@PageableDefault(size = 10,direction = Sort.Direction.ASC,value = 10) Pageable pageable) {
        return paymentService.findAll(pageable);
    }

}
