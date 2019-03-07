package com.ynyes.tianya.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdUserVisitor;
import com.ynyes.tianya.repository.TdUserVisitorRepo;

/**
 * TdUserVisitor 服务类
 * 
 * @author lulu
 *
 */

@Service
@Transactional
public class TdUserVisitorService {
    
    @Autowired
    TdUserVisitorRepo repository;
    
    // Max 
    public TdUserVisitor findOne(Long id)
    {
    	return repository.findOne(id);
    }
    
    // Max
    public List<TdUserVisitor> findByUsernameAndIsSelect(String username)
    {
    	if(null == username)
    	{
    		return null;
    	}
    	return repository.findByUsernameAndIsSelectTrue(username);
    }
    
    
	public List<TdUserVisitor> findByUsername(String username) {
		
		if(null == username){
			return null;
		}
		return repository.findByUsername(username);
	}

	public void save(TdUserVisitor tdUserVisitor) {
		repository.save(tdUserVisitor);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public TdUserVisitor findById(Long id) {
		return repository.findById(id);
	}

	public Page<TdUserVisitor> findByUsername(String username, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsername(username,pageRequest);
	}
    
}
