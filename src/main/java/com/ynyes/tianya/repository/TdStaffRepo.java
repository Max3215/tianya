package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdStaff;
/**
 * 职工表数据库操作接口
 * @author Max
 *
 */
public interface TdStaffRepo extends
	PagingAndSortingRepository<TdStaff, Long>,
	JpaSpecificationExecutor<TdStaff>{

	TdStaff findByUsername(String username);
	TdStaff findByUsernameAndCompanyId(String username,Integer companyId);
	TdStaff findByUsernameAndIdNot(String username,Long id);
}
