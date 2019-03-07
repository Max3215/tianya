package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdBargainSetting;
import com.ynyes.tianya.repository.TdBargainSettingRepo;

@Service
@Transactional
public class TdBargainSettingService {
	@Autowired
	TdBargainSettingRepo repository;
	
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
    public void delete(TdBargainSetting e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdBargainSetting> entities)
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
    public TdBargainSetting save(TdBargainSetting e)
    {       
        return repository.save(e);
    }
    
    public List<TdBargainSetting> save(List<TdBargainSetting> entities)
    {
        
        return (List<TdBargainSetting>) repository.save(entities);
    }
    
    // 查询
    public TdBargainSetting findTopBy()
    {
        return repository.findTopBy();
    }
    
    public TdBargainSetting findOne(Long id){
    	return repository.findOne(id);
    }
    
}
