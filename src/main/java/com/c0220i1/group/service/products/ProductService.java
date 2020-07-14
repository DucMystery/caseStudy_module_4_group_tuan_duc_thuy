package com.c0220i1.group.service.products;

import com.c0220i1.group.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Long id);
    void save(Product product);
    void remove(Long id);
    Page<Product> findAllByNameContaining(String name,Pageable pageable);
}
