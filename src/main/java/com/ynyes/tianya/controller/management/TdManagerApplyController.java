package com.ynyes.tianya.controller.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerApply;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdManagerApplyService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdUserCommentService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台客户管理控制器
 * @author gl
 *
 */
@Controller
@RequestMapping(value="/Verwalter/managerApply")
public class TdManagerApplyController {
	
	@Autowired
	TdManagerApplyService tdManagerApplyService;
	
	@Autowired
	TdManagerService tdManagerService;
	
	@Autowired
	TdGoodsService tdGoodsService;

	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	TdProductCategoryService tdProductCategoryService;
	
	@Autowired
	TdUserCommentService tdUserCommentService;
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdManagerRoleService tdManagerRoleService;
	
	@RequestMapping(value="/list")
	public String tdManagerApplyList(HttpServletRequest request ,Model model, Integer page, Integer size, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Long[] listId, Integer[] listChkId, String type){
		
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        model.addAttribute("tdManager", tdManager);
        
        if (null != __EVENTTARGET)
        {
            
           
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;
        	//size = 2;
        }
        
        // 我发出且已通过的, passList
        Page<TdManagerApply> passList_page = tdManagerApplyService.findByManagerIdAndCheckManagerStrNotContains(tdManager.getId() , "%-%", page, size);
        model.addAttribute("passList_page", passList_page);
        
        
        // 我发出的且未通过的, unpassList
        Page<TdManagerApply> unpassList_page = tdManagerApplyService.findByManagerIdAndCheckManagerStrContains(tdManager.getId(), "%-%", page, size);
        model.addAttribute("unpassList_page", unpassList_page);
        
        // 我已审核的
        Page<TdManagerApply> myCheckedList_page = tdManagerApplyService.findByCheckManagerStrLikeOrCheckManagerStrLikeAndRefuse("%+" + tdManager.getId() + ",%", "%-" + tdManager.getId() + ",%", "已拒绝", page, size);
        model.addAttribute("myCheckedList_page", myCheckedList_page);
        
        Page<TdManagerApply> myUncheckList_page = tdManagerApplyService.findByCheckManagerStrContainsAndNotRefuse("%-" + tdManager.getId() + ",%", "已拒绝", page, size);
        
        model.addAttribute("myUncheckList_page", myUncheckList_page);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        if(type != null && !type.equals("")){
        	if(type.equals("passList_page")){ 
        		model.addAttribute("type", "passList_page");
        		return "/site_mag/manager_apply_list2";
        	}else if(type.equals("unpassList_page")){
        		model.addAttribute("type", "unpassList_page");
        		return "/site_mag/manager_apply_list2";
        	}else if(type.equals("myCheckedList_page")){
        		model.addAttribute("type", "myCheckedList_page");
        		return "/site_mag/manager_apply_list2";
        	}else if(type.equals("myUncheckList_page")){
        		model.addAttribute("type", "myUncheckList_page");
        		return "/site_mag/manager_apply_list2";
        	}        	
        }
        return "/site_mag/manager_apply_list";
        
	}
	
	@RequestMapping(value="/edit")
	public String managerApplyEdit(HttpServletRequest request, Model model, Long id){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		if(id !=  null){
			TdManagerApply tma = tdManagerApplyService.findById(id);
			if(tma != null){
				model.addAttribute("tma", tma);
			}
			
		}
		// 审核人列表,不能包含自己
		List<TdManager> checkManagerList = tdManagerService.findAllAndIsEnable();
		checkManagerList.remove(tdManager);
		model.addAttribute("checkManagerList", checkManagerList);
		
		// 已下架商品
		List<TdGoods> unsaleGoodsList = tdGoodsService.searchNotonsaleGoods();
		model.addAttribute("unsaleGoodsList", unsaleGoodsList);
		
		// 已上架商品
		List<TdGoods> saleGoodsList = tdGoodsService.searchOnsaleGoods();
		model.addAttribute("saleGoodsList", saleGoodsList);
		
		return "/site_mag/manager_apply_edit";
	}
	
	@RequestMapping(value="/save")
	public String managerApplySave(HttpServletRequest request, Model model, Long id, TdManagerApply tdManagerApply, Long[] listId, int[] listChkId, Long[] checkId, String listChkIdStr, String order){
		
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        tdManagerApply.setApplyTime(new Date());
        tdManagerApply.setManagerId(tdManager.getId());
        tdManagerApply.setManagerName(tdManager.getRealName());
        String checkManagerStr = "";

        //利用js带顺序
        order = order.replaceAll("-", "");
        String[] orders = order.split(",");
       
        for(int i = 0; i < orders.length; i ++){
        	
        	checkManagerStr += "-" + checkId[Integer.parseInt(orders[i])] + ",";
        }
        
        tdManagerApply.setCheckManagerStr(checkManagerStr);
        if(tdManagerApply.getApplyType() != null && tdManagerApply.getApplyType() == 1L){
        	String[] saleGoodsIds = spitString(tdManagerApply.getUnsaleGoodsId());
        	if(null != saleGoodsIds){
        		tdManagerApply.setUnsaleGoodsUrl(detailUrl(saleGoodsIds));
        	}
//        		String unsaleGoodsUrl = "/Verwalter/managerApply/goodsDetail?id="+tdManagerApply.getUnsaleGoodsId();
//        		tdManagerApply.setUnsaleGoodsUrl(unsaleGoodsUrl);
        }else if(tdManagerApply.getApplyType() != null && tdManagerApply.getApplyType() == 2L){
//        	String saleGoodsUrl = "/Verwalter/managerApply/goodsDetail?id="+tdManagerApply.getSaleGoodsId();
//        	tdManagerApply.setSaleGoodsUrl(saleGoodsUrl);
        	String[] goodsIds = spitString(tdManagerApply.getSaleGoodsId());
        	if(null != goodsIds){
        		tdManagerApply.setSaleGoodsUrl(detailUrl(goodsIds));
        	}
        }
        tdManagerApplyService.save(tdManagerApply);
		//return null;
        //下面是复制的detail
		model.addAttribute("tdManagerApply", tdManagerApply);
        List<TdManager> selectedCheckManagerList = new ArrayList<>();
        checkManagerStr = tdManagerApply.getCheckManagerStr();
        String[] checkManagerIdStr = checkManagerStr.split(","); 
        Long[] checkManagerId1 = new Long[checkManagerIdStr.length]; 
        Long[] checkManagerId2 = new Long[checkManagerIdStr.length]; //审核人Id,便于查询全全部取回正数
        for(int i = 0; i < checkManagerIdStr.length; i ++ ){
        	Long tempId = Long.parseLong(checkManagerIdStr[i]);
        	checkManagerId1[i] = tempId;
        	checkManagerId2[i] = Math.abs(tempId);
        }
        for(int i = 0; i < checkManagerId2.length; i ++){
        	TdManager tempManager = tdManagerService.findOne(checkManagerId2[i]);
        	//tempManager.setId(checkManagerId1[i]);
        	selectedCheckManagerList.add(tempManager);
        }
        for(int i = 0; i < checkManagerId1.length; i ++){
        	selectedCheckManagerList.get(i).setId(checkManagerId1[i]);
        }
        model.addAttribute("selectedCheckManagerList", selectedCheckManagerList);
		// 审核的话,加上该审核的申请人id
        for(int i = 0; i < checkManagerId1.length; i ++){
        	if(checkManagerId1[i] < 0){
        		//我审核的时候,第一个负数是我的Id
        		if(checkManagerId2[i] == (Long)Math.abs(tdManager.getId()) && !tdManagerApply.getIsRefuse() ){
        			model.addAttribute("canICheck", "yes");
        		}
        		break;
        	}
        }
        
        return "/site_mag/manager_apply_detail";
		
		
	}
	
	private String[] spitString(String str){
		if(null == str){
			return null;
		}
		return str.split(",");
	}
	
	private String detailUrl(String[] goodsIds){
		StringBuffer url =new StringBuffer();
		if(null != goodsIds){
			for (String goodsId : goodsIds) {
				url.append("/Verwalter/managerApply/goodsDetail?id="+goodsId+",");
			}
		}
		return url.toString();
	}
	
