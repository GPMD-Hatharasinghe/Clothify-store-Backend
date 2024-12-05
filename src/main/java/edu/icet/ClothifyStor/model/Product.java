package edu.icet.ClothifyStor.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data


public class Product {

    private Long id;

    private String name;
    private double price;
    private String description;
    private List<String> images;
    private Map<String, Integer> quantities;
    private String mainCategory;
    private String subCategory;
}
