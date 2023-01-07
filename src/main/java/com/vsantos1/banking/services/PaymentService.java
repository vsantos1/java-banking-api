package com.vsantos1.banking.services;

import com.vsantos1.banking.mapper.MapperUtils;
import com.vsantos1.banking.models.Payment;
import com.vsantos1.banking.repositories.PaymentRepository;
import com.vsantos1.banking.vo.PaymentVO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Page<PaymentVO> findAll(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);


        return payments.map(payment -> MapperUtils.parseObject(payment, PaymentVO.class));
    }

    public Page<PaymentVO> findAllByPaymentStatus(Integer paymentStatus, Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAllByPaymentStatus(paymentStatus, pageable);

        return payments.map(payment -> MapperUtils.parseObject(payment, PaymentVO.class));
    }
    @Transactional
    public PaymentVO save(Payment payment) {
        /* SAVE AND PARSE THE PAYMENT MODEL TO VALUE OBJECT */
        return MapperUtils.parseObject(paymentRepository.save(payment), PaymentVO.class);
    }


    public PaymentVO findById(UUID id) {
        /* FIND BY ID AND PARSE THE PAYMENT MODEL TO VALUE OBJECT */
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }

        return MapperUtils.parseObject(paymentOptional, PaymentVO.class);

    }


    @Transactional
    public void delete(UUID id) {
        /* FIND BY ID AND DELETE THE PAYMENT MODEL */
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }

        paymentRepository.delete(paymentOptional.get());
    }

}
