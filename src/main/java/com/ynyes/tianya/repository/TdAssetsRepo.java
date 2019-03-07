package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdAssets;
/**
 * 资产调节表数据接口
 * @author Max
 *
 */
public interface TdAssetsRepo extends 
	PagingAndSortingRepository<TdAssets, Long>,
	JpaSpecificationExecutor<TdAssets>{
	
	
	Page<TdAssets> findByUseDateAfter(Date startTime,Pageable page);
	Page<TdAssets> findByUseDateBefore(Date endTime,Pageable page);
	Page<TdAssets> findByUseDateBetween(Date startTime,Date endTime,Pageable page);
	
	Page<TdAssets> findByIsSettlement(boolean isSettlement,Pageable page);
	Page<TdAssets> findByUseDateAfterAndIsSettlement(Date startTime,boolean isSettlement,Pageable page);
	Page<TdAssets> findByUseDateBeforeAndIsSettlement(Date endTime,boolean isSettlement,Pageable page);
	Page<TdAssets> findByUseDateBetweenAndIsSettlement(Date startTime,Date endTime,boolean isSettlement,Pageable page);
	
	Page<TdAssets> findByManagerTreeContaining(String managerId,Pageable page);
	List<TdAssets> findByManagerTreeContaining(String managerId);
	Page<TdAssets> findByManagerTreeContainingAndUseDateBefore(String managerId,Date endTime,Pageable page);
	Page<TdAssets> findByManagerTreeContainingAndUseDateAfter(String managerId,Date endTime,Pageable page);
	Page<TdAssets> findByManagerTreeContainingAndUseDateBetween(String managerId,Date startTime,Date endTime,Pageable page);
	
	Page<TdAssets> findByManagerTreeContainingAndIsSettlement(String managerId,boolean isSettlement,Pageable page);
	Page<TdAssets> findByManagerTreeContainingAndUseDateBeforeAndIsSettlement(String managerId,Date endTime,boolean isSettlement,Pageable page);
	Page<TdAssets> findByManagerTreeContainingAndUseDateAfterAndIsSettlement(String managerId,Date endTime,boolean isSettlement,Pageable page);
	Page<TdAssets> findByManagerTreeContainingAndUseDateBetweenAndIsSettlement(String managerId,Date startTime,Date endTime,boolean isSettlement,Pageable page);
	
	int countByManagerId(Long managerId);
	
}
