package com.ynyes.tianya.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdProductCategoryService;

/**
 * 搜索列表
 * 
 * @author lulu
 *
 */
@Controller
public class TdSearchController{
	@Autowired
	TdCommonService tdCommonService;

	@Autowired
	TdGoodsService tdGoodsService;
    
	@Autowired
	TdAdTypeService tdAdTypeService;
	@Autowired
	TdProductCategoryService tdProductCategoryService;
    
	@Autowired
	TdAdService tdAdService;
	
	// keyword:目的地, keyword2:money, 
    @RequestMapping(value = "/search_list", method = RequestMethod.GET)
    public String searchList(HttpServletRequest req, String privateCustom, ModelMap map, String keyword, Double keyword2, Integer page){
    	tdCommonService.setCommon(map, req);
    	if(null==page){
    		page=0;
    	}
    	if(keyword==null){
    		keyword="";
    	}
    	
    	if(privateCustom == null || privateCustom.equals("")){
    		if(keyword2==null||keyword2.equals("")){
        		if(keyword.equals("")||keyword==null){
            		Page<TdGoods> tdGoods = tdGoodsService.findAllAndIsOnSaleTrue(page, 12);
            		map.addAttribute("goods_page", tdGoods);
            	}
            	if(!keyword.equals("")&&keyword!=null){
            		Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywords(keyword,page,12);
            		map.addAttribute("keyword", keyword);
            		map.addAttribute("goods_page", tdGoods);
            	}
        	}
        	if(keyword2!=null&&!keyword2.equals("")){
        		map.addAttribute("keyword2", keyword2);
        		if(keyword.equals("")||keyword==null){
            		Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanIsOnSaleTrue(keyword2,page, 12);
            		map.addAttribute("goods_page", tdGoods);
            	}
            	if(!keyword.equals("")&&keyword!=null){
            		Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywords(keyword,page,12);
            		map.addAttribute("keyword", keyword);
            		map.addAttribute("goods_page", tdGoods);
            	}
        	}
    	}else{
    		//获取私家定制类别
        	TdProductCategory pc = tdProductCategoryService.findByTitle("私家定制");
        	Page<TdGoods> tdGoods = tdGoodsService.findByCategoryIdAndIsOnSaleTrue(pc.getId(), page, 12);
        	map.addAttribute("keyword", keyword);
    		map.addAttribute("goods_page", tdGoods);
    	}
    	
    	// 私家定制轮播广告
		TdAdType adType5 = tdAdTypeService.findByTitle("私家定制轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType5) {
			map.addAttribute("big_scroll_ad_list5", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType5.getId()));
		}
        return "/client/search_list";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(HttpServletRequest req,ModelMap map,String keyword,Double keyword2,Integer page,Integer grade){
    	tdCommonService.setCommon(map, req);
    	if(null==page){
    		page=0;
    	}
    	if(keyword==null){
    		keyword="";
    	}
    	map.addAttribute("keyword",keyword);
    	map.addAttribute("keyword2",keyword2);
    	map.addAttribute("grade",grade);
    	if(null==keyword2||keyword2.equals("")){
    		if(keyword.equals("")){
        		if(!grade.equals("")&&grade!=null){
        			if(grade==0){
        				Page<TdGoods> tdGoods = tdGoodsService.findAllAndIsOnSaleTrue(page, 12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==1){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrue(page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==2){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueOrderByDesc(page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==3){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueOrderBySaleNumber(page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==4){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueOrderBySaleNumberDesc(page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}
        		}
        	}
        	if(!keyword.equals("")){
        		if(!grade.equals("")&&grade!=null){
        			if(grade==0){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywords(keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==1){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywordsOrderBySalePriceAsc(keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==2){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywordsOrderBySalePriceDesc(keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==3){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywordsOrderBySoldNumberAsc(keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==4){
        				Page<TdGoods> tdGoods = tdGoodsService.findByIsOnSaleTrueContainsKeywordsOrderBySoldNumberDesc(keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}
        		}
        	}
    	}
    	
    	if(null!=keyword2&&!keyword2.equals("")){
    		if(keyword.equals("")){
        		if(!grade.equals("")&&grade!=null){
        			if(grade==0){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndIsOnSaleTrue(keyword2,page, 12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==1){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceAsc(keyword2,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==2){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndIsOnSaleTrueOrderBySalePriceDesc(keyword2,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==3){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberAsc(keyword2,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==4){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndIsOnSaleTrueOrderBySoldNumberDesc(keyword2,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}
        		}
        	}
        	if(!keyword.equals("")){
        		if(!grade.equals("")&&grade!=null){
        			if(grade==0){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrue(keyword2,keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==1){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceAsc(keyword2,keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==2){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySalePriceDesc(keyword2,keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==3){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberAsc(keyword2,keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}else if(grade==4){
        				Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndTitleContainingOrSalePriceLessThanAndCategoryTitleContainingOrSalePriceLessThanAndReachPortContainingAndIsOnSaleTrueOrderBySoldNumberDesc(keyword2,keyword,page,12);
        				map.addAttribute("goods_page", tdGoods);
        			}
        		}
        	}
    	}
    	
    	// 私家定制轮播广告
		TdAdType adType5 = tdAdTypeService.findByTitle("私家定制轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType5) {
			map.addAttribute("big_scroll_ad_list5", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType5.getId()));
		}
        return "/client/search_list";
    }
}
