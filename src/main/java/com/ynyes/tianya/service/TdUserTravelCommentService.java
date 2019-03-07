package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.entity.TdUserTravelComment;
import com.ynyes.tianya.repository.TdUserTravelCommentRepo;

/**
 * TdAd 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserTravelCommentService {
    
    @Autowired
    TdUserTravelCommentRepo repository;

	public List<TdUserTravelComment> findByCommentIdAndTypeId(Long id,Long typeId) {
		if(null == id){
			return null;
		}
		if(null == typeId){
			return null;
		}
		return repository.findByCommentIdAndTypeIdOrderByIdDesc(id,typeId);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

	public Page<TdUserTravelComment> findByCommentIdAndTypeIdAndStatusId(Long id,Long typeId,Long statusId, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByCommentIdAndTypeIdAndStatusIdOrderByIdDesc(id,typeId,statusId, pageRequest);
	}

	public void save(TdUserTravelComment tdUserTravelComment) {
		// TODO Auto-generated method stub
		repository.save(tdUserTravelComment);
	}

	public Page<TdUserTravelComment> findByUsername(String username, Integer page, int size) {
			PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
	}

	public Page<TdUserTravelComment> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }

	public Page<TdUserTravelComment> searchAndOrderByIdDesc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrTitleContainingOrContentContainingOrderByIdDesc(keywords, keywords, keywords, pageRequest);
    }

	public Page<TdUserTravelComment> findByStatusIdOrderByIdDesc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }

	public Page<TdUserTravelComment> searchAndFindByStatusIdOrderByIdDesc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }

	 public TdUserTravelComment findOne(Long id)
	    {
	        if (null == id)
	        {
	            return null;
	        }
	        
	        return repository.findOne(id);
	    }
    
    
}
