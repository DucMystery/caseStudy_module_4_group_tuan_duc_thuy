package com.c0220i1.group.repository.order;

import com.c0220i1.group.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {
}
