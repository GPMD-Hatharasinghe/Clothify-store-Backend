package edu.icet.ClothifyStor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="order_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String status;
    private String date;

    private String fullName;
    private String country;
    private String address;
    private String city;
    private String stat;
    private String email;
    private String postalCode;
    private String phone;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cus_id", referencedColumnName = "id")
    private UserEntity user;
}
