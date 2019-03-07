package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdVisitorCustom;

public interface TdVisitorCustomRepo extends
		PagingAndSortingRepository<TdVisitorCustom, Long>,
		JpaSpecificationExecutor<TdVisitorCustom> {
	

	
}
