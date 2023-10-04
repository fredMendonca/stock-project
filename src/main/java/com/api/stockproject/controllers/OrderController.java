package com.api.stockproject.controllers;

import com.api.stockproject.dtos.OrderDto;
import com.api.stockproject.models.Order;
import com.api.stockproject.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private static final Logger logger = LogManager.getLogger(OrderController.class);
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create an order
    @PostMapping("/")
    public Order createOrder(@RequestBody OrderDto orderDto) {
        logger.info("Starting order...");
        return orderService.createOrder(orderDto);
    }

    // Read all orders
    @GetMapping("/")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Read an order by ID
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // Update an order
    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderId, orderDto);
    }

    // Delete an order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
