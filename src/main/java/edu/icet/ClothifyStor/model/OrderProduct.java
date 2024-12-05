package edu.icet.ClothifyStor.model;

import lombok.Data;

@Data
public class OrderProduct {

    private Integer productId;
    private String productName;
    private Double totalPrice;
    private String size;
    private Integer quantity;
    private String image;

}
