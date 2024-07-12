package com.hamy.tradeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hamy.tradeshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
