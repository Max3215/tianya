package com.ynyes.tianya.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdReserveCar;

public interface TdReserveCarRepo extends
		PagingAndSortingRepository<TdReserveCar, Long>,
		JpaSpecificationExecutor<TdReserveCar> {
	
		Page<TdReserveCar> findByType(Integer type, Pageable pageRequest);
		
		// Max
		List<TdReserveCar> findByType(int type);
	
}
