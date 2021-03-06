package com.ynyes.tianya.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdBrand;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdGoodsCombination;
import com.ynyes.tianya.entity.TdGoodsGift;
import com.ynyes.tianya.entity.TdGoodsParameter;
import com.ynyes.tianya.entity.TdGoodsPs;
import com.ynyes.tianya.entity.TdGoodsXc;
import com.ynyes.tianya.entity.TdPriceChangeLog;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdProvider;
import com.ynyes.tianya.entity.TdSite;
import com.ynyes.tianya.entity.TdWarehouse;
import com.ynyes.tianya.repository.TdGoodsRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;

/**
 * TdGoods 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdGoodsService {
    @Autowired
    TdGoodsRepo repository;

    @Autowired
    TdProductCategoryService tdProductCategoryService;

    @Autowired
    TdBrandService tdBrandService;

    @Autowired
    TdArticleService tdArticleService;

    @Autowired
    TdParameterService tdParameterService;

    @Autowired
    TdGoodsParameterService tdGoodsParameterService;

    @Autowired
    TdWarehouseService tdWarehouseService;

    @Autowired
    TdProviderService tdProviderService;

    @Autowired
    TdGoodsGiftService tdGoodsGiftService;

    @Autowired
    TdGoodsCombinationService tdGoodsCombinationService;

    @Autowired
    TdPriceChangeLogService tdPriceChangeLogService;

    @Autowired
    TdSiteService tdSiteService;
    
    @Autowired
    TdGoodsPsService tdGoodsPsService;
    
    @Autowired
    TdGoodsXcService tdGoodsXcService;

    /**
     * 查找所有商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findAll(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findAll(pageRequest);
    }

    public TdGoods findByIdAndIsOnSaleTrue(Long id) {
        return repository.findByIdAndIsOnSaleTrue(id);
    }

    public Page<TdGoods> findAllOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findAll(pageRequest);
    }

    public Page<TdGoods> findByIsFlashSaleTrueOrderBySortIdAsc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsGroupSaleTrueOrIsGroupSaleHundredTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueAndFlashSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrueAndIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueAndGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrueAndIsGroupSaleTrueOrIsOnSaleTrueAndIsGroupSaleHundredTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseOrderBySortIdAsc(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalse(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalseAndIsFlashSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleFalseAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleFalseAndIsGroupSaleTrueOrIsOnSaleFalseAndIsGroupSaleHundredTrue(pageRequest);
    }

    public Page<TdGoods> findAllAndIsOnSaleTrue(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public List<TdGoods> findTop10ByIsOnSaleTrueOrderBySoldNumberDesc() {
        return repository.findTop10ByIsOnSaleTrueOrderBySoldNumberDesc();
    }

    public Page<TdGoods> searchAndOrderBySortIdAsc(String keywords, int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseOrSubTitleContainingIgnoreCaseOrDetailContaining(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsGroupSaleHundredTrue(
                        keywords, keywords, keywords, keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueOrderBySortIdAsc(String keywords,
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(
                        keywords, keywords, keywords, keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleFalseOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseOrDetailContainingIgnoreCaseAndIsOnSaleFalse(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrue(
                        keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> searchAndIsOnSaleFalseAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrue(
                        keywords, keywords, keywords, keywords, keywords, keywords,pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseOrCategoryIdTreeContainingAndDetailContainingIgnoreCase(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsGroupSaleHundredTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords,catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr, keywords,catIdStr, keywords,catIdStr, keywords,catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsFlashSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr, keywords,catIdStr, keywords,catIdStr, keywords,catIdStr,
                        keywords, pageRequest);
    }

    public Page<TdGoods> searchAndFindByCategoryIdAndIsOnSaleFalseAndIsFlashSaleTrueAndIsGroupSaleTrueOrderBySortIdAsc(
            String keywords, Long categoryId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + categoryId + "]";

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndTitleContainingIgnoreCaseAndIsOnSaleFalseAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndSubTitleContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndDetailContainingIgnoreCaseAndIsOnSaleTrueAndIsGroupSaleHundredTrue(
                        catIdStr, keywords, catIdStr, keywords, catIdStr, keywords,catIdStr, keywords,catIdStr, keywords,catIdStr,
                        keywords, pageRequest);
    }

    public List<TdGoods> findByIdAndIsOnSaleTrue(Iterable<Long> ids) {
        return repository.findByIdAndIsOnSaleTrue(ids);
    }

    /**
     * 按类型查找所有商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByReturnPriceNotZeroAndIsOnSaleTrue(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findByReturnPriceNotAndIsOnSaleTrue(0.0, pageRequest);
    }

    public Page<TdGoods> findByReturnPriceNotZeroAndSearchAndIsOnSaleTrue(
            int page, int size, String keywords) {
        if (null == keywords) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByReturnPriceNotAndTitleContainingAndIsOnSaleTrue(0.0,
                        keywords, pageRequest);
    }

    /**
     * 正在团购商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSalingOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeAfterAndGroupSaleStartTimeBeforeOrderByGroupSaleStartTimeAsc(
                        new Date(), new Date(), pageRequest);
    }
    
    /**
     * 正在百人团购商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSalingHundredOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeAfterAndGroupSaleHundredStartTimeBeforeOrderBySortIdAsc(
                        new Date(), new Date(), pageRequest);
    }

    /**
     * 即将开始团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleGoingToStartOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStartTimeAfterOrderByGroupSaleStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
	 * @author lc
	 * 即将开始百人团购
	 */
	public Page<TdGoods> findByGroupSaleGoingToHundredOrderByGroupSaleHundredStartTimeAsc(int page, int size){
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		 return repository.findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStartTimeAfterOrderByGroupSaleHundredStartTimeAsc(
                 new Date(), pageRequest);
	} 
	
    /**
     * 已结束团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleEndedOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueAndGroupSaleStopTimeBeforeOrderByGroupSaleStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
     * 已经结束百人团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleHundredEndedOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeBeforeOrderByGroupSaleHundredStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
	 * @author lc
	 * 已经结束百人团购
	 */
    public Page<TdGoods> findByGroupSaleEndedHundredOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueAndGroupSaleHundredStopTimeBeforeOrderByGroupSaleHundredStartTimeAsc(
                        new Date(), pageRequest);
    }
    
    /**
     * 所有团购
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleAllOrderByGroupSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleTrueAndIsOnSaleTrueOrderByGroupSaleStartTimeAsc(pageRequest);
    }
    
    /**
     * 所有百人团
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByGroupSaleHundredAllOrderByGroupSaleHundredStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsGroupSaleHundredTrueAndIsOnSaleTrueOrderByGroupSaleHundredStartTimeAsc(pageRequest);
    }
    

    /**
     * 正在秒杀商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSalingOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeOrderByFlashSaleStartTimeAsc(
                        new Date(), new Date(), pageRequest);
    }

    /**
     * 通过开始时间查找秒杀商品
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) 
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        startTime, pageRequest);
    }
    public Page<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(
            Date startTime, int page, int size) 
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAndFlashSaleStopTimeAfterOrderBySortIdAsc(
                        startTime, new Date(), pageRequest);
    }
    
    public Page<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(
            Date startTime, int page, int size) 
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderBySortIdAsc(
                        startTime, pageRequest);
    }
    
    public List<TdGoods> findByIsFlashSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(Date startTime){
    	return repository.findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(startTime);
    }
    /**
     * 正在秒杀商品，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSalingAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeAfterAndFlashSaleStartTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), new Date(), startTime, pageRequest);
    }

    /**
     * 即将开始秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleGoingToStartOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterOrderByFlashSaleStartTimeAsc(
                        new Date(), pageRequest);
    }

    /**
     * 即将开始秒杀，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleGoingToStartAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStartTimeAfterAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), startTime, pageRequest);
    }

    /**
     * 已结束秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleEndedOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeOrderByFlashSaleStartTimeAsc(
                        new Date(), pageRequest);
    }

    /**
     * 已结束秒杀，限定开始时间
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleEndedAndStartTimeOrderByFlashSaleStartTimeAsc(
            Date startTime, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueAndFlashSaleStopTimeBeforeAndFlashSaleStartTimeOrderByFlashSaleStartTimeAsc(
                        new Date(), startTime, pageRequest);
    }

    /**
     * 所有秒杀
     * 
     * @param page
     * @param size
     * @return
     */
    public Page<TdGoods> findByFlashSaleAllOrderByFlashSaleStartTimeAsc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsFlashSaleTrueAndIsOnSaleTrueOrderByFlashSaleStartTimeAsc(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository.findByCategoryIdTreeContaining(
                catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsGroupSaleHundredTrue(catIdStr,
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsOnSaleTrueAndIsGroupSaleHundredTrue(
                        catIdStr, catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalse(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsFlashSaleTrue(
                        catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueAndGroupSaleHundredTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catIdStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleTrueOrCategoryIdTreeContainingAndIsOnSaleFalseAndIsGroupSaleHundredTrue(
                        catIdStr, catIdStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrue(Long catId, int page,
            int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository.findByCategoryIdTreeContainingAndIsOnSaleTrue(catStr,
                pageRequest);
    }
    
    public Page<TdGoods> findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(Long catId, int page,
            int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository.findByCategoryIdTreeContainingAndIsHotTrueAndIsOnSaleTrue(catStr,
                pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderBySortIdAsc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId"));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsRecommendTypeTrueAndIsOnSaleTrue(
                        catStr, pageRequest);
    }
    
    public Page<TdGoods> findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
    }

    public Page<TdGoods> findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
            int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository
                .findByIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderBySoldNumberDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderBySoldNumberDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndIsOnSaleTrueOrderByOnSaleTimeDesc(
            Long catId, int page, int size) {
        if (null == catId) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        String catStr = "[" + catId + "]";

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                        catStr, pageRequest);
    }

    public Page<TdGoods> findByIsOnSaleTrueOrderBySoldNumberDesc(int page,
            int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "soldNumber"));

        return repository.findByIsOnSaleTrue(pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
            long catId, double priceLow, double priceHigh, int page, int size,
            List<String> paramValueList) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "soldNumber"));

        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, priceLow, priceHigh, paramStr,
                        pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh, 
            List<String> paramValueList, Pageable pageRequest) {

        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, priceLow, priceHigh, paramStr,
                        pageRequest);
    }

    

    public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, 0L, priceLow, priceHigh,
                        paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(
            long catId, List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndLeftNumberGreaterThanAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", 0L, paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndLeftNumberGreaterThanAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, 0L, paramStr, pageRequest);
    }

    

    public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh,String keyword,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndTitleContainingOrCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndSubTitleContainingAndIsOnSaleTrue(
                        "[" + catId + "]", priceLow, priceHigh, paramStr,"%"+keyword+"%",
                        "[" + catId + "]", priceLow, priceHigh, paramStr,"%"+keyword+"%",pageRequest);
    }
    
    /**
     * @author Max
     */
    public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh,String keyword,
           Pageable pageRequest) {
      
        return repository
                .findByCategoryIdTreeContainingAndSalePriceBetweenAndTitleContainingOrSubTitleContainingAndIsOnSaleTrue(
                        "[" + catId + "]", priceLow, priceHigh, "%"+keyword+"%","%"+keyword+"%",
                        pageRequest);
    }
    
    /**
     * @author Max
     * 
     */
    public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", priceLow, priceHigh, paramStr,pageRequest);
    }
    /**
     * 
     * @author Max
     */
    public Page<TdGoods> findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, double priceLow, double priceHigh,
             Pageable pageRequest) {

        return repository
                .findByCategoryIdTreeContainingAndSalePriceBetweenAndIsOnSaleTrue(
                        "[" + catId + "]", priceLow, priceHigh,pageRequest);
    }

    public Page<TdGoods> searchGoods(String keywords, int page, int size) {
        if (null == keywords) {
            return null;
        }
        
        keywords = keywords.replace(" ", "%");

        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrParamValueCollectContainingIgnoreCaseAndIsOnSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrue(
                        keywords, keywords, keywords, keywords, pageRequest);
    }

    /**
     * 搜索商品
     * 
     * @param keywords
     *            关键字
     * @param page
     *            页号
     * @param size
     *            页大小
     * @param sortName
     *            排序字段名
     * @param sd
     *            排序方向
     * @return
     */
    public Page<TdGoods> searchGoods(String keywords, int page, int size,
            String sortName, Direction dir) {
        if (null == keywords || null == sortName) {
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, size, new Sort(dir,
                sortName));

        return repository
                .findByTitleContainingIgnoreCaseAndIsOnSaleTrueOrSubTitleContainingIgnoreCaseAndIsOnSaleTrueOrParamValueCollectContainingIgnoreCaseAndIsOnSaleTrueOrDetailContainingIgnoreCaseAndIsOnSaleTrue(
                        keywords, keywords, keywords, keywords, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId, double priceLow, double priceHigh,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndSalePriceBetweenAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, priceLow, priceHigh,
                        paramStr, pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
            long catId, List<String> paramValueList, String keyword,Pageable pageRequest) {
        String paramStr = "%";

        if (null != paramValueList) {
            for (int i = 0; i < paramValueList.size(); i++) {
                String value = paramValueList.get(i);
                if (!"".equals(value)) {
                    paramStr += value;
                    paramStr += "%";
                }
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndTitleContainingOrCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndSubTitleContainingAndIsOnSaleTrue(
                        "[" + catId + "]", paramStr,"%"+keyword+"%",
                        "[" + catId + "]", paramStr,"%"+keyword+"%", pageRequest);
    }
    
    /**
     * @author Max
     * 
     */
    public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
            long catId,  String keyword,Pageable pageRequest) {

        return repository
                .findByCategoryIdTreeContainingAndTitleContainingOrCategoryIdTreeContainingAndSubTitleContainingAndIsOnSaleTrue(
                        "[" + catId + "]","%"+keyword+"%","[" + catId + "]","%"+keyword+"%", pageRequest);
    }
    
    /**
     * @author Max
     * 
     */
    public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
            long catId, List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        if (null != paramValueList) {
            for (int i = 0; i < paramValueList.size(); i++) {
                String value = paramValueList.get(i);
                if (!"".equals(value)) {
                    paramStr += value;
                    paramStr += "%";
                }
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", paramStr, pageRequest);
    }
    
    /**
     * @author Max
     * 
     */
    public Page<TdGoods> findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
            long catId,  Pageable pageRequest) {
        

        return repository
                .findByCategoryIdTreeContainingAndIsOnSaleTrue(
                        "[" + catId + "]",  pageRequest);
    }

    public Page<TdGoods> findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrue(
            long catId, long brandId,
            List<String> paramValueList, Pageable pageRequest) {
        String paramStr = "%";

        for (int i = 0; i < paramValueList.size(); i++) {
            String value = paramValueList.get(i);
            if (!"".equals(value)) {
                paramStr += value;
                paramStr += "%";
            }
        }

        return repository
                .findByCategoryIdTreeContainingAndBrandIdAndParamValueCollectLikeIgnoreCaseAndIsOnSaleTrue(
                        "[" + catId + "]", brandId, paramStr, pageRequest);
    }

    public List<TdGoods> findByProductIdAndIsOnSaleTrue(Long productId) {
        if (null == productId) {
            return null;
        }

        return repository.findByProductIdAndIsOnSaleTrue(productId);
    }

    /**
     * 删除
     * 
     * @param id
     *            菜单项ID
     */
    public void delete(Long id) {
        if (null != id) {
            repository.delete(id);
        }
    }

    /**
     * 删除
     * 
     * @param e
     *            菜单项
     */
    public void delete(TdGoods e) {
        if (null != e) {
            repository.delete(e);
        }
    }

    /**
     * 查找
     * 
     * @param id
     *            ID
     * @return
     */
    public TdGoods findOne(Long id) {
        if (null == id) {
            return null;
        }

        return repository.findOne(id);
    }

    /**
     * 判断该商品是否正在进行秒杀
     * 
     * @param tdGoods
     * @return
     */
    public boolean isFlashSaleTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsFlashSale() && tdGoods.getIsFlashSale()
                && null != tdGoods.getFlashSaleStartTime()
                && tdGoods.getFlashSaleStartTime().before(curr)
                && null != tdGoods.getFlashSaleStopTime()
                && tdGoods.getFlashSaleStopTime().after(curr)
                && null != tdGoods.getFlashSaleLeftNumber()
                && tdGoods.getFlashSaleLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断该商品是否正在进行十人团
     * 
     * @param tdGoods
     * @return
     */
    public boolean isGroupSaleTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsGroupSale() && tdGoods.getIsGroupSale()
                && null != tdGoods.getGroupSaleStartTime()
                && tdGoods.getGroupSaleStartTime().before(curr)
                && null != tdGoods.getGroupSaleStopTime()
                && tdGoods.getGroupSaleStopTime().after(curr)
                && null != tdGoods.getGroupSaleLeftNumber()
                && tdGoods.getGroupSaleLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断该商品是否正在进行百人团
     * 
     * @param tdGoods
     *            商品
     * @return
     */
    public boolean isGroupSaleHundredTrue(TdGoods tdGoods) {
        if (null == tdGoods) {
            return false;
        }

        Date curr = new Date();

        if (null != tdGoods.getIsGroupSaleHundred()
                && tdGoods.getIsGroupSaleHundred()
                && null != tdGoods.getGroupSaleHundredStartTime()
                && tdGoods.getGroupSaleHundredStartTime().before(curr)
                && null != tdGoods.getGroupSaleHundredStopTime()
                && tdGoods.getGroupSaleHundredStopTime().after(curr)
                && null != tdGoods.getGroupSaleHundredLeftNumber()
                && tdGoods.getGroupSaleHundredLeftNumber().compareTo(0L) > 0) {
            return true;
        }

        return false;
    }

    public TdGoods save(TdGoods e)
    {
        if (null == e)
        {
            return null;
        }
        
        return repository.save(e);
    }
    /**
     * 保存类型
     * 
     * @param e
     * @return
     */
    public TdGoods save(TdGoods e, String manager) {
        if (null == e) {
            return null;
        }

        // 参数类型ID
        Long paramCategoryId = null;

        // 保存分类名称
        if (null != e.getCategoryId()) {
            TdProductCategory cat = tdProductCategoryService.findOne(e
                    .getCategoryId());
            e.setCategoryTitle(cat.getTitle());
            e.setCategoryIdTree(cat.getParentTree());

            paramCategoryId = cat.getParamCategoryId();
        }

        // 保存品牌名称
        if (null != e.getCategoryId()) {
            TdBrand brand = tdBrandService.findOne(e.getBrandId());

            if (null != brand) {
                e.setBrandTitle(brand.getTitle());
            }
        }

        // 保存销售价
        // if (null == e.getReturnPrice()) {
        // e.setReturnPrice(0.0);
        // }
        //
        // if (null == e.getOutFactoryPrice()) {
        // e.setOutFactoryPrice(0.0);
        // }
        //
        // e.setSalePrice(e.getReturnPrice() + e.getOutFactoryPrice());

        // 创建时间
        if (null == e.getCreateTime()) {
            e.setCreateTime(new Date());
        }

        // 上架时间
        if (null == e.getOnSaleTime() && e.getIsOnSale()) {
            e.setOnSaleTime(new Date());
        }

        // 站点
        if (null != e.getSiteId()) {
            TdSite s = tdSiteService.findOne(e.getSiteId());

            if (null != s) {
                e.setSiteTitle(s.getTitle());
            }
        }

        // 仓库名
        if (null != e.getWarehouseId()) {
            TdWarehouse w = tdWarehouseService.findOne(e.getWarehouseId());

            if (null != w) {
                e.setWarehouseTitle(w.getTitle());
            }
        }

        // 供应商名
        if (null != e.getProductId()) {
            TdProvider p = tdProviderService.findOne(e.getProductId());

            if (null != p) {
                e.setProviderTitle(p.getTitle());
            }
        }
        
        // 当有空的TdGoodsPs时，删除这个TdGoodsPs
        if (null != e.getPsList() && e.getPsList().size() > 0)
        {
        	Iterator<TdGoodsPs> iter = e.getPsList().iterator();  
        	
        	while(iter.hasNext()){  
        		TdGoodsPs n = iter.next();  
        	    if(null == n.getId()
        				&& null == n.getPrice1()
        				&& null == n.getPrice2()
        				&& (null == n.getType() || n.getType().isEmpty())
        				&& (null == n.getTitle() || n.getTitle().isEmpty())
        				&& (null == n.getSubTitle() || n.getSubTitle().isEmpty()))
        	    {  
        	        iter.remove();  
        	    }  
        	}
        }
        // 当有空的TdGoodsXc时，删除这个TdGoodsXc
        if (null != e.getXcList() && e.getXcList().size() > 0)
        {
        	Iterator<TdGoodsXc> iter = e.getXcList().iterator();  
        	
        	while(iter.hasNext()){  
        		TdGoodsXc n = iter.next();  
        		if(null == n.getId()
        				&& (null == n.getDay() || n.getDay().isEmpty())
        				&& (null == n.getPort() || n.getPort().isEmpty())
        				&& (null == n.getReachTime() || n.getReachTime().isEmpty())
        				&& (null == n.getLeaveTime() || n.getLeaveTime().isEmpty())
        				&& (null == n.getTitle() || n.getTitle().isEmpty())
        				&& (null == n.getEat() || n.getEat().isEmpty())
        				&& (null == n.getLive() || n.getLive().isEmpty())
        				&& (null == n.getDescription() || n.getDescription().isEmpty()))
        		{  
        			iter.remove();  
        		}  
        	}
        }

        // 当修改时，若切换过类型，参数数量由多变少，需要删除多余的参数
        if (null != e.getId() && null != paramCategoryId) {
            int count = tdParameterService
                    .countByCategoryTreeContaining(paramCategoryId);
            int size = e.getParamList().size();

            if (size > count) {
                List<TdGoodsParameter> subList = e.getParamList().subList(
                        count, size);
                tdGoodsParameterService.delete(subList);
                e.getParamList().removeAll(subList);
            }
        }

        // 当修改时，赠品数量减少时，需删除多余的赠品
        if (null != e.getId() && null != e.getGiftList()
                && null != e.getTotalGift()) {
            int count = e.getTotalGift();
            int size = e.getGiftList().size();

            if (size > count) {
                List<TdGoodsGift> subList = e.getGiftList()
                        .subList(count, size);
                tdGoodsGiftService.delete(subList);
                e.getGiftList().removeAll(subList);
            }
        }

        // 当修改时，组合商品数量减少时，需删除多余的组合商品
        if (null != e.getId() && null != e.getCombList()
                && null != e.getTotalComb()) {
            int count = e.getTotalComb();
            int size = e.getCombList().size();

            if (size > count) {
                List<TdGoodsCombination> subList = e.getCombList().subList(
                        count, size);
                tdGoodsCombinationService.delete(subList);
                e.getCombList().removeAll(subList);
            }
        }

        if (null != e.getParamList() && e.getParamList().size() > 0) {
            String valueCollect = "";
            for (TdGoodsParameter gp : e.getParamList()) {
                valueCollect += gp.getValue();
                valueCollect += ",";
            }
            e.setParamValueCollect(valueCollect);

            // 保存参数
            tdGoodsParameterService.save(e.getParamList());
        } else {
            e.setParamValueCollect("");
        }

        if (null == e.getId()) {
            if (null != e.getGiftList() && e.getGiftList().size() > 0) {
                e.setTotalGift(e.getGiftList().size());
            }

            if (null != e.getCombList() && e.getCombList().size() > 0) {
                e.setTotalComb(e.getCombList().size());
            }
        }
        
        // 保存TdGoodsPs
        tdGoodsPsService.save(e.getPsList());
        
        // 保存TdGoodsXc
        tdGoodsXcService.save(e.getXcList());

        // 保存赠品
        tdGoodsGiftService.save(e.getGiftList());

        // 保存组合
        tdGoodsCombinationService.save(e.getCombList());

        e = repository.save(e);

        // 添加改价记录
        TdPriceChangeLog priceLog = tdPriceChangeLogService
                .findTopByGoodsIdOrderByIdDesc(e.getId());

        // 没有改过价，或改价后的记录与当前销售价不相等
        if (null == priceLog || !priceLog.getPrice().equals(e.getSalePrice())) {
            TdPriceChangeLog newPriceLog = new TdPriceChangeLog();

            newPriceLog.setCreateTime(new Date());
            newPriceLog.setGoodsId(e.getId());
            newPriceLog.setGoodsTitle(e.getTitle()
                    + (null == e.getSelectOneValue() ? "" : " "
                            + e.getSelectOneValue())
                    + (null == e.getSelectTwoValue() ? "" : " "
                            + e.getSelectTwoValue())
                    + (null == e.getSelectThreeValue() ? "" : " "
                            + e.getSelectThreeValue()));
            newPriceLog.setOperator(manager);

            if (null != priceLog) {
                newPriceLog.setOriginPrice(priceLog.getPrice());
            }

            newPriceLog.setPrice(e.getSalePrice());
            newPriceLog.setSortId(99L);

            tdPriceChangeLogService.save(newPriceLog);
        }

        return e;
    }

    /**
     * 计算实时秒杀价
     * 
     * @param goods
     * @return
     */
    public Double getFlashPrice(TdGoods goods) {
        if (null == goods) {
            return null;
        }

        Double flashPrice = null;
        Date curr = new Date();

        if (null != goods.getIsFlashSale()
                && null != goods.getFlashSaleStartTime()
                && null != goods.getFlashSaleStopTime()
                && null != goods.getFlashSalePrice() && goods.getIsFlashSale()
                && goods.getFlashSaleStopTime().after(curr)
                && goods.getFlashSaleStartTime().before(curr)) {
            // 剩余毫秒数
            long ts = goods.getFlashSaleStopTime().getTime() - curr.getTime();
            // 总共毫秒数
            long allts = goods.getFlashSaleStopTime().getTime()
                    - goods.getFlashSaleStartTime().getTime();

            flashPrice = goods.getFlashSalePrice() * ts / allts;
            
            // 抢购价最低为1
            if (flashPrice < 1.0)
            {
                flashPrice = 1.0;
            }

            BigDecimal b = new BigDecimal(flashPrice);

            flashPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        return flashPrice;
    }

	public TdGoods findByGoodsId(Long goodsId) {
		// TODO Auto-generated method stub
		return repository.findById(goodsId);
	}

	public Page<TdGoods> findByIsOnSaleTrueContainsKeywords(String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrue(keyword, keyword,keyword,pageRequest);
	}
	public Page<TdGoods> findByIsOnSaleTrueContainsKeywordsOrderBySalePriceAsc(String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(keyword, keyword,keyword,pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleTrueContainsKeywordsOrderBySalePriceDesc(String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(keyword, keyword,keyword,pageRequest);
	}
	public Page<TdGoods> findByIsOnSaleTrueContainsKeywordsOrderBySoldNumberAsc(String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(keyword, keyword,keyword,pageRequest);
	}
	
	public Page<TdGoods> findByIsOnSaleTrueContainsKeywordsOrderBySoldNumberDesc(String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findByTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(keyword, keyword,keyword,pageRequest);
	}
	
	public Page<TdGoods> findByIsOnSaleTrue(Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByIsOnSaleTrueOrderBySalePriceAsc(pageRequest);
	}
	public Page<TdGoods> findByIsOnSaleTrueOrderByDesc(Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByIsOnSaleTrueOrderBySalePriceDesc(pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleTrueOrderBySaleNumber(Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByIsOnSaleTrueOrderBySoldNumberAsc(pageRequest);
	}

	public Page<TdGoods> findByIsOnSaleTrueOrderBySaleNumberDesc(Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByIsOnSaleTrueOrderBySoldNumberDesc(pageRequest);
	}

	public Page<TdGoods> findByCategoryTitleAndIsOnSaleTrue(String categoryTitle,Integer page,int size) {
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

		return repository.findByCategoryTitleAndIsOnSaleTrue(categoryTitle,pageRequest);
	}
	
	public List<TdGoods> findByCategoryTitleAndIsOnSaleTrue(String categoryTitle) {

		return repository.findByCategoryTitleAndIsOnSaleTrue(categoryTitle);
	}
	
	public Page<TdGoods> findByLeavePortOrReachportAndCategoryTitleAndIsOnSaleTrue(String leavePort, String passPort, String categoryTitle,Integer page,int size) {
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByLeavePortOrReachPortAndCategoryTitleAndIsOnSaleTrue(leavePort, passPort, categoryTitle, pageRequest);
	}
	
	public Page<TdGoods> findByLeavePortAndCategoryTitleAndIsOnSaleTrueOrReachPortAndCategoryTitleAndIsOnSaleTrue(String leavePort, String categoryTitle, String reachPort, String categoryTitle2,Integer page,int size) {
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByLeavePortAndCategoryTitleAndIsOnSaleTrueOrReachPortAndCategoryTitleAndIsOnSaleTrue(leavePort, categoryTitle, reachPort, categoryTitle2, pageRequest);
	}

	public Page<TdGoods> findByReachPortContainingAndCategoryTitleAndIsOnSaleTrue(String string, String reachPort,
			Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByReachPortContainingAndCategoryTitleAndIsOnSaleTrue(reachPort,string,pageRequest);
	}

	public Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndCategoryTitleAndIsOnSaleTrue(String reachPort,
			String leavePort,String string, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByReachPortContainingAndLeavePortContainingAndCategoryTitleAndIsOnSaleTrue(reachPort,leavePort,string,pageRequest);
	}


	public Page<TdGoods> findByOnSaleTime(Date date,Integer page,int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByOnSaleTime(date,pageRequest);
	}

	public Page<TdGoods> findByOnSaleTimeAndReachPort(Date date, String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByOnSaleTimeAndReachPort(date,reachPort,pageRequest);
	}

	public Page<TdGoods> findByOnSaleTimeAndLeavePort(Date date, String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByOnSaleTimeAndLeavePort(date,leavePort,pageRequest);
	}

	public Page<TdGoods> findByOnSaleTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByOnSaleTimeAndLeavePortAndReachPort(date,leavePort,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStopTime(Date date, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStopTime(date,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStopTimeAndReachPort(Date date, String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStopTimeAndReachPort(date,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStopTimeAndLeavePort(Date date, String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStopTimeAndLeavePort(date,leavePort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStopTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStopTimeAndLeavePortAndReachPort(date,leavePort,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTime(Date date, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTime(date,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndReachPort(Date date, String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndReachPort(date,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndLeavePort(Date date, String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndLeavePort(date,leavePort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndLeavePortAndReachPort(Date date, String leavePort, String reachPort,
			Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndLeavePortAndReachPort(date,leavePort,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTime(Date date1, Date date2, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndLeavePortAndReachPort(date1,date2,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(Date date1, Date date2,
			String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(date1,date2,reachPort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndLeavePort(Date date1, Date date2,
			String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(date1,date2,leavePort,pageRequest);
	}

	public Page<TdGoods> findByGroupSaleStartTimeAndGroupSaleStopTimeAndLeavePortAndReachPort(Date date1, Date date2,
			String leavePort, String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByGroupSaleStartTimeAndGroupSaleStopTimeAndReachPort(date1,date2,leavePort,reachPort,pageRequest);
	}

	public Page<TdGoods> findByReachPortAndIsOnSaleTrue(String reachPort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByReachPortAndIsOnSaleTrue(reachPort,pageRequest);
	}

	public Page<TdGoods> findByIsSpecialPriceTrueAndIsOnSaleTrue(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
		return repository.findByIsSpecialPriceTrueAndIsOnSaleTrue(pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanIsOnSaleTrue(Double keyword2,int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findBySalePriceLessThanAndIsOnSaleTrue(keyword2,pageRequest);
    }

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrCategoryTitleContainingOrReachPortContainingAndIsOnSaleTrue(Double keyword2,String keyword, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		if (keyword.equals("")||keyword.isEmpty()) {
   			return null;
   		}
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrue(keyword2,keyword, keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public Page<TdGoods> findBySalePriceAndIsOnSaleTrue(Double keyword2, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));

        return repository.findBySalePriceLessThanAndIsOnSaleTrue(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceDesc(Double keyword2, Integer page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceDesc(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrue(Double keyword2, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndIsOnSaleTrue(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceAsc(Double keyword2, Integer page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceAsc(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberAsc(Double keyword2, Integer page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberAsc(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberDesc(Double keyword2, Integer page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberDesc(keyword2,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrue(
			Double keyword2, String keyword, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrue(keyword2,keyword,keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(
			Double keyword2, String keyword, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(keyword2,keyword,keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(
			Double keyword2, String keyword, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(keyword2,keyword,keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(
			Double keyword2, String keyword, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(keyword2,keyword,keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(
			Double keyword2, String keyword, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(keyword2,keyword,keyword2,keyword,keyword2,keyword,pageRequest);
	}

	public TdGoods findByTitleAndCategoryIdTreeContaining(String txtcountry, String string) {
		return repository.findByTitleAndCategoryIdTreeContaining(txtcountry,string);
	}
	//gl
	public List<TdGoods> findByTitleContainigAndCategoryIdTreeContaining(String txtcountry, String string) {
		return repository.findByTitleContainingAndCategoryIdTreeContaining(txtcountry,string);
	} 

	public Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(
			String reachPort, String leavePort, String subTitle, String string, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(
				reachPort,leavePort,subTitle,string,pageRequest);
	}

	public Page<TdGoods> findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(String string, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(string,pageRequest);
	}

	public Page<TdGoods> findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(String string, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(string,pageRequest);
	}

	public Page<TdGoods> findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(
			String reachPort, String leavePort, String subTitle, String string, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findByReachPortContainingAndLeavePortContainingAndSubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(
				reachPort,leavePort,subTitle,string,pageRequest);
	}

	public List<TdGoods> findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(String string) {
		return repository.findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(string);
	}

	public List<TdGoods> findByTitleAndCategoryIdTreeContaining2(String txtcountry, String string) {
		// TODO Auto-generated method stub
		return repository.findByCategoryIdTreeContainingAndTitle(string,txtcountry);
	}

	public List<TdGoods> findTop4ByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(String string) {
		// TODO Auto-generated method stub
		return repository.findTop4ByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc(string);
	}

	public Page<TdGoods> findBySalePriceGreaterThanOrSalePriceLessThanOrSubTitleContainingOrCategoryTitleOrIdAndIsOnSaleTrueAndCarTypeTrue(
			double price11, double price22, String subTitle, Long id, String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "sortId").and(new Sort(Direction.DESC, "id")));
		return repository.findBySalePriceGreaterThanOrSalePriceLessThanOrSubTitleContainingOrCategoryTitleOrIdAndIsOnSaleTrueAndCarTypeTrue(price11,price22,subTitle,id,string,pageRequest);

	}

	public Page<TdGoods> findById(Long id11, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findById(id11,pageRequest);
	}

	public Page<TdGoods> findBySubTitleContainingAndCategoryTitle(String subTitle, String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(subTitle,string,pageRequest);
	}

	public Page<TdGoods> findBySalePriceGreaterThanAndCategoryTitle(Double price11, String string, Integer page,
			int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(price11,string,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndCategoryTitle(Double price22, String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(price22,string,pageRequest);
	}

	public Page<TdGoods> findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitle(Double price11, Double price22,
			String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanEqualAndSalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeTrue(price11,price22,string,pageRequest);
	}
	public Page<TdGoods> findBySubTitleContainingAndCategoryTitle1(String subTitle, String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleContainingAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(subTitle,string,pageRequest);
	}
	
	public Page<TdGoods> findBySalePriceGreaterThanAndCategoryTitle1(Double price11, String string, Integer page,
			int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(price11,string,pageRequest);
	}
	
	public Page<TdGoods> findBySalePriceLessThanAndCategoryTitle1(Double price22, String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(price22,string,pageRequest);
	}
	
	public Page<TdGoods> findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitle1(Double price11, Double price22,
			String string, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySalePriceLessThanEqualAndSalePriceGreaterThanEqualAndCategoryTitleAndIsOnSaleTrueAndCarTypeFalse(price11,price22,string,pageRequest);
	}
	//驾龄
	public Page<TdGoods> findBySubTitle(String subTitle, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleAndCategoryTitleAndIsOnSaleTrue(subTitle, "代驾服务", pageRequest);
	}
	//驾照
	public Page<TdGoods> findByLeavePort(String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByLeavePortAndCategoryTitleAndIsOnSaleTrue(leavePort, "代驾服务",pageRequest);
	}
	//司机
	public Page<TdGoods> findByTitle(String title, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTitleAndCategoryTitleAndIsOnSaleTrue(title, "代驾服务",pageRequest);
	}
	
	//驾龄+驾照
	public Page<TdGoods> findBySubTitleAndLeavePort(String subTitle, String leavePort, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleAndLeavePortAndCategoryTitleAndIsOnSaleTrue(subTitle, leavePort,"代驾服务", pageRequest);
	}
	
	//驾照+司机
	public Page<TdGoods> findLeavePortAndTitle(String leavePort, String title, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByLeavePortAndTitleAndCategoryTitleAndIsOnSaleTrue(leavePort, title, "代驾服务",pageRequest);
	}
	
	//驾龄+司机
	public Page<TdGoods> findSubTitleAndTitle(String subTitle, String title, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleAndTitleAndCategoryTitleAndIsOnSaleTrue(subTitle, title, "代驾服务",pageRequest);
	}
	
	//驾龄+驾照+司机
	public Page<TdGoods> findSubTitleAndLeavePortAndTitle(String subTitle, String leavePort, String title, Integer page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findBySubTitleAndLeavePortAndTitleAndCategoryTitleAndIsOnSaleTrue(subTitle, leavePort, title, "代驾服务",pageRequest);
	}
	
	/**
	 * @author Max
	 */
	public List<TdGoods> findByCategoryIdTreeContaining(Long categoryId)
	{
		String catIdStr = "[" + categoryId + "]";
		return repository.findByCategoryIdTreeContainingOrderBySortIdAsc(catIdStr);
	}
	
	public Page<TdGoods> searchCar(Double price1,Double price2, String carType, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		Criteria<TdGoods> c = new Criteria<TdGoods>();
		c.add(Restrictions.eq("categoryTitle", "长租服务", true));
		c.add(Restrictions.eq("isOnSale", true, true));
		if(price1 != null){
			c.add(Restrictions.gte("salePrice", price1, true));
		}
		if(price2 != null){
			c.add(Restrictions.lte("salePrice", price2, true));
		}
		if(carType != null && !carType.equals("")){
			c.add(Restrictions.like("subTitle", carType, true));
		}
		
		return repository.findAll(c, pageRequest);
		
		
	}
	
	public Page<TdGoods> searchCar2(String subTitle, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		Criteria<TdGoods> c = new Criteria<TdGoods>();
		c.add(Restrictions.eq("categoryTitle", "包车服务", true));
		c.add(Restrictions.eq("isOnSale", true, true));
		
		if(subTitle != null && !subTitle.equals("")){
			c.add(Restrictions.eq("subTitle", subTitle, true));
		}
		
		return repository.findAll(c, pageRequest);
	}
	
	public Page<TdGoods> searchCarLZ(String subTitle, Double price1, Double price2, Long goodsId, Integer page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(page, size);
		Criteria<TdGoods> c = new Criteria<TdGoods>();
		c.add(Restrictions.eq("categoryTitle", "临租服务", true));
		c.add(Restrictions.eq("isOnSale", true, true));
		if(subTitle != null && !subTitle.equals("")){
			c.add(Restrictions.eq("subTitle", subTitle, true));
		}
		if(price1 != null){
			c.add(Restrictions.gte("salePrice", price1, true));
		}
		if(price2 != null){
			c.add(Restrictions.lte("salePrice", price2, true));
		}
		if(goodsId != null){
			c.add(Restrictions.eq("id", goodsId, true));
		}
		
		return repository.findAll(c, pageRequest);
	}
	
	public List<TdGoods> searchCarLZAll() {
		Criteria<TdGoods> c = new Criteria<TdGoods>();
		c.add(Restrictions.eq("categoryTitle", "临租服务", true));
		c.add(Restrictions.eq("isOnSale", true, true));
		return repository.findAll(c);
	}
	
	// 已下架商品
	public List<TdGoods> searchNotonsaleGoods(){
		Criteria<TdGoods> c = new Criteria<>();
		c.add(Restrictions.eq("isOnSale", false, true));
//		c.setOrderByAsc("sortId");
		return repository.findAll(c);
	}
	
	// 已上架商品
	public List<TdGoods> searchOnsaleGoods(){
		Criteria<TdGoods> c = new Criteria<>();
		c.add(Restrictions.eq("isOnSale", true, true));
//		c.setOrderByAsc("sortId");
		return repository.findAll(c);
	}
	
	
	
}
