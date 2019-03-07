package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdBargainRecord;

public interface TdBargainRecordRepo extends
PagingAndSortingRepository<TdBargainRecord, Long>,
JpaSpecificationExecutor<TdBargainRecord>{
	List<TdBargainRecord> findByMobile(String mobile);
	
	Page<TdBargainRecord> findByMobile(String mobile, Pageable page);
	
	Long countByMobile(String mobile);
}
