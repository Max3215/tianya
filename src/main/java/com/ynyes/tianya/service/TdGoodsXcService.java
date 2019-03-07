package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdGoodsPs;
import com.ynyes.tianya.entity.TdGoodsXc;
import com.ynyes.tianya.repository.TdGoodsXcRepo;

/**
 * TdGoodsXc 服务类
 * 
 * @author lulu
 *
 */

@Service
@Transactional
public class TdGoodsXcService {
    @Autowired
    TdGoodsXcRepo repository;
    
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
    public void delete(TdGoodsXc e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdGoodsXc> entities)
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
    public TdGoodsXc findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdGoodsXc> findAll(Iterable<Long> ids)
    {
        return (List<TdGoodsXc>) repository.findAll(ids);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdGoodsXc save(TdGoodsXc e)
    {
        
        return repository.save(e);
    }
    
    public List<TdGoodsXc> save(List<TdGoodsXc> entities)
    {
        
        return (List<TdGoodsXc>) repository.save(entities);
    }
}
