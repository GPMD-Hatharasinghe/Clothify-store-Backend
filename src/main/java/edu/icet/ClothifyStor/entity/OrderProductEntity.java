package edu.icet.ClothifyStor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="order_Products")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;

    private Integer productId;
    private String productName;
    private Double totalPrice;
    private String size;
    private Integer quantity;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
