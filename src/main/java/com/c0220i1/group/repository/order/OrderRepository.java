package com.c0220i1.group.repository.order;

import com.c0220i1.group.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders,Long> {
}
