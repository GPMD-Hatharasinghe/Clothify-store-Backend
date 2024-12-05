package edu.icet.ClothifyStor.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sub_categories")
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategoryEntity mainCategory;
}
