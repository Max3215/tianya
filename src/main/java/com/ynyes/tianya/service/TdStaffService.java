package com.ynyes.tianya.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdStaff;
import com.ynyes.tianya.repository.TdStaffRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;

/**
 * 职工表服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdStaffService {

	@Autowired
	private TdStaffRepo repository;
	
	
	public void delete(Long id)
	{
		if(null != id)
		{
			repository.delete(id);
		}
	}
	
	public TdStaff save(TdStaff e)
	{
		if(null == e)
		{
			return null;
		}
		return repository.save(e);
	}
	
	
	public TdStaff findOne(Long id)
	{
		if(null == id)
		{
			return null;
		}
		return repository.findOne(id);
	}
	
	public TdStaff findByUsername(String username)
	{
		if(null == username)
		{
			return null;
		}
		return repository.findByUsername(username);
	}
	
	public TdStaff findByUsernameAndCompany(String username,Integer companyId)
	{
		if(null == username | null == companyId)
		{
			return null;
		}
		return repository.findByUsernameAndCompanyId(username, companyId);
	}
	
	public TdStaff findByUsernameAndIdNot(String username,Long id)
	{
		if(null == username || null == id)
		{
			return null;
		}
		return repository.findByUsernameAndIdNot(username, id);
	}
	
	public List<TdStaff> findAll()
	{
		return (List<TdStaff>) repository.findAll();
	}
	
	
	public Page<TdStaff> findAll(Integer companyId,String keywords,Boolean isClose,Date start,Date end,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size);
		
		Criteria<TdStaff> c = new Criteria<>();
		if(null != companyId)
		{
			c.add(Restrictions.eq("companyId", companyId, true));
		}
		if(null != keywords)
		{
			c.add(Restrictions.like("username", keywords, true));
		}
		if(null != isClose)
		{
			c.add(Restrictions.eq("isClose", isClose, true));
		}
		if(null != start)
		{
			c.add(Restrictions.gte("accountTime", start, true));
		}
		if(null != end)
		{
			c.add(Restrictions.lte("accountTime", end, true));
		}
		
		return repository.findAll(c,pageRequest);
	}
	
	public List<TdStaff> findAll(Integer companyId,String keywords,Boolean isClose,Date start,Date end)
	{
		
		Criteria<TdStaff> c = new Criteria<>();
		if(null != companyId)
		{
			c.add(Restrictions.eq("companyId", companyId, true));
		}
		if(null != keywords)
		{
			c.add(Restrictions.eq("username", keywords, true));
		}
		if(null != isClose)
		{
			c.add(Restrictions.eq("isClose", isClose, true));
		}
		if(null != start)
		{
			c.add(Restrictions.gte("accountTime", start, true));
		}
		if(null != end)
		{
			c.add(Restrictions.lte("accountTime", end, true));
		}
		
		return repository.findAll(c);
	}
	
	
}
