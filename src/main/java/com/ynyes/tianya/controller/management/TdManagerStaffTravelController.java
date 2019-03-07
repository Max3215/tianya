package com.ynyes.tianya.controller.management;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdStaff;
import com.ynyes.tianya.entity.TdStationCenterStaffTravelLine;
import com.ynyes.tianya.service.TdClientService;
import com.ynyes.tianya.service.TdCostBalanceService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdOutMoneyItemService;
import com.ynyes.tianya.service.TdStaffService;
import com.ynyes.tianya.service.TdStationCenterStaffTravelLineService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 站务中心职工出游线路表
 * @author gl
 * 
 */
@Controller
@RequestMapping(value="/Verwalter/staffTravel")
public class TdManagerStaffTravelController {
	
	@Autowired
	TdClientService tdClientService;
	@Autowired
	TdManagerLogService tdManagerLogService;
	
	@Autowired
	TdManagerService tdManagerService;
	
	@Autowired
	TdOutMoneyItemService tdOutMoneyItemService; 
	
	@Autowired
	TdManagerRoleService tdManagerRoleService;
	
	@Autowired
	TdStationCenterStaffTravelLineService tdStationCenterStaffTravelLineService;
	
	@Autowired
	TdCostBalanceService tdCostBalanceService;
	
	@Autowired
	TdStaffService tdStaffService;
	
	/**
	 * 
	 * @param calendar1		生成时间的搜索下限
	 * @param calendar2		生成时间的搜索上限 
	 * @param isSettle 		是否已结算
	 */
	@RequestMapping(value="/list")
	public String staffTravelList(HttpServletRequest request, 
										Integer unit_s,Boolean isSettle_s,
										String calendar1_s,String calendar2_s,
										String exportUrl,String keywords,
										HttpServletResponse response,
										Model model, Integer page, 
										Integer size, 
										String __EVENTTARGET,
										String __EVENTARGUMENT, 
										String __VIEWSTATE,
										Long[] listId, 
										Integer[] listChkId) throws ParseException{
		
		String username = (String) request.getSession().getAttribute("manager");
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
        	model.addAttribute("tdManagerRole", tdManagerRole);
		}
        Date c1 = null, c2 = null;
        if (null != calendar1_s && !"".equals(calendar1_s.trim())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				c1 = sdf.parse(calendar1_s);
				c1.setHours(0);
				c1.setMinutes(0);
				c1.setSeconds(0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
        if (null != calendar2_s && !"".equals(calendar2_s.trim())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				c2 = sdf.parse(calendar2_s);
				c2.setHours(23);
				c2.setMinutes(59);
				c2.setSeconds(59);
			} catch (ParseException e) {
				e.printStackTrace();
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
        
        if(isSettle_s != null){
        	model.addAttribute("isSettle_s", isSettle_s);
        }
        if(calendar1_s != null){
        	model.addAttribute("calendar1_s", calendar1_s);
        }
        if(calendar2_s != null){
        	model.addAttribute("calendar2_s", calendar2_s);
        }
        if(unit_s != null && !unit_s.equals("")){
        	model.addAttribute("unit_s", unit_s);
        }
        model.addAttribute("keywords", keywords);
        
        
//        if(isSettle_s == null){
//        	if(calendar1_s == null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettle(tdManager.getId(), null, page, size);
//        	}else if(calendar1_s != null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeAfter(tdManager.getId(), null, calendar1_s, page, size);
//        	}else if(calendar1_s == null && calendar2_s != null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBefore(tdManager.getId(), null, calendar2_s, page, size);
//        	}else{
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBetween(tdManager.getId(), null, calendar1_s, calendar2_s, page, size);
//        	}
//        }else if(isSettle_s){
//        	if(calendar1_s == null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettle(tdManager.getId(), true, page, size);
//        	}else if(calendar1_s != null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeAfter(tdManager.getId(), true, calendar1_s, page, size);
//        	}else if(calendar1_s == null && calendar2_s != null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBefore(tdManager.getId(), true, calendar2_s, page, size);
//        	}else{
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBetween(tdManager.getId(), true, calendar1_s, calendar2_s, page, size);
//        	}
//        }else{
//        	if(calendar1_s == null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettle(tdManager.getId(), false, page, size);
//        	}else if(calendar1_s != null && calendar2_s == null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeAfter(tdManager.getId(), false, calendar1_s, page, size);
//        	}else if(calendar1_s == null && calendar2_s != null){
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBefore(tdManager.getId(), false, calendar2_s, page, size);
//        	}else{
//        		scstlPage = tdStationCenterStaffTravelLineService.findByManagerIdAndIsSettleAndCreateTimeBetween(tdManager.getId(), false, calendar1_s, calendar2_s, page, size);
//        	}
//        }
        
        
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除职工出游线路", request);
                return "redirect:/Verwalter/staffTravel/list";
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPayoff"))
            {
                btnPayoff(listId, listChkId,tdManager);
                tdManagerLogService.addLog("edit", "结算职工出游线路", request);
                return "redirect:/Verwalter/payoff/list";
            }else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出线路表", request);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnCopy")){
            	btnCopy(listId, listChkId);
            	tdManagerLogService.addLog("copy", "复制资产表", request);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            
        }
        boolean isCw = false;
        if(tdManagerRole.getTitle().contains("财务")){
        	isCw = true;
        }
        Page<TdStationCenterStaffTravelLine> scstlPage = tdStationCenterStaffTravelLineService.searchStaffTravel(unit_s,keywords, isSettle_s, c1, c2 ,"["+tdManager.getId()+"]", isCw,page, size);
        if(scstlPage != null){
        	model.addAttribute("scstl_page", scstlPage);
        }
        Page<TdStationCenterStaffTravelLine> totalScstlPage = tdStationCenterStaffTravelLineService.searchStaffTravel(unit_s,keywords, isSettle_s, c1, c2 ,"["+tdManager.getId()+"]", isCw,0, Integer.MAX_VALUE);
        
        double totalContractPrice = 0.0;
        double totalDiscountAmount = 0.0;
        double totalCurrentBalance = 0.0;
        List<TdStationCenterStaffTravelLine> totalScstlPageContent = totalScstlPage.getContent();
        if(totalScstlPageContent != null){
        	for(TdStationCenterStaffTravelLine tscstl : totalScstlPageContent){
        		totalContractPrice += tscstl.getContractPrice();
        		totalDiscountAmount += tscstl.getDiscountAmount();
        		totalCurrentBalance += tscstl.getCurrentBalance();
        	}
        }
        model.addAttribute("totalContractPrice", totalContractPrice);
        model.addAttribute("totalDiscountAmount", totalDiscountAmount);
        model.addAttribute("totalCurrentBalance", totalCurrentBalance);
	    
		return "/site_mag/staffTravel_list";
	}
	
