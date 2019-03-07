package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdClient;
import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOutMoneyItem;
import com.ynyes.tianya.entity.TdStationCenterStaffTravelLine;
import com.ynyes.tianya.service.TdClientService;
import com.ynyes.tianya.service.TdCostBalanceService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdOutMoneyItemService;
import com.ynyes.tianya.service.TdStationCenterStaffTravelLineService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台客户管理控制器
 * @author gl
 *
 */
//@Controller
//@RequestMapping(value="/Verwalter/client")
public class TdManagerClientController {
	
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
	
	@RequestMapping(value="/list")
	public String clientList(HttpServletRequest request ,String calendar1,String calendar2,HttpServletResponse response,Model model, Integer page, Integer size, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Long[] listId, Integer[] listChkId,String exportUrl) throws ParseException{
		
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        
        if(null==calendar1||calendar1.equals("导出Excel起始时间")){
        	calendar1="2016-01-01 00:00:00";
        }
        if(null==calendar2||calendar2.equals("导出Excel结束时间")){
        	calendar2="2030-01-01 00:00:00";
        }
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除客户", request);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出客户信息", request);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        /**
  		 * @author lulu
  		 * @注释：根据不同条件导出excel文件
  		 */
	      // 第一步，创建一个webbook，对应一个Excel文件  
	      HSSFWorkbook wb = new HSSFWorkbook();  
	      // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	      HSSFSheet sheet = wb.createSheet("order");  
	      // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	      HSSFRow row = sheet.createRow((int) 0);  
	      // 第四步，创建单元格，并设置值表头 设置表头居中  
	      HSSFCellStyle style = wb.createCellStyle();  
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
	      
	      HSSFCell cell = row.createCell((short) 0);  
	      cell.setCellValue("客户姓名");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 1);  
	      cell.setCellValue("客户类别");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 2);  
	      cell.setCellValue("客户电话");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 3);  
	      cell.setCellValue("客户描述");  
	      cell.setCellStyle(style);
	      
	      
	      cell = row.createCell((short) 4);
	      cell.setCellValue("所属员工");  
	      cell.setCellStyle(style);  
	      cell = row.createCell((short) 5);  
	      cell.setCellValue("拥有名额的车站");  
	      cell.setCellStyle(style);  
	      cell = row.createCell((short) 6);  
	      cell.setCellValue("收入金额(银行)");  
	      cell.setCellStyle(style);  
	      cell = row.createCell((short) 7);  
	      cell.setCellValue("收入金额(现金)");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 8);  
	      cell.setCellValue("收入金额(挂账)");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 9);  
	      cell.setCellValue("现金总支出");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 10);  
	      cell.setCellValue("银行总支出");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 11);  
	      cell.setCellValue("成本");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 12);  
	      cell.setCellValue("利润");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 13);  
	      cell.setCellValue("营业收入");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 14);  
	      cell.setCellValue("录入人");  
	      cell.setCellStyle(style);
	      cell = row.createCell((short) 15);  
	      cell.setCellValue("添加时间");  
	      cell.setCellStyle(style);
	      
