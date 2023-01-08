package com.vsantos1.banking.services;

import com.vsantos1.banking.enums.PaymentMethod;
import com.vsantos1.banking.enums.PaymentStatus;
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
        // TODO : implement the customer to get the customer account information
        double customerBalance = 1000.0;
        boolean isCardUnlocked = true;
        double cardLimit = 1000.0;

        if (payment.getPaymentMethod() == PaymentMethod.CREDIT_CARD) {
            if (isCardUnlocked && payment.getAmount() <= cardLimit) {
                payment.setPaymentStatus(PaymentStatus.APPROVED);
                // TODO: reduce the card limit
                return MapperUtils.parseObject(paymentRepository.save(payment), PaymentVO.class);

            }
            payment.setPaymentStatus(PaymentStatus.REJECTED);

            throw new RuntimeException("Card is locked or the amount is greater than the card limit");

        }

        if (customerBalance >= payment.getAmount() && payment.getPaymentMethod() != PaymentMethod.BOLETO) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            return MapperUtils.parseObject(paymentRepository.save(payment), PaymentVO.class);

        }
        if (payment.getPaymentMethod() == PaymentMethod.BOLETO) {
            payment.setPaymentStatus(PaymentStatus.PENDING);
            return MapperUtils.parseObject(paymentRepository.save(payment), PaymentVO.class);
        }

        payment.setPaymentStatus(PaymentStatus.REJECTED);

        return MapperUtils.parseObject(paymentRepository.save(payment), PaymentVO.class);


    }

    public PaymentVO update(UUID id, Payment payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this id");
        }

        optionalPayment.get().setPaymentStatus(payment.getPaymentStatus() == null ? optionalPayment.get().getPaymentStatus() : payment.getPaymentStatus());
        optionalPayment.get().setPaymentMethod(payment.getPaymentMethod() == null ? optionalPayment.get().getPaymentMethod() : payment.getPaymentMethod());
        optionalPayment.get().setAmount(payment.getAmount() == null ? optionalPayment.get().getAmount() : payment.getAmount());
        optionalPayment.get().setDescription(payment.getDescription() == null ? optionalPayment.get().getDescription() : payment.getDescription());

        return MapperUtils.parseObject(paymentRepository.save(optionalPayment.get()), PaymentVO.class);

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
