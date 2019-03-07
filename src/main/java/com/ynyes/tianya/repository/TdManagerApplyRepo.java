package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdManagerApply;

public interface TdManagerApplyRepo extends
		PagingAndSortingRepository<TdManagerApply, Long>,
		JpaSpecificationExecutor<TdManagerApply> {
	
		Page<TdManagerApply> findByManagerIdOrderByApplyTimeDesc(Long managerId, Pageable page);
		
		Page<TdManagerApply> findByCheckManagerStrContainingOrderByApplyTimeDesc(String keyword, Pageable page);
		//替换上面
		Page<TdManagerApply> findByCheckManagerStrLikeOrderByApplyTimeDesc(String keyword, Pageable page);
		
		Page<TdManagerApply> findByCheckManagerStrLikeAndStateNotLikeOrderByApplyTimeDesc(String keyword, String state, Pageable page);
		
		Page<TdManagerApply> findByCheckManagerStrLikeOrCheckManagerStrLikeAndStateLikeOrderByApplyTimeDesc(String keyword, String keyword2, String state, Pageable page);
		
		
		Page<TdManagerApply> findByManagerIdAndCheckManagerStrContainingOrderByApplyTimeDesc(Long managerId, String keyword, Pageable page);
		//替换上面
		Page<TdManagerApply> findByManagerIdAndCheckManagerStrLikeOrderByApplyTimeDesc(Long managerId, String keyword, Pageable page);
		
		Page<TdManagerApply> findByManagerIdAndCheckManagerStrNotLikeOrderByApplyTimeDesc(Long managerId, String keyword, Pageable page);
		
		List<TdManagerApply> findByManagerId(Long managerId);
		
		

}
