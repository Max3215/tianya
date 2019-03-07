package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdPrice;

/**
 * 区间价目表数据库操作接口
 * @author Max
 *
 */
public interface TdPriceRepo extends 
		PagingAndSortingRepository<TdPrice, Long>,
		JpaSpecificationExecutor<TdPrice>{

	List<TdPrice> findByGoodsIdOrderByStartTimeDesc(Long goodsId);
}
