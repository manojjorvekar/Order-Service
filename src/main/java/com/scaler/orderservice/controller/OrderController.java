package com.scaler.orderservice.controller;

import com.scaler.orderservice.dtos.OrderRequest;
import com.scaler.orderservice.dtos.OrderResponse;
import com.scaler.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order APIs", description = "Operations related to orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "Create a new order")
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all orders")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("get/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update an existing order")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        OrderResponse response = orderService.updateOrder(id, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete an order by id")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
