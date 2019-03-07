package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdNaviBarItem;

/**
 * TdNaviBarItem 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdNaviBarItemRepo extends
		PagingAndSortingRepository<TdNaviBarItem, Long>,
		JpaSpecificationExecutor<TdNaviBarItem> 
{
    List<TdNaviBarItem> findByIsEnableTrueAndIsTouchShowNullOrIsEnableTrueAndIsTouchShowFalseOrderBySortIdAsc();
    List<TdNaviBarItem> findByIsEnableTrueAndIsTouchShowTrueOrderBySortIdAsc();
    List<TdNaviBarItem> findByParentIdIsNullAndIsEnableTrueOrderBySortIdAsc();
    List<TdNaviBarItem> findByParentIdIsNullOrderBySortIdAsc();
    List<TdNaviBarItem> findByParentIdAndIsEnableTrueOrderBySortIdAsc(Long parentId);
    List<TdNaviBarItem> findByParentIdOrderBySortIdAsc(Long parentId);
}
