package com.ynyes.tianya.controller.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdNavigationMenu;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdReserveCar;
import com.ynyes.tianya.entity.TdSetting;
import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.entity.TdUserConsult;
import com.ynyes.tianya.entity.TdUserSuggestion;
import com.ynyes.tianya.entity.TdVisitorCustom;
import com.ynyes.tianya.service.TdDemandService;
import com.ynyes.tianya.service.TdManagerApplyService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdNavigationMenuService;
import com.ynyes.tianya.service.TdNoticeService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdReserverCarService;
import com.ynyes.tianya.service.TdSettingService;
import com.ynyes.tianya.service.TdUserCommentService;
import com.ynyes.tianya.service.TdUserConsultService;
import com.ynyes.tianya.service.TdUserSuggestionService;
import com.ynyes.tianya.service.TdVisitorCustomService;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */
@Controller
public class TdManagerIndexController {

    @Autowired
    TdNavigationMenuService tdNavigationMenuService;

    @Autowired
    TdManagerService tdManagerService;

    @Autowired
    TdSettingService tdSettingService;
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdUserCommentService tdUserCommentService;
    
    @Autowired
    TdUserConsultService tdUserConsultService;
    
    @Autowired
    TdUserSuggestionService tdUserSuggestionService;
    
    @Autowired
    TdDemandService tdDemandService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerApplyService tdManagerApplyService;
    
    @Autowired
    TdNoticeService tdNoticeService;
    
    @Autowired
    TdVisitorCustomService tdVisitorCustomService;
    
    @Autowired
    TdReserverCarService tdReserverCarService;

    @RequestMapping(value = "/Verwalter")
    public String index(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        /**
		 * @author lc
		 * @注释：管理员角色判断
		 */
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager && null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole && !tdManagerRole.getIsSys()) {
        	List<TdNavigationMenu> rootMenuList = tdNavigationMenuService
                    .findByParentIdAndSort(0L);
        	TdNavigationMenu rootmenuList[] = null;
        	if (null !=rootMenuList ) {
        		//将list中的数据存入数组中
                rootmenuList = new TdNavigationMenu[rootMenuList.size()];
            	for(int i = 0; i < rootMenuList.size(); i++){
            		rootmenuList[i] = rootMenuList.get(i);
            	}
			}
        	
        	int tempNumber = 0;
        	int total_index = 0;
			for(int i = 0; i < rootmenuList.length && total_index < tdManagerRole.getTotalPermission(); i++){
					if (total_index >= tdManagerRole.getPermissionList().size()) {
						//rootMenuList.remove(i);
						rootmenuList[i] = null;
					}else{
						if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
							rootmenuList[i] = null;
						}
					}
	
					total_index = total_index + 1;
					TdNavigationMenu rootMenu = null;
					if (i < rootmenuList.length) {
					    rootMenu = rootMenuList.get(i);
					}
					
	                // 取一级菜单列表
	                List<TdNavigationMenu> level0MenuList = null;
	                if (null != rootMenu) {
	                	level0MenuList = tdNavigationMenuService
		                        .findByParentIdAndSort(rootMenu.getId());
					}else{
						
					}
	                
	                TdNavigationMenu level0menuList[] = null;
	                if (null != level0MenuList) {
	                	//将list中的数据存入数组中
		                level0menuList = new TdNavigationMenu[level0MenuList.size()];
		            	for(int a = 0; a < level0MenuList.size(); a++){
		            		level0menuList[a] = level0MenuList.get(a);
		            	}
					}
	                
	                int tempIndex = 0;
	                if (null != level0menuList && level0menuList.length > 0){
		                for(int j = 0; j < level0menuList.length && total_index < tdManagerRole.getTotalPermission(); j++){
		                	if (total_index >= tdManagerRole.getPermissionList().size()) {
		                		level0menuList[j] = null;
							}else{
								if(null!=tdManagerRole.getPermissionList().get(total_index)){
			                		if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
			                			level0menuList[j] = null;
				    				}
			                	}
							}
		                	
			                	total_index = total_index + 1;
			                	
			                	TdNavigationMenu level0Menu = null;
			                	if (j < level0menuList.length) {
			                		level0Menu = level0MenuList.get(j);
								}			                	
			                 
			                    // 取二级菜单列表
			                	
			                    List<TdNavigationMenu> level1MenuList = null;
			                    if(null != level0Menu){
			                    	level1MenuList = tdNavigationMenuService
				                            .findByParentIdAndSort(level0Menu.getId());
			                    }
			                    
			                    TdNavigationMenu level1menuList[] = null;
			                    if (null != level1MenuList) {
			                    	//将list中的数据存入数组中
					            	level1menuList = new TdNavigationMenu[level1MenuList.size()];
					            	for(int b = 0; b < level1MenuList.size(); b++){
					            		level1menuList[b] = level1MenuList.get(b);
					            	}
								}
			                    
			                    if (null != level1menuList && level1menuList.length > 0) {
				                    for(int c = 0; c < level1menuList.length && total_index < tdManagerRole.getTotalPermission(); c++){
				                    	if (total_index >= tdManagerRole.getPermissionList().size()) {
				                    		level1menuList[c] = null;
										}else{
											if(null!=tdManagerRole.getPermissionList().get(total_index)){
					                    		if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
					                    			level1menuList[c] = null;
						        				}
						                    	
					                    	}
										}
				                    	
				                    	total_index = total_index + 1;
				                    }
				                    
				                    change(level1MenuList, level1menuList);
				                    if (null != level1MenuList && level1MenuList.size() > 0) {
					                    map.addAttribute("level_" + i + tempIndex + "_menu_list",
					                                level1MenuList);
					                    tempIndex += 1;
				                    }
			                    }
	
		                }
		                change(level0MenuList, level0menuList);
		                if (null != level0MenuList && level0MenuList.size() > 0){
			                map.addAttribute("level_" + i + "_menu_list",
			                        level0MenuList);
			                }
	                }    		
				
			}
			//change(rootMenuList, rootmenuList); //不交换保证了一级菜单全部显示
			if (null != rootMenuList && rootMenuList.size() > 0){
				map.addAttribute("root_menu_list", rootMenuList);
				//map.addAttribute("root_menu_list", rootmenuList);
		    }
		}
        else{
        	List<TdNavigationMenu> rootMenuList = tdNavigationMenuService
                    .findByParentIdAndSort(0L);

            if (null != rootMenuList && rootMenuList.size() > 0) {
                for (int i = 0; i < rootMenuList.size(); i++) {
                    TdNavigationMenu rootMenu = rootMenuList.get(i);

                    // 取一级菜单列表
                    List<TdNavigationMenu> level0MenuList = tdNavigationMenuService
                            .findByParentIdAndSort(rootMenu.getId());

                    if (null != level0MenuList && level0MenuList.size() > 0) {
                        map.addAttribute("level_" + i + "_menu_list",
                                level0MenuList);

                        for (int j = 0; j < level0MenuList.size(); j++) {
                            TdNavigationMenu level0Menu = level0MenuList.get(j);

                            //取二级菜单列表
                            List<TdNavigationMenu> level1MenuList = tdNavigationMenuService
                                    .findByParentIdAndSort(level0Menu.getId());

                            if (null != level1MenuList && level1MenuList.size() > 0) {
                                map.addAttribute("level_" + i + j + "_menu_list",
                                        level1MenuList);
                            }
                        }
                    }
                }
            }
            
            map.addAttribute("root_menu_list", rootMenuList);
        }
            
        // 您有新的审核通知
        int newApplyNum = tdManagerApplyService.getNewApplyNum(tdManager.getId());
        if(newApplyNum > 0){
        	map.addAttribute("newApplyNum", newApplyNum);
        }
        // 您有新的待确认通知
        int toSureNoticeNum = tdNoticeService.getToSureNoticeNum(tdManager.getId());
        if(toSureNoticeNum > 0){
        	map.addAttribute("toSureNoticeNum", toSureNoticeNum);
        }
        // 您有新的游客定制
        
        // 您有新的私家定制
        return "/site_mag/frame";
    }
    
    @RequestMapping(value="/Verwalter/remaind", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remaind(ModelMap map, HttpServletRequest req){
    	Map<String, Object> remaindMap = new HashMap<>();
    	remaindMap.put("code", 1);
    	String username = (String) req.getSession().getAttribute("manager");
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        int newApplyNum = 0;
        int toSureNoticeNum = 0;
        if(tdManager != null){
        	// 审核
        	newApplyNum = tdManagerApplyService.getNewApplyNum(tdManager.getId());
        	// 通知
        	toSureNoticeNum = tdNoticeService.getToSureNoticeNum(tdManager.getId());
        }
    	
    	// 新订单
    	List<TdOrder> orderList = tdOrderService.findByStatusId(2L);
    	if(null != orderList)
    	{
    		remaindMap.put("orderNum", orderList.size());
    	}else{
    		remaindMap.put("orderNum", 0);
    	}
    	
    	// 私家定制
    	List<TdDemand> demandList = tdDemandService.findAll();
    	if(null != demandList){
    		remaindMap.put("privateNum", demandList.size());
    	}else{
    		remaindMap.put("privateNum", 0);
    	}
    	
    	// 游客定制
    	List<TdVisitorCustom> visitList = tdVisitorCustomService.findAll();
    	if(null != visitList)
    	{
    		remaindMap.put("visitorNum", visitList.size());
    	}else{
    		remaindMap.put("visitorNum", 0);
    	}
    	// 约租
    	List<TdReserveCar> yzList = tdReserverCarService.findByType(0);
    	if(null != yzList)
    	{
    		remaindMap.put("yzNum", yzList.size());
    	}else{
    		remaindMap.put("yzNum", 0);
    	}
    	// 代驾
    	List<TdReserveCar> djList = tdReserverCarService.findByType(1);
    	if(null != yzList)
    	{
    		remaindMap.put("djNum", djList.size());
    	}else{
    		remaindMap.put("djNum", 0);
    	}
    	
    	remaindMap.put("newApplyNum", newApplyNum);
    	remaindMap.put("toSureNoticeNum", toSureNoticeNum);
    	remaindMap.put("code", 0);
    	
    	return remaindMap;
    	
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public List<TdNavigationMenu> change(List<TdNavigationMenu> list, TdNavigationMenu shu[]){
    	list.clear();
    	for(int i = 0; i < shu.length; i++){
    		if (null != shu[i]) {
				list.add(shu[i]);
			}
    	}
    	
    	return list;
    } 
    
	
    @RequestMapping(value = "/Verwalter/center")
    public String center(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        Properties props = System.getProperties();

        map.addAttribute(
                "os_name",
                props.getProperty("os.name") + " "
                        + props.getProperty("os.version"));
        map.addAttribute("java_home", props.getProperty("java.home"));
        map.addAttribute("java_version", props.getProperty("java.version"));
        map.addAttribute("remote_ip", req.getRemoteAddr());
        map.addAttribute("server_name", req.getServerName());
        map.addAttribute("server_ip", req.getLocalAddr());
        map.addAttribute("server_port", req.getServerPort());

        TdSetting setting = tdSettingService.findTopBy();

        if (null != setting) 
        {
            map.addAttribute("site_name", setting.getTitle());
            map.addAttribute("company_name", setting.getCompany());
        }

        if (!username.equalsIgnoreCase("admin")) {
            TdManager manager = tdManagerService
                    .findByUsernameAndIsEnableTrue(username);
            map.addAttribute("last_ip", manager.getLastLoginIp());
            map.addAttribute("last_login_time", manager.getLastLoginTime());
        }

        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        map.addAttribute("client_ip", ip);

        return "/site_mag/center";
    }

}
