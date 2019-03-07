package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdManagerApply;
import com.ynyes.tianya.entity.TdProduct;
import com.ynyes.tianya.repository.TdManagerApplyRepo;
/**
 * 员工申请服务类
 * @author gl
 *
 */
@Service
@Transactional
public class TdManagerApplyService {
	
	@Autowired
	TdManagerApplyRepo repository;
	
	public TdManagerApply findById(Long id){
		if(id != null){
			return repository.findOne(id);
		}else{
			return null;
		}
	}
	
	public int getNewApplyNum(Long managerId){
		List<TdManagerApply> mal = (List<TdManagerApply>) repository.findAll(); 
		int num = 0;
		// 我待审核的的审核人Id链的第一个负数是我的Id
		for(TdManagerApply ma : mal){
			String cms = ma.getCheckManagerStr();
			String[] cma = cms.split(",");
			for(String s : cma){
				if(s.indexOf("-") > -1){
					Long id = Math.abs(Long.parseLong(s));
					if(id == managerId){
						num ++;
					}
					break;
				}
			}
		}
		return num;
	}
	
	
	public Page<TdManagerApply> findByManagerId(Long managerId, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		if(managerId != null){
			return repository.findByManagerIdOrderByApplyTimeDesc(managerId, pageRequest);			
		}else{
			return null;
		}
	}
	
	
	public Page<TdManagerApply> findByManagerIdAndCheckManagerStrContains(Long managerId, String keyword, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		//return repository.findByManagerIdAndCheckManagerStrContainingOrderByApplyTimeAsc(managerId, keyword, pageRequest);
		return repository.findByManagerIdAndCheckManagerStrLikeOrderByApplyTimeDesc(managerId, keyword, pageRequest);
		
	}
	
	public Page<TdManagerApply> findByManagerIdAndCheckManagerStrNotContains(Long managerId, String keyword, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByManagerIdAndCheckManagerStrNotLikeOrderByApplyTimeDesc(managerId, keyword, pageRequest);
	}
	
	public Page<TdManagerApply> findByCheckManagerStrContains(String keyword, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		//return repository.findByCheckManagerStrContainingOrderByApplyTimeAsc(keyword, pageRequest);
		return repository.findByCheckManagerStrLikeOrderByApplyTimeDesc(keyword, pageRequest);
	}
	
	public Page<TdManagerApply> findByCheckManagerStrContainsAndNotRefuse(String keyword, String state, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		//return repository.findByCheckManagerStrContainingOrderByApplyTimeAsc(keyword, pageRequest);
		//return repository.findByCheckManagerStrLikeOrderByApplyTimeDesc(keyword, pageRequest);
		return repository.findByCheckManagerStrLikeAndStateNotLikeOrderByApplyTimeDesc(keyword, state, pageRequest);
	}
	
	public Page<TdManagerApply> findByCheckManagerStrLikeOrCheckManagerStrLikeAndRefuse(String keyword, String keyword2, String state, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size);
		//return repository.findByCheckManagerStrContainingOrderByApplyTimeAsc(keyword, pageRequest);
		return repository.findByCheckManagerStrLikeOrCheckManagerStrLikeAndStateLikeOrderByApplyTimeDesc(keyword, keyword2, state, pageRequest);
	}
	

	
	
	public TdManagerApply save(TdManagerApply tma){
		return repository.save(tma);
	}
	
	
	
	
	public List<TdManagerApply> findAllManagerApplys(){
		return (List<TdManagerApply>) repository.findAll();
	}
	
	
	public List<TdManagerApply> findByManagerId(Long managerId){
		return repository.findByManagerId(managerId);
	}
	
	public void delete(Long id){
		if(id != null){
			repository.delete(id);
		}
	}
}
