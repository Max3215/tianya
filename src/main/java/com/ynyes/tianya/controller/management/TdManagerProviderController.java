package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdProvider;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台用户管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/provider")
public class TdManagerProviderController {
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    String filepath = SiteMagConstant.imagePath;
    
    /*@RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();
        
        res.put("status", "n");
        
        if (null == param || param.isEmpty())
        {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        if (null == id)
        {
            if (null != tdProviderService.findByTitle(param))
            {
                res.put("info", "已存在同名供应商");
                return res;
            }
        }
        else
        {
            if (null != tdProviderService.findByTitleAndIdNot(param, id))
            {
                res.put("info", "已存在同名供应商");
                return res;
            }
        }
        
        res.put("status", "y");
        
        return res;
    }*/
    
    @RequestMapping(value="/list")
    public String setting(Integer page,
                          Integer size,
                          String keywords,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
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
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除签证订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改签证订单", req);
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
        
        if (null != keywords)
        {
            keywords = keywords.trim();
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdOrder> orderPage = null;
        
        if (null == keywords || "".equalsIgnoreCase(keywords))
        {
        	// providerPage = tdProviderService.findAllOrderBySortIdAsc(page, size);
        	orderPage = tdOrderService.findByTypeIdOrderByIdDesc(3L,page,size);
        }
        else
        {
        	orderPage = tdOrderService.searchAndOrderBySortIdAsc(keywords, page, size);
        }
        
        map.addAttribute("order_page", orderPage);
        
        return "/site_mag/provider_list";
    }
    
    /**
     * @author lulu
     * @注释 组客车后台信息展示
     */
    @RequestMapping(value="/list1")
    public String setting1(Integer page,
                          Integer size,
                          String keywords,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
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
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除租客车信息订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改租客车信息订单", req);
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
        
        if (null != keywords)
        {
            keywords = keywords.trim();
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdOrder> orderPage = null;
        
        if (null == keywords || "".equalsIgnoreCase(keywords))
        {
        	// providerPage = tdProviderService.findAllOrderBySortIdAsc(page, size);
        	orderPage = tdOrderService.findByTypeIdAndOrderTypeOrderByIdDesc(4L,"客车包租",page,size);
        }
        else
        {
        	orderPage = tdOrderService.searchAndOrderBySortIdAsc(keywords, page, size);
        }
        
        map.addAttribute("order_page", orderPage);
        
        return "/site_mag/provider_list1";
    }
    
    @RequestMapping(value="/param/edit")
    public String paramEdit(Long id,Long statusId,HttpServletRequest req,ModelMap map)
    {
    	 String username = (String) req.getSession().getAttribute("manager");
         if (null == username) {
             return "redirect:/Verwalter/login";
         }
         
       //管理员角色
         TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
         
    	if(null != id && null != statusId)
    	{
    		TdOrder order = tdOrderService.findOne(id);
    		if(order.getStatusId() ==2 )
    		{
    			order.setDistributionPerson(tdManager.getRealName());
    		}
    		order.setStatusId(statusId);
    		tdOrderService.save(order);
    	}
    	
    	return "redirect:/Verwalter/provider/list1?cid=0&mid=91";
    }
    
    /**
     * @author lulu
     * @注释 后台签证资料下载 
     * @param id
     * @param model
     */
    
    @RequestMapping(value="/download/data", method = RequestMethod.GET)
    @ResponseBody
    public void download(String fileName,HttpServletResponse resp,HttpServletRequest req) throws IOException {
        if (null == fileName)
        {
            return;
        }
        
        String name = fileName.substring(8);
        
        OutputStream os = resp.getOutputStream();  
        
        File file = new File(filepath +"/" + name);
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                		+URLEncoder.encode(name, "UTF-8"));
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
        else 
        {
        	return;
        }
    }
    
   /* @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        if (null != id)
        {
            map.addAttribute("provider", tdProviderService.findOne(id));
        }
        return "/site_mag/provider_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdProvider tdProvider,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null == tdProvider.getId())
        {
            tdManagerLogService.addLog("add", "用户修改供应商", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改供应商", req);
        }
        
        tdProviderService.save(tdProvider);
        
        return "redirect:/Verwalter/provider/list";
    }
*/
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdProvider", tdOrderService.findOne(id));
        }
    }
    
    private void btnSave(Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdOrder e = tdOrderService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdOrderService.save(e);
                }
            }
        }
    }
    
    private void btnDelete(Long[] ids, Integer[] chkIds)
    {
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
                
                tdOrderService.delete(id);
            }
        }
    }
}
