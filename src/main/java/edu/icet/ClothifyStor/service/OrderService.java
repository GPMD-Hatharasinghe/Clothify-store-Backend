package edu.icet.ClothifyStor.service;

import edu.icet.ClothifyStor.model.Order;

import java.util.List;

public interface OrderService {
     Order save(Order order, String token);
     boolean updateStatus(Integer orderId, String status);
     List<Order> load();
     public Order loadById(Integer orderId);
}
