package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdOrderVisitor;

/**
 * 
 * @author Max
 *
 */
public interface TdOrderVisitorRepo extends
	PagingAndSortingRepository<TdOrderVisitor, Long>,
	JpaSpecificationExecutor<TdOrderVisitor>{

	List<TdOrderVisitor> findByTdOrderId(Long id);
}
