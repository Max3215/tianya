package com.ynyes.tianya.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdBargainSetting;

public interface TdBargainSettingRepo  extends
PagingAndSortingRepository<TdBargainSetting, Long>,
JpaSpecificationExecutor<TdBargainSetting>{
	TdBargainSetting findTopBy();
}
