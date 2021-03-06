package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdPayType;

/**
 * TdPayType 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdPayTypeRepo extends
		PagingAndSortingRepository<TdPayType, Long>,
		JpaSpecificationExecutor<TdPayType> 
{
    Page<TdPayType> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    List<TdPayType> findByIsEnableTrue();
}
