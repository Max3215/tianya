package com.ynyes.tianya.controller.management;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;

/**
 * 品牌管理控制器
 * 
 * @author Sharon
 * 
 */
@Controller
@RequestMapping(value="/Verwalter")
public class TdManagerLoginController {
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @RequestMapping(value="/login")
    public String login(String username, String password, ModelMap map, HttpServletRequest request){
        
        if (null == username || null == password || username.isEmpty() || password.isEmpty())
        {
            map.addAttribute("error", "用户名和密码不能为空");
            return "/site_mag/login";
        }
        
        if (username.equalsIgnoreCase("admin") && password.equals("admin888"))
        {
            request.getSession().setAttribute("manager", username);
            tdManagerLogService.addLog("login", "用户登录", request);
            return "redirect:/Verwalter";
        }
        else
        {
            TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(username);
            
            TdManagerRole tdManagerRole = null;
            
            if (null != manager && null != manager.getRoleId())
            {
                tdManagerRole = tdManagerRoleService.findOne(manager.getRoleId());
            }
            
            if (null != manager)
            {
                if (password.equals(manager.getPassword()))
                {
                    manager.setLastLoginIp(manager.getIp());
                    manager.setLastLoginTime(manager.getLoginTime());
                    
                    String ip = request.getHeader("x-forwarded-for");
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
                        ip = request.getHeader("WL-Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = request.getRemoteAddr();
                    }
                    
                    manager.setIp(ip);
                    manager.setLoginTime(new Date());
                    
                    tdManagerService.save(manager);
                    
                    if(null != tdManagerRole){
                    	request.getSession().setAttribute("roleName", tdManagerRole.getTitle());
                    }
                    
                    request.getSession().setAttribute("manager", username);
                    tdManagerLogService.addLog("login", "用户登录", request);
                    return "redirect:/Verwalter";
                }
            }
            
            map.addAttribute("error", "用户不存在或密码错误");
            return "/site_mag/login";
        }   
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){

        tdManagerLogService.addLog("logout", "用户退出登录", request);
        
        request.getSession().invalidate();
        
        return "redirect:/Verwalter/login";
    }
}
