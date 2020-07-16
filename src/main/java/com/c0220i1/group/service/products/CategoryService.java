package com.c0220i1.group.service.products;

import com.c0220i1.group.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(Long id);
    void save(Category category);
    void remove(Long id);

    Page<Category> findAllByNameContaining(String name, Pageable pageable);
}
