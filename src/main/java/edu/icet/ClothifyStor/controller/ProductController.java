package edu.icet.ClothifyStor.controller;

import edu.icet.ClothifyStor.model.Product;
import edu.icet.ClothifyStor.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        log.info("product {}", product);
        return productService.save(product);

    }

    @GetMapping("/products")
    public List<Product> retrive(){
        return productService.retrive();

    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {
       return productService.updateProduct(id, updatedProduct);

    }

}

