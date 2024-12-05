package edu.icet.ClothifyStor.service.impl;

import edu.icet.ClothifyStor.entity.MainCategoryEntity;
import edu.icet.ClothifyStor.entity.SubCategoryEntity;
import edu.icet.ClothifyStor.model.MainCategory;
import edu.icet.ClothifyStor.model.SubCategory;
import edu.icet.ClothifyStor.repository.MainCategoryRepository;
import edu.icet.ClothifyStor.repository.SubCategoryRepository;
import edu.icet.ClothifyStor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MainCategoryRepository mainRepo;

    @Autowired
    private SubCategoryRepository subRepo;


    public void initializeDefaultMainCategories() {
        String[] defaultCategories = {"Man", "Women", "Kids", "Baby","Clothify"};
        for (String category : defaultCategories) {
            if (mainRepo.findByName(category) == null) {
                MainCategoryEntity mainCategory = new MainCategoryEntity();
                mainCategory.setName(category);
                mainRepo.save(mainCategory);
            }
        }
    }


    public List<MainCategory> getAllMainCategories() {
        Iterable <MainCategoryEntity> mainCategoryEntityList = mainRepo.findAll();

        List <MainCategory> mainCategories = new ArrayList<>();

        for(MainCategoryEntity mainCategoryEntity : mainCategoryEntityList){

            MainCategory mainCategory = new MainCategory();

            mainCategory.setId(mainCategoryEntity.getId());
            mainCategory.setName(mainCategoryEntity.getName());

            List <SubCategory> subCategoryList = new ArrayList<>();

            for (SubCategoryEntity subCategoryEntity : mainCategoryEntity.getSubCategories() ){
                SubCategory subCategory = new SubCategory();

                subCategory.setId(subCategoryEntity.getId());
                subCategory.setName(subCategoryEntity.getName());

                subCategoryList.add(subCategory);
            }

            mainCategory.setSubCategories(subCategoryList);
            mainCategories.add(mainCategory);
        }

        return mainCategories;
    }

    public SubCategoryEntity addSubCategory(String mainCategoryName, String subCategoryName) {
        MainCategoryEntity mainCategory = mainRepo.findByName(mainCategoryName);
        if (mainCategory == null) {
            throw new IllegalArgumentException("Main category not found: " + mainCategoryName);
        }
        SubCategoryEntity subCategory = new SubCategoryEntity();
        subCategory.setName(subCategoryName);
        subCategory.setMainCategory(mainCategory);
        return subRepo.save(subCategory);
    }
}
