package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdUserVisitor;

/**
 * TdUserVisitor 实体数据库操作接口
 * 
 * @author lulu
 *
 */

public interface TdUserVisitorRepo extends
		PagingAndSortingRepository<TdUserVisitor, Long>,
		JpaSpecificationExecutor<TdUserVisitor> 
{

	List<TdUserVisitor> findByUsername(String username);

	TdUserVisitor findById(Long id);
	
	List<TdUserVisitor> findByUsernameAndIsSelectTrue(String username);

	Page<TdUserVisitor> findByUsername(String username, Pageable pageRequest);
}
