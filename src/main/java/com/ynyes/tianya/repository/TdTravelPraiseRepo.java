package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdTravelPraise;

/**
 * TdTravelPraise 实体数据库操作接口
 * 
 * @author lulu
 *
 */

public interface TdTravelPraiseRepo extends
		PagingAndSortingRepository<TdTravelPraise, Long>,
		JpaSpecificationExecutor<TdTravelPraise> 
{

	TdTravelPraise findByNameAndTravelNotesId(String username,Long id);

}
