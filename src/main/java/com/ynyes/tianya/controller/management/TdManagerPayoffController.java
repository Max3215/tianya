package com.ynyes.tianya.controller.management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.service.TdCostBalanceService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台结算表列表
 * @author Max
 *
 */
@Controller
@RequestMapping(value="/Verwalter/payoff")
public class TdManagerPayoffController {

	@Autowired
	private TdManagerService tdManagerService;
	
	@Autowired
	private TdCostBalanceService tdCostBalanceService;
	
	@Autowired
	TdManagerRoleService tdManagerRoleService;
	
	@Autowired
	private TdManagerLogService tdManagerLogService;
	
	@RequestMapping(value="/list")
	public String payoffList(Integer page, 
            Integer size,
            String start,
            String end,
            Long typeId,
            String __EVENTTARGET,
            String __EVENTARGUMENT,
            String __VIEWSTATE,
            Long[] listId,
            Integer[] listChkId,
            ModelMap map,
            String exportUrl,
            String settle,
//            String dateId,
            HttpServletResponse resp,
            HttpServletRequest req)
	{
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
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
            if (__EVENTTARGET.equalsIgnoreCase("btnCancel"))
            {
//                btnCancel(listId, listChkId);
                tdManagerLogService.addLog("cancel", "取消订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnConfirm"))
            {
//                btnConfirm(listId, listChkId);
                tdManagerLogService.addLog("confirm", "确认订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除结算表", req);
                return "redirect:/Verwalter/payoff/list";
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出资产表", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
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
        
        Date startTime =null; // 起始时间
        Date endTime = null; // 截止时间
        if(null != start && !"".equals(start.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
        		startTime = sdf.parse(start);
        		startTime.setHours(0);
        		startTime.setMinutes(0);
        		startTime.setSeconds(0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        if(null != end && !"".equals(end.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
				endTime = sdf.parse(end);
				endTime.setHours(23);
				endTime.setMinutes(59);
				endTime.setSeconds(59);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        boolean isCw = false;
        if(null != tdManagerRole && tdManagerRole.getTitle().contains("财务")){
        	isCw = true;
        }
        Page<TdCostBalance> payoff_page = tdCostBalanceService.searchCostBalance(isCw, "["+tdManager.getId()+"]", startTime, endTime, page, size);
//        Page<TdCostBalance> payoff_page = tdCostBalanceService.findByManagerTreeContainingOrderByCreateTimeDesc("[" + tdManager.getId()+"]", page, size);
//        if(tdManagerRole != null && tdManagerRole.getTitle().contains("财务")){
//        	payoff_page = tdCostBalanceService.findAll(page, size);
//        }else{
//        	if(tdManager.getParentId()!= null && tdManager.getParentId() == 0){
//        		payoff_page = tdCostBalanceService.findAll(page, size);
//        	}        	
//        }
        //合计
        Page<TdCostBalance> total_page = tdCostBalanceService.searchCostBalance(isCw, "["+tdManager.getId()+"]", startTime, endTime, 0, Integer.MAX_VALUE);

        int totalPersonNum = 0;
    	double totalTotalIncome = 0.0;
    	double totalTotalCost = 0.0;
    	double totalPureIncome = 0.0;
        
        if(null != total_page && null != total_page.getContent())
        {
	        	List<TdCostBalance> totalContent = total_page.getContent();
	        	
	        	for(TdCostBalance tcb : totalContent){
	        		if(tcb.getPersonNum() != null){
	        			totalPersonNum += tcb.getPersonNum();
	        		}
	        		if(tcb.getTotalIncome() != null){
	        			totalTotalIncome += tcb.getTotalIncome();
	        		}
	        		if(tcb.getTotalCost() != null){
	        			totalTotalCost += tcb.getTotalCost();
	        		}
	        		if(tcb.getPureIncome() != null){
	        			totalPureIncome += tcb.getPureIncome();
	        		}
	        	}
        }
        map.addAttribute("totalPersonNum", totalPersonNum);
        map.addAttribute("totalTotalIncome", totalTotalIncome);
        map.addAttribute("totalTotalCost", totalTotalCost);
        map.addAttribute("totalPureIncome", totalPureIncome);
        
        map.addAttribute("payoff_page", payoff_page);
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
//        map.addAttribute("keywords", keywords);
        map.addAttribute("startime", startTime);
        map.addAttribute("endTime", endTime);
        map.addAttribute("typeId", typeId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		
		return "/site_mag/payoff_list";
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
                tdCostBalanceService.deleteOne(id);
            }
        }
    }
	
}
