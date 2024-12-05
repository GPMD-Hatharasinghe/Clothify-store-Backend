package edu.icet.ClothifyStor.repository;

import edu.icet.ClothifyStor.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Long> {
    MainCategoryEntity findByName(String name);
}
