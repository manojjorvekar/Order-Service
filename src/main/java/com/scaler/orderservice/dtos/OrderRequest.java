package com.scaler.orderservice.dtos;
import java.util.List;

public class OrderRequest {
    public String customerName;
    public Double totalAmount;
    public List<Long> productIds;
}