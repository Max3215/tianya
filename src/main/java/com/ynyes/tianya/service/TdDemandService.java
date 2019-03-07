package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.repository.TdDemandRepo;

/**
 * TdDemand 服务类
 * 
 * @author Zhangji
 *
 */

@Service
@Transactional
public class TdDemandService {
    @Autowired
    TdDemandRepo repository;
    
    /**
     * 未完成私家定制的数量 
     */
    public int getNotReplyDemandNum(){
    	int num = 0;
    	List<TdDemand> tdl = (List<TdDemand>) repository.findAll();
    	for(TdDemand td : tdl){
    		if(td.getIsReplied() != null && !td.getIsReplied()){
    			num ++;
    		}
    	}
    	return num;
    }

    public List<TdDemand> findAll() {
        return (List<TdDemand>) repository.findAll();
    }

    public List<TdDemand> findByNameOrderByTimeDesc() {

        return (List<TdDemand>) repository.findAll();
    }
    

    /*// 筛选statusId为1L的
    public List<TdDemand> findByStatusIdAndIsShowable() {
        return repository.findByStatusIdOrderByIdDesc(1L);
    }*/

    public TdDemand findOne(Long id) {
        if (null == id) {
            return null;
        }

        return repository.findOne(id);
    }

    public Page<TdDemand> findAllOrderByTimeDesc(int page, int size) {

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "time"));
        return repository.findAll(pageRequest);
    }
    
    public Page<TdDemand> findByNameOrderByTimeDesc(String name, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        return repository.findByNameOrderByTimeDesc(name, pageRequest);
        
        //return repository.findByUsernameOrderByIdDesc(name, pageRequest);
    }

    /**
     * 添加
     */

    public void save(TdDemand e) {
        repository.save(e);
    }

    public List<TdDemand> save(List<TdDemand> entities) {

        return (List<TdDemand>) repository.save(entities);
    }

    /**
     * 删除
     */
    public void delete(Long id) {
        if (null != id) {
            repository.delete(id);
        }
    }

	public Page<TdDemand> findByStatusIdOrderByIdDesc(Long l,Integer page,Integer size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByStatusIdOrderByIdDesc(l,pageRequest);
	}
	
	
}
