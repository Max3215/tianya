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
import com.ynyes.tianya.entity.TdUserTravelNotes;
import com.ynyes.tianya.repository.TdUserTravelNotesRepo;

/**
 * TdAd 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserTravelNotesService {
    @Autowired
    TdUserTravelNotesRepo repository;

	public Page<TdUserTravelNotes> findByUsername(String username,Long type,int page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsernameAndType(username,type,pageRequest);
	}
	// gl
	public Page<TdUserTravelNotes> findByUsernameAndTypeAndTileLikeOrClassifyLike(String username,Long type, String title, String classify, int page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsernameAndTypeAndTitleLikeOrUsernameAndTypeAndClassifyLike(username, type, title, classify, pageRequest);
	}
	public Page<TdUserTravelNotes> findByUsernameAndTypeAndTitleLike(String username,Long type,String title,int page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsernameAndTypeAndTitleLike(username,type,"%"+title+"%",pageRequest);
	}
	public Page<TdUserTravelNotes> findByUsernameAndTypeAndClassifyLike(String username,Long type,String classify,int page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsernameAndTypeAndClassifyLike(username,type,"%"+classify+"%",pageRequest);
	}
	public Page<TdUserTravelNotes> findByUsernameAndTypeAndTitleLikeAndClassifyLike(String username,Long type,String title,String classify,int page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByUsernameAndTypeAndTitleLikeAndClassifyLike(username,type,"%"+title+"%","%"+classify+"%",pageRequest);
	}
	

	public Long countByUsername(String username) {
		if(null==username){
			return null;
		}
		return repository.countByUsername(username);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public Page<TdUserTravelNotes> findByClassify(String classify,Long type, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByClassifyAndTypeOrderByIdDesc(classify,type,pageRequest);
	}

	public Page<TdUserTravelNotes> findByTitle(String title,Long type, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTitleAndTypeOrderByIdDesc(title,type,pageRequest);
	}

	public Page<TdUserTravelNotes> findByTitleAndClassify(String title, String classify,Long type, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTitleAndClassifyAndTypeOrderByIdDesc(title,classify,type,pageRequest);
	}

	public void save(TdUserTravelNotes article) {
		repository.save(article);
	}

	public TdUserTravelNotes findById(Long id) {
		if(null==id){
			return null;
		}
		return repository.findById(id);
	}

	 public Page<TdUserTravelNotes> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
	 
	 public Page<TdUserTravelNotes> findAllOrderBySortId(int page, int size){
		 
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
	     return repository.findAll(pageRequest);
	 }

	 public Page<TdUserTravelNotes> searchAndOrderByIdDesc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrTitleContainingOrClassifyContainingOrderByIdDesc(keywords, keywords, keywords, pageRequest);
    }

	 public Page<TdUserTravelNotes> findByStatusIdOrderByIdDesc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }
	 
	 public Page<TdUserTravelNotes> findByStatusIdOrderBySortIdASC(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.ASC, "sortId"));
        return repository.findByStatusId(statusId, pageRequest);
    }

	public Page<TdUserTravelNotes> searchAndFindByStatusIdOrderByIdDesc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }

	public TdUserTravelNotes findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }

	public Page<TdUserTravelNotes> findByTypeContainingOrderByIdDesc(long l,String keyword, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTypeAndTitleContainingOrderByIdDesc(l,keyword,pageRequest);
	}

	public Page<TdUserTravelNotes> findByTypeOrderByIdDesc(long l, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTypeOrderByIdDesc(l,pageRequest);
	}
	
	public Page<TdUserTravelNotes> findByTypeAndStatusIdOrderByIdDesc(Long type, Long statusId, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTypeAndStatusIdOrderByIdDesc(type, statusId, pageRequest);
	}
	
	public Page<TdUserTravelNotes> findByTypeAndStatusIdOrderBySortIdASC(Long type, Long statusId, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
		return repository.findByTypeAndStatusId(type, statusId, pageRequest);
	}
	
	public Page<TdUserTravelNotes> findByTypeAndStatusIdAndTitleLikeOrderBySortIdASC(Long type, Long statusId, String title, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
		return repository.findByTypeAndStatusIdAndTitleLike(type, statusId,"%"+title+"%", pageRequest);
	}

}
