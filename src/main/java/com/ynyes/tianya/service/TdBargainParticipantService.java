package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdBargainParticipant;
import com.ynyes.tianya.repository.TdBargainParticipantRepo;

@Service
@Transactional
public class TdBargainParticipantService {
	@Autowired
    TdBargainParticipantRepo repository;
	
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
    public void delete(TdBargainParticipant e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdBargainParticipant> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdBargainParticipant save(TdBargainParticipant e)
    {       
        return repository.save(e);
    }
    
    public List<TdBargainParticipant> save(List<TdBargainParticipant> entities)
    {
        
        return (List<TdBargainParticipant>) repository.save(entities);
    }
    
    // 查询
    public TdBargainParticipant findOne(Long id){
    	return repository.findOne(id);
    }
    
    public TdBargainParticipant findByMobile(String mobile){
    	return repository.findByMobile(mobile);
    }
    
    public Page<TdBargainParticipant> findAll(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
    	return repository.findAll(pageRequest);
    }
}
