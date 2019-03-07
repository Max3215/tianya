package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdGoodsPs;
import com.ynyes.tianya.repository.TdGoodsPsRepo;

/**
 * TdGoodsPs 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdGoodsPsService {
    @Autowired
    TdGoodsPsRepo repository;
    
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
    public void delete(TdGoodsPs e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdGoodsPs> entities)
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
    public TdGoodsPs findOne(Long id)
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
    public List<TdGoodsPs> findAll(Iterable<Long> ids)
    {
        return (List<TdGoodsPs>) repository.findAll(ids);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdGoodsPs save(TdGoodsPs e)
    {
        
        return repository.save(e);
    }
    
    public List<TdGoodsPs> save(List<TdGoodsPs> entities)
    {
        
        return (List<TdGoodsPs>) repository.save(entities);
    }
}
