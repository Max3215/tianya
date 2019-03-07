package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdAssets;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdStaff;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdStaffService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 职工管理控制器
 * @author Max
 *
 */
@Service
@RequestMapping(value="/Verwalter/staff")
public class TdManagerStaffController {
	
	
	@Autowired
	private TdStaffService tdStaffService;
	
	@Autowired
	private TdManagerService tdManagerService;
	
	@Autowired
	private TdManagerLogService tdManagerLogService;
	
	@Autowired
	private TdManagerRoleService tdManagerRoleService;
	
	@RequestMapping(value="/list")
	public String list(Integer page,
	            Integer size,
	            Integer companyId, 
	            String keywords,
	            Boolean isClose,
	            String start,
	            String end,
	            String exportUrl,
	            String __EVENTTARGET,
	            String __EVENTARGUMENT,
	            String __VIEWSTATE,
	            Long[] listId,
	            Integer[] listChkId,
	            ModelMap map,
	            HttpServletResponse resp,
	            HttpServletRequest req) throws Exception{
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
      //管理员角色
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager && null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "用户删除职工", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnCopy"))
            {
                btnCopy(listId, listChkId);
                tdManagerLogService.addLog("edit", "用户复制职工", req);
            }
            else if(__EVENTTARGET.equalsIgnoreCase("btnRecover"))
            {
            	recover(listId, listChkId);
            	tdManagerLogService.addLog("edit", "恢复职工信息", req);
            }
            else if(__EVENTTARGET.equalsIgnoreCase("recoverAll"))
            {
            	recoverAll();
            	tdManagerLogService.addLog("edit", "恢复职工信息", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出职工表", req);
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
        
        Date startTime = null;
        Date endTime = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        if(null != start && !start.isEmpty())
        {
        	startTime = sdf.parse(start);
        }
        if(null != end && !end.isEmpty())
        {
        	endTime = sdf.parse(end);
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("isClose", isClose);
        map.addAttribute("companyId", companyId);
        map.addAttribute("keywords", keywords);
        map.addAttribute("startime", startTime);
        map.addAttribute("endTime", endTime);
        
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		
        map.addAttribute("staff_page", tdStaffService.findAll(companyId, keywords, isClose,startTime,endTime, page, size));
		List<TdStaff> staffList = tdStaffService.findAll(companyId, keywords, isClose, startTime, endTime);
		
		map.addAttribute("discount", countDiscount(staffList));
		
		if(null != exportUrl)
		{
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
	        cell.setCellValue("公司");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("职工姓名");  
	        cell.setCellStyle(style); 
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("性别");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("入职时间");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 4);  
	        cell.setCellValue("工龄");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 5);  
	        cell.setCellValue("工休假天数");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 6);  
	        cell.setCellValue("优惠金额");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 7);  
	        cell.setCellValue("可使用优惠金额");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 8);  
	        cell.setCellValue("挂账结余");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 9);  
	        cell.setCellValue("是否结算");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 10);  
	        cell.setCellValue("出行时间");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 11);  
	        cell.setCellValue("结算时间");  
	        cell.setCellStyle(style);
	        cell = row.createCell((short) 12);  
	        cell.setCellValue("备注");  
	        cell.setCellStyle(style);
	        
	        if (ImportData(staffList, row, cell, sheet)) {
				download(wb, username, resp);
			}
		}
		
		return "/site_mag/staff_list";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id,
					String __VIEWSTATE,
					HttpServletRequest req,
					ModelMap map){
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		
		if(null != id)
		{
			map.addAttribute("staff", tdStaffService.findOne(id));
		}
		
		return "/site_mag/staff_edit";
	}
	
	@RequestMapping(value="/check")
	@ResponseBody
	public Map<String,String> check(Long id,String param,HttpServletRequest req)
	{
		 Map<String, String> res = new HashMap<String, String>();
		 res.put("status", "n");
		 
		 if(null == param || param.isEmpty())
		 {
			 res.put("info", "用户名不能为空");
             return res;
		 }
		 if(null == id)
		 {
			 TdStaff staff = tdStaffService.findByUsername(param);
			 if(null != staff)
			 {
				 res.put("info", "已经存在同名职工");
				 return res;
			 }
		 }
		 else
		 {
			if(null != tdStaffService.findByUsernameAndIdNot(param, id))
			{
				 res.put("info", "已经存在同名职工");
				 return res;
			} 
		 }
		 
		 res.put("info", "通过信息验证！");
		 res.put("status", "y");
		 return res;
	}
	
	@RequestMapping(value="/save")
	public String save(TdStaff tdStaff,
			String __VIEWSTATE,
			HttpServletRequest req,ModelMap map)
	{
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		
		 map.addAttribute("__VIEWSTATE", __VIEWSTATE);
	        
        if (null == tdStaff.getId())
        {
            tdManagerLogService.addLog("add", "用户新增职工信息"+tdStaff.getUsername(), req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改职工信息"+tdStaff.getUsername(), req);
        }
		tdStaff.setCompany(tdStaff.getCompanyId());
        tdStaffService.save(tdStaff);
		
		return "redirect:/Verwalter/staff/list";
	}
	
	
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdStaff", tdStaffService.findOne(id));
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
                tdStaffService.delete(id);
            }
        }
    }
	
	private void recover(Long[] ids, Integer[] chkIds)
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
                TdStaff staff = tdStaffService.findOne(id);
                if(null != staff)
                {
                	// 恢复数据
                	staff.setSurDiscount(staff.getDiscount());
                	staff.setSurplus(0.0);
                	staff.setIsClose(false);
                	staff.setTravelTime(null);
                	staff.setAccountTime(null);
                	
                	tdStaffService.save(staff);
                }
            }
        }
    }
	
	private void recoverAll()
	{
		List<TdStaff> staffList = tdStaffService.findAll();
		if(null != staffList && staffList.size() > 0 )
		{
			for (TdStaff staff : staffList) {
				// 恢复数据
            	staff.setSurDiscount(staff.getDiscount());
            	staff.setSurplus(0.0);
            	staff.setIsClose(false);
            	staff.setTravelTime(null);
            	staff.setAccountTime(null);
            	
            	tdStaffService.save(staff);
			}
		}
		
	}
	
	private void btnCopy(Long[] ids, Integer[] chkIds)
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
                TdStaff staff = tdStaffService.findOne(id);
                if(null != staff)
                {
                	// 复制
                	TdStaff tdStaff = new TdStaff();
                	
                	tdStaff.setCompanyId(staff.getCompanyId());
                	tdStaff.setCompany(staff.getCompanyId());
                	tdStaff.setSex(staff.getSex());
                	tdStaff.setWorkYear(staff.getWorkYear());
                	tdStaff.setEntryTime(staff.getEntryTime());
                	tdStaff.setLeaveDay(staff.getLeaveDay());
                	tdStaff.setDiscount(staff.getDiscount());
                	tdStaff.setSurDiscount(staff.getSurDiscount());
                	tdStaff.setSurplus(0.0);
                	tdStaff.setIsClose(false);
                	
                	tdStaffService.save(tdStaff);
                }
            }
        }
    }
	
	/**
	 * 计算优惠金额
	 * 
	 */
	private Double countDiscount(List<TdStaff> stafList)
	{
		Double discount = 0.0;
		if(null != stafList)
		{
			for (TdStaff tdStaff : stafList) {
				if(null != tdStaff.getDiscount())
				{
					discount += tdStaff.getDiscount();
				}
			}
		}
		return discount;
	}
	
	@SuppressWarnings("deprecation")
	public boolean ImportData(List<TdStaff> staffList, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
		Double discount=0.0;
		Double surDiscount=0.0;
		Double surplus =0.0;
		
    	for (int i = 0; i < staffList.size(); i++)  
        {  
    	 				
            row = sheet.createRow((int) i + 1);  
            TdStaff staff = staffList.get(i);  
            
            //获取用户信息
            row.createCell((short) 0).setCellValue(staff.getCompany());
            row.createCell((short) 1).setCellValue(staff.getUsername());
        	row.createCell((short) 2).setCellValue(staff.getSex());
        	if (null != staff.getEntryTime()) {
        		cell = row.createCell((short) 3);  
        		cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(staff.getEntryTime()));
        	}
        	row.createCell((short) 4).setCellValue(staff.getWorkYear());  
        	row.createCell((short) 5).setCellValue(staff.getLeaveDay());
        	row.createCell((short) 6).setCellValue(staff.getDiscount());
        	row.createCell((short) 7).setCellValue(staff.getSurDiscount());
        	row.createCell((short) 8).setCellValue(staff.getSurplus());
        	if(staff.getIsClose())
        	{
        		row.createCell((short) 9).setCellValue("是");
        	}else{
        		row.createCell((short) 9).setCellValue("否");
        	}
        	if (null != staff.getTravelTime()) {
        		cell = row.createCell((short) 10);  
        		cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(staff.getTravelTime()));
        	}
        	if (null != staff.getAccountTime()) {
        		cell = row.createCell((short) 11);  
        		cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(staff.getAccountTime()));
        	}
        	row.createCell((short) 12).setCellValue(staff.getRemark());
        
        	discount += staff.getDiscount();
        	surDiscount += staff.getSurDiscount();
        	surplus += staff.getSurplus();
        }
    	row = sheet.createRow(staffList.size() + 1);  
    	
    	row.createCell((short) 0).setCellValue("合计：");
    	row.createCell((short) 6).setCellValue(discount);  
    	row.createCell((short) 7).setCellValue(surDiscount);
    	row.createCell((short) 8).setCellValue(surplus);
    	
    	return true;
    }
	
	public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
	   	 try  
	        {  
		          FileOutputStream fout = new FileOutputStream(exportUrl+"staff.xls");  
//		          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
		          wb.write(fout);  
		          fout.close();
	        }catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
	   	 	OutputStream os;
			 try {
					os = resp.getOutputStream();
					File file = new File(exportUrl + "staff.xls");
	                
	            if (file.exists())
	                {
	                  try {
	                        resp.reset();
	                        resp.setHeader("Content-Disposition", "attachment; filename="
	                                + "staff.xls");
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

}
