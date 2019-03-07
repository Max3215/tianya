package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdGoodsPs;

/**
 * TdGoodsPs 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGoodsPsRepo extends
		PagingAndSortingRepository<TdGoodsPs, Long>,
		JpaSpecificationExecutor<TdGoodsPs> 
{
}
