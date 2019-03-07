package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdDemand;
/**
 * TdDemand  实体数据库操作接口
 * @author Zhangji
 *
 */
public interface TdDemandRepo extends 
           PagingAndSortingRepository<TdDemand, Long>,
           JpaSpecificationExecutor<TdDemand> 
{
   TdDemand findByStatusId(Long statusId);
   Page<TdDemand> findByStatusIdOrderByIdDesc(Long statusId,Pageable pageRequest);
   
   Page<TdDemand> findByNameOrderByTimeDesc(String name, Pageable pageRequest);
   
   
} 
