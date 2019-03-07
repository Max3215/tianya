package com.ynyes.tianya.controller.management;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.service.TdClientService;
import com.ynyes.tianya.service.TdCostBalanceService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台费用结算表管理控制器
 * @author gl
 *
 */
@Controller
@RequestMapping(value="/Verwalter/costBalance")
public class TdManagerCostBalanceController {
	
	@Autowired
	TdClientService tdClientService;
	
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdManagerService tdManagerService;
	
	@Autowired
	TdManagerRoleService tdManagerRoleService;
	
	@Autowired
	TdCostBalanceService tdCostBalanceService;
	
	@RequestMapping(value="/edit")
	public String costBalanceEdit(Long id,String type, String __VIEWSTATE, Model model, HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        model.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        TdCostBalance tcb = tdCostBalanceService.findOne(id);
        if(tcb != null){
        	model.addAttribute("costBalance", tcb);
        }
        List<TdManager> tdManagerList = tdManagerService.findAllAndIsEnable();
        model.addAttribute("manager_list", tdManagerList);
        //是否已经提交审核
        if(tcb.getCheckPersonIdList() != null && !tcb.getCheckPersonIdList().equals("")){
        	model.addAttribute("isSubmit", "yes");
        }
        // 1已审核 ，-1未审核
        int[] checkFlag = new int[]{-1, -1, -1, -1, -1};
        Long currentCheckId = 0L;
        boolean goOn = true;
        if(tcb.getCheckPersonIdList() != null && !tcb.getCheckPersonIdList().equals("")){
        	String[] checkIds = tcb.getCheckPersonIdList().split(",");
        	for(int i = 0; i < checkIds.length; i ++){
        		String s = checkIds[i].replace("[", "");
        		s = s.replace("]", "");
        		if(Long.parseLong(s) > 0){
        			checkFlag[i] = 1;        			
        		}else{
        			if(goOn){
        				currentCheckId = -Long.parseLong(s);
        				goOn = false;
        			}
        		}
        	}
        }
        TdManager tm = tdManagerService.findByUsernameAndIsEnableTrue(username);
        if(tm.getId() == currentCheckId){
        	model.addAttribute("canIAssign", "yes");        	
        }
        model.addAttribute("checkFlag", checkFlag);
        if(tcb.getCheckPersonIdList() != null && !tcb.getCheckPersonIdList().equals("")){
        	String s = tcb.getCheckPersonIdList();
        	s = s.replace("[", "");
        	s = s.replace("]", "");
        	s = s.replace("-", "");
        	String[] ss = s.split(",");
        	TdManager zbr = tdManagerService.findOne(Long.parseLong(ss[0])); // 制表人
        	TdManager fgld = tdManagerService.findOne(Long.parseLong(ss[1])); // 分管领导
        	TdManager cn = tdManagerService.findOne(Long.parseLong(ss[2])); // 出纳
        	TdManager cwzg = tdManagerService.findOne(Long.parseLong(ss[3])); // 财务主管
        	TdManager fzr = tdManagerService.findOne(Long.parseLong(ss[4])); // 负责人
        	model.addAttribute("zbr", zbr);
        	model.addAttribute("cn", cn);
        	model.addAttribute("cwzg", cwzg);
        	model.addAttribute("fgld", fgld);
        	model.addAttribute("fzr", fzr);
        }
        
        if(null != type)
        {
        	model.addAttribute("type", type);
        }
        
		return "/site_mag/costBalance_edit";
	}
	
