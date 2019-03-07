package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdCartGoodsPs;

/**
 * 
 * @author Max
 *
 */
public interface TdCartGoodsPsRepo extends 
		PagingAndSortingRepository<TdCartGoodsPs, Long>,
		JpaSpecificationExecutor<TdCartGoodsPs>{

	@Query("select ps from TdCartGoods ca join ca.psList ps where ca.id = ?1 and ps.goodsPsId=?2")
	TdCartGoodsPs findTopByCartIdAndGoodsPsId(Long cartId,Long gpId);
	
}
