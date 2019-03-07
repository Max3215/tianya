package com.ynyes.tianya.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.tianya.entity.TdGoods;

/**
 * TdGoods 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGoodsRepo extends
		PagingAndSortingRepository<TdGoods, Long>,
		JpaSpecificationExecutor<TdGoods> 
{
	TdGoods findByIdAndIsOnSaleTrue(Long Id);
	
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrue(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsHotTrueAndIsOnSaleTrue(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrue(String categoryId, Pageable page);
    
    Page<TdGoods> findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(Pageable page);
    
    Page<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySoldNumberDesc(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderByOnSaleTimeDesc(String categoryId, Pageable page);
    
    List<TdGoods> findTop10ByIsOnSaleTrueOrderBySoldNumberDesc();
    
    Page<TdGoods> findByIsOnSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleTrueAndIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleTrueAndIsGroupSaleTrueOrIsOnSaleTrueAndIsGroupSaleHundredTrue(Pageable page);
    
    Page<TdGoods> findByIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsGroupSaleTrueOrIsGroupSaleHundredTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalse(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalseAndIsFlashSaleTrue(Pageable page);
    
    Page<TdGoods> findByIsOnSaleFalseAndIsGroupSaleTrueOrIsOnSaleFalseAndIsGroupSaleHundredTrue(Pageable page);
    
    List<TdGoods> findByIdAndIsOnSaleTrue(Iterable<Long> ids);
    
    Page<TdGoods> findByCategoryIdTreeContaining(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsFlashSaleTrue(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsGroupSaleHundredTrue(String catId, String catId1, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleHundredTrue(String catId, String catId1, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrue(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalse(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrue(String catId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleHundredTrue(String catId, String catId1, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseOrSubTitleContainingIgnoreCaseOrDetailContaining(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsFlashSaleTrue(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsGroupSaleHundredTrue(String keywords1, String keywords2, String keywords3, String keywords4, String keywords5, String keywords6, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrue(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(String keywords1, String keywords2, String keywords3, String keywords4, String keywords5, String keywords6, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleFalseOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseOrDetailContainingIgnoreCaseAndIsOnSaleFalse(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrue(String keywords1, String keywords2, String keywords3, String keywords4, String keywords5, String keywords6, Pageable page);
    
    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrue(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseOrCategoryIdTreeContainingAndDetailContainingIgnoreCase(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsGroupSaleHundredTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3, String keywords3, String catId4, String keywords4, String catId5, String keywords5, String catId6,  
            String keywords6, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsFlashSaleTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,String keywords3, String catId4, String keywords4,String catId5, String keywords5,String catId6, 
            String keywords6, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(String catId1,
            String keywords1, 
            String catId2,
            String keywords2, 
            String catId3,
            String keywords3, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(String catId1,
            String keywords1, String catId2, String keywords2,String catId3, String keywords3,String catId4, String keywords4,
            String catId5,
            String keywords5, 
            String catId6,
            String keywords6, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long leftNumber, Double priceLow, Double priceHigh, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long brandId, Long leftNumber, Double priceLow, Double priceHigh, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long leftNumber, String paramStr, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long brandId, Long leftNumber, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndTitleContainingOrCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndSubTitleContainingAndIsOnSaleTrue(
    														String categoryId, Double priceLow, Double priceHigh, String paramStr,String keyword,
    														String categoryId1, Double priceLow1, Double priceHigh1, String paramStr1,String keyword1, Pageable page);
    
    //Max
    Page<TdGoods> findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Double priceLow, Double priceHigh, String paramStr, Pageable page);
    Page<TdGoods> findByCategoryIdTreeContainingAndSalePriceBetweenAndIsOnSaleTrue(String categoryId, Double priceLow, Double priceHigh, Pageable page);
    Page<TdGoods> findByCategoryIdTreeContainingAndSalePriceBetweenAndTitleContainingOrSubTitleContainingAndIsOnSaleTrue(String categoryId, Double priceLow, Double priceHigh, String keyword,String keyword1, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long brandId, Double priceLow, Double priceHigh, String paramStr, Pageable page);

    Page<TdGoods> findByCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndTitleContainingOrCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndSubTitleContainingAndIsOnSaleTrue(
    											String categoryId, String paramStr,String keyword,
    											String categoryId1, String paramStr1,String keyword1, Pageable page);
    
    // Max
    Page<TdGoods> findByCategoryIdTreeContainingAndTitleContainingOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrue(String categoryId, String keyword,String categoryId2,String keyword1, Pageable page);
    
    // Max
    Page<TdGoods> findByCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, String paramStr, Pageable page);
//    Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrue(String categoryId, Pageable page);
    
    Page<TdGoods> findByCategoryIdTreeContainingAndBrandIdAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(String categoryId, Long brandId, String paramStr, Pageable page);

    // 正在团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(Date current, Date current1, Pageable page);
    
    // 正在百人团购
    Page<TdGoods> findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeAfterAndGroupSaleHundredStartTimeBeforeOrderByGroupSaleHundredStartTimeAsc(Date current, Date current1, Pageable page);
    Page<TdGoods> findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeAfterAndGroupSaleHundredStartTimeBeforeOrderBySortIdAsc(Date current, Date current1, Pageable page);

    // 即将开始团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStartTimeAfterOrderByGroupSaleStartTimeAsc(Date current, Pageable page);
 
    // 即将开始百人团购
    Page<TdGoods> findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStartTimeAfterOrderByGroupSaleHundredStartTimeAsc(Date current, Pageable page);
   
    // 已经结束团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeBeforeOrderByGroupSaleStartTimeAsc(Date current, Pageable page);
  
    // 已经结束百人团购
    Page<TdGoods> findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeBeforeOrderByGroupSaleHundredStartTimeAsc(Date current, Pageable page);
   
    // 全部团购
    Page<TdGoods> findByIsGroupSaleTrueAndIsOnSaleTrueOrderByGroupSaleStartTimeAsc(Pageable page);
    
    // 全部百人团
    Page<TdGoods> findByIsGroupSaleHundredTrueAndIsOnSaleTrueOrderByGroupSaleHundredStartTimeAsc(Pageable page);
    
    // 正在秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeOrderByFlashSaleStartTimeAsc(Date current, Date current1, Pageable page);
    
    // 通过时间筛选
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date startTime, Pageable page);
    
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(Date startTime, Pageable page);
 
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(Date startTime, Date stopTime, Pageable page);

    List<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date startTime);
    
    // 正在秒杀 限定开始时间
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date current1, Date startTime, Pageable page);
    
    // 即将开始秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterOrderByFlashSaleStartTimeAsc(Date current, Pageable page);
    
    // 即将开始秒杀，限定开始时刻
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date startTime, Pageable page);
    
    // 已经结束秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeOrderByFlashSaleStartTimeAsc(Date current, Pageable page);
    
    // 已经结束秒杀，限定开始时刻
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date current, Date startTime, Pageable page);
    
    // 全部秒杀
    Page<TdGoods> findByIsFlashSaleTrueAndIsOnSaleTrueOrderByFlashSaleStartTimeAsc(Pageable page);

    Page<TdGoods> findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrParamValueCollectContainingIgnoreCaseAndIsOnSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrue(String key1,
            String key2,
            String key3,
            String key4,
            Pageable page);

    List<TdGoods> findByProductIdAndIsOnSaleTrue(Long productId);
    
    Page<TdGoods> findByReturnPriceNotAndIsOnSaleTrue(double returnPrice, Pageable page);
    
    Page<TdGoods> findByReturnPriceNotAndTitleContainingAndIsOnSaleTrue(double returnPrice, String keywords, Pageable page);

	TdGoods findById(Long goodsId);

	Page<TdGoods> findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrue(
			String keyword,String keyword3,String keyword4, Pageable pageRequest);

	Page<TdGoods> findByIsOnSaleTrueOrderBySalePriceAsc(Pageable pageRequest);

	Page<TdGoods> findByIsOnSaleTrueOrderBySalePriceDesc(Pageable pageRequest);

	Page<TdGoods> findByIsOnSaleTrueOrderBySoldNumberAsc(Pageable pageRequest);

	Page<TdGoods> findByIsOnSaleTrueOrderBySoldNumberDesc(Pageable pageRequest);

	Page<TdGoods> findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(String keyword,
			String keyword2, String keyword3,Pageable pageRequest);

	Page<TdGoods> findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(String keyword,
			String keyword2,String keyword3, Pageable pageRequest);

	Page<TdGoods> findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(String keyword,
			String keyword2,String keyword3, Pageable pageRequest);

	Page<TdGoods> findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(String keyword,
			String keyword2,String keyword3, Pageable pageRequest);

	Page<TdGoods> findByCategoryTitleAndIsOnSaleTrue(String categoryTitle,Pageable pageRequest);
	
	List<TdGoods> findByCategoryTitleAndIsOnSaleTrue(String categoryTitle);
	// gl
	Page<TdGoods> findByLeavePortAndReachPortAndCategoryTitleAndIsOnSaleTrue(String leavePort, String passPort, String categoryTitle,Pageable pageRequest);
	
	Page<TdGoods> findByLeavePortOrReachPortAndCategoryTitleAndIsOnSaleTrue(String leavePort, String passPort, String categoryTitle,Pageable pageRequest);
	
	Page<TdGoods> findByLeavePortAndCategoryTitleAndIsOnSaleTrueOrReachPortAndCategoryTitleAndIsOnSaleTrue(String leavePort, String categoryTitle, String reachPort, String categoryTitle2, Pageable pageRequest);
	//驾龄
	Page<TdGoods> findBySubTitleAndCategoryTitleAndIsOnSaleTrue(String subTitle, String categoryTitle, Pageable pageRequest);
	//驾照
	Page<TdGoods> findByLeavePortAndCategoryTitleAndIsOnSaleTrue(String leavePort, String categoryTitle, Pageable pageRequest);
	//司机
	Page<TdGoods> findByTitleAndCategoryTitleAndIsOnSaleTrue(String title,String categoryTitle, Pageable pageRequest);
	//驾龄+驾照
	Page<TdGoods> findBySubTitleAndLeavePortAndCategoryTitleAndIsOnSaleTrue(String subTitle, String leavePort, String categoryTitle,Pageable pageRequest);
	//驾龄+司机
	Page<TdGoods> findBySubTitleAndTitleAndCategoryTitleAndIsOnSaleTrue(String subTitle, String title, String categoryTitle,Pageable pageRequest);
	//驾照+司机
	Page<TdGoods> findByLeavePortAndTitleAndCategoryTitleAndIsOnSaleTrue(String leavePort, String title, String categoryTitle,Pageable pageRequest);
	//驾龄+驾照+司机
	Page<TdGoods> findBySubTitleAndLeavePortAndTitleAndCategoryTitleAndIsOnSaleTrue(String subTitle, String leavePort, String title,String categoryTitle, Pageable pageRequest);
	

	Page<TdGoods> findByReachPortContainingAndCategoryTitleAndIsOnSaleTrue(String reachPort, String string,
			Pageable pageRequest);

	Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndCategoryTitleAndIsOnSaleTrue(String reachPort,
			String leavePort,String string, Pageable pageRequest);


	Page<TdGoods> findByOnSaleTime(Date date,Pageable pageRequest);

	Page<TdGoods> findByOnSaleTimeAndReachPort(Date date, String reachPort, Pageable pageRequest);

	Page<TdGoods> findByOnSaleTimeAndLeavePort(Date date, String leavePort, Pageable pageRequest);

	Page<TdGoods> findByOnSaleTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStopTime(Date date, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStopTimeAndReachPort(Date date, String reachPort, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStopTimeAndLeavePort(Date date, String leavePort, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStopTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTime(Date date, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndLeavePort(Date date, String leavePort, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndReachPort(Date date, String reachPort, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndLeavePortAndReachPort(Date date1, Date date2, Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(Date date1, Date date2, String reachPort,
			Pageable pageRequest);
	Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndLeavePort(Date date1, Date date2, String LeavePort,
			Pageable pageRequest);

	Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(Date date1, Date date2, String leavePort,
			String reachPort, Pageable pageRequest);

	Page<TdGoods> findByReachPortAndIsOnSaleTrue(String reachPort, Pageable pageRequest);

	Page<TdGoods> findByIsSpecialPriceTrueAndIsOnSaleTrue(Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrue(Double keyword2, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrue(
			Double keyword2, String keyword, Double keyword22, String keyword3, Double keyword23, String keyword4,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceDesc(Double keyword2, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceAsc(Double keyword2, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberAsc(Double keyword2, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberDesc(Double keyword2, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(
			Double keyword2, String keyword, Double keyword22, String keyword3, Double keyword23, String keyword4,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(
			Double keyword2, String keyword, Double keyword22, String keyword3, Double keyword23, String keyword4,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(
			Double keyword2, String keyword, Double keyword22, String keyword3, Double keyword23, String keyword4,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(
			Double keyword2, String keyword, Double keyword22, String keyword3, Double keyword23, String keyword4,
			Pageable pageRequest);

	TdGoods findByTitleAndCategoryIdTreeContaining(String txtcountry, String string);
	//gl
	List<TdGoods> findByTitleContainingAndCategoryIdTreeContaining(String string,String txtcountry );
	List<TdGoods> findByCategoryIdTreeContainingAndTitle(String string,String txtcountry );

	Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(
			String reachPort, String leavePort, String subTitle, String string, Pageable pageRequest);

	Page<TdGoods> findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(String string, Pageable pageRequest);

	Page<TdGoods> findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(String string, Pageable pageRequest);

	Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(
			String reachPort, String leavePort, String subTitle, String string, Pageable pageRequest);

	List<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(String string);

	List<TdGoods> findTop4ByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(String string);

	Page<TdGoods> findBySalePriceGreaterThanOrSalePriceLessThanOrSubTitleContainingOrCategoryTitleOrIdAndIsOnSaleTrueAndCarTypeTrue(
			double price11, double price22, String subTitle, Long id, String string, Pageable pageRequest);

	Page<TdGoods> findById(Long id11, Pageable pageRequest);

	Page<TdGoods> findBySubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(String subTitle, String string,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceGreaterThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price11, String string,
			Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price11, String string,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price22, String string,
			Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceLessThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price22, String string,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price11,
			Double price22, String string, Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceLessThanEqualAndSalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(Double price11,
			Double price22, String string, Pageable pageRequest);

	Page<TdGoods> findBySubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(String subTitle, String string,
			Pageable pageRequest);

	Page<TdGoods> findBySalePriceGreaterThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(Double price11,
			String string, Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(Double price11,
			String string, Pageable pageRequest);

	Page<TdGoods> findBySalePriceLessThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(Double price22, String string,
			Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceLessThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(Double price22, String string,
			Pageable pageRequest);
	

	Page<TdGoods> findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(
			Double price11, Double price22, String string, Pageable pageRequest);
	//gl
	Page<TdGoods> findBySalePriceLessThanEqualAndSalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(
			Double price11, Double price22, String string, Pageable pageRequest);

	// Max
	List<TdGoods> findByCategoryIdTreeContainingOrderBySortIdAsc(String catId);
	
}
