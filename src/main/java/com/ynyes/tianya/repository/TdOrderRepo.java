package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdProvider;

/**
 * TdOrder 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdOrderRepo extends
		PagingAndSortingRepository<TdOrder, Long>,
		JpaSpecificationExecutor<TdOrder> 
{
    @Query("select o from TdOrder o join o.orderGoodsList g where o.username=?1 and g.goodsId=?2")
    List<TdOrder> findByUsernameAndGoodsId(String username, Long goodsId);
    
    Page<TdOrder> findByStatusIdOrderByIdDesc(Long statusId, Pageable page);
    
    Page<TdOrder> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    //Page<TdOrder> findByUsernameAndOrderTypeOrderByIdDesc(String username, String orderType, Pageable page);
    
    
    Page<TdOrder> findByUsernameAndOrderTimeAfterOrderByIdDesc(String username, Date time, Pageable page);
    
    Page<TdOrder> findByUsernameAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String username, Date time, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndOrderNumberContainingOrderByIdDesc(String username, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdOrderByIdDesc(String username, Long statusId, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderNumberContainingOrderByIdDesc(String username, Long statusId, String keywords, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderTimeAfterOrderByIdDesc(String username, Long statusId, Date time, Pageable page);
    
    Page<TdOrder> findByUsernameAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String username, Long statusId, Date time, String keywords, Pageable page);
  
    Long countByUsernameAndStatusId(String username, Long statusId);
    
    Long countByUsername(String username);
    
    TdOrder findByOrderNumber(String orderNumber);
    
    /**
	 * @author lichong
	 * @注释：同盟店订单查询
	 */
    Page<TdOrder> findByshopTitleOrderByIdDesc(String diystiename, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderNumberContainingOrderByIdDesc(String diystiename, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderNumberContainingOrderByIdDesc(String diystiename, Long statusId, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdOrderByIdDesc(String diystiename, Long statusId, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderTimeAfterOrderByIdDesc(String diystiename, Date time, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String diystiename, Long statusId, Date time, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(String diystiename, Date time, String keywords, Pageable page);
    Page<TdOrder> findByshopTitleAndStatusIdAndOrderTimeAfterOrderByIdDesc(String diystiename, Long statusId, Date time, Pageable page);
    
    /**
	 * @author lc
	 * @注释：同盟店信息查询
	 */
    List<TdOrder> findByShopIdAndStatusIdOrderByIdDesc(long shopId, long statusId);
    
    /**
	 * @author lc
	 * @注释：同盟店订单收入查询
	 */
    List<TdOrder> findByStatusIdAndShopTitleOrStatusIdAndShopTitle(Long statusId, String diystiename, Long statusId1, String diystiename1);
    Page<TdOrder> findByStatusIdAndShopTitleOrStatusIdAndShopTitleOrderByIdDesc(Long statusId, String diystiename,  Long statusId1, String diystiename1, Pageable page);
    
    List<TdOrder> findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(Long statusId, String diystiename, Date time, Long statusId1, String diystiename1, Date time1);
    Page<TdOrder> findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(Long statusId, String diystiename, Date time, Long statusId1, String diystiename1, Date time1, Pageable page);
    
    /**
	 * @author lc
	 * @注释：同盟店返利收入
	 */
    List<TdOrder> findByUsernameIn(List<String> tdUsers);
    Page<TdOrder> findByUsernameInOrderByIdDesc(List<String> tdUsers , Pageable page);
    
    List<TdOrder> findByUsernameInAndOrderTimeAfterOrderByIdDesc(List<String> tdUsers,  Date time);
    Page<TdOrder> findByUsernameInAndOrderTimeAfterOrderByIdDesc(List<String> tdUsers,  Date time, Pageable page);
    /**
	 * @author lc
	 * @注释：按订单类型和状态查询
	 */
    Page<TdOrder> findByStatusIdAndTypeIdOrderByIdDesc(long statusId, long typeId, Pageable page);
    List<TdOrder> findByStatusIdAndTypeIdOrderByIdDesc(long statusId, long typeId);
    Page<TdOrder> findByStatusIdOrderByIdDesc(long statusId, Pageable page);
    List<TdOrder> findByStatusIdOrderByIdDesc(long statusId);
    Page<TdOrder> findBytypeIdOrderByIdDesc(long typeId, Pageable page);
    List<TdOrder> findBytypeIdOrderByIdDesc(long typeId);
    /**
	 * @author lc
	 * @注释： 按时间、订单类型和订单状态查询
	 */
    Page<TdOrder> findByOrderTimeAfterOrderByIdDesc(Date time, Pageable page);
    List<TdOrder> findByOrderTimeAfterOrderByIdDesc(Date time);
    Page<TdOrder> findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(long statusId, long typeId, Date time, Pageable page);
    List<TdOrder> findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(long statusId, long typeId, Date time);
    Page<TdOrder> findByStatusIdAndOrderTimeAfterOrderByIdDesc(long statusId, Date time, Pageable page);
    List<TdOrder> findByStatusIdAndOrderTimeAfterOrderByIdDesc(long statusId, Date time);
    Page<TdOrder> findBytypeIdAndOrderTimeAfterOrderByIdDesc(long typeId, Date time, Pageable page);
    List<TdOrder> findBytypeIdAndOrderTimeAfterOrderByIdDesc(long typeId, Date time);
    /**
     * 按交易状态查询
     * @author libiao
     */
    List<TdOrder> findByStatusId(Long statusId);
//    List<TdOrder> findAll();
    /**
     * 按订单号查询
     * @author libiao
     */
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCaseOrOrderTypeContainingIgnoreCase(String orderNumber, String username, String payTypeTitle,String orderType, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCaseOrOrderTypeContainingIgnoreCaseOrderByIdDesc(String orderNumber, String username, String payTypeTitle,String orderType, long type);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdOrUsernameContainingIgnoreCaseAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndTypeIdOrOrderTypeContainingIgnoreCaseAndTypeId(String orderNumber, Long typeId1, String username, Long typeId2, String payTypeTitle, Long typeId3,String orderType, Long typeId4, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrOrderTypeContainingIgnoreCaseAndStatusIdOrderByIdDesc(String orderNumber, Long statusId1, String username, Long statusId2, String payTypeTitle, Long statusId3,String orderType,Long statusId4);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrOrderTypeContainingIgnoreCaseAndStatusIdOrderByIdDesc(String orderNumber,Long statusId1, String username, Long statusId2, String payTypeTitle, Long statusId3, String orderType, Long statusId4,Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdOrderByIdDesc(String orderNumber, Long statusId, Long typeId, String username, Long statusId2, Long typeId2, String payTypeTitle, Long statusId3, Long typeId3,String orderType, Long statusId4, Long typeId4);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeId(String orderNumber, Long statusId, Long  typeId, String username, Long statusId2, Long  typeId2, String payTypeTitle, Long statusId3, Long  typeId3, String orderType, Long statusId4, Long  typeId4, Pageable pageRequest);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndOrderTimeAfterOrderByIdDesc(String orderNumber, Date time, String orderNumber2, Date time2, String orderNumber3, Date time3,  String orderNumber4, Date time4);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndOrderTimeAfter(String orderNumber,Date time, String orderNumber2,Date time2, String orderNumber3,Date time3, String orderNumber4,Date time4,Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long typeId, Date time, String orderNumber2, Long typeId2, Date time2, String orderNumber3, Long typeId3, Date time3,String orderNumber4, Long typeId4, Date time4);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndTypeIdAndOrderTimeAfter(String orderNumber, Long typeId, Date time, String orderNumber2, Long typeId2, Date time2, String orderNumber3, Long typeId3, Date time3,String orderNumber4, Long typeId4, Date time4, Pageable page);
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long statusId, Date time,String orderNumber4, Long statusId4, Date time4, String orderNumber2, Long statusId2, Date time2, String orderNumber3, Long statusId3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndOrderTimeAfter(String OrderNumber, Long statusId, Date time,String OrderNumber4, Long statusId4, Date time4, String OrderNumber2, Long statusId2, Date time2, String OrderNumber3, Long statusId3, Date time3, Pageable page);
    
    List<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(String orderNumber, Long statusId, Long typeId, Date time, String orderNumber4, Long statusId4, Long typeId4, Date time4,String orderNumber2, Long statusId2, Long typeId2, Date time2, String orderNumber3, Long statusId3, Long typeId3, Date time3);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfter(String orderNumber,Long statusId, Long typeId, Date time,String orderNumber4,Long statusId4, Long typeId4, Date time4, String orderNumber2,Long statusId2, Long typeId2, Date time2, String orderNumber3,Long statusId3, Long typeId3, Date time3, Pageable page);



	/*Page<TdOrder> findByUsernameAndOrderNumberOrderByIdDesc(String username, String orderNumber,
			Pageable pageRequest);*/

	Page<TdOrder> findByOrderNumberOrOrderTimeOrderByIdDesc(String orderNumber, Date d, Pageable pageRequest);

	Page<TdOrder> findByOrderNumberAndOrderTypeOrderByIdDesc(String orderNumber, String orderType, Pageable pageRequest);

	Page<TdOrder> findByUsernameAndOrderTypeAndStatusIdOrderByIdDesc(String username, String orderType, long l,
			Pageable pageRequest);

	Page<TdOrder> findByUsernameAndOrderNumberAndStatusIdOrderByIdDesc(String username, String orderNumber, long l,
			Pageable pageRequest);

	Page<TdOrder> findByOrderNumberAndOrderTypeAndStatusIdOrderByIdDesc(String orderNumber,String orderType, long l,
			Pageable pageRequest);

	Page<TdOrder> findByUsernameAndOrderTypeOrderByIdDesc(String username, String orderType, Pageable pageRequest);

	Page<TdOrder> findByUsernameAndOrderNumberOrderByIdDesc(String username, String orderNumber,
			Pageable pageRequest);

	TdOrder findById(Long id);

	Page<TdOrder> findByTypeIdAndOrderTypeOrderByIdDesc(long typeId,String string, Pageable pageRequest);
	Page<TdOrder> findByShippingNameContainingOrOrderNumberContainingOrGoodsTitleContainingOrShippingPhoneContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, String keywords4, Pageable page);

	Page<TdOrder> findByTypeIdOrderByIdDesc(long l, Pageable pageRequest);
	
	/**
	 * 后台订单筛选
	 * @author Max
	 * 
	 */
	/**
     *  加截止时间筛选
     *  
     */
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndOrderTimeBefore(
    																				String ordernumber,Date orderTime1,
    																				String username,Date orderTime2,Pageable page);
    Page<TdOrder> findByOrderTimeBefore(Date orderTime,Pageable page);
    
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeBefore(
    																				String orderNumber,Long statusId1,Date endTime1,
    																				String username, Long statusId2, Date endTime2, Pageable page);

    Page<TdOrder> findByStatusIdAndOrderTimeBefore(Long statusId,Date endTime,Pageable page);
    
	// 加起始时间
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfter(
																					String ordernumber,Date orderTime1,
																					String username,Date orderTime2,Pageable page);
    
    Page<TdOrder> findByOrderTimeAfter(Date orderTime,Pageable page);
    
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfter(
																					String orderNumber,Long statusId1,Date endTime1,
																					String username, Long statusId2, Date endTime2, Pageable page);
    
    Page<TdOrder> findByStatusIdAndOrderTimeAfter(Long statusId,Date orderTime,Pageable page); 
    
   	// 起始时间+截止时间
   	Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndOrderTimeBetween(
																	String ordernumber,Date startTime1,Date endTime1,
																	String username,Date startTime2,Date endTime2,Pageable page);
    
    Page<TdOrder> findByOrderTimeBetween(Date startTime,Date endTime,Pageable page);
    
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeBetween(
																			String orderNumber,Long statusId1,Date startTime1,Date endTime1,
																			String username, Long statusId2, Date startTime2,Date endTime2, Pageable page);
    
    Page<TdOrder> findByStatusIdAndOrderTimeBetween(Long statusId,Date startTime,Date endTime,Pageable page);
    
    // 订单类型
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdOrUsernameContainingIgnoreCaseAndTypeId(String orderNumber,Long typeId, String username, Long typeId1,Pageable page);
    Page<TdOrder> findByTypeIdOrderByIdDesc(Long typeId,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeId(String orderNumber,Long statusId1,Long typeId1, String username, Long statusId2, Long typeId,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndAndTypeIdAndOrderTimeBefore(
																								String ordernumber,Long typeId1,Date orderTime1,
																								String username,Long typeId2,Date orderTime2,Pageable page);
    Page<TdOrder> findByTypeIdAndOrderTimeBefore(Long typeId,Date endTime,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBefore(
																							String orderNumber,Long statusId1,Long typeId1,Date endTime1,
																							String username, Long statusId2, Long typeId2,Date endTime2, Pageable page);
    Page<TdOrder> findByStatusIdAndTypeIdAndOrderTimeBefore(Long statusId,Long typeId, Date endTime,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfter(
																									String ordernumber, Long typeId1, Date orderTime1,
																									String username, Long typeId2, Date orderTime2,Pageable page);
    Page<TdOrder> findByTypeIdAndOrderTimeAfter(Long typeId, Date startTime,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfter(
																										String orderNumber,Long statusId1, Long typeId1, Date endTime1,
																										String username, Long statusId2, Long typeId2, Date endTime2, Pageable page);
    Page<TdOrder> findByStatusIdAndTypeIdAndOrderTimeAfter(Long statusId, Long typeId, Date orderTime,Pageable page); 
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeBetween(
																										String ordernumber,Long typeId1, Date startTime1,Date endTime1,
																										String username, Long typeId2, Date startTime2,Date endTime2,Pageable page);
    Page<TdOrder> findByTypeIdAndOrderTimeBetween(Long typeId, Date startTime,Date endTime,Pageable page);
    Page<TdOrder> findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBetween(
																										String orderNumber,Long statusId1, Long typeId1, Date startTime1,Date endTime1,
																										String username, Long statusId2, Long typeId2,  Date startTime2,Date endTime2, Pageable page);
    Page<TdOrder> findByStatusIdAndTypeIdAndOrderTimeBetween(Long statusId,Long typeId, Date startTime,Date endTime,Pageable page);

    
    

}
