package com.vsantos1.banking.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String cpf;

    private Double balance;

    private Double cardLimit;

    private Boolean isCardUnlocked;


    public Customer() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Double cardLimit) {
        this.cardLimit = cardLimit;
    }

    public Boolean getCardUnlocked() {
        return isCardUnlocked;
    }

    public void setCardUnlocked(Boolean cardUnlocked) {
        isCardUnlocked = cardUnlocked;
    }
}
