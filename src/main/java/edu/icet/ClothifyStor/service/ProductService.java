package edu.icet.ClothifyStor.service;

import edu.icet.ClothifyStor.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product updateProduct(Long productId, Product updatedProduct);
    List<Product> retrive();
}
