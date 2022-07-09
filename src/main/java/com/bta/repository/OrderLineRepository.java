package com.bta.repository;

import com.bta.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

    OrderLine findByCustomerOrder(Integer customerOrder);
}
