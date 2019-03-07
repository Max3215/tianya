package com.ynyes.tianya.service;

import java.util.List;

import org.hibernate.metamodel.binding.PluralAttributeBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdBargainParticipant;
import com.ynyes.tianya.entity.TdBargainRecord;
import com.ynyes.tianya.repository.TdBargainRecordRepo;

@Service
@Transactional
public class TdBargainRecordService {
	@Autowired
	TdBargainRecordRepo repository;
	
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
    public void delete(TdBargainRecord e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdBargainRecord> entities)
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
    public TdBargainRecord save(TdBargainRecord e)
    {       
        return repository.save(e);
    }
    
    public List<TdBargainRecord> save(List<TdBargainRecord> entities)
    {
        
        return (List<TdBargainRecord>) repository.save(entities);
    }
    
    // 查询记录
    public List<TdBargainRecord> findByMobile(String mobile){
    	return repository.findByMobile(mobile);
    }
    
    public Page<TdBargainRecord> findByMobile(String mobile, int page ,int size){
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
    	return repository.findByMobile(mobile, pageRequest);
    }
    
    public Long countByMobile(String mobile){
    	return repository.countByMobile(mobile);
    }
    
    public Page<TdBargainRecord> findAll(int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
    	return repository.findAll(pageRequest);
    }
}
