package com.ynyes.tianya.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynyes.tianya.entity.TdOrderVisitor;
import com.ynyes.tianya.repository.TdOrderVisitorRepo;

/**
 * 
 * @author Max
 *
 */
@Service
@Transactional
public class TdOrderVisitorService {
	
	@Autowired
	private TdOrderVisitorRepo repository;
	
	
	public TdOrderVisitor save(TdOrderVisitor e)
	{
		if(null == e)
		{
			return null;
		}
		return repository.save(e);
	} 
	
	public List<TdOrderVisitor> save(List<TdOrderVisitor> entities)
	{
		return (List<TdOrderVisitor>) repository.save(entities);
	}

	public List<TdOrderVisitor> findByTdOrderId(Long id) {
		// TODO Auto-generated method stub
		return repository.findByTdOrderId(id);
	}
	

}