//	      cell = row.createCell((short) 16);
//	      cell.setCellValue("单位");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 17);  
//	      cell.setCellValue("职工");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 18);  
//	      cell.setCellValue("家属");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 19);  
//	      cell.setCellValue("出行线路");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 20);  
//	      cell.setCellValue("出行时间");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 21);  
//	      cell.setCellValue("合同价格");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 22);  
//	      cell.setCellValue("前期余额(职工优惠)");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 23);  
//	      cell.setCellValue("应优惠金额(职工优惠)");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 24);  
//	      cell.setCellValue("本次余额(职工优惠)");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 25);  
//	      cell.setCellValue("现金");
//	      cell.setCellStyle(style);
//	      cell = row.createCell((short) 26);  
//	      cell.setCellValue("签字人");
//	      cell.setCellStyle(style);
          
	      
	      if (null != exportUrl) {
	    	  SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      	Date calendar11=sim.parse(calendar1);
		      	Date calendar22=sim.parse(calendar2);
				List<TdClient> clientList = tdClientService.findByCreateTimeBetween(calendar11,calendar22);
				
				if (ImportData(clientList, row, cell, sheet)) {
					download(wb, username, response);
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
        
        List<TdManager> subManagerList = new ArrayList<>();
        subManagerList.add(tdManager);
        
        List<TdManager> temperManagerList = childrenManagerList(subManagerList);
        // 遍历树
        while(temperManagerList != null){
        	subManagerList.addAll(temperManagerList);
        	temperManagerList = childrenManagerList(temperManagerList);
        }
        List<Long> managerIds = new ArrayList<>();
        for(TdManager m : subManagerList){
        	managerIds.add(m.getId());
        }
        
        Page<TdClient> client_page = tdClientService.findByManagerIdIn(managerIds, page, size);
        
        TdManagerRole role = null;
        role = tdManagerRoleService.findOne(tdManager.getRoleId());
        
        if(role != null && role.getTitle().contains("财务")){
        	client_page = tdClientService.findAll(page, size);
        }
        
        if(tdManager.getParentId()!= null && tdManager.getParentId() == 0){
        	client_page = tdClientService.findAll(page, size);
        }
        
        for(int i = 0; i < client_page.getContent().size(); i ++){
        	TdCostBalance cb = tdCostBalanceService.getByClientId(client_page.getContent().get(i).getId());
        	client_page.getContent().get(i).setCostBalance(cb);
        }
        
        
        
        //Page<TdClient> client_page = tdClientService.findByManagerId(tdManager.getId(), page, size);
        
        
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("client_page", client_page);
        
        return "/site_mag/client_list";
	}
	
	@RequestMapping(value="/edit")
	public String clientEdit(Long id, String __VIEWSTATE, Model model, HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        model.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        TdClient tdClient = null;
        //编辑客户
        if(id != null){
        	tdClient = tdClientService.findById(id);
        
	        if(null != tdClient){
	        	List<TdOutMoneyItem> tdOutMoneyItemList = tdOutMoneyItemService.tdOutMoneyItemListByClientId(id);
	        	tdClient.setOutMoneyItemList(tdOutMoneyItemList);
	        	//model.addAttribute("managerName", tdManagerService.findOne(tdClient.getManagerId()).getRealName());
	        	List<TdStationCenterStaffTravelLine> tdStationCenterStaffTravelLineList = tdStationCenterStaffTravelLineService.tdStationCenterStaffTravelLineListByClientId(id);
	        	tdClient.setTdStationCenterStaffTravelLineList(tdStationCenterStaffTravelLineList);
	        	model.addAttribute("managerName", tdClient.getManagerName());
	        }
        }else{
        	model.addAttribute("managerName", username);
        }
        model.addAttribute("tdClient", tdClient);
        
		return "/site_mag/client_edit";
	}
	
	@RequestMapping(value="/save")
    public String orderEdit(TdClient tdClient,
                        String __VIEWSTATE,
                        ModelMap map,
                        String[] itemName,
                        Double[] itemCash,
                        Double[] itemBank,
                        Long[] itemId,
                        Long[] itemId2,
                        Long clientId,
                        String[] unit,
                        String[] staffName,
                        Integer[] family,
                        String[] travelTime,
                        Double[] contractPrice,
                        Double[] previousBalance,
                        Double[] discountAmount,
                        Double[] currentBalance,
                        Double[] bankMoney,
                        Double[] cashMoney,
                        Double[] creditMoney,
                        Double[] costing,
                        String[] siger,
                        String[] travelLine,
                        HttpServletRequest req){
		boolean isUpdateClient = false;
		if(clientId != null){
			isUpdateClient = true;
		}
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        tdClient.setManagerId(tdManager.getId());
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if(clientId != null){
        	tdClient.setId(clientId);
        }
        TdClient c = tdClientService.save(tdClient);
        
        TdOutMoneyItem tdOutMoneyItem = null;
        if(itemName != null){        	
        	for(int i = 0; i < itemName.length; i ++){
        		tdOutMoneyItem = new TdOutMoneyItem();
        		tdOutMoneyItem.setTitle(itemName[i]);
        		tdOutMoneyItem.setOutMoneyFromCash(itemCash[i]);
        		tdOutMoneyItem.setOutMoneyFromBank(itemBank[i]);
        		tdOutMoneyItem.setClientId(c.getId());
        		if(itemId[i] != -1){
        			tdOutMoneyItem.setId(itemId[i]);
        		}
        		tdOutMoneyItemService.save(tdOutMoneyItem);
        	}
        }
        TdStationCenterStaffTravelLine tdStationCenterStaffTravelLine = null;
        Double bankMoneySum = 0D;
        Double cashMoneySum = 0D;
        Double creditMoneySum = 0D;
        Double costingMoneySum = 0D;
        Double contractMoneySum = 0D;
        if(staffName!=null){
        	for(int i = 0; i < staffName.length; i ++){
        		tdStationCenterStaffTravelLine = new TdStationCenterStaffTravelLine();
        		tdStationCenterStaffTravelLine.setUnit(unit[i]);
        		tdStationCenterStaffTravelLine.setStaffName(staffName[i]);
        		tdStationCenterStaffTravelLine.setFamily(family[i]);
        		Date tt;
				try {
					tt = new SimpleDateFormat("yyyy-MM-dd").parse(travelTime[i]);
					tdStationCenterStaffTravelLine.setTravelTime(tt);
				} catch (ParseException e) {
					e.printStackTrace();
				}
        		tdStationCenterStaffTravelLine.setContractPrice(contractPrice[i]);
        		contractMoneySum += contractPrice[i];
        		tdStationCenterStaffTravelLine.setPreviousBalance(previousBalance[i]);
        		tdStationCenterStaffTravelLine.setDiscountAmount(discountAmount[i]);
        		tdStationCenterStaffTravelLine.setCurrentBalance(currentBalance[i]);
        		tdStationCenterStaffTravelLine.setBankMoney(bankMoney[i]);
        		bankMoneySum += bankMoney[i];
        		tdStationCenterStaffTravelLine.setCashMoney(cashMoney[i]);
        		cashMoneySum += cashMoney[i];
        		tdStationCenterStaffTravelLine.setCreditMoney(creditMoney[i]);
        		creditMoneySum += creditMoney[i];
        		tdStationCenterStaffTravelLine.setCosting(costing[i]);
        		costingMoneySum += costing[i];
        		
        		tdStationCenterStaffTravelLine.setSiger(siger[i]);
        		tdStationCenterStaffTravelLine.setTravelLine(travelLine[i]);
        		tdStationCenterStaffTravelLine.setClientId(c.getId());
        		if(itemId2[i] != -1){
        			tdStationCenterStaffTravelLine.setId(itemId2[i]);
        		}
        		tdStationCenterStaffTravelLineService.save(tdStationCenterStaffTravelLine);
        	}
        	c.setInMoneyFromBank(bankMoneySum);
        	c.setInMoneyFromCash(cashMoneySum);
        	c.setInMoneyFromTransfer(creditMoneySum);
        	c.setCost(costingMoneySum);
        	c.setOperationIncome(contractMoneySum);
        	c.setProfits(contractMoneySum-costingMoneySum);
        	tdClient = tdClientService.save(c);
        	
        }
        
     // 添加对应的费用结算表
    	if(!isUpdateClient){
    		TdCostBalance cb = new TdCostBalance();
    		if(c.getOperationIncome() != null){
    			cb.setTotalIncome(c.getOperationIncome());        			
    		}
    		if(c.getCost() != null){
    			cb.setTotalCost(c.getCost());        			
    		}
    		if(c.getProfits() != null){
    			cb.setPureIncome(c.getProfits());
    		}
    		
    		int totalPerson = 0;
    		if(family != null){
    			for(int n : family){
    				totalPerson += n;
    			}
    			totalPerson += family.length;
    		}
    		cb.setAdultNum(totalPerson);
    		cb.setClientId(c.getId());
    		tdCostBalanceService.save(cb);        		
    	}else{
    		TdCostBalance costBalance =  tdCostBalanceService.getByClientId(clientId);
    		if(c.getOperationIncome() != null){
    			costBalance.setTotalIncome(c.getOperationIncome());        			
    		}
    		if(c.getCost() != null){
    			costBalance.setTotalCost(c.getCost());        			
    		}
    		if(c.getProfits() != null){
    			costBalance.setPureIncome(c.getProfits());
    		}
    		int totalPerson = 0;
    		if(family != null){
    			for(int n : family){
    				totalPerson += n;
    			}
    			totalPerson += family.length;
    		}
    		costBalance.setAdultNum(totalPerson);
    		costBalance.setClientId(c.getId());
    		tdCostBalanceService.save(costBalance);
    	}
        
        return "redirect:/Verwalter/client/list";
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
                
                tdClientService.delete(id);
                //删除该客户对应的outMoneyItem
                List<TdOutMoneyItem> tdOutMoneyItemList = tdOutMoneyItemService.tdOutMoneyItemListByClientId(id);
                for(TdOutMoneyItem mti : tdOutMoneyItemList){
                	tdOutMoneyItemService.deleteOutMoneyItem(mti.getId());
                } 
                //删除对应的站务中心
                List<TdStationCenterStaffTravelLine> travelLineItemList = tdStationCenterStaffTravelLineService.tdStationCenterStaffTravelLineListByClientId(id);
                for(TdStationCenterStaffTravelLine scstl : travelLineItemList){
                	tdStationCenterStaffTravelLineService.deleteTdStationCenterStaffTravelLine(scstl.getId());
                }
                //删除对应的费用结算表
                TdCostBalance tcb = tdCostBalanceService.getByClientId(id);
                if(tcb != null){
                	tdCostBalanceService.delete(tcb);
                }
            }
        }
    }
	
	
	
	/**
	 * @author lc
	 * @throws ParseException 
	 * @注释：将page中的订单数据存入excel表格中
	 */
    @SuppressWarnings("deprecation")
	public boolean ImportData(List<TdClient> clientList, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
    	for (int i = 0; i < clientList.size(); i++)  
        {  	
    		
    		//计算所有项目的现金支出总额和银行支出总额
        	//现金
        	Double outMoneyFromCashAll = 0.00;
        	//银行
        	Double outMoneyFromBankAll = 0.00;
        	List<TdOutMoneyItem> moneyItemList = tdOutMoneyItemService.findByClientId(clientList.get(i).getId());
        	for(int x=0;x<moneyItemList.size();x++){
        		outMoneyFromCashAll += moneyItemList.get(x).getOutMoneyFromCash();
        		outMoneyFromBankAll += moneyItemList.get(x).getOutMoneyFromBank();
        	}
    	 				
            row = sheet.createRow((int) i + 1);  
            TdClient tdClient = clientList.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(tdClient.getName());
            row.createCell((short) 1).setCellValue(tdClient.getTypeName());
            row.createCell((short) 2).setCellValue(tdClient.getPhoneNum());
            row.createCell((short) 3).setCellValue(tdClient.getDescription());
            
            
            row.createCell((short) 4).setCellValue(tdClient.getManagerName());
            row.createCell((short) 5).setCellValue(tdClient.getStationsWithMoney()); 
            row.createCell((short) 6).setCellValue(tdClient.getInMoneyFromBank()); 
            row.createCell((short) 7).setCellValue(tdClient.getInMoneyFromCash()); 
            row.createCell((short) 8).setCellValue(tdClient.getInMoneyFromTransfer()); 
            row.createCell((short) 9).setCellValue(outMoneyFromCashAll); 
            row.createCell((short) 10).setCellValue(outMoneyFromBankAll); 
            row.createCell((short) 11).setCellValue(tdClient.getCost()); 
            row.createCell((short) 12).setCellValue(tdClient.getProfits()); 
            row.createCell((short) 13).setCellValue(tdClient.getOperationIncome()); 
            row.createCell((short) 14).setCellValue(tdClient.getEditor());
            if(tdClient.getCreateTime() != null && !tdClient.getCreateTime().equals("")){
            	row.createCell((short) 15).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tdClient.getCreateTime()));             	
            }else{
            	row.createCell((short) 15).setCellValue("");
            }
         
        } 
    	return true;
    }
    /**
	 * @author lc
	 * @注释：文件写入和下载
	 */
    public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
    	 try  
         {  
	          FileOutputStream fout = new FileOutputStream(exportUrl+"order.xls");  
//	          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
	          wb.write(fout);  
	          fout.close();
         }catch (Exception e)  
         {  
             e.printStackTrace();  
         } 
    	 OutputStream os;
		 try {
				os = resp.getOutputStream();
				File file = new File(exportUrl + "order.xls");
                 
             if (file.exists())
                 {
                   try {
                         resp.reset();
                         resp.setHeader("Content-Disposition", "attachment; filename="
                                 + "order.xls");
                         resp.setContentType("application/octet-stream; charset=utf-8");
                         os.write(FileUtils.readFileToByteArray(file));
                         os.flush();
                     } finally {
                         if (os != null) {
                             os.close();
                         }
                     }
             }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 return true;	
    }
    
    // 找出下一层子列表
    public List<TdManager> childrenManagerList(List<TdManager> parentManagerList){
    	List<TdManager> tempManagerList = new ArrayList<>();
    	if(parentManagerList != null && parentManagerList.size() > 0){
    		for(int i = 0; i < parentManagerList.size(); i ++){
    			List<TdManager> tempManagerList2 = null;
    			if(parentManagerList.get(i) != null){
    				tempManagerList2 = tdManagerService.findByParentId(parentManagerList.get(i).getId());
    				if(tempManagerList2 != null){
    					tempManagerList.addAll(tempManagerList2);
    				}
    			}
    		}
    	}
    	if(tempManagerList.size() > 0){
    		return tempManagerList;
    	}else{
    		return null;
    	}
    }
}
