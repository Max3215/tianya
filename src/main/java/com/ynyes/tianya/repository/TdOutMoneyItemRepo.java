package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdOutMoneyItem;

public interface TdOutMoneyItemRepo extends
		PagingAndSortingRepository<TdOutMoneyItem, Long>,
		JpaSpecificationExecutor<TdOutMoneyItem> {
	
	List<TdOutMoneyItem> findByClientId(Long clientId);

}
