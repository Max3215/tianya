package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdClient;

public interface TdClientRepo extends
		PagingAndSortingRepository<TdClient, Long>,
		JpaSpecificationExecutor<TdClient> {
	
	TdClient findById(Long id);
	
	Page<TdClient> findByManagerIdOrderByCreateTimeAsc(Long managerId, Pageable page);
	
	Page<TdClient> findByManagerIdInOrderByCreateTimeAsc(List<Long> managerIds, Pageable page);

	List<TdClient> findByCreateTimeBetween(Date calendar11, Date calendar22);
	
}
