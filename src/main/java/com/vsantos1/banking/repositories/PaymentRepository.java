package com.vsantos1.banking.repositories;

import com.vsantos1.banking.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Page<Payment> findAllByPaymentStatus(Integer paymentStatus, Pageable pageable);
}
