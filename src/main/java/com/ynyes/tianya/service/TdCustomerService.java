package com.ynyes.tianya.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdCustomer;
import com.ynyes.tianya.repository.TdCustomerRepo;

/**
 * 
 * 客户管理服务类
 * @author Max
 *
 */

@Service
@Transactional
public class TdCustomerService {
	
	@Autowired
	private TdCustomerRepo repository;
	
	public TdCustomer save(TdCustomer e)
	{
		if(null == e){
			return null;
		}
		return repository.save(e);
	}
	
	public TdCustomer findOne(Long id)
	{
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public void delete(Long id)
	{
		if(null != id)
		{
			repository.delete(id);
		}
	}
	
	public Page<TdCustomer> findAll(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		
		return repository.findAll(pageRequest);
	}
	
	public Page<TdCustomer> findByBirthdayBefore(Date endTime,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		
		return repository.findByBirthdayBefore(endTime, pageRequest);
	}
	
	public Page<TdCustomer> findByBirthdayAfter(Date startTime,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		
		return repository.findByBirthdayBefore(startTime, pageRequest);
	}
	
	public Page<TdCustomer> findByBirthdayBetween(Date startTime,Date endTime,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		
		return repository.findByBirthdayBetween(startTime, endTime, pageRequest);
	}
	
	
}
