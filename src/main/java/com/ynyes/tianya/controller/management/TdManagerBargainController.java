package com.ynyes.tianya.controller.management;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdBargainParticipant;
import com.ynyes.tianya.entity.TdBargainSetting;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.service.TdBargainParticipantService;
import com.ynyes.tianya.service.TdBargainRecordService;
import com.ynyes.tianya.service.TdBargainSettingService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.util.SiteMagConstant;

@Controller
@RequestMapping(value="/Verwalter")
public class TdManagerBargainController {

    @Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdBargainSettingService tdBargainSettingService;
	
	@Autowired
	TdBargainParticipantService tdBargainParticipantService;
	
	@Autowired
	TdBargainRecordService tdBargainRecordService;
	
	@Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
	
	@RequestMapping(value="/bargain/setting")
    public String setting(Long status, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("bargain_setting", tdBargainSettingService.findTopBy());
        map.addAttribute("status", status);
        return "/site_mag/activity_bargain_setting";
    }
	
	 @RequestMapping(value="/bargain/setting/save")
	 public String orderEdit(TdBargainSetting tdBargainSetting,
	                        ModelMap map,
	                        HttpServletRequest req){
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username)
	        {
	            return "redirect:/Verwalter/login";
	        }
	        
	        if (null == tdBargainSetting.getId())
	        {
	            tdManagerLogService.addLog("add", "用户修改活动设置", req);
	        }
	        else
	        {
	            tdManagerLogService.addLog("edit", "用户修改活动设置", req);
	        }
	        
	        tdBargainSettingService.save(tdBargainSetting);
	        
	        return "redirect:/Verwalter/bargain/setting?status=1";
	    }
	 
	 @RequestMapping(value="/bargain/participant/edit")
	 @ResponseBody
	 public Map<String, Object> editcurrentPrice(String data,String type,Long id,
             									 String info, ModelMap map,
             			                        HttpServletRequest req){
		    Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("code", 1);
	        
	        String username = (String) req.getSession().getAttribute("manager");
	        if (null == username)
	        {
	            res.put("message", "请重新登录");
	            return res;
	        }
	        
	        if (null != id && null != type) {
				TdBargainParticipant tdBargainParticipant = tdBargainParticipantService.findOne(id);
				
				if (type.equalsIgnoreCase("editCurrentPrice"))
	            {
	                tdBargainParticipant.setCurrentPrice(Double.parseDouble(data));
	                tdBargainParticipantService.save(tdBargainParticipant);
	                tdManagerLogService.addLog("edit", info, req);
	            }
				res.put("code", 0);
			}
	        
	        return res;
	 }
	 
	 @RequestMapping(value="/bargain/{type}/list")
	 public String participant(@PathVariable String type, 
			 				String __EVENTTARGET,
	                        String __EVENTARGUMENT,
	                        String __VIEWSTATE,
	                        Integer page,
	                        Integer size,	                 
	                        Long[] listId,
	                        Integer[] listChkId,
	                        Long[] listSortId,
	                        ModelMap map,
	                        HttpServletRequest req){
	    	 String username = (String) req.getSession().getAttribute("manager");
	         if (null == username) {
	             return "redirect:/Verwalter/login";
	         }
	         
	         //管理员角色
	         TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
	         TdManagerRole tdManagerRole = null;
	         
	         if (null != tdManager.getRoleId())
	         {
	             tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
	         }
	         
	         if (null != tdManagerRole) {
	 			map.addAttribute("tdManagerRole", tdManagerRole);
	 		}
	    	
	    	if (null != __EVENTTARGET)
	        {
	            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
	            {
	                if (null != __EVENTARGUMENT)
	                {
	                    page = Integer.parseInt(__EVENTARGUMENT);
	                } 
	            }
	            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
	            {
	            	btnDelete(type, listId, listChkId);
	            	if (type.equalsIgnoreCase("participant"))
	                {
	                    tdManagerLogService.addLog("delete", "删除砍价参与者", req);
	                }
	                else if (type.equalsIgnoreCase("record"))
	                {
	                    tdManagerLogService.addLog("delete", "删除砍价记录", req);
	                }
	            }
	            
	        }
	  
	       if (null == page || page < 0)
	       {
	           page = 0;
	       }
	       if (null == size || size <= 0)
	       {
	           size = SiteMagConstant.pageSize;;
	       }
	     
	       map.addAttribute("page", page);
	       map.addAttribute("size", size);
	       map.addAttribute("__EVENTTARGET", __EVENTTARGET);
	       map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
	       map.addAttribute("__VIEWSTATE", __VIEWSTATE);

	       if (type.equalsIgnoreCase("participant"))
           {
               map.addAttribute("participant_page", tdBargainParticipantService.findAll(page, size));
               return "/site_mag/activity_bargain_participant_list";
           }
           else if (type.equalsIgnoreCase("record"))
           {
        	   map.addAttribute("record_page", tdBargainRecordService.findAll(page, size));
        	   return "/site_mag/activity_bargain_record_list";
           }
	        
	       return "/site_mag/activity_bargain_record_list";
	    }
	 
	  @ModelAttribute
	  public void getModel(@RequestParam(value = "id", required = false) Long id,	                           
	                            ModelMap map) {
	        if (null != id) {
	            map.addAttribute("tdBargainSetting", tdBargainSettingService.findOne(id));
	        }
	        	       
	  } 
	  
	  private void btnDelete(String type, Long[] ids, Integer[] chkIds)
	    {
	        if (null == type || type.isEmpty())
	        {
	            return;
	        }
	        
	        if (null == ids || null == chkIds
	                || ids.length < 1 || chkIds.length < 1)
	        {
	            return;
	        }
	        
	        for (int chkId : chkIds)
	        {
	            if (chkId >=0 && ids.length > chkId)
	            {
	                Long id = ids[chkId];
	                
	                if (type.equalsIgnoreCase("participant"))
	                {
	                    tdBargainParticipantService.delete(id);
	                }
	                else if (type.equalsIgnoreCase("record"))
	                {
	                    tdBargainRecordService.delete(id);
	                }
	               
	            }
	        }
	    }
}
