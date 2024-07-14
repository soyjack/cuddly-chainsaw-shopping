package com.hamy.tradeshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hamy.tradeshop.model.ItemPost;

@Repository
public interface ItemPostRepository extends CrudRepository<ItemPost, Long> {
	List<ItemPost> findBySellerId(Long sellerId);
}
