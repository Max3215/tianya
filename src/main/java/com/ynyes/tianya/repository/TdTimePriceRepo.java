package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdTimePrice;

/**
 * 价格日历表数据库操作接口
 * @author Max
 *
 */
public interface TdTimePriceRepo extends 
		PagingAndSortingRepository<TdTimePrice, Long>,
		JpaSpecificationExecutor<TdTimePrice>{
	
	List<TdTimePrice> findByGoodsIdAndTimeAfter(Long goodsId,Date time);
	
	@Query(value="select * from td_time_price t where t.goods_id=?1 and  DATE(t.time) = ?2",nativeQuery=true)
	TdTimePrice findByGoodsIdAndTime(Long goodsId,String time);
	
	//author liuxinbing
	Page<TdTimePrice> findByCategoryIdTreeContaining(String catId, Pageable page);
	
	// Max
	List<TdTimePrice> findByPriceId(Long priceId);
	void deleteByPriceId(Long priceId);

}
