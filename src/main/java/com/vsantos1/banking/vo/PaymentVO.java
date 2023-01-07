package com.vsantos1.banking.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class PaymentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private Integer paymentStatus;

    private String description;

    private Float amount;

    private Date createdAt;

    public PaymentVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
