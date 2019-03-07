package com.ynyes.tianya.controller.front;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdTimePrice;
import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdArticleCategoryService;
import com.ynyes.tianya.service.TdArticleService;
import com.ynyes.tianya.service.TdBrandService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdSiteLinkService;
import com.ynyes.tianya.service.TdTimePriceService;
import com.ynyes.tianya.service.TdUserCommentService;

/**
 * 详情控制器
 *
 */
@Controller
@RequestMapping("/detail")
public class TdDetailController {
	@Autowired
	private TdUserCommentService tdUserCommentService;

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdAdTypeService tdAdTypeService;

    @Autowired
    private TdAdService tdAdService;

    @Autowired
    private TdBrandService tdBrandService;
    
    @Autowired
    private TdTimePriceService tdTimePriceService;

    @RequestMapping("/{goodsId}")
    public String product(@PathVariable Long goodsId, Long shareId,
            Integer qiang, ModelMap map, HttpServletRequest req,Integer page) 
    {
    	// 首次访问,清除次订单session
    	req.getSession().removeAttribute("PSLIST");
    	req.getSession().removeAttribute("adiltNum");
    	req.getSession().removeAttribute("childNum");
    	
    	tdCommonService.setCommon(map, req);
        
        TdGoods goods = tdGoodsService.findByIdAndIsOnSaleTrue(goodsId);
        
        if (null == goods)
        {
        	return "/client/error_404";
        }
        
        // 商品
        map.addAttribute("goods", goods);
        
        // 查找类型
        TdProductCategory tdProductCategory = tdProductCategoryService
                .findOne(goods.getCategoryId());

        // 获取该类型所有父类型
        if (null != tdProductCategory) {
            if (null != tdProductCategory.getParentTree()
                    && !"".equals(tdProductCategory.getParentTree())) {
                List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

                for (String cid : tdProductCategory.getParentTree().split(",")) {
                    if (!"".equals(cid)) {
                        // 去除方括号
                        cid = cid.replace("[", "");
                        cid = cid.replace("]", "");

                        TdProductCategory tpc = tdProductCategoryService
                                .findOne(Long.parseLong(cid));

                        if (null != tpc) {
                            catList.add(tpc);
                        }
                    }
                }

                map.addAttribute("category_tree_list", catList);
            }
        }
        
        // 邮轮俱乐部
 		TdProductCategory shipCat = tdProductCategoryService
 				.findByTitle("邮轮俱乐部");
 		// 目的地
 		TdProductCategory destinationCat = tdProductCategoryService
 				.findByTitle("目的地");
 		// 主题活动
 		TdProductCategory activityCat = tdProductCategoryService
 				.findByTitle("主题活动");
 		// 私家定制
 		TdProductCategory customCat = tdProductCategoryService
 				.findByTitle("私家定制");
 		// 特产商城
 		TdProductCategory specialCat = tdProductCategoryService
 				.findByTitle("特产商城");
 		// 签证
 		TdProductCategory visaCat = tdProductCategoryService.findByTitle("签证");
 		// 旅游直通车
 		TdProductCategory throughCat = tdProductCategoryService.findByTitle("旅游直通车");
 		//汽车租赁
 		TdProductCategory rentCat = tdProductCategoryService.findByTitle("汽车租赁");
 		if(null==page){
 			page=0;
 		}
 		
 		//评论列表显示  审核通过了 才能在前台显示
    	Page<TdUserComment> tdUserCommentList = tdUserCommentService.findByGoodsIdAndStatusIdOrderByIdDesc(goodsId,1L,page,10);
		map.addAttribute("comment_page", tdUserCommentList);
		Long count = tdUserCommentService.countByGoodsId(goodsId);
		map.addAttribute("count", count);
		
    	
 		if (goods.getCategoryIdTree().contains("["+ shipCat.getId() +"]"))
 		{
 			return "/client/ship_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ destinationCat.getId() +"]"))
 		{
 			return "/client/destination_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ activityCat.getId() +"]"))
 		{
 			return "/client/activity_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ throughCat.getId() +"]"))
 		{
 			return "/client/through_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ rentCat.getId() +"]"))
 		{
 			return "/client/rent_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ specialCat.getId() +"]"))
 		{
 			return "/client/special_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ visaCat.getId() +"]"))
 		{
 			return "/client/visa_detail";
 		}
 		else if(goods.getCategoryIdTree().contains("["+ customCat.getId() +"]")){
 			return "/client/activity_detail";
 		}
        return "/client/error_404";
    }
    
    @RequestMapping(value="/price")
    public String ss(HttpServletRequest req,ModelMap map)
    {
    	return "/client/a_a";
    }
    
    @RequestMapping(value="/showprice",method=RequestMethod.GET)
    @ResponseBody
    public StringBuffer ces(Long id,HttpServletRequest req)
    {
    	if(null == id){
    		id=1L;
    	}
    	
    	Date cur = new Date();
        Calendar calendar = Calendar.getInstance();// 日历对象
        calendar.setTime(cur);// 设置当前日期
//        calendar.add(Calendar.MONTH, -1);// 月份减一
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date startTime = calendar.getTime();
        
    	List<TdTimePrice> list = tdTimePriceService.findByGoodsId(id,startTime);
		StringBuffer str = new StringBuffer();
		
		if(null != list && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				TdTimePrice timePrice = list.get(i);
				
				Double childPrice = 0.0;
				if(null != timePrice.getChildPrice()){
					childPrice = timePrice.getChildPrice();
				}
				
				Date date = timePrice.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String time = sdf.format(date);
				if(list.size()==1){
					str.append("[{'Date':'"+time +"','Price':'"+timePrice.getPrice()+"','childPrice':'"+childPrice+"'}]");
				}else if(i==0){
					str.append("[{'Date':'"+time +"','Price':'"+timePrice.getPrice()+"','childPrice':'"+childPrice+"'},");
				}else if(i == list.size()-1){
					str.append("{'Date':'"+time +"','Price':'"+timePrice.getPrice()+"','childPrice':'"+childPrice+"'}]");
				}else{
					str.append("{'Date':'"+time +"','Price':'"+timePrice.getPrice()+"','childPrice':'"+childPrice+"'},");
				}
			}
		}else{
			str.append("[{'Date':'','Price':'','childPrice':''}]");
		}
		
		return str;
    }
}
