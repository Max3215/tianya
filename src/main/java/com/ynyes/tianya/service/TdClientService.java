package com.ynyes.tianya.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdClient;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.repository.TdClientRepo;

/**
 * 客户服务类
 * 
 * @author gl
 *
 */

@Service
@Transactional
public class TdClientService {

	@Autowired
	TdClientRepo repository;

	public Page<TdClient> findByManagerId(Long managerId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByManagerIdOrderByCreateTimeAsc(managerId,
				pageRequest);
	}
	
	public Page<TdClient> findByManagerIdIn(List<Long> managerIds, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		if(managerIds != null && managerIds.size() > 0){
			return repository.findByManagerIdInOrderByCreateTimeAsc(managerIds, pageRequest);
		}else{
			return null;
		}
	}
	
	public Page<TdClient> findAll(int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findAll(pageRequest);
	}
	
	public TdClient findById(Long id){
		return repository.findOne(id);
	}
	
	public TdClient save(TdClient c)
    {
        if (null == c.getCreateTime())
        {
            c.setCreateTime(new Date());
        }
        
        return repository.save(c);
    }
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}

	public List<TdClient> findByCreateTimeBetween(Date calendar11, Date calendar22) {
		// TODO Auto-generated method stub
		return repository.findByCreateTimeBetween(calendar11,calendar22);
	}
	


}
