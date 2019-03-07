package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdOutMoneyItem;
import com.ynyes.tianya.repository.TdOutMoneyItemRepo;
/**
 * 客户支出项目服务类
 * @author Administrator
 *
 */
@Service
@Transactional
public class TdOutMoneyItemService {
	
	@Autowired
	TdOutMoneyItemRepo repository;
	
	public TdOutMoneyItem save(TdOutMoneyItem omi)
    {	
        return repository.save(omi);
    }
	
	public List<TdOutMoneyItem> tdOutMoneyItemListByClientId(Long clientId){
		return repository.findByClientId(clientId);
	} 
	
	public void deleteOutMoneyItem(Long id){
		if(id != null){
			repository.delete(id);
		}
	}

	public List<TdOutMoneyItem> findByClientId(Long id) {
		if(null==id){
			return null;
		}
		return repository.findByClientId(id);
	}
	
}
