package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdArticleCategory;
import com.ynyes.tianya.entity.TdArticleWt;

/**
 * TdArticleWt 实体数据库操作接口
 * 
 * @author lulu
 *
 */

public interface TdArticleWtRepo extends
		PagingAndSortingRepository<TdArticleWt, Long>,
		JpaSpecificationExecutor<TdArticleWt> 
{
}
