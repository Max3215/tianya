package com.ynyes.tianya.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdTravelPraise;
import com.ynyes.tianya.repository.TdAdRepo;
import com.ynyes.tianya.repository.TdTravelPraiseRepo;

/**
 * TdTravelPraise 服务类
 * 
 * @author lulu
 *
 */

@Service
@Transactional
public class TdTravelPraiseService {
    
    @Autowired
    TdTravelPraiseRepo repository;

	public void save(TdTravelPraise travelPraise) {
		// TODO Auto-generated method stub
		repository.save(travelPraise);
	}

	public TdTravelPraise findByNameAndTravelNotesId(String username,Long id) {
		// TODO Auto-generated method stub
		return repository.findByNameAndTravelNotesId(username,id);
	}

}
