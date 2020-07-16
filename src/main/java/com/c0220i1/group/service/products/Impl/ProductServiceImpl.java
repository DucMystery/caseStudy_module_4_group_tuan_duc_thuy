package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.Product;
import com.c0220i1.group.repository.products.ProductRepository;
import com.c0220i1.group.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Product> findAllByCategorySetEquals(Category category,Pageable pageable) {
        return productRepository.findAllByCategorySetEquals(category,pageable);
    }

//    @Override
//    public Page<Product> findAllByCategorySet(Set<Category> categories, Pageable pageable) {
//        return productRepository.findAllByCategorySet(categories, pageable );
//    }
}
