package edu.icet.ClothifyStor.model;

import lombok.Data;

import java.util.List;

@Data
public class MainCategory {

    private Long id;

    private String name;

    private List<SubCategory> subCategories;
}
