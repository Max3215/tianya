package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdUserTravelNotes;

/**
 * TdAd 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserTravelNotesRepo extends
		PagingAndSortingRepository<TdUserTravelNotes, Long>,
		JpaSpecificationExecutor<TdUserTravelNotes> 
{

	Page<TdUserTravelNotes> findByUsernameAndType(String username,Long type,Pageable pageRequest);
	//gl
	Page<TdUserTravelNotes> findByUsernameAndTypeAndTitleLikeOrUsernameAndTypeAndClassifyLike(String username,Long type, String title,  String classify, Pageable pageRequest);
	
	Page<TdUserTravelNotes> findByUsernameAndTypeAndTitleLike(String username, Long type, String title, Pageable pageRequest);
	Page<TdUserTravelNotes> findByUsernameAndTypeAndClassifyLike(String username, Long type, String classify, Pageable pageRequest);
	Page<TdUserTravelNotes> findByUsernameAndTypeAndTitleLikeAndClassifyLike(String username, Long type, String title, String classify, Pageable pageRequest);
	
	

	Long countByUsername(String username);

	TdUserTravelNotes findByTitle(String text1);

	Page<TdUserTravelNotes> findByClassifyAndTypeOrderByIdDesc(String classify,Long type, Pageable pageRequest);

	Page<TdUserTravelNotes> findByTitleAndTypeOrderByIdDesc(String title,Long type, Pageable pageRequest);

	Page<TdUserTravelNotes> findByTitleAndClassifyAndTypeOrderByIdDesc(String title, String classify,Long type, Pageable pageRequest);

	TdUserTravelNotes findById(Long id);

	Page<TdUserTravelNotes> findByUsernameContainingOrTitleContainingOrClassifyContainingOrderByIdDesc(String keywords,
			String keywords2, String keywords3, Pageable pageRequest);

	Page<TdUserTravelNotes> findByStatusIdOrderByIdDesc(Long statusId, Pageable pageRequest);
	
	// gl
	Page<TdUserTravelNotes> findByStatusId(Long statusId, Pageable pageRequest);
	
	Page<TdUserTravelNotes> findByUsernameContainingAndStatusIdOrTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(
			String keywords, Long statusId, String keywords2, Long statusId2, String keywords3, Long statusId3,
			Pageable pageRequest);

	Page<TdUserTravelNotes> findByTypeAndTitleContainingOrderByIdDesc(long l, String keyword,Pageable pageRequest);

	Page<TdUserTravelNotes> findByTypeOrderByIdDesc(long l, Pageable pageRequest);
	//gl
	Page<TdUserTravelNotes> findByTypeAndStatusIdOrderByIdDesc(Long type, Long status, Pageable pageRequest);
	
	Page<TdUserTravelNotes> findByTypeAndStatusId(Long type, Long status, Pageable pageRequest);
	Page<TdUserTravelNotes> findByTypeAndStatusIdAndTitleLike(Long type, Long status, String title, Pageable pageRequest);


}
