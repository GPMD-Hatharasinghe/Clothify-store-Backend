package edu.icet.ClothifyStor.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Order {
    private Integer custId;
    private Integer orderId;

    private List<OrderProduct>products;

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


}
