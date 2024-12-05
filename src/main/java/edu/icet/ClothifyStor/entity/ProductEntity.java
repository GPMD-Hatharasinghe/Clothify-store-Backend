package edu.icet.ClothifyStor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@Table(name= "product_table")
@Entity
public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private double price;
        private String description;
        private String mainCategory;
        private String subCategory;

        @ElementCollection
        private List<String> images;

        @ElementCollection
        private Map<String, Integer> quantities;

        @ManyToOne
        @JoinColumn(name = "subcategory_id")
        private SubCategoryEntity subcategory;

}


