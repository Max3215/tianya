package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdAccessToken;

public interface TdAccessTokenRepo extends
PagingAndSortingRepository<TdAccessToken, Long>,
JpaSpecificationExecutor<TdAccessToken>{
	TdAccessToken findTopBy();
	TdAccessToken findById(Long id);
}
