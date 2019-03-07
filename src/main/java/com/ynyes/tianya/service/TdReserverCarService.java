package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdReserveCar;
import com.ynyes.tianya.repository.TdReserveCarRepo;

/**
 * 汽车约租服务类
 * 
 * @author gl
 *
 */

@Service
@Transactional
public class TdReserverCarService {

	@Autowired
	TdReserveCarRepo repository;
	
	// 查看约租 type=0
	public void goCheckYZ(){
		List<TdReserveCar> rcl = (List<TdReserveCar>) repository.findAll();
		for(TdReserveCar rc : rcl){
			if(rc.getType() != null && rc.getType() == 0){
				rc.setIsCheck(true);
			}
			repository.save(rc);
		}
	}
	
	// 查看代驾 type=1
	public void goCheckDJ(){
		List<TdReserveCar> rcl = (List<TdReserveCar>) repository.findAll();
		for(TdReserveCar rc : rcl){
			if(rc.getType() != null && rc.getType() == 1){
				rc.setIsCheck(true);
			}
			repository.save(rc);
		}
	}
	
	// 未查看约租数量
	public int getUncheckedYZ(){
		int num = 0;
		List<TdReserveCar> rcl = (List<TdReserveCar>) repository.findAll();
		for(TdReserveCar rc : rcl){
			if(rc.getType() != null && rc.getType() == 0){
				if(rc.getIsCheck() != null && !rc.getIsCheck()){
					num ++;
				}
			}
		}
		return num;
	}
	
	// 未查看代驾数量
	public int getUncheckedDJ(){
		int num = 0;
		List<TdReserveCar> rcl = (List<TdReserveCar>) repository.findAll();
		for(TdReserveCar rc : rcl){
			if(rc.getType() != null && rc.getType() == 1){
				if(rc.getIsCheck() != null && !rc.getIsCheck()){
					num ++;
				}
			}
		}
		return num;
	}
	
	public void delete(Long id){
		if(null != id){
			repository.delete(id);
		}
	}
	
	public void delete(TdReserveCar rc){
		if(null != rc){
			repository.delete(rc);
		}
	}
	
	public void delete(List<TdReserveCar> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
	
	public TdReserveCar findOne(Long id)
	{
		if(null == id){
			
			return null;
		}
		
		return repository.findOne(id);
	}
	
	
	
	public TdReserveCar save(TdReserveCar e)
	{
		return repository.save(e);
	}
	
	public Page<TdReserveCar> findAll(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"id"));
		return repository.findAll(pageRequest);
	}
	
	public Page<TdReserveCar> findByType(int type, int page, int size)
	{
		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"id"));
		return repository.findByType(type, pageRequest);
	}

	/**
	 * @author Max
	 * 
	 */
	public List<TdReserveCar> findByType(int type)
	{
		return repository.findByType(type);
	}
	


}
