package com.api.stockproject.services;

import com.api.stockproject.controllers.EmailController;
import com.api.stockproject.controllers.ItemController;
import com.api.stockproject.controllers.UserController;
import com.api.stockproject.dtos.OrderDto;
import com.api.stockproject.models.Item;
import com.api.stockproject.models.Order;
import com.api.stockproject.models.User;
import com.api.stockproject.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    @Autowired EmailController emailController;
    @Autowired UserController userController;
    @Autowired ItemController itemController;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create an order
    public Order createOrder(OrderDto orderDto) {

        Order order = converterDto(orderDto);

        if (order.getUser().getId()!= null){
            if(order.getUser().getEmail() != null){
                //email test ----------
                String email = "frederyco.mendonca@gmail.com";
                emailController.sendMail(email);

            }
        }
        logger.info("Order completed");
        return orderRepository.save(order);
    }

    // Read all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Read an order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }

    // Update an order
    public Order updateOrder(Long orderId, OrderDto orderDto) {
        Order existingOrder = getOrderById(orderId);
        if(orderDto != null && orderDto.getQuantity() > 0){
            existingOrder.setQuantity(orderDto.getQuantity());
        }
        return orderRepository.save(existingOrder);
    }

    // Delete an order
    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    public Order converterDto(OrderDto orderDto){
        Order order = new Order();

        if(orderDto.getItem_id() != null){
            Item item = itemController.getItemById(orderDto.getItem_id());
            if(item.getId() != null){
                order.setItem(item);
            }
        }
        if(orderDto.getUser_id() != null){
            User user = userController.getUserById(orderDto.getUser_id());
            if(user.getId() != null){
                order.setUser(user);
            }
        }
        order.setCreationDate(Date.valueOf(LocalDate.now()));
        order.setQuantity(orderDto.getQuantity());
        return order;
    }
}