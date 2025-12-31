package com.khoslak.WebMicroservice.controller;

import com.khoslak.WebMicroservice.exceptions.OrderNotFoundException;
import com.khoslak.WebMicroservice.model.CreateOrderRequest;
import com.khoslak.WebMicroservice.model.Order;
import com.khoslak.WebMicroservice.repository.OrderRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/orders")
@Tag(name = "Order Endpoints", description = "Operations related to orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // GET /orders
    @GetMapping
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    // POST /orders
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        Order order = new Order(null, request.getItem(), request.getQuantity(), "CREATED");
        return repository.save(order);
    }

    // PUT /orders/{id}/status
    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id,
                              @RequestParam String status) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        Order updated = new Order(existing.getId(), existing.getItem(), existing.getQuantity(), status);
        return repository.save(updated);
    }

    // DELETE /orders/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        repository.delete(id);
    }
}