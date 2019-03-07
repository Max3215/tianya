package com.ynyes.tianya.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.tianya.entity.TdArticle;
import com.ynyes.tianya.entity.TdArticleCategory;
import com.ynyes.tianya.entity.TdSetting;

@Service
public class TdCommonService {

    @Autowired
    private TdSettingService tdSettingService;

    @Autowired
    private TdKeywordsService tdKeywordsService;

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdNaviBarItemService tdNaviBarItemService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdServiceItemService tdServiceItemService;
    
    @Autowired
    private TdAdTypeService tdAdTypeService;
    
    @Autowired
    private TdAdService tdAdService;
    
    //团购 zhangji
    @Autowired
    private TdDemandService tdDemandService;

    public void setCommon(ModelMap map, HttpServletRequest req) {
        
//    	String username = (String) req.getSession().getAttribute("username");

        // 用户名，购物车
//        if (null != username) {
//            map.addAttribute("username", username);
//            map.addAttribute("user",
//                    tdUserService.findByUsernameAndIsEnabled(username));
//            map.addAttribute("cart_goods_list",
//                    tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(username)));
//        } else {
//            map.addAttribute("cart_goods_list",
//                    tdCartGoodsService.updateGoodsInfo(tdCartGoodsService.findByUsername(req.getSession().getId())));
//        }
        
        // 网站基本信息
        TdSetting setting = tdSettingService.findTopBy();

        map.addAttribute("setting", setting);
        
        map.addAttribute("navi_bar_list", tdNaviBarItemService.findByIsEnableTrueOrderBySortIdAsc());
        
        map.addAttribute("service_qq_list", tdServiceItemService.findByIsEnableTrueOrderBySortIdAsc());
        
        
//        map.addAttribute("keywords_list",
//                tdKeywordsService.findByIsEnableTrueOrderBySortIdAsc());
//
//        // 全部商品分类，取三级
//        List<TdProductCategory> topCatList = tdProductCategoryService
//                .findByParentIdIsNullOrderBySortIdAsc();
//        map.addAttribute("top_cat_list", topCatList);
//
//        if (null != topCatList && topCatList.size() > 0) 
//        {
//            for (int i = 0; i < topCatList.size(); i++) 
//            {
//                TdProductCategory topCat = topCatList.get(i);
//                List<TdProductCategory> secondLevelList = tdProductCategoryService
//                        .findByParentIdOrderBySortIdAsc(topCat.getId());
//                map.addAttribute("second_level_" + i + "_cat_list", secondLevelList);
//
//                if (null != secondLevelList && secondLevelList.size() > 0) 
//                {
//                    for (int j=0; j<secondLevelList.size(); j++)
//                    {
//                        TdProductCategory secondLevelCat = secondLevelList.get(j);
//                        List<TdProductCategory> thirdLevelList = tdProductCategoryService
//                                .findByParentIdOrderBySortIdAsc(secondLevelCat.getId());
//                        map.addAttribute("third_level_" + i + j + "_cat_list", thirdLevelList);
//                    }
//                }
//            }
//        }
//
//        // 导航菜单
//        map.addAttribute("navi_item_list",
//                tdNaviBarItemService.findByIsEnableTrueOrderBySortIdAsc());
//        
//        // 商城服务
//        map.addAttribute("service_item_list", tdServiceItemService.findByIsEnableTrueOrderBySortIdAsc());
//
        // 文章信息
//        Long menuId = 10L;
//
        List<TdArticleCategory> level0HelpList = tdArticleCategoryService.findByChannelIdOrderBySortIdAsc(1L);
        
        if(null != level0HelpList ){
        	for (TdArticleCategory tdArticleCategory : level0HelpList) {
				if(null != tdArticleCategory && tdArticleCategory.getTitle().equals("底部文章")){
					map.addAttribute("article_cat_list", tdArticleService.findByCategoryId(tdArticleCategory.getId()));
				}
			}
        }
        
//        List<TdArticle> articlList = tdArticleService.findByMenuId(10L);
//        map.addAttribute("article_cat_list", articlList);
        
        // 文章内容信息
        /*for(TdArticleCategory tac : level0HelpList){
        	map.addAttribute(tac.getTitle(), tdArticleService.findByCategoryId(tac.getId()));
        }*/
        
//        if (null != level0HelpList) {
//			for (int i = 0; i < level0HelpList.size(); i++) {
//				TdArticleCategory articleCat = level0HelpList.get(i);
//				map.addAttribute("help_second_" + i + "_cat_list",
//						tdArticleService.findByCategoryId(articleCat.getId()));
//			}
//		}
        

//        if (null != level0HelpList) {
//
//            for (int i = 0; i < level0HelpList.size() && i < 4; i++) {
//                TdArticleCategory articleCat = level0HelpList.get(i);
//                map.addAttribute("help_" + i + "_cat_list",
//                        tdArticleCategoryService.findByMenuIdAndParentId(
//                                helpId, articleCat.getId()));
//            }
//        }
//
//        // 友情链接
//        map.addAttribute("site_link_list",
//                tdSiteLinkService.findByIsEnableTrue());
//        
//        //团购留言     
//        List<TdDemand> tdDemand = tdDemandService.findByStatusIdAndIsShowable();
//        map.addAttribute("demand_list",tdDemand);
        
    }

}
