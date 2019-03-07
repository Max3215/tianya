package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ynyes.tianya.entity.TdAssets;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdOrderGoods;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.service.TdAssetsService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.util.SiteMagConstant;


/**
 * 资产调节表控制器
 * @author Max
 *
 */
@Controller
@RequestMapping("/Verwalter")
public class TdManagerAssetsController {

	@Autowired
	private TdAssetsService tdAssetsService;
	
	@Autowired
	private TdManagerService tdManagerService;
	
	@Autowired
	private TdManagerRoleService tdManagerRoleService;
	
	@Autowired
	private TdManagerLogService tdManagerLogService;
	
	@RequestMapping(value="/assets/list")
	public String assetList(
						Integer page, 
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
			            HttpServletRequest req){
		
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
                tdManagerLogService.addLog("delete", "删除订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出资产表", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnCopy")){
            	btnCopy(listId, listChkId);
            	tdManagerLogService.addLog("copy", "复制资产表", req);
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
            size = SiteMagConstant.pageSize;;
        }
        
        Date startTime =null; // 起始时间
        Date endTime = null; // 截止时间
        if(null != start && !"".equals(start.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
				startTime = sdf.parse(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        if(null != end && !"".equals(end.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
				endTime = sdf.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        boolean isSettlement = false;
        if(null != settle && !"".equals(settle.trim()))
        {
        	if("yes".equals(settle))
        	{
        		isSettlement = true;
        	}else{
        		isSettlement = false;
        	}
        	map.addAttribute("isSettlement", isSettlement);
        }
        
     // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("assets");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("用车单位");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("用车时间");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 2);  
        cell.setCellValue("车型");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("行程");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);  
        cell.setCellValue("数量");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("车费");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 6);  
        cell.setCellValue("已开票");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);  
        cell.setCellValue("未开票");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);  
        cell.setCellValue("已付款");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);  
        cell.setCellValue("车费（营收）");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);  
        cell.setCellValue("已开票（营收）");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);  
        cell.setCellValue("未开票（营收）");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);  
        cell.setCellValue("已收款（营收）");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 13);  
        cell.setCellValue("结算（营收）");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 14);  
        cell.setCellValue("利润");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);  
        cell.setCellValue("车公司");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 16);  
        cell.setCellValue("业务员");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 17);  
        cell.setCellValue("编号");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 18);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style);
        
        if(null != tdManagerRole && tdManagerRole.getTitle().contains("财务")) // 财务人员
        {
        	if(null == endTime)
        	{
        		if(null == startTime)
        		{
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findAll(page, size));
        				List<TdAssets> list = tdAssetsService.findAll(0, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
        				if(null != exportUrl)
        				{
        					Page<TdAssets> assetPage = tdAssetsService.findAll(page, size);
        					if (ImportData(assetPage, row, cell, sheet)) {
        						download(wb, username, resp);
        					}
        				}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByIsSettlement(isSettlement, page, size));
        				List<TdAssets> list = tdAssetsService.findByIsSettlement(isSettlement, page, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
        				if(null != exportUrl)
        				{
        					Page<TdAssets> assetPage = tdAssetsService.findByIsSettlement(isSettlement, page, size);
        					if (ImportData(assetPage, row, cell, sheet)) {
        						download(wb, username, resp);
        					}
        				}
        			}
        		}else{
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeAfter(page, size, startTime));
        				List<TdAssets> list = tdAssetsService.findByUseTimeAfter(page, Integer.MAX_VALUE, startTime).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeAfter(page, size, startTime);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeAfterAndIsSettlement(page, size, startTime,isSettlement));
        				List<TdAssets> list = tdAssetsService.findByUseTimeAfterAndIsSettlement(page, Integer.MAX_VALUE, startTime,isSettlement).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeAfterAndIsSettlement(page, size, startTime,isSettlement);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}
        	}
        	else
        	{
        		if(null == startTime){
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeBefore(page, size, endTime));
        				List<TdAssets> list = tdAssetsService.findByUseTimeBefore(page, Integer.MAX_VALUE, endTime).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeBefore(page, size, endTime);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeBeforeAndIsSettlement(page, size, endTime,isSettlement));
        				 List<TdAssets> list = tdAssetsService.findByUseTimeBeforeAndIsSettlement(page, Integer.MAX_VALUE, endTime,isSettlement).getContent();
        				 sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeBeforeAndIsSettlement(page, size, endTime,isSettlement);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}else{
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeBetween(page, size, startTime, endTime));
        				List<TdAssets> list = tdAssetsService.findByUseTimeBetween(page, Integer.MAX_VALUE, startTime, endTime).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeBetween(page, size, startTime, endTime);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByUseTimeBetweenAndIsSettlement(page, size, startTime, endTime,isSettlement));
        				List<TdAssets> list = tdAssetsService.findByUseTimeBetweenAndIsSettlement(page, Integer.MAX_VALUE, startTime, endTime,isSettlement).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByUseTimeBetweenAndIsSettlement(page, size, startTime, endTime,isSettlement);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}
        	}
        }
        else // 其他部门
        {
        	if(null == startTime)
        	{
        		if(null == endTime){
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerId(tdManager.getId(), page, size));
        				List<TdAssets> assList = tdAssetsService.findByManagerId(tdManager.getId());
        				sumPrice(assList, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerId(tdManager.getId(), page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerIdAndIsSettlement(tdManager.getId(),isSettlement, page, size));
        				List<TdAssets> list = tdAssetsService.findByManagerIdAndIsSettlement(tdManager.getId(),isSettlement, page, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerIdAndIsSettlement(tdManager.getId(),isSettlement, page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}else{
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerAndUseDateBefore(tdManager.getId(), endTime, page, size));
        				List<TdAssets> list = tdAssetsService.findByManagerAndUseDateBefore(tdManager.getId(), endTime, page, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerAndUseDateBefore(tdManager.getId(), endTime, page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerAndUseDateBeforeAndIsSettlement(tdManager.getId(), endTime,isSettlement, page, size));
        				List<TdAssets> list = tdAssetsService.findByManagerAndUseDateBeforeAndIsSettlement(tdManager.getId(), endTime,isSettlement, page, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerAndUseDateBeforeAndIsSettlement(tdManager.getId(), endTime,isSettlement, page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}
        	}
        	else
        	{
        		if(null == endTime){
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerAndUseDateAfter(tdManager.getId(), startTime, page, size));
        				List<TdAssets> list = tdAssetsService.findByManagerAndUseDateAfter(tdManager.getId(), startTime, page, Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerAndUseDateAfter(tdManager.getId(), startTime, page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerAndUseDateAfterAndIsSettlement(tdManager.getId(), startTime,isSettlement, page, size));
        				List<TdAssets> list = tdAssetsService.findByManagerAndUseDateAfterAndIsSettlement(tdManager.getId(), startTime,isSettlement, page,  Integer.MAX_VALUE).getContent();
        				sumPrice(list, map);
            			if(null != exportUrl)
      					{
            				Page<TdAssets> assetPage = tdAssetsService.findByManagerAndUseDateAfterAndIsSettlement(tdManager.getId(), startTime,isSettlement, page, size);
              				if (ImportData(assetPage, row, cell, sheet)) {
              					download(wb, username, resp);
              				}
      					}
        			}
        		}else{
        			if(null == settle ||  "".equals(settle.trim()))
        			{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerIdAndUseTimeBetween(page, size, tdManager.getId(), startTime, endTime));
        				List<TdAssets> list = tdAssetsService.findByManagerIdAndUseTimeBetween(page,  Integer.MAX_VALUE, tdManager.getId(), startTime, endTime).getContent();
        				sumPrice(list, map);
        				if(null != exportUrl)
        				{
        					Page<TdAssets> assetPage = tdAssetsService.findByManagerIdAndUseTimeBetween(page, size, tdManager.getId(), startTime, endTime);
        					if (ImportData(assetPage, row, cell, sheet)) {
        						download(wb, username, resp);
        					}
        				}
        			}else{
        				map.addAttribute("asset_page", tdAssetsService.findByManagerIdAndUseTimeBetweenAndIsSettlement(page, size, tdManager.getId(), startTime, endTime,isSettlement));
        				List<TdAssets> list = tdAssetsService.findByManagerIdAndUseTimeBetweenAndIsSettlement(page, Integer.MAX_VALUE, tdManager.getId(), startTime, endTime,isSettlement).getContent();
        				sumPrice(list, map);
        				if(null != exportUrl)
        				{
        					Page<TdAssets> assetPage = tdAssetsService.findByManagerIdAndUseTimeBetweenAndIsSettlement(page, size, tdManager.getId(), startTime, endTime,isSettlement);
        					if (ImportData(assetPage, row, cell, sheet)) {
        						download(wb, username, resp);
        					}
        				}
        			}
        		}
        	}
        }
		
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
//        map.addAttribute("keywords", keywords);
        map.addAttribute("startime", startTime);
        map.addAttribute("endTime", endTime);
        map.addAttribute("typeId", typeId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		return "/site_mag/assets_list";
	}
	
	@RequestMapping(value="/assets/edit")
	public String assetsEdit(Long id, String __VIEWSTATE,
			HttpServletRequest req,ModelMap map){
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
	        
        map.addAttribute("manager", tdManagerService.findByUsernameAndIsEnableTrue(username));
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		
        if(null != id){
        	map.addAttribute("assets", tdAssetsService.findOne(id));
        }
		
		return "/site_mag/assets_edit";
	}
	
	@RequestMapping(value="/assets/save")
	public String save(TdAssets tdAssets,
				String __VIEWSTATE,
	            ModelMap map,
	            HttpServletRequest req){
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if(null != tdAssets){
        	if(null == tdAssets.getId()){
        		tdAssets.setManager(tdManager.getRealName());
        		if(null != tdManager){
        			tdAssets.setManagerId(tdManager.getId());
        			tdAssets.setManagerTree(tdManager.getParentTree());
        		}
        		tdAssets.setCreateTime(new Date());
        	}
        }
        
        tdAssetsService.save(tdAssets);
        
		return "redirect:/Verwalter/assets/list";
	}
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdAssets", tdAssetsService.findOne(id));
        }
    }
	
	
	
	
	@SuppressWarnings("deprecation")
	public boolean ImportData(Page<TdAssets> tdAssetsPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
		Long number=0L;
		Double CFare=0.0;
		Double RFare =0.0;
		Double profit=0.0;
    	for (int i = 0; i < tdAssetsPage.getContent().size(); i++)  
        {  
    	 				
            row = sheet.createRow((int) i + 1);  
            TdAssets assets = tdAssetsPage.getContent().get(i);  
            
            //获取用户信息
            row.createCell((short) 0).setCellValue(assets.getCompany());
            if (null != assets.getUseDate()) {
            	cell = row.createCell((short) 1);  
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(assets.getUseDate()));
			}
        	row.createCell((short) 2).setCellValue(assets.getModels());
        	row.createCell((short) 3).setCellValue(assets.getTrip());
        	row.createCell((short) 4).setCellValue(assets.getNumber());  
        	row.createCell((short) 5).setCellValue(assets.getCFare());
        	row.createCell((short) 6).setCellValue(assets.getCInvoiced());
        	row.createCell((short) 7).setCellValue(assets.getCUnbilled());
        	row.createCell((short) 8).setCellValue(assets.getCAlready());
        	row.createCell((short) 9).setCellValue(assets.getRFare());
        	row.createCell((short) 10).setCellValue(assets.getRInvoiced());
        	row.createCell((short) 11).setCellValue(assets.getRUnbilled());
        	row.createCell((short) 12).setCellValue(assets.getRAlready());
        	if(assets.getIsSettlement())
        	{
        		row.createCell((short) 13).setCellValue("是");
        	}else{
        		row.createCell((short) 13).setCellValue("否");
        	}
        	row.createCell((short) 14).setCellValue(assets.getProfit());
        	row.createCell((short) 15).setCellValue(assets.getCarCompany());
        	row.createCell((short) 16).setCellValue(assets.getManager());
        	row.createCell((short) 17).setCellValue(assets.getAssNumber());
        	row.createCell((short) 18).setCellValue(assets.getMark());
        	
        	number +=assets.getNumber();
        	CFare += assets.getCFare();
        	RFare += assets.getRFare();
        	profit += assets.getProfit();
        }
    	row = sheet.createRow(tdAssetsPage.getContent().size() + 1);  
    	row.createCell((short) 0).setCellValue("合计：");
    	row.createCell((short) 4).setCellValue(number);  
    	row.createCell((short) 5).setCellValue(CFare);
    	row.createCell((short) 9).setCellValue(RFare);
    	row.createCell((short) 14).setCellValue(profit);
    	
    	return true;
    }
	
	
	public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
   	 try  
        {  
	          FileOutputStream fout = new FileOutputStream(exportUrl+"assets.xls");  
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
				File file = new File(exportUrl + "assets.xls");
                
            if (file.exists())
                {
                  try {
                        resp.reset();
                        resp.setHeader("Content-Disposition", "attachment; filename="
                                + "assets.xls");
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
                tdAssetsService.delete(id);
            }
        }
    }
	
	/**
	 * 复制资产调剂表
	 * 
	 */
	public void btnCopy(Long[] ids, Integer[] chkIds) {
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
                TdAssets assets = tdAssetsService.findOne(id);
                if(null != assets){
                	TdAssets tdAssets = new TdAssets();
                	
                	tdAssets.setCAlready(assets.getCAlready());
                	tdAssets.setCarCompany(assets.getCarCompany());
                	tdAssets.setCFare(assets.getCFare());
                	tdAssets.setCInvoiced(assets.getCInvoiced());
                	tdAssets.setCompany(assets.getCompany());
                	tdAssets.setCreateTime(new Date());
                	tdAssets.setCUnbilled(assets.getCUnbilled());
                	tdAssets.setEndDate(assets.getEndDate());
                	tdAssets.setIsSettlement(assets.getIsSettlement());
                	tdAssets.setManager(assets.getManager());
                	tdAssets.setManagerId(assets.getManagerId());
                	tdAssets.setManagerTree(assets.getManagerTree());
                	tdAssets.setMark(assets.getMark());
                	tdAssets.setModels(assets.getModels());
                	tdAssets.setNumber(assets.getNumber());
                	tdAssets.setProfit(assets.getProfit());
                	tdAssets.setRAlready(assets.getRAlready());
                	tdAssets.setRFare(assets.getRFare());
                	tdAssets.setRInvoiced(assets.getRInvoiced());
                	tdAssets.setRUnbilled(assets.getRUnbilled());
                	tdAssets.setTrip(assets.getTrip());
                	tdAssets.setUnpaid(assets.getUnpaid());
                	tdAssets.setUseDate(assets.getUseDate());
                	
                	tdAssetsService.save(tdAssets);
                }
            }
        }
		
	}

	
	/**
	 * 
	 * 汇总计算
	 * @author Max
	 * 
	 */
	public void sumPrice(List<TdAssets> assLIst,ModelMap map)
	{
		Double cost =0.0;
		Double fare =0.0;
		Double profit =0.0;
		
		if(null != assLIst && assLIst.size()>0)
		{
			for (TdAssets tdAssets : assLIst) {
				cost += tdAssets.getCFare();
				fare += tdAssets.getRFare();
				profit += tdAssets.getProfit();
			}
		}
		map.addAttribute("cost", cost);
		map.addAttribute("fare", fare);
		map.addAttribute("profit", profit);
	}
	
}
