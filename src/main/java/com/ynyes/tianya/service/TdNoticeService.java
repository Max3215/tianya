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

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdNotice;
import com.ynyes.tianya.repository.TdNoticeRepo;

/**
 * TdAd 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdNoticeService {
    
    @Autowired
    TdNoticeRepo repository;
    
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
     * 待确认通知数量
     * 未确认的id为正 
     */
    public int getToSureNoticeNum(Long managerId){
    	List<TdNotice> nl = (List<TdNotice>) repository.findAll();
    	int num = 0;
    	for(TdNotice n : nl){
    		String ns = n.getNoticedIds();
    		String[] nsa = ns.split(",");
    		for(String s : nsa){
    			s = s.replace("[", "");
    			s =  s.replace("]", "");
    			Long sl = Long.parseLong(s);
    			if(sl == managerId){
    				num ++;
    				break;
    			}
    		}
    	}
    	return num;
    } 
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdNotice e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdNotice findOne(Long id)
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
    public List<TdNotice> findAll(Iterable<Long> ids)
    {
        return (List<TdNotice>) repository.findAll(ids);
    }
    
    public List<TdNotice> findAll()
    {
        return (List<TdNotice>) repository.findAll();
    }
    // 我发出的
    public Page<TdNotice> findByNoticeId(Long id, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "createTime"));
    	if(id != null){
    		return repository.findByNoticeId(id, pageRequest);
    	}else{
    		return null;
    	}
    }
    
    // 我收到的
    public Page<TdNotice> findByNoticedIdsContaining(Long id, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "createTime"));
    	if(id != null){
    		String idStr = id + "]";
    		return repository.findByNoticedIdsContaining(idStr, pageRequest);
    	}else{
    		return null;
    	}
    }
    
    public TdNotice save(TdNotice n){
    	return repository.save(n);
    }
    
}
