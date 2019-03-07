package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdGuessingGame;

public interface TdGuessingGameRepo extends
PagingAndSortingRepository<TdGuessingGame, Long>,
JpaSpecificationExecutor<TdGuessingGame>
{
	List<TdGuessingGame> findByUsername(String username);
	
	List<TdGuessingGame> findByMobile(String mobile);
}
