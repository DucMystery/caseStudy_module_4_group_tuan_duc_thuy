package com.c0220i1.group.repository.products;

import com.c0220i1.group.model.CustomerInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends PagingAndSortingRepository<CustomerInfo,Long> {

}
