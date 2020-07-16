package com.c0220i1.group.repository.products;

import com.c0220i1.group.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
    Page<Category> findAllByNameContaining(String name, Pageable pageable);
}
