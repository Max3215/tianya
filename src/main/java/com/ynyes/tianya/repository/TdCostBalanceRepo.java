package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdClient;
import com.ynyes.tianya.entity.TdCostBalance;

public interface TdCostBalanceRepo
		extends PagingAndSortingRepository<TdCostBalance, Long>, JpaSpecificationExecutor<TdCostBalance> {
	TdCostBalance findByClientId(Long clientId); 
	
	Page<TdCostBalance> findBycheckPersonIdListContaining(String s, Pageable pageRequest);
	
	Page<TdCostBalance> findByManagerTreeContaining(String managerIdStr, Pageable page);
}
