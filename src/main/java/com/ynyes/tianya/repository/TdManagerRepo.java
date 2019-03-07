package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdManager;

/**
 * TdManager 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdManagerRepo extends
		PagingAndSortingRepository<TdManager, Long>,
		JpaSpecificationExecutor<TdManager> 
{
    TdManager findByUsernameAndIsEnableTrue(String username);

    List<TdManager> findByParentIdAndIsEnableTrue(Long parentId);

    
    // 查找第一级管理员角色 Max
    List<TdManager> findByParentIdIsNullOrderBySortIdAsc();
    
    // 查找子级角色 Max
    List<TdManager> findByParentIdOrderBySortIdAsc(Long parentId);
    
    List<TdManager> findByParentTreeContaining(String s);

    // Max
    List<TdManager> findByIsEnableTrueOrderBySortIdAsc();
}
