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
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerApply;
import com.ynyes.tianya.entity.TdNotice;
import com.ynyes.tianya.service.TdManagerApplyService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdNoticeService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台客户管理控制器
 * @author gl
 *
 */
@Controller
@RequestMapping(value="/Verwalter/notice")
public class TdManagerNoticeController {
	
	@Autowired
	TdNoticeService tdNoticeService;
	
	@Autowired
	TdManagerApplyService tdManagerApplyService;
	
	@Autowired
	TdManagerService tdManagerService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, Model model, String type, Integer page, Integer size, String __EVENTTARGET, String __EVENTARGUMENT){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        List<TdManager> tdManagerList = tdManagerService.findByParentTreeContaining(tdManager.getId());
        String isLowestManager = "no";
        if(tdManagerList.size() < 2){
        	isLowestManager = "yes";
        }
        model.addAttribute("isLowestManager", isLowestManager);
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
        }
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        
		
        Long managerId = tdManager.getId();
        
        if(type == null || type.equals("")){
        	//我发出的
        	Page<TdNotice> notice_out_page = tdNoticeService.findByNoticeId(managerId, 0, 10);
        	//我收到的
        	Page<TdNotice> notice_in_page = tdNoticeService.findByNoticedIdsContaining(managerId, 0, 10);
        	model.addAttribute("notice_out_page", notice_out_page);
        	model.addAttribute("notice_in_page", notice_in_page);
        	
        	
        	return "/site_mag/notice_list";
        }else if(type.equals("out")){
        	//我发出的
        	Page<TdNotice> notice_out_page = tdNoticeService.findByNoticeId(managerId, page, size);
        	model.addAttribute("notice_page2", notice_out_page);
        	model.addAttribute("typeTitle", "我发出的");
        	return "/site_mag/notice_list2";
        }else{
        	//我收到的
        	Page<TdNotice> notice_in_page = tdNoticeService.findByNoticedIdsContaining(managerId, page, size);
        	model.addAttribute("notice_page2", notice_in_page);
        	model.addAttribute("typeTitle", "我收到的");
        	return "/site_mag/notice_list2";
        }
	}
	
	@RequestMapping(value="/edit")
	public String edit(HttpServletRequest request, Model model){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        List<TdManager> tdManagerList = tdManagerService.findByParentTreeContaining(tdManager.getId());
        tdManagerList.remove(tdManager);
        model.addAttribute("tdManagerList", tdManagerList);
        return "/site_mag/notice_edit";
	}
	
	@RequestMapping(value="/save")
	public String save(HttpServletRequest request, Model model, TdNotice tdNotice, Long[] listId, int[] listChkId){
		
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        
        if(listChkId == null || listChkId.length < 1){
        	model.addAttribute("mNotNull", "no");
        	return "/site_mag/notice_edit";
        }
        tdNotice.setCreateTime(new Date());
        tdNotice.setNoticeId(tdManager.getId());
        tdNotice.setNoticeRealName(tdManager.getRealName());
        String ids = "";
        for(int i = 0; i < listChkId.length; i ++){
        	ids += "[" + listId[listChkId[i]] + "]";
        	if(i < listChkId.length - 1){
        		ids += ",";
        	}
        }
        System.err.println(ids);
        tdNotice.setNoticedIds(ids);
        tdNoticeService.save(tdNotice);
        return "redirect:/Verwalter/notice/list?type=out";
	}
	
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request, Model model, Long id){
		
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        List<TdManager> allList = new ArrayList<>();
        List<TdManager> sureList = new ArrayList<>();
        String canSure = "no";
        if(id != null){
        	TdNotice tdNotice = tdNoticeService.findOne(id);
        	model.addAttribute("tdNotice", tdNotice);
        	String[] ids = tdNotice.getNoticedIds().split(",");
        	for(int i = 0; i < ids.length; i ++){
        		String s = ids[i];
        		s = s.replace("[", "");
        		s = s.replace("]", "");
        		Long ls = Long.parseLong(s);
        		TdManager tm = null;
        		if(ls < 0){
        			 tm = tdManagerService.findOne(-ls);
        			 sureList.add(tm);
        			 allList.add(tm);
        		}else{
        			tm = tdManagerService.findOne(ls);
        			allList.add(tm);
        			if(ls == tdManager.getId()){
        				canSure = "yes";
        			}
        		}
        		
        	}
        	
        	model.addAttribute("allList", allList);
        	model.addAttribute("sureList", sureList);
        	model.addAttribute("id", id);
        	model.addAttribute("canSure", canSure);
        }
        
        return "/site_mag/notice_detail";
	}
	
	@RequestMapping(value="/goSure")
	public String goSure(HttpServletRequest request, Model model, Long id){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        if(id != null){
        	TdNotice tn = tdNoticeService.findOne(id);
        	tn.setNoticedIds(tn.getNoticedIds().replace("["+tdManager.getId()+"]", "[-"+tdManager.getId()+"]"));
        	tdNoticeService.save(tn);
        }
		return "redirect:/Verwalter/notice/list";
	}
	
	
	//@RequestMapping(value="/list")
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
        Page<TdManagerApply> myCheckedList_page = tdManagerApplyService.findByCheckManagerStrLikeOrCheckManagerStrLikeAndRefuse("%+" + tdManager.getId() + ",%", "%-" + tdManager.getId() + ",%", "已拒绝", page, size);
        model.addAttribute("myCheckedList_page", myCheckedList_page);
        
        Page<TdManagerApply> myUncheckList_page = tdManagerApplyService.findByCheckManagerStrContainsAndNotRefuse("%-" + tdManager.getId() + ",%", "已拒绝", page, size);
        
        model.addAttribute("myUncheckList_page", myUncheckList_page);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        //model.addAttribute("client_page", client_page);
        if(type != null && !type.equals("")){
        	if(type.equals("passList_page")){ 
        		model.addAttribute("type", "passList_page");
        		return "/site_mag/manager_apply_list2";
        		//return "/site_mag/manager_apply_passList_page_list";
        	}else if(type.equals("unpassList_page")){
        		model.addAttribute("type", "unpassList_page");
        		return "/site_mag/manager_apply_list2";
        	}else if(type.equals("myCheckedList_page")){
        		model.addAttribute("type", "myCheckedList_page");
        		return "/site_mag/manager_apply_list2";
        		//return "/site_mag/manager_apply_myCheckedList_page_list";
        	}else if(type.equals("myUncheckList_page")){
        		model.addAttribute("type", "myUncheckList_page");
        		return "/site_mag/manager_apply_list2";
        		//return "/site_mag/manager_apply_myUncheckList_page_list";
        	}        	
        }
        return "/site_mag/manager_apply_list";
        
	}
	
	
	
	//@RequestMapping(value="/edit")
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
		
		return "/site_mag/manager_apply_edit";
	}
	
	
	
	//@RequestMapping(value="/getCheck")
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
	
	//@RequestMapping(value="/detail")
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
	
	//@RequestMapping(value="/delete")
	public String delete(Long id){
		if(id != null){
			tdManagerApplyService.delete(id);
		}
		return "redirect:/Verwalter/managerApply/list?type=unpassList_page";
	}
	
}
