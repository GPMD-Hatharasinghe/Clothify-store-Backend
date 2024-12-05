package edu.icet.ClothifyStor.service.impl;

import edu.icet.ClothifyStor.entity.OrderEntity;
import edu.icet.ClothifyStor.entity.OrderProductEntity;
import edu.icet.ClothifyStor.entity.UserEntity;
import edu.icet.ClothifyStor.model.Order;
import edu.icet.ClothifyStor.model.OrderProduct;
import edu.icet.ClothifyStor.repository.OrderRepository;
import edu.icet.ClothifyStor.repository.UserRepository;
import edu.icet.ClothifyStor.service.OrderService;
import edu.icet.ClothifyStor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Order save(Order order, String token) {

        String email = userService.checkToken(token);
        if (email == null) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new IllegalStateException("User not found");
        }
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setStatus(order.getStatus());
        orderEntity.setDate(order.getDate());
        orderEntity.setCountry(order.getCountry());
        orderEntity.setCity(order.getCity());
        orderEntity.setAddress(order.getAddress());
        orderEntity.setPostalCode(order.getPostalCode());
        orderEntity.setFullName(order.getFullName());
        orderEntity.setPhone(order.getPhone());
        orderEntity.setStat(order.getStat());
        orderEntity.setEmail(order.getEmail());
        orderEntity.setUser(userEntity);

        orderEntity.setPostalCode(order.getPostalCode());

        List<OrderProductEntity> orderProductEntities = new ArrayList<>();

        order.getProducts().forEach(orderProduct -> {
            OrderProductEntity orderProductEntity = new OrderProductEntity();

            orderProductEntity.setProductId(orderProduct.getProductId());
            orderProductEntity.setProductName(orderProduct.getProductName());
            orderProductEntity.setSize(orderProduct.getSize());
            orderProductEntity.setQuantity(orderProduct.getQuantity());
            orderProductEntity.setTotalPrice(orderProduct.getTotalPrice());
            orderProductEntity.setImage(orderProduct.getImage());
            orderProductEntity.setOrder(orderEntity);

            orderProductEntities.add(orderProductEntity);
        });

        orderEntity.setProducts(orderProductEntities);
        orderRepository.save(orderEntity);




        order.setOrderId(orderEntity.getOrderId());
        order.setStatus(orderEntity.getStatus());
        order.setDate(orderEntity.getDate());
        order.setCustId(orderEntity.getUser().getId());

        double totalAmount = orderProductEntities.stream()
                .mapToDouble(OrderProductEntity::getTotalPrice)
                .sum();

        return order;
    }

    @Override
    public boolean updateStatus(Integer orderId, String status) {

            OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow (null);
            orderEntity.setStatus(status);
            orderRepository.save(orderEntity);
            return true;
        }


    @Override
    public List<Order> load() {
        Iterable<OrderEntity> orderEntities = orderRepository.findAll();

        List<Order> orders = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntities){
            Order order = new Order();

            order.setOrderId(orderEntity.getOrderId());
            order.setDate(orderEntity.getDate());
            order.setStatus(orderEntity.getStatus());

            order.setCountry(orderEntity.getCountry());
            order.setCity(orderEntity.getCity());
            order.setStat(orderEntity.getStat());
            order.setAddress(orderEntity.getAddress());
            order.setFullName(orderEntity.getFullName());
            order.setPhone(orderEntity.getPhone());
            order.setPostalCode(orderEntity.getPostalCode());
            order.setEmail(orderEntity.getEmail());

            if(orderEntity.getUser()!= null){
                order.setCustId(orderEntity.getUser().getId());
                order.setFullName(orderEntity.getUser().getName());

            }

            List<OrderProduct> orderProducts = new ArrayList<>();

            for(OrderProductEntity orderProductEntity : orderEntity.getProducts()){
                OrderProduct orderProduct = new OrderProduct();

                orderProduct.setProductId(orderProductEntity.getProductId());
                orderProduct.setProductName(orderProductEntity.getProductName());
                orderProduct.setSize(orderProductEntity.getSize());
                orderProduct.setTotalPrice(orderProductEntity.getTotalPrice());
                orderProduct.setQuantity(orderProductEntity.getQuantity());
                orderProduct.setImage(orderProductEntity.getImage());

                orderProducts.add(orderProduct);
            }
            order.setProducts(orderProducts);
            orders.add(order);
        }
        return orders;
    }
    @Override
    public Order loadById(Integer orderId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if(orderEntityOptional.isPresent()){
            OrderEntity orderEntity = orderEntityOptional.get();

            Order order = new Order();
            order.setOrderId(orderEntity.getOrderId());
            order.setDate(orderEntity.getDate());
            order.setStatus(orderEntity.getStatus());

            if (orderEntity.getUser() != null) {
                order.setCustId(orderEntity.getUser().getId());
            }

            List<OrderProduct> orderProducts = new ArrayList<>();

            for(OrderProductEntity orderProductEntity : orderEntity.getProducts()){
                OrderProduct orderProduct = new OrderProduct();

                orderProduct.setProductName(orderProductEntity.getProductName());
                orderProduct.setQuantity(orderProductEntity.getQuantity());
                orderProduct.setTotalPrice(orderProductEntity.getTotalPrice());



                orderProducts.add(orderProduct);
            }
            order.setProducts(orderProducts);
            return order;
        } else throw (null);
    }
}
