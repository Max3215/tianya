package com.ynyes.tianya.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdNaviBarItem;
import com.ynyes.tianya.repository.TdNaviBarItemRepo;

/**
 * TdNaviBarItem 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdNaviBarItemService {
    
    @Autowired
    TdNaviBarItemRepo repository;
    
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
    public void delete(TdNaviBarItem e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdNaviBarItem> entities)
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
    public TdNaviBarItem findOne(Long id)
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
    public List<TdNaviBarItem> findAllOrderBySortIdAsc()
    {
    	List<TdNaviBarItem> resList = new ArrayList<TdNaviBarItem>();
        List<TdNaviBarItem> topList = (List<TdNaviBarItem>) repository.findByParentIdIsNullOrderBySortIdAsc();
        
        for (TdNaviBarItem top : topList)
        {
            resList.add(top);
            
            List<TdNaviBarItem> childList = repository.findByParentIdOrderBySortIdAsc(top.getId());
            
            if (null != childList && childList.size() > 0)
            {
                for (TdNaviBarItem child : childList)
                {
                    resList.add(child);
                    
                    List<TdNaviBarItem> grandChildList = repository.findByParentIdOrderBySortIdAsc(child.getId());
                    
                    if (null != grandChildList && grandChildList.size() > 0)
                    {
                        resList.addAll(grandChildList);
                    }
                }
            }
        }
        
        return resList;
    }
    
    public List<TdNaviBarItem> findByIsEnableTrueOrderBySortIdAsc()
    {
        List<TdNaviBarItem> resList = new ArrayList<TdNaviBarItem>();
        List<TdNaviBarItem> topList = repository.findByParentIdIsNullAndIsEnableTrueOrderBySortIdAsc();
        
        for (TdNaviBarItem top : topList)
        {
            resList.add(top);
            
            List<TdNaviBarItem> childList = repository.findByParentIdAndIsEnableTrueOrderBySortIdAsc(top.getId());
            
            if (null != childList && childList.size() > 0)
            {
                for (TdNaviBarItem child : childList)
                {
                    resList.add(child);
                    
                    List<TdNaviBarItem> grandChildList = repository.findByParentIdAndIsEnableTrueOrderBySortIdAsc(child.getId());
                    
                    if (null != grandChildList && grandChildList.size() > 0)
                    {
                        resList.addAll(grandChildList);
                    }
                }
            }
        }
        
        return resList;
    }
    
    public List<TdNaviBarItem> findByIsEnableTrueAndIsTouchShowTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueAndIsTouchShowTrueOrderBySortIdAsc();
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdNaviBarItem save(TdNaviBarItem e)
    {
    	if (null == e)
        {
            return null;
        }
        
        // 设置layerCount和parentTree
        e = repository.save(e); // 保存后才会有ID值
        
        if (null == e.getParentId() || e.getParentId().equals(0L))
        {
            e.setLayerCount(1L);
        }
        else
        {
        	TdNaviBarItem parent = repository.findOne(e.getParentId());
            e.setLayerCount(parent.getLayerCount() + 1L);
        }
        
        return repository.save(e);
    }
    
    public List<TdNaviBarItem> save(List<TdNaviBarItem> entities)
    {
        
        return (List<TdNaviBarItem>) repository.save(entities);
    }
}
