package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdUserTravelComment;

/**
 * TdAd 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserTravelCommentRepo extends
		PagingAndSortingRepository<TdUserTravelComment, Long>,
		JpaSpecificationExecutor<TdUserTravelComment> 
{

	List<TdUserTravelComment> findByCommentIdAndTypeIdOrderByIdDesc(Long id,Long typeId);

	Page<TdUserTravelComment> findByCommentIdAndTypeIdAndStatusIdOrderByIdDesc(Long id,Long typeId,Long statusId, Pageable pageRequest);

	Page<TdUserTravelComment> findByUsernameOrderByIdDesc(String username, Pageable pageRequest);

	Page<TdUserTravelComment> findByUsernameContainingOrTitleContainingOrContentContainingOrderByIdDesc(String keywords,
			String keywords2, String keywords3, Pageable pageRequest);

	Page<TdUserTravelComment> findByStatusIdOrderByIdDesc(Long statusId, Pageable pageRequest);

	Page<TdUserTravelComment> findByUsernameContainingAndStatusIdOrTitleContainingAndStatusIdOrContentContainingAndStatusIdOrderByIdDesc(
			String keywords, Long statusId, String keywords2, Long statusId2, String keywords3, Long statusId3,
			Pageable pageRequest);
}