	@RequestMapping(value="/getCheck")
	public String getCheck(Long id, HttpServletRequest request, Model model, String agree, String refuseReson, String reject){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerApply tdManagerApply = tdManagerApplyService.findById(id);
        if(refuseReson==null || refuseReson.equals("")){
        	refuseReson="无";
        }
        if(tdManagerApply.getRefuseReason()==null){
        	tdManagerApply.setRefuseReason("");
        }
        tdManagerApply.setRefuseReason(tdManagerApply.getRefuseReason() + "_" + tdManager.getRealName()+"_"+refuseReson);
        
        String str = tdManagerApply.getCheckManagerStr();
        if(reject != null && reject.equals("reject")){
        	tdManagerApply.setIsRefuse(true);
        	tdManagerApply.setState("已拒绝");
        }else{
        	str = str.replaceAll("-"+tdManager.getId(), "+"+tdManager.getId());        	
        }
        if(str.indexOf("-") < 0){
        	tdManagerApply.setState("已通过");
        	// 上架审核通过后，
        	if(null != tdManagerApply.getApplyType() && tdManagerApply.getApplyType() ==1)
        	{
        		String[] goodsIds = spitString(tdManagerApply.getUnsaleGoodsId());
        		if(null != goodsIds){
        			for (String goodsId : goodsIds) {
//        				TdGoods goods = tdGoodsService.findOne(tdManagerApply.getUnsaleGoodsId());
        				TdGoods goods = tdGoodsService.findOne(Long.parseLong(goodsId));
        				if(null != goods){
        					// 如果管理员有上下架权限 自动上架
        					if(null != tdManager && null != tdManager.getCanUpDown() && tdManager.getCanUpDown())
        					{
        						goods.setIsOnSale(true);
        						tdGoodsService.save(goods);
        						tdManagerLogService.addLog("edit", "通过审核上架商品", request);
        					}
        					
        				}
					}
        		}
        	}
        	// 下架审核通过后
        	if(null != tdManagerApply.getApplyType() && tdManagerApply.getApplyType() ==2)
        	{
        		String[] goodsIds = spitString(tdManagerApply.getSaleGoodsId());
        		if(null != goodsIds){
        			for (String goodId : goodsIds) {
        				TdGoods goods = tdGoodsService.findOne(Long.parseLong(goodId));
//        		TdGoods goods = tdGoodsService.findOne(tdManagerApply.getSaleGoodsId());
        				if(null != goods){
        					// 如果管理员有上下架权限  自动下架
        					if(null != tdManager && null != tdManager.getCanUpDown() && tdManager.getCanUpDown())
        					{
        						goods.setIsOnSale(false);
        						tdGoodsService.save(goods);
        						tdManagerLogService.addLog("edit", "通过审核下架商品", request);
        					}
        				}
					}
        		}
        	}
        }
        
        
        tdManagerApply.setCheckManagerStr(str);
        tdManagerApplyService.save(tdManagerApply);
        
        
		//return null;
        //下面复制detail
        model.addAttribute("tdManagerApply", tdManagerApply);
        List<TdManager> selectedCheckManagerList = new ArrayList<>();
        String checkManagerStr = tdManagerApply.getCheckManagerStr();
        String[] checkManagerIdStr = checkManagerStr.split(","); 
        Long[] checkManagerId1 = new Long[checkManagerIdStr.length]; 
        Long[] checkManagerId2 = new Long[checkManagerIdStr.length]; //审核人Id,便于查询全全部取回正数
        for(int i = 0; i < checkManagerIdStr.length; i ++ ){
        	Long tempId = Long.parseLong(checkManagerIdStr[i]);
        	checkManagerId1[i] = tempId;
        	checkManagerId2[i] = Math.abs(tempId);
        }
        for(int i = 0; i < checkManagerId2.length; i ++){
        	TdManager tempManager = tdManagerService.findOne(checkManagerId2[i]);
        	//tempManager.setId(checkManagerId1[i]);
        	selectedCheckManagerList.add(tempManager);
        }
        for(int i = 0; i < checkManagerId1.length; i ++){
        	selectedCheckManagerList.get(i).setId(checkManagerId1[i]);
        }
        model.addAttribute("selectedCheckManagerList", selectedCheckManagerList);
		// 审核的话,加上该审核的申请人id
        for(int i = 0; i < checkManagerId1.length; i ++){
        	if(checkManagerId1[i] < 0){
        		//我审核的时候,第一个负数是我的Id
        		if(checkManagerId2[i] == (Long)Math.abs(tdManager.getId()) && !tdManagerApply.getIsRefuse()){
        			model.addAttribute("canICheck", "yes");
        		}
        		break;
        	}
        }
        
      //评论展示
        if(tdManagerApply.getRefuseReason()!=null){
	        String ss=tdManagerApply.getRefuseReason().replaceFirst("_", "");
	        String[] comments = ss.split("_");
	        List<Map<String, String>> commentList = new ArrayList<>();
	        for(int i = 0; i < comments.length; i=i+2){
	        	Map<String, String> clm = new HashMap<>();
	        	clm.put("name", comments[i]);
	        	clm.put("comment", comments[i+1]);
	        	commentList.add(clm);
	        }
	        if(commentList.size()>0){
	        	model.addAttribute("commentList", commentList);
	        }
        }
        
        return "/site_mag/manager_apply_detail";
	}
	
	@RequestMapping(value="/detail")
	public String managerApplyDetail(Model model, Long id, HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        model.addAttribute("tdManager", tdManager);
        TdManagerApply tdManagerApply = tdManagerApplyService.findById(id);
        model.addAttribute("tdManagerApply", tdManagerApply);
        //评论展示
        if(tdManagerApply.getRefuseReason()!=null){
        	//String[] comments = tdManagerApply.getRefuseReason().split("_");
        	String ss=tdManagerApply.getRefuseReason().replaceFirst("_", "");
	        String[] comments = ss.split("_");
        	List<Map<String, String>> commentList = new ArrayList<>();
        	for(int i = 0; i < comments.length; i=i+2){
        		Map<String, String> clm = new HashMap<>();
        		clm.put("name", comments[i]);
        		clm.put("comment", comments[i+1]);
        		commentList.add(clm);
        	}
        	if(commentList.size()>0){
        		model.addAttribute("commentList", commentList);
        	}        	
        }
        
        List<TdManager> selectedCheckManagerList = new ArrayList<>();
        String checkManagerStr = tdManagerApply.getCheckManagerStr();
        String[] checkManagerIdStr = checkManagerStr.split(","); 
        Long[] checkManagerId1 = new Long[checkManagerIdStr.length]; 
        Long[] checkManagerId2 = new Long[checkManagerIdStr.length]; //审核人Id,便于查询全全部取回正数
        for(int i = 0; i < checkManagerIdStr.length; i ++ ){
        	Long tempId = Long.parseLong(checkManagerIdStr[i]);
        	checkManagerId1[i] = tempId;
        	checkManagerId2[i] = Math.abs(tempId);
        }
        for(int i = 0; i < checkManagerId2.length; i ++){
        	TdManager tempManager = tdManagerService.findOne(checkManagerId2[i]);
        	//tempManager.setId(checkManagerId1[i]);
        	selectedCheckManagerList.add(tempManager);
        }
        for(int i = 0; i < checkManagerId1.length; i ++){
        	selectedCheckManagerList.get(i).setId(checkManagerId1[i]);
        }
        model.addAttribute("selectedCheckManagerList", selectedCheckManagerList);
		// 审核的话,加上该审核的申请人id
        for(int i = 0; i < checkManagerId1.length; i ++){
        	if(checkManagerId1[i] < 0){
        		//我审核的时候,第一个负数是我的Id
        		if(checkManagerId2[i] == (Long)Math.abs(tdManager.getId()) && !tdManagerApply.getIsRefuse()){
        			model.addAttribute("canICheck", "yes");
        		}
        		break;
        	}
        }
        
        return "/site_mag/manager_apply_detail";
        
	}
	
	@RequestMapping(value="/delete")
	public String delete(Long id){
		if(id != null){
			tdManagerApplyService.delete(id);
		}
		return "redirect:/Verwalter/managerApply/list?type=unpassList_page";
	}
	
	@RequestMapping(value="/goodsDetail")
	public String goodsDetail(Long id, HttpServletRequest req,ModelMap map)
	{
		tdCommonService.setCommon(map, req);
		
		if(null == id)
		{
			return "/site_mag/error_404";
		}
		
		TdGoods goods = tdGoodsService.findOne(id);
		if(null == goods)
		{
			return "/site_mag/error_404"; 
		}
		
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
 		
 		
 		//评论列表显示  审核通过了 才能在前台显示
    	Page<TdUserComment> tdUserCommentList = tdUserCommentService.findByGoodsIdAndStatusIdOrderByIdDesc(id,1L,0,10);
		map.addAttribute("comment_page", tdUserCommentList);
		Long count = tdUserCommentService.countByGoodsId(id);
		map.addAttribute("count", count);
		
    	
 		if (goods.getCategoryIdTree().contains("["+ shipCat.getId() +"]"))
 		{
 			return "/site_mag/ship_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ destinationCat.getId() +"]"))
 		{
 			return "/site_mag/destination_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ activityCat.getId() +"]"))
 		{
 			return "/site_mag/activity_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ specialCat.getId() +"]"))
 		{
 			return "/site_mag/special_detail";
 		}
 		else if (goods.getCategoryIdTree().contains("["+ visaCat.getId() +"]"))
 		{
 			return "/site_mag/visa_detail";
 		}else if(goods.getCategoryIdTree().contains("["+ customCat.getId() +"]")){
 			return "/client/activity_detail";
 		}
		return "/site_mag/error_404";
		
	}
	
	

}
