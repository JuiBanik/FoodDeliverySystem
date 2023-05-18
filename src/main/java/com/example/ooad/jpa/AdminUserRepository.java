package com.example.ooad.jpa;

import com.example.ooad.jpa.entity.AdminUser;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
    AdminUser findByUserName(String userName);
}
