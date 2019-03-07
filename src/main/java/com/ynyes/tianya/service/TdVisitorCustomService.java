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

import com.ynyes.tianya.entity.TdTag;
import com.ynyes.tianya.entity.TdVisitorCustom;
import com.ynyes.tianya.repository.TdVisitorCustomRepo;

/**
 * 客户服务类
 * 
 * @author gl
 *
 */

@Service
@Transactional
public class TdVisitorCustomService {

	@Autowired
	TdVisitorCustomRepo repository;
	
	/**
	 * 查看游客定制
	 */
	public void goCheck(){
		List<TdVisitorCustom> tvcl = (List<TdVisitorCustom>) repository.findAll();
		for(TdVisitorCustom tvc : tvcl){
			tvc.setAreadyCheck(true);
			repository.save(tvc);
		}
	}
	
	/**
	 * 待查看游客定制数量 
	 */
	public int getNewVisitorCustomNum(){
		int num = 0;
		List<TdVisitorCustom> tvcl = (List<TdVisitorCustom>) repository.findAll();
		for(TdVisitorCustom tvc : tvcl){
			if(tvc.getAreadyCheck() != null && !tvc.getAreadyCheck()){
				num ++;
			}
		}
		return num;
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public void delete(TdVisitorCustom tvc){
		if(null != tvc){
			repository.delete(tvc);
		}
	}
	
	public void delete(List<TdVisitorCustom> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
	
	public TdVisitorCustom findOne(Long id)
	{
		if(null == id){
			
			return null;
		}
		
		return repository.findOne(id);
	}
	
	public Page<TdVisitorCustom> findAllBySortIdAsc(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.ASC,"sortId"));
		return repository.findAll(pageRequest);
	}
	
	public TdVisitorCustom save(TdVisitorCustom e)
	{
		return repository.save(e);
	}
	
	public Page<TdVisitorCustom> findAllByCustomTimeDesc(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"customTime"));
		return repository.findAll(pageRequest);
	}
	
	/**
	 * @author Max
	 */
	public List<TdVisitorCustom> findAll(){
		return (List<TdVisitorCustom>) repository.findAll();
	}

	
	


}
