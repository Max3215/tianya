package com.ynyes.tianya.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdNotice;

/**
 * TdAd 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdNoticeRepo extends
		PagingAndSortingRepository<TdNotice, Long>,
		JpaSpecificationExecutor<TdAd> 
{
	Page<TdNotice> findByNoticeId(Long id, Pageable page);
	Page<TdNotice> findByNoticedIdsContaining(String s, Pageable page);
}
