package com.scaler.orderservice.dtos;

import java.util.List;

public class OrderResponse {
    public Long id;
    public String customerName;
    public Double totalAmount;
    public String status;
    public List<Long> productIds;
}
