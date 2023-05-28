package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.Item;
import com.example.ooad.jpa.entity.Menu;
import org.springframework.data.repository.CrudRepository;
public interface ItemRepository extends CrudRepository<Item, Long>{
    Menu findById(long id);

    Item findByItemName(String itemName);
}