	@RequestMapping(value="/save")
    public String save(TdStationCenterStaffTravelLine tdStationCenterStaffTravelLine, HttpServletRequest req, String __VIEWSTATE, ModelMap map){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        if(tdStationCenterStaffTravelLine.getId() != null){
        	tdManagerLogService.addLog("edit", "修改职工出游线路", req);
        }else{
        	tdManagerLogService.addLog("edit", "添加职工出游线路", req);
        	
        	tdStationCenterStaffTravelLine.setManagerId(tdManager.getId());
        	tdStationCenterStaffTravelLine.setManagerName(tdManager.getUsername());
        	tdStationCenterStaffTravelLine.setParentTree(tdManager.getParentTree());
        	tdStationCenterStaffTravelLine.setCreateTime(new Date());
        }
        if(tdStationCenterStaffTravelLine.getIsSettle() == null){
        	tdStationCenterStaffTravelLine.setIsSettle(false);
        }
        
        // 判断类型
        if(null != tdStationCenterStaffTravelLine.getType() && tdStationCenterStaffTravelLine.getType()==0)
        {
        	// 内部职工，添加单位
        	tdStationCenterStaffTravelLine.setUnit(tdStationCenterStaffTravelLine.getUnitId());
        }else{
        	// 外部人员
        	// 设置优惠金额及余额为0
        	// 单位类别同意设置为17
        	tdStationCenterStaffTravelLine.setPreviousBalance(0.0);
        	tdStationCenterStaffTravelLine.setDiscountAmount(0.0);
        	tdStationCenterStaffTravelLine.setCurrentBalance(0.0);
        	tdStationCenterStaffTravelLine.setUnitId(17);
        }
        
        tdStationCenterStaffTravelLineService.save(tdStationCenterStaffTravelLine);
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        return "redirect:/Verwalter/staffTravel/list";
    }
	
