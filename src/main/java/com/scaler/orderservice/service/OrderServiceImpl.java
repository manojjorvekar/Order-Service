package com.scaler.orderservice.service;

import com.scaler.orderservice.model.Order;
import com.scaler.orderservice.dtos.*;
import com.scaler.orderservice.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.customerName);
        order.setTotalAmount(request.totalAmount);
        order.setProductIds(request.productIds);
        order.setStatus("PENDING");

        Order savedOrder = repository.save(order);
        return convertToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> allOrders = repository.findAll();
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : allOrders) {
            responseList.add(convertToResponse(order));
        }
        return responseList;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = repository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return convertToResponse(order);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order existingOrder = repository.findById(id).orElse(null);
        if (existingOrder == null) {
            return null;
        }

        existingOrder.setCustomerName(request.customerName);
        existingOrder.setTotalAmount(request.totalAmount);
        existingOrder.setProductIds(request.productIds);

        Order updated = repository.save(existingOrder);
        return convertToResponse(updated);
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    // Helper method using basic assignments
    private OrderResponse convertToResponse(Order order) {
        OrderResponse res = new OrderResponse();
        res.id = order.getId();
        res.customerName = order.getCustomerName();
        res.totalAmount = order.getTotalAmount();
        res.status = order.getStatus();
        res.productIds = order.getProductIds();
        return res;
    }
}
