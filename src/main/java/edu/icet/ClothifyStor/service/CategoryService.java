package edu.icet.ClothifyStor.service;

import edu.icet.ClothifyStor.entity.MainCategoryEntity;
import edu.icet.ClothifyStor.entity.SubCategoryEntity;
import edu.icet.ClothifyStor.model.MainCategory;

import java.util.List;

public interface CategoryService {
    void initializeDefaultMainCategories();
    public List<MainCategory> getAllMainCategories();
    SubCategoryEntity addSubCategory(String mainCategoryName, String subCategoryName);
}