	@RequestMapping(value="/edit")
    public String edit(Long id, HttpServletRequest req, String __VIEWSTATE, ModelMap map){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
//        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        if(id != null){
        	map.addAttribute("staffTravel", tdStationCenterStaffTravelLineService.findOne(id));
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        return "/site_mag/staffTravel_edit";
    }
	
	
	/**
	 * @author Max
	 * Js事件根据输入的职工名称查找职工表，
	 * 计算优惠金额以及结余挂账
	 */
	@RequestMapping(value="/countAmount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> countAmount(String param,
				Integer company,
				HttpServletRequest req,ModelMap map){
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		String username = (String) req.getSession().getAttribute("manager");
		if(null == username)
		{
			res.put("msg", "登录超时");
			return res;
		}
		
		if(null != param && !param.isEmpty())
		{
			String[] names = param.split("，");
			Double previous = 0.0;
			Double discount = 0.0;
			
			
			for (int i = 0; i < names.length; i++) {
				// 根据名字查找职工表，获取职工的优惠金额以及前期余额
//				TdStaff staff = tdStaffService.findByUsername(names[i]);
				TdStaff staff = tdStaffService.findByUsernameAndCompany(names[i],company);
				if(null != staff)
				{
					previous += staff.getSurplus();
					discount += staff.getSurDiscount();
				}
			}
			res.put("previous", previous);
			res.put("discount", discount);
			res.put("code", 1);
			return res;
		}
		
		
		res.put("msg", "参数错误");
		return res;
	}
	
	
	 @ModelAttribute
	    public void getModel(@RequestParam(value = "id", required = false) Long id,
	                        Model model) {
	        if (null != id) {
	            model.addAttribute("tdStationCenterStaffTravelLine", tdStationCenterStaffTravelLineService.findOne(id));
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
                tdStationCenterStaffTravelLineService.deleteOne(id);
            }
        }
    }
	
	/**
	 * 
	 * @author Max
	 * 复制线路表
	 * 
	 */
	private void btnCopy(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            Long id = ids[chkId];
			TdStationCenterStaffTravelLine travelLine = tdStationCenterStaffTravelLineService.findOne(id);
			
			if(null != travelLine){
				TdStationCenterStaffTravelLine line = new TdStationCenterStaffTravelLine();
				
				line.setAdultNumber(travelLine.getAdultNumber());
				line.setBankMoney(travelLine.getBankMoney());
				line.setCashMoney(travelLine.getCashMoney());
				line.setChildNumber(travelLine.getChildNumber());
				line.setClientId(travelLine.getClientId());
				line.setContractPrice(travelLine.getContractPrice());
				line.setCosting(travelLine.getCosting());
				line.setCostNumber(travelLine.getCostNumber());
				line.setCreateTime(new Date());
				line.setCreditMoney(travelLine.getCreditMoney());
				line.setCurrentBalance(travelLine.getCurrentBalance());
				line.setDiscountAmount(travelLine.getDiscountAmount());
				line.setFamily(travelLine.getFamily());
				line.setIsSettle(travelLine.getIsSettle());
				line.setManagerId(travelLine.getManagerId());
				line.setManagerName(travelLine.getManagerName());
				line.setParentTree(travelLine.getParentTree());
				line.setPremium(travelLine.getPremium());
				line.setPreviousBalance(travelLine.getPreviousBalance());
				line.setSiger(travelLine.getSiger());
				line.setStaffName(travelLine.getStaffName());
				line.setTravelLine(travelLine.getTravelLine());
				line.setTravelTime(travelLine.getTravelTime());
				line.setUnitId(travelLine.getUnitId());
				line.setUnit(travelLine.getUnit());
				line.setRemark(travelLine.getRemark());
				line.setType(travelLine.getType());
				
				tdStationCenterStaffTravelLineService.save(line);
			}
        }
    }
	
	/**
	 *  生成结算表
	 *  @author Max
	 *  
	 */
	private void btnPayoff(Long[] ids, Integer[] chkIds,TdManager manager)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        int adultNumber =0; // 成人数
        int childNumber =0; // 儿童数
        Double totalIncome =0.0;  // 营业收入总额
        Double insuranceCost =0.0; // 保险
        Double otherCost =0.0;  // 其他成本
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                TdStationCenterStaffTravelLine travelLine = tdStationCenterStaffTravelLineService.findOne(id);
                
                adultNumber += travelLine.getAdultNumber();
                childNumber += travelLine.getChildNumber();
                totalIncome += travelLine.getContractPrice();  // 营业收入总额=合同金额之和
                insuranceCost += travelLine.getPremium();
                otherCost += travelLine.getCosting();
            }
        }
        Double totalCost = insuranceCost + otherCost ; // 成本合计 = 保险+其他成本
        Double pureIncome = totalIncome - totalCost; // 营业收入净额 = 营业收入总额-成本合计
        
        Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String curStr = sdf.format(current);
		Random random = new Random();
        
        TdCostBalance balance = new TdCostBalance();
        balance.setState("审核中");
        String costNumber = "TY"+curStr+leftPad(Integer.toString(random.nextInt(999)), 3, "0");
        balance.setCostNumber(costNumber);
        balance.setCreateTime(current); // 结算时间
        balance.setManagerId(manager.getId());
        balance.setManager(manager.getRealName());
        balance.setManagerTree(manager.getParentTree());
        
        balance.setAdultNum(adultNumber); // 成人数
        balance.setChildNum(childNumber); // 儿童数
        balance.setPersonNum(adultNumber+childNumber); // 实际数
        balance.setTotalIncome(totalIncome); // 营业收入总额
        balance.setInsuranceCost(insuranceCost); // 保险
        balance.setOtherCost(0.0); // 其他成本费
        balance.setTotalCost(totalCost); // 成本合计
        balance.setPureIncome(pureIncome); // 营业收入净额
        
        balance = tdCostBalanceService.save(balance);
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                TdStationCenterStaffTravelLine travelLine = tdStationCenterStaffTravelLineService.findOne(id);
                
                travelLine.setCostNumber(costNumber);
                travelLine.setIsSettle(true);
                tdStationCenterStaffTravelLineService.save(travelLine);
                
            }
        }
        
    }
	
}
