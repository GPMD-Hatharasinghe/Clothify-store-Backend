package edu.icet.ClothifyStor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.ClothifyStor.entity.ProductEntity;
import edu.icet.ClothifyStor.model.Product;
import edu.icet.ClothifyStor.repository.ProductRepository;
import edu.icet.ClothifyStor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repo;
    @Autowired
    ObjectMapper mapper;

    @Override
    public  Product save(Product product) {

        ProductEntity entity=mapper.convertValue(product, ProductEntity.class);
        repo.save(entity);
        return mapper.convertValue(entity,Product.class);
    }

    @Override
    public List<Product> retrive() {
        List<ProductEntity> entities = repo.findAll();

        entities.sort((e1, e2) -> Long.compare(e2.getId(), e1.getId()));

        return entities.stream()
                .map(entity -> mapper.convertValue(entity, Product.class))
                .toList();
    }

    public Product updateProduct(Long productId, Product updatedProduct) {

        ProductEntity updateProductEntity = repo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));


        if (updatedProduct.getName() != null) {
            updateProductEntity.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null) {
            updateProductEntity.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getPrice() != 0) {
            updateProductEntity.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getQuantities() != null) {
            updateProductEntity.setQuantities(updatedProduct.getQuantities());
        }

        ProductEntity savedEntity = repo.save(updateProductEntity);

        return mapper.convertValue(savedEntity, Product.class);
    }

}
