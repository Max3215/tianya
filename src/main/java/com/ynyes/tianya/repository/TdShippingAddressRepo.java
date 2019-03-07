package com.ynyes.tianya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdShippingAddress;

/**
 * TdShippingAddress 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdShippingAddressRepo extends
		PagingAndSortingRepository<TdShippingAddress, Long>,
		JpaSpecificationExecutor<TdShippingAddress> 
{

	TdShippingAddress findByUserIdAndIsDefaultAddressTrue(Long id);

	List<TdShippingAddress> findByUserIdAndIsDefaultAddressFalse(Long id);

	TdShippingAddress findById(Long id);

	List<TdShippingAddress> findByUserId(Long id);
}
