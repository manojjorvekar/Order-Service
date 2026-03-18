package com.scaler.orderservice.service;


import java.util.List;

import com.scaler.orderservice.dtos.OrderRequest;
import com.scaler.orderservice.dtos.OrderResponse;
import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest request);

    void deleteOrder(Long id);
}

