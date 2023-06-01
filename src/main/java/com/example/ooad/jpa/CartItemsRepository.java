package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.CartItem;
import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;
public interface CartItemsRepository extends CrudRepository<CartItem, Long>{
    CartItem findByUserAndItemAndCartStatus(User user, Item item, String cartStatus);

    Iterable<CartItem> findByUserAndCartStatus(User user, String cartStatus);
}
