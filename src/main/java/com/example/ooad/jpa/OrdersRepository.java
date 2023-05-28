package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
}
