package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdGoodsGift;

/**
 * TdGoodsGift 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGoodsGiftRepo extends
		PagingAndSortingRepository<TdGoodsGift, Long>,
		JpaSpecificationExecutor<TdGoodsGift> 
{
	
}
