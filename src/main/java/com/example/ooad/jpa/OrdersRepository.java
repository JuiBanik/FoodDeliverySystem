package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.Orders;
import com.example.ooad.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    Iterable<Orders> findByUser(User user);

}
