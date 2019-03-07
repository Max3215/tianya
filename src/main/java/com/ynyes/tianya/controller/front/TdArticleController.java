package com.ynyes.tianya.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdArticle;
import com.ynyes.tianya.entity.TdServiceItem;
import com.ynyes.tianya.service.TdArticleService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdServiceItemService;

/**
 * lulu
 * 页面底部的跳转
 *
 */
@Controller
public class TdArticleController{
	
	@Autowired
	private TdCommonService tdCommonService;

	// Max
	@Autowired
	private TdArticleService tdArticleService;
	
	@Autowired
    private TdServiceItemService tdServiceItemService;
	
	
	@RequestMapping("/content/{id}")
    public String content(@PathVariable Long id,Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setCommon(map, req);
	    
        if (null == id )
        {
            return "/client/error_404";
        }
        
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            if(null != tdArticle.getCategoryId())
            {
            	map.addAttribute("info_list", tdArticleService.findByCategoryId(tdArticle.getCategoryId()));
            }
//            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
//            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        List<TdServiceItem> tdServiceItemList = tdServiceItemService.findByIsEnableFalseOrderBySortIdAsc();
        for(TdServiceItem si : tdServiceItemList){
        	if(si.getDepartment().contains("商旅酒店/机票代理")){
        		map.addAttribute("popQQ", si.getQq());
        	}
        }
        
        
        return "/client/article_page";
    }
}
