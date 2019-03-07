package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdGoodsXc;

/**
 * TdGoodsXc 实体数据库操作接口
 * 
 * @author lulu
 *
 */

public interface TdGoodsXcRepo extends
		PagingAndSortingRepository<TdGoodsXc, Long>,
		JpaSpecificationExecutor<TdGoodsXc> 
{
}
