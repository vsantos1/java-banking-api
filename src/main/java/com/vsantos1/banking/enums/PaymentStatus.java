package com.vsantos1.banking.enums;

public enum PaymentStatus {

    PENDING(0),
    CANCELED(1),
    WAITING_PAYMENT(2),
    COMPLETED(3),
    REFUNDED(4);

    private int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static PaymentStatus valueOf(int code) {
        for (PaymentStatus value : PaymentStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentStatus code");
    }
}
