package com.hamy.tradeshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hamy.tradeshop.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	 Optional<Admin> findById(Long id);
}
