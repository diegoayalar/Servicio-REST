package com.service.products.services;

import com.service.products.entities.Product;
import com.service.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // or throw a custom exception
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product findByPrice(Double price) {
        return productRepository.findByPrice(price);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product foundProduct = existingProduct.get();
            foundProduct.setName(product.getName());
            foundProduct.setPrice(product.getPrice());
            return productRepository.save(foundProduct);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}