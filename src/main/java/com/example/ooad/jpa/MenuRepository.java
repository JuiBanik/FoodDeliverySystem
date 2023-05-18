package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    Menu findById(long id);
}
