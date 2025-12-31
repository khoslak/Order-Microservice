package com.khoslak.WebMicroservice.repository;

import com.khoslak.WebMicroservice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public OrderRepository() {
        // Seed data
        save(new Order(null, "Laptop", 1, "CREATED"));
        save(new Order(null, "Phone", 2, "SHIPPED"));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Order save(Order order) {
        Long id = order.getId() != null ? order.getId() : idGenerator.getAndIncrement();
        Order saved = new Order(id, order.getItem(), order.getQuantity(), order.getStatus());
        orders.put(id, saved);
        return saved;
    }

    public void delete(Long id) {
        orders.remove(id);
    }
}
