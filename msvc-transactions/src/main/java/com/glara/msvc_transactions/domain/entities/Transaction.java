package com.glara.msvc_transactions.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "subcategory_id")
    private UUID subcategoryId;

    @Column(name = "vendor_id")
    private UUID vendorId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(UUID subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public UUID getVendorId() {
        return vendorId;
    }

    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
