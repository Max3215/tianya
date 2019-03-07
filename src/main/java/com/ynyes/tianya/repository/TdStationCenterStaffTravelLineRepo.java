package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdStationCenterStaffTravelLine;

public interface TdStationCenterStaffTravelLineRepo
		extends PagingAndSortingRepository<TdStationCenterStaffTravelLine, Long>,
		JpaSpecificationExecutor<TdStationCenterStaffTravelLine> {
	
	List<TdStationCenterStaffTravelLine> findByClientId(Long ClientId);

	Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeBetween(Long managerId,
			Boolean isSettle, Date calendar1, Date calendar2, Pageable page);
	
	Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeAfter(Long managerId,
			Boolean isSettle, Date calendar1, Pageable page);
	
	Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeBefore(Long managerId,
			Boolean isSettle, Date calendar2, Pageable page);
	
	Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettle(Long managerId,
			Boolean isSettle, Pageable page);

}
