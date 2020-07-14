package com.c0220i1.group.repository.products;

import com.c0220i1.group.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {


    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
