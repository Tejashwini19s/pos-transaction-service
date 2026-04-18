package com.example.pos_transaction_service.dto;

public class TransactionRequest {
	private String description;
    private double amount;
    private String currency;

    // Getters & Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
  
}