	/**
	 * @param model
	 * @param cbId
	 * @param request
	 * @param tcb
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Model model, Long cbId, HttpServletRequest request, TdCostBalance tcb){
		if(cbId !=null && tcb != null){
			tcb.setId(cbId);
			TdCostBalance tdcb = tdCostBalanceService.findOne(cbId);
			tcb.setState(tdcb.getState());
			tcb.setRejectReason(tdcb.getRejectReason());
			if(tdcb.getCheckPersonIdList() != null && !tdcb.getCheckPersonIdList().contains("-")){
				tdcb.setState("已通过");
			}
			if(tdcb != null){
				tcb.setCreateTime(tdcb.getCreateTime());
				tcb.setManagerId(tdcb.getManagerId());
				tcb.setManager(tdcb.getManager());
				tcb.setManagerTree(tdcb.getManagerTree());
			}
		}else{
			tcb.setState("审核中");
		}
		
		//获取签字人姓名
		String responsiblePerson = tdManagerService.findOne(tcb.getResponsiblePersonId()).getRealName();
		String fgLeader = tdManagerService.findOne(tcb.getFgLeaderId()).getRealName();
		String financeSupervisor = tdManagerService.findOne(tcb.getFinanceSupervisorId()).getRealName();
		String cashier = tdManagerService.findOne(tcb.getCashierId()).getRealName();
		String lister = tdManagerService.findOne(tcb.getListerId()).getRealName();
		//设置签字人
		tcb.setResponsiblePerson(responsiblePerson);
		tcb.setFgLeader(fgLeader);
		tcb.setFinanceSupervisor(financeSupervisor);
		tcb.setCashier(cashier);
		tcb.setLister(lister);
		//设置签字人Id链
		String checkPersonIdList = "";
		checkPersonIdList += "[-" + tcb.getListerId() + "],"; 
		checkPersonIdList += "[-" + tcb.getFgLeaderId() + "],";
		checkPersonIdList += "[-" + tcb.getCashierId() + "],";
		checkPersonIdList += "[-" + tcb.getFinanceSupervisorId() + "],";
		checkPersonIdList += "[-" + tcb.getResponsiblePersonId() + "]";
		tcb.setCheckPersonIdList(checkPersonIdList);
		tdCostBalanceService.save(tcb);
		
		
		return "redirect:/Verwalter/payoff/list";
	} 
	
	@RequestMapping(value="/goCheck")
	public String save(Model model, HttpServletRequest request, Integer page, Integer size){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
        
		TdManager tm = tdManagerService.findByUsernameAndIsEnableTrue(username);
		
		Page<TdCostBalance> needToAssignPage =  tdCostBalanceService.needToAssign("[-" + tm.getId() + "]", page, size);
		model.addAttribute("need_assign_page", needToAssignPage);
		return "/site_mag/need_to_assign";
	}
	
	@RequestMapping(value="/assign")
	public String assign(Model model, HttpServletRequest request, Long cbId){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
		TdManager tm = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdCostBalance tcb = tdCostBalanceService.findOne(cbId);
		tcb.setCheckPersonIdList(tcb.getCheckPersonIdList().replace("[-"+tm.getId()+"]", "["+tm.getId()+"]"));
		if(!tcb.getCheckPersonIdList().contains("-")){
			tcb.setState("已通过");
		}
		tdCostBalanceService.save(tcb);
		return "redirect:/Verwalter/payoff/list";
	}
	
	@RequestMapping(value="/rejectAssign")
	public String rejectAssign(Model model, HttpServletRequest request, Long cbId, String dataMessage){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
		TdManager tm = tdManagerService.findByUsernameAndIsEnableTrue(username);
		
		TdCostBalance tcb = tdCostBalanceService.findOne(cbId);
		
		tcb.setState("已拒绝");
		tcb.setRejectReason(dataMessage);
		
		tdCostBalanceService.save(tcb);
		return "redirect:/Verwalter/payoff/list";
	}
	
	
	
	@RequestMapping(value="/print")
    public String printDialog(String orderNumber, ModelMap map,
            HttpServletRequest req, Long cbId){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        map.addAttribute("manager", req.getSession().getAttribute("manager"));
        map.addAttribute("now", new Date());
        TdCostBalance tcb = tdCostBalanceService.findOne(cbId);
        if(tcb != null){
        	map.addAttribute("costBalance", tcb);
        }
        if(tcb.getCheckPersonIdList() != null && !tcb.getCheckPersonIdList().equals("")){
        	String s = tcb.getCheckPersonIdList();
        	s = s.replace("[", "");
        	s = s.replace("]", "");
        	s = s.replace("-", "");
        	String[] ss = s.split(",");
        	
        	TdManager zbr = tdManagerService.findOne(Long.parseLong(ss[0]));
        	TdManager fgld = tdManagerService.findOne(Long.parseLong(ss[1]));
        	TdManager cn = tdManagerService.findOne(Long.parseLong(ss[2]));
        	TdManager cwzg = tdManagerService.findOne(Long.parseLong(ss[3]));
        	TdManager fzr = tdManagerService.findOne(Long.parseLong(ss[4]));
        	
//        	TdManager zbr = tdManagerService.findOne(Long.parseLong(ss[0]));
//        	TdManager cn = tdManagerService.findOne(Long.parseLong(ss[1]));
//        	TdManager cwzg = tdManagerService.findOne(Long.parseLong(ss[2]));
//        	TdManager fgld = tdManagerService.findOne(Long.parseLong(ss[3]));
//        	TdManager fzr = tdManagerService.findOne(Long.parseLong(ss[4]));
        	map.addAttribute("zbr", zbr);
        	map.addAttribute("cn", cn);
        	map.addAttribute("cwzg", cwzg);
        	map.addAttribute("fgld", fgld);
        	map.addAttribute("fzr", fzr);
        	
        	int[] checkFlag = new int[]{-1, -1, -1, -1, -1};
            if(tcb.getCheckPersonIdList() != null && !tcb.getCheckPersonIdList().equals("")){
            	String[] checkIds = tcb.getCheckPersonIdList().split(",");
            	for(int i = 0; i < checkIds.length; i ++){
            		String str = checkIds[i].replace("[", "");
            		str = str.replace("]", "");
            		if(Long.parseLong(str) > 0){
            			checkFlag[i] = 1;        			
            		}
            	}
            }
            map.addAttribute("checkFlag", checkFlag);
        }
        return "/site_mag/dialog_costBalance_print";
    }
	
	
	
	
}
