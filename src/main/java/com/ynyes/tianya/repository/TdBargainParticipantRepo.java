package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdBargainParticipant;

public interface TdBargainParticipantRepo extends
PagingAndSortingRepository<TdBargainParticipant, Long>,
JpaSpecificationExecutor<TdBargainParticipant>{
	TdBargainParticipant findByMobile(String mobile);
}
