package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdGuessingGame;
import com.ynyes.tianya.repository.TdGuessingGameRepo;

@Service
@Transactional
public class TdGuessingGameService {
	@Autowired
	TdGuessingGameRepo repository;
	
	/**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdGuessingGame save(TdGuessingGame e)
    {
        
        return repository.save(e);
    }
    
    public List<TdGuessingGame> save(List<TdGuessingGame> entities)
    {
        
        return (List<TdGuessingGame>) repository.save(entities);
    }
	
	/**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdGuessingGame e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdGuessingGame> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdGuessingGame findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public List<TdGuessingGame> findAll(Iterable<Long> ids)
    {
        return (List<TdGuessingGame>) repository.findAll(ids);
    }
    
    public List<TdGuessingGame> findAll(){
    	return (List<TdGuessingGame>) repository.findAll();
    }
    
    public Page<TdGuessingGame> findAll(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findAll(pageRequest);
    }
    
    public List<TdGuessingGame> findByUsername(String username){
    	return repository.findByUsername(username);
    }
    
    public List<TdGuessingGame> findByMobile(String mobile){
    	return repository.findByMobile(mobile);
    }
}
