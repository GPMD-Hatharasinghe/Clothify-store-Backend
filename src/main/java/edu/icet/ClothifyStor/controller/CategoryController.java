package edu.icet.ClothifyStor.controller;

import edu.icet.ClothifyStor.entity.MainCategoryEntity;
import edu.icet.ClothifyStor.entity.SubCategoryEntity;
import edu.icet.ClothifyStor.model.MainCategory;
import edu.icet.ClothifyStor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService Service;

    // Initialize default categories (call this only once, ideally on application startup)
    @PostMapping("/initialize")
    public ResponseEntity<String> initializeCategories() {
        Service.initializeDefaultMainCategories();
        return ResponseEntity.ok("Default categories initialized");
    }

    // Get all main categories
    @GetMapping
    public List<MainCategory> getAllMainCategories() {
        return Service.getAllMainCategories();
    }

    // Add a subcategory under a main category
    @PostMapping("/{mainCategoryName}/subcategories")
    public ResponseEntity<SubCategoryEntity> addSubCategory(
            @PathVariable String mainCategoryName,
            @RequestBody String subCategoryName
    ) {
        SubCategoryEntity subCategory = Service.addSubCategory(mainCategoryName, subCategoryName);
        return ResponseEntity.ok(subCategory);
    }
}
