package com.c0220i1.group.repository.account;

import com.c0220i1.group.model.CustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfo ,Long> {
    CustomerInfo findByAccount_Username(String username);
}
