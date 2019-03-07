package com.ynyes.tianya.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdCustomer;

/**
 * 客户管理数据接口
 * @author Max
 *
 */
public interface TdCustomerRepo extends
	PagingAndSortingRepository<TdCustomer, Long>,
	JpaSpecificationExecutor<TdCustomer>{
	
	
	Page<TdCustomer> findByBirthdayBefore(Date endTime,Pageable page);
	Page<TdCustomer> findByBirthdayAfter(Date startTime,Pageable page);
	Page<TdCustomer> findByBirthdayBetween(Date startTime,Date endTime,Pageable page);

	
}
