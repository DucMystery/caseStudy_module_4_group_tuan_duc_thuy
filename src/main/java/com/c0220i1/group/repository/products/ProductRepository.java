package com.c0220i1.group.repository.products;

import com.c0220i1.group.model.Category;
import com.c0220i1.group.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {


    Page<Product> findAllByNameContaining(String name, Pageable pageable);


//    Page<Product> findAllByCategorySet(Set<Category> categories, Pageable pageable);
        Page<Product> findAllByCategorySetEquals(Category category,Pageable pageable);
}
