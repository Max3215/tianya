package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdReserveCar;
import com.ynyes.tianya.entity.TdServiceItem;
import com.ynyes.tianya.entity.TdSetting;
import com.ynyes.tianya.entity.TdUserSuggestion;
import com.ynyes.tianya.entity.TdVisitorCustom;
import com.ynyes.tianya.service.TdDemandService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdReserverCarService;
import com.ynyes.tianya.service.TdServiceItemService;
import com.ynyes.tianya.service.TdSettingService;
import com.ynyes.tianya.service.TdUserSuggestionService;
import com.ynyes.tianya.service.TdVisitorCustomService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台广告管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value = "/Verwalter/setting")
public class TdManagerSettingController {

	@Autowired
	TdSettingService tdSettingService;

	@Autowired
	TdManagerLogService tdManagerLogService;

	@Autowired
	TdServiceItemService tdServiceItemService;

	@Autowired
	TdUserSuggestionService tdUserSuggestionService;

	@Autowired
	TdDemandService tdDemandService;

	@Autowired
	TdManagerRoleService tdManagerRoleService;

	@Autowired
	TdManagerService tdManagerService;

	@Autowired
	TdVisitorCustomService tdVisitorCustomService;
	
	@Autowired
	TdReserverCarService tdReserverCarService;

	@RequestMapping
	public String setting(Long status, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("setting", tdSettingService.findTopBy());
		map.addAttribute("status", status);

		return "/site_mag/setting_edit";
	}

	@RequestMapping(value = "/save")
	public String orderEdit(TdSetting tdSetting, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null == tdSetting.getId()) {
			tdManagerLogService.addLog("add", "用户修改系统设置", req);
		} else {
			tdManagerLogService.addLog("edit", "用户修改系统设置", req);
		}

		tdSettingService.save(tdSetting);

		return "redirect:/Verwalter/setting?status=1";
	}

	@RequestMapping(value = "/service/list")
	public String service(String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Long[] listId,
			Integer[] listChkId, Long[] listSortId, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteVisitorCustom(listId, listChkId);
				tdManagerLogService.addLog("edit", "删除服务", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnSave")) {
				btnSave(listId, listSortId);
				tdManagerLogService.addLog("edit", "修改服务", req);
			}
		}

		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		map.addAttribute("service_item_list", tdServiceItemService.findAllOrderBySortIdAsc());

		return "/site_mag/service_item_list";
	}

	/**
	 * 后台投诉查看页面跳转
	 * 
	 * @author Zhangji
	 * 
	 */
	@RequestMapping(value = "/suggestion/list")
	public String suggestion(String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Integer page,
			Integer size, Long id, String name, String content, String mail, Long mobile, Long statusId, Long[] listId,
			Integer[] listChkId, Long[] listSortId, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteSuggesiton(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除投诉", req);
			}

		}

		if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
			;
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);

		Page<TdUserSuggestion> suggestionPage = null;

		suggestionPage = tdUserSuggestionService.findAllOrderByTimeDesc(page, size);

		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		map.addAttribute("suggestion_page", suggestionPage);

		return "/site_mag/suggestion_list";
	}
	// /**
	// * 删除投诉
	// * @param req
	// * @param id
	// * @param map
	// * @return
	// */
	// @RequestMapping(value = "/suggestion/delete")
	// public String address(HttpServletRequest req,
	// Long id,
	// // TdUserSuggestion tdUserSuggestion,
	// ModelMap map){
	// TdUserSuggestion tdUserSuggestion = new TdUserSuggestion();
	// tdUserSuggestion.setId(id);
	// tdUserSuggestionService.delete(id);
	// map.addAttribute("suggestion_page",tdUserSuggestion);
	//
	// return "/setting/suggestion_list";
	// }
	//
	//
	// ///////////////////////////////////

	/**
	 * 游客定制
	 */
	@RequestMapping(value = "/visitorCustom/list")
	public String visitorCustom(HttpServletResponse response, HttpServletRequest request, String exportUrl,
			ModelMap map, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Integer page, Integer size,
			Long[] listId, Integer[] listChkId) {
		String username = (String) request.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		tdVisitorCustomService.goCheck();

		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteVisitorCustom(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除游客定制", request);
			} else if (__EVENTTARGET.equalsIgnoreCase("export")) {
				exportUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("export", "导出游客定制信息", request);
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("visitorCustom");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("定制时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("定制内容");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("管理员备注");
		cell.setCellStyle(style);

		Page<TdVisitorCustom> visitor_custom_page = tdVisitorCustomService.findAllByCustomTimeDesc(page, size);
		if (null != exportUrl) {
			if (ImportData(visitor_custom_page.getContent(), row, cell, sheet)) {
				download(wb, username, response);
			}
		}

		map.addAttribute("visitor_custom_page", visitor_custom_page);

		return "/site_mag/visitor_custom_list";
	}
	
	/**
	 * 约租服务
	 */
	@RequestMapping(value = "/reserveCar/list")
	public String reserveCar(HttpServletResponse response, HttpServletRequest request, String exportUrl,
			ModelMap map, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Integer page, Integer size,
			Long[] listId, Integer[] listChkId) {
		String username = (String) request.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		
		tdReserverCarService.goCheckYZ();
		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteReserveCar(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除汽车约租", request);
			} else if (__EVENTTARGET.equalsIgnoreCase("export")) {
				exportUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("export", "导出汽车约租信息", request);
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("reserveCar");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("取车时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("取车地点");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("还车时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("车型");
		cell.setCellStyle(style);

		Page<TdReserveCar> reserve_car_page = tdReserverCarService.findByType(0, page, size);
		if (null != exportUrl) {
			if (ImportDataReserveCar(reserve_car_page.getContent(), row, cell, sheet)) {
				download(wb, username, response);
			}
		}

		map.addAttribute("reserve_car_page", reserve_car_page);

		return "/site_mag/reserve_car_list";
	}
	
	/**
	 * 代驾服务
	 */
	@RequestMapping(value = "/steadCar/list")
	public String steadCar(HttpServletResponse response, HttpServletRequest request, String exportUrl,
			ModelMap map, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Integer page, Integer size,
			Long[] listId, Integer[] listChkId) {
		String username = (String) request.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		tdReserverCarService.goCheckDJ();
		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteReserveCar(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除代驾信息", request);
			} else if (__EVENTTARGET.equalsIgnoreCase("export")) {
				exportUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("export", "导出代驾信息", request);
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);
		
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("steadCar");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("司机");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("电子邮箱");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("QQ号码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		Page<TdReserveCar> stead_car_page = tdReserverCarService.findByType(1, page, size);
		if (null != exportUrl) {
			if (ImportDataSteadCar(stead_car_page.getContent(), row, cell, sheet)) {
				download(wb, username, response);
			}
		}

		map.addAttribute("stead_car_page", stead_car_page);

		return "/site_mag/stead_car_list";
	}

	/**
	 * 后台“车友还想团购”查看页面跳转
	 * 
	 * @author Zhangji
	 * 
	 */
	@RequestMapping(value = "/demand/list")
	public String demand(String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, Long statusId, Integer page,
			Integer size, Long id, String name, String content, String mail, Long mobile, Long[] listId,
			Integer[] listChkId, Long[] listSortId, ModelMap map, String exportUrl, HttpServletResponse resp,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		// 管理员角色
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDeleteDemand(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除demand", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnVerify")) {
				btnVerifyDemand(listId, listChkId);
			} else if (__EVENTTARGET.equalsIgnoreCase("export")) {
				exportUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("export", "导出订单", req);
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}
		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
			;
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);

		Page<TdDemand> tdDemandPage = null;
		if (statusId != null) {
			if (0 == statusId) {
				tdDemandPage = tdDemandService.findByStatusIdOrderByIdDesc(0L, page, size);
			}
			if (1 == statusId) {
				tdDemandPage = tdDemandService.findByStatusIdOrderByIdDesc(1L, page, size);
			}
			map.addAttribute("statusId", statusId);
		} else {
			tdDemandPage = tdDemandService.findAllOrderByTimeDesc(page, size);
		}

		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		map.addAttribute("demand_page", tdDemandPage);

		if (exportUrl != null) {
			/*
			 * 导出报表 gl
			 */
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("demand");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);

			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			// 表头字段
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("定制类型");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("会员名");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("目的地");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("出行人数");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("出发日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 5);
			cell.setCellValue("返程日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 6);
			cell.setCellValue("出发方式");
			cell.setCellStyle(style);
			cell = row.createCell((short) 7);
			cell.setCellValue("酒店级别");
			cell.setCellStyle(style);
			cell = row.createCell((short) 8);
			cell.setCellValue("预支付金额");
			cell.setCellStyle(style);
			cell = row.createCell((short) 9);
			cell.setCellValue("备注");
			cell.setCellStyle(style);
			cell = row.createCell((short) 10);
			cell.setCellValue("电话");
			cell.setCellStyle(style);
			cell = row.createCell((short) 11);
			cell.setCellValue("邮箱");
			cell.setCellStyle(style);
			cell = row.createCell((short) 12);
			cell.setCellValue("定制日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 13);
			cell.setCellValue("交易状态");
			cell.setCellStyle(style);
			cell = row.createCell((short) 14);
			cell.setCellValue("完成时间");
			cell.setCellStyle(style);
			TdDemand tdDemand = null;
			// 标题内容
			for (int i = 1; i <= tdDemandPage.getContent().size(); i++) {
				tdDemand = tdDemandPage.getContent().get(i - 1);
				row = sheet.createRow(i);
				cell = row.createCell((short) 0);
//				if (tdDemand.getStatusId() == 1L) {
//					cell.setCellValue("普通定制");
//				} else {
//					cell.setCellValue("邮轮定制");
//				}
				cell.setCellValue(tdDemand.getPrivateType());
				cell.setCellStyle(style);
				cell = row.createCell((short) 1);
				cell.setCellValue(tdDemand.getName());
				cell.setCellStyle(style);
				cell = row.createCell((short) 2);
				cell.setCellValue(tdDemand.getReachPort());
				cell.setCellStyle(style);
				cell = row.createCell((short) 3);
				cell.setCellValue(tdDemand.getTotalPeople());
				cell.setCellStyle(style);
				cell = row.createCell((short) 4);
				cell.setCellValue(tdDemand.getGroupSaleStartTime());
				cell.setCellStyle(style);
				cell = row.createCell((short) 5);
				cell.setCellValue(tdDemand.getGroupSaleStopTime());
				cell.setCellStyle(style);
				cell = row.createCell((short) 6);
				cell.setCellValue(tdDemand.getWay());
				cell.setCellStyle(style);
				cell = row.createCell((short) 7);
				cell.setCellValue(tdDemand.getHotel());
				cell.setCellStyle(style);
				cell = row.createCell((short) 8);
				cell.setCellValue(tdDemand.getMoney());
				cell.setCellStyle(style);
				cell = row.createCell((short) 9);
				cell.setCellValue(tdDemand.getRemark());
				cell.setCellStyle(style);
				cell = row.createCell((short) 10);
				cell.setCellValue(tdDemand.getMobile());
				cell.setCellStyle(style);
				cell = row.createCell((short) 11);
				cell.setCellValue(tdDemand.getEmail());
				cell.setCellStyle(style);
				cell = row.createCell((short) 12);
				if (tdDemand.getTime() != null) {
					cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tdDemand.getTime()));
				}
				cell.setCellStyle(style);
				cell = row.createCell((short) 13);
				if (tdDemand.getIsReplied()) {
					cell.setCellValue("已完成");
				} else {
					cell.setCellValue("未完成");
				}
				cell.setCellStyle(style);
				cell = row.createCell((short) 14);
				if (null != tdDemand.getReplyTime()) {
					cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdDemand.getReplyTime()));
				}
				cell.setCellStyle(style);
			}

			// 写入本地Excel
			try {
				FileOutputStream fout = new FileOutputStream(exportUrl + "demand.xls");
				// OutputStreamWriter writer = new OutputStreamWriter(fout,
				// "utf8");
				wb.write(fout);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 输出Excel
			OutputStream os;
			try {
				os = resp.getOutputStream();
				File file = new File(exportUrl + "demand.xls");
				if (file.exists()) {
					try {
						resp.reset();
						resp.setHeader("Content-Disposition", "attachment; filename=" + "demand.xls");
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

		}
		return "/site_mag/demand_list";
	}

	/**
	 * @author lc @注释：
	 */
	@RequestMapping(value = "/demand/edit")
	public String demandEdit(Long id, String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");

		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null != id) {
			map.addAttribute("demand_item", tdDemandService.findOne(id));
		}

		return "/site_mag/demand_edit";
	}

	@RequestMapping(value = "/demand/save")
	public String demandSave(TdDemand tdDemand, String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdDemand.getIsReplied() || !tdDemand.getIsReplied()) {
			tdDemand.setIsReplied(true);
			tdDemand.setReplyTime(new Date());
		}

		tdManagerLogService.addLog("edit", "回复用户还想团", req);

		tdDemandService.save(tdDemand);

		return "redirect:/Verwalter/setting/demand/list?statusId=" + __VIEWSTATE;
	}

	@RequestMapping(value = "/demand/nextStatus", method = RequestMethod.GET)
	public String demandSuccess(Long id, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdDemand tdDemand = tdDemandService.findOne(id);
		if (tdDemand != null) {
			int status = tdDemand.getStatus();
			if(status == 1 || status ==0){
				tdDemand.setDealManager(tdManager.getRealName());
				tdDemand.setDealManagerId(tdManager.getId());
				tdDemand.setStatus(2);
			}
			if(status == 2){
				tdDemand.setReplyTime(new Date());				
				tdDemand.setStatus(3);
			}
			
			tdDemandService.save(tdDemand);

		}
		return "redirect:/Verwalter/setting/demand/list";
	}
	
	@RequestMapping(value = "/demand/endSatus", method = RequestMethod.GET)
	public String demandSuccessCancel(Long id,HttpServletRequest req,ModelMap map) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		
		TdDemand tdDemand = tdDemandService.findOne(id);
		if (tdDemand != null) {
			tdDemand.setStatus(4);
			tdDemand.setReplyTime(null);
			tdDemand.setDealManager(tdManager.getRealName());
			tdDemand.setDealManagerId(tdManager.getId());
			
			tdDemandService.save(tdDemand);
			
		}
		return "redirect:/Verwalter/setting/demand/list";
	}
	
	@RequestMapping(value = "/steadCar/nextStatus", method = RequestMethod.GET)
	public String demandSuccess2(Long id, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdReserveCar rc = tdReserverCarService.findOne(id);
		if (rc != null) {
			int status = rc.getStatus();
			if(status == 1){
				rc.setDealManager(tdManager.getRealName());
				rc.setDealManagerId(tdManager.getId());
			}
			rc.setStatus(status+1);
			tdReserverCarService.save(rc);

		}
		return "redirect:/Verwalter/setting/steadCar/list";
	}
	
	@RequestMapping(value = "/steadCar/endSatus", method = RequestMethod.GET)
	public String demandSuccessCancel2(Long id,HttpServletRequest req,ModelMap map) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		
		TdReserveCar rc = tdReserverCarService.findOne(id);
		if (rc != null) {
			rc.setStatus(4);
			rc.setDealManager(tdManager.getRealName());
			rc.setDealManagerId(tdManager.getId());
			tdReserverCarService.save(rc);

		}
		return "redirect:/Verwalter/setting/steadCar/list";
	}
	
	@RequestMapping(value = "/reserveCar/nextStatus", method = RequestMethod.GET)
	public String demandSuccess3(Long id, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdReserveCar rc = tdReserverCarService.findOne(id);
		if (rc != null) {
			int status = rc.getStatus();
			if(status == 1){
				rc.setDealManager(tdManager.getRealName());
				rc.setDealManagerId(tdManager.getId());
			}
			rc.setStatus(status+1);
			tdReserverCarService.save(rc);

		}
		return "redirect:/Verwalter/setting/reserveCar/list";
	}
	
	@RequestMapping(value = "/reserveCar/endSatus", method = RequestMethod.GET)
	public String demandSuccessCancel3(Long id,HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdReserveCar rc = tdReserverCarService.findOne(id);
		if (rc != null) {
			rc.setStatus(4);
			rc.setDealManager(tdManager.getRealName());
			rc.setDealManagerId(tdManager.getId());
			tdReserverCarService.save(rc);

		}
		return "redirect:/Verwalter/setting/reserveCar/list";
	}
	
	

	@RequestMapping(value = "/service/edit")
	public String edit(Long id, String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");

		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null != id) {
			map.addAttribute("service_item", tdServiceItemService.findOne(id));
		}

		return "/site_mag/service_item_edit";
	}

	// 添加游客定制备注
	@RequestMapping(value = "/visitorCustom/doNote")
	public String doNote(Long id, String content, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");

		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		TdVisitorCustom tdVisitorCustom = tdVisitorCustomService.findOne(id);
		map.addAttribute("tdVisitorCustom", tdVisitorCustom);

		return "/site_mag/visitor_custom_edit";
	}

	// 保存游客定制备注
	@RequestMapping(value = "/visitorCustomNote/save")
	public String saveNote(Long id, String content, ModelMap map, HttpServletRequest req, String note) {
		String username = (String) req.getSession().getAttribute("manager");

		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		TdVisitorCustom tdVisitorCustom = tdVisitorCustomService.findOne(id);
		if (note != null) {
			tdVisitorCustom.setNote(note);
		}
		tdVisitorCustomService.save(tdVisitorCustom);
		return "redirect:/Verwalter/setting/visitorCustom/list/";
	}

	@RequestMapping(value = "/service/save", method = RequestMethod.POST)
	public String serviceItemEdit(TdServiceItem tdServiceItem, String __VIEWSTATE, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		tdServiceItemService.save(tdServiceItem);

		tdManagerLogService.addLog("edit", "修改商城服务", req);

		return "redirect:/Verwalter/setting/service/list";
	}

	@ModelAttribute
	public void getModel(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "serviceItemId", required = false) Long serviceItemId,
			@RequestParam(value = "demandId", required = false) Long demandId, ModelMap map) {
		if (null != id) {
			map.addAttribute("tdSetting", tdSettingService.findOne(id));
		}

		if (null != demandId) {
			map.addAttribute("tdDemand", tdDemandService.findOne(demandId));
		}

		if (null != serviceItemId) {
			TdServiceItem serviceItem = tdServiceItemService.findOne(serviceItemId);
			map.addAttribute("tdServiceItem", serviceItem);
		}
	}

	private void btnSave(Long[] ids, Long[] sortIds) {
		if (null == ids || null == sortIds || ids.length < 1 || sortIds.length < 1) {
			return;
		}

		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];

			TdServiceItem e = tdServiceItemService.findOne(id);

			if (null != e) {
				if (sortIds.length > i) {
					e.setSortId(sortIds[i]);
					tdServiceItemService.save(e);
				}
			}
		}
	}

	private void btnDelete(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				tdServiceItemService.delete(id);
			}
		}
	}

	/**
	 * 删除团购要求
	 * 
	 * @author Zhangji 2015年7月30日12:47:56
	 * @param ids
	 * @param chkIds
	 */
	private void btnDeleteDemand(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				tdDemandService.delete(id);
			}
		}
	}

	private void btnDeleteVisitorCustom(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				tdVisitorCustomService.delete(id);
			}
		}
	}
	
	private void btnDeleteReserveCar(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				tdReserverCarService.delete(id);
			}
		}
	}

	/**
	 * 审核团购要求
	 * 
	 * @author Zhangji 2015年7月30日13:24:06
	 * @param ids
	 * @param chkIds
	 */
	private void btnVerifyDemand(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				TdDemand e = tdDemandService.findOne(id);
				if (null != e) {
					e.setStatusId(1L);
					tdDemandService.save(e);
				}

			}
		}
	}

	/**
	 * 删除投诉
	 * 
	 * @author Zhangji 2015年7月30日13:29:18
	 * @param ids
	 * @param chkIds
	 */
	private void btnDeleteSuggesiton(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				tdUserSuggestionService.delete(id);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public boolean ImportData(List<TdVisitorCustom> visitorCustomList, HSSFRow row, HSSFCell cell, HSSFSheet sheet) {

		for (int i = 0; i < visitorCustomList.size(); i++) {

			row = sheet.createRow((int) i + 1);
			TdVisitorCustom tdVisitorCustom = visitorCustomList.get(i);
			// 第四步，创建单元格，并设置值
			String customTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdVisitorCustom.getCustomTime());
			row.createCell((short) 0).setCellValue(customTime);
			row.createCell((short) 1).setCellValue(tdVisitorCustom.getContent());
			row.createCell((short) 2).setCellValue(tdVisitorCustom.getNote());
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean ImportDataReserveCar(List<TdReserveCar> tdReserveCarList, HSSFRow row, HSSFCell cell, HSSFSheet sheet) {

		for (int i = 0; i < tdReserveCarList.size(); i++) {

			row = sheet.createRow((int) i + 1);
			TdReserveCar tdReserveCar = tdReserveCarList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(tdReserveCar.getName());
			row.createCell((short) 1).setCellValue(tdReserveCar.getPhone());
			row.createCell((short) 2).setCellValue(tdReserveCar.getTakeCarTime());
			row.createCell((short) 3).setCellValue(tdReserveCar.getGetCarPlace());
			row.createCell((short) 4).setCellValue(tdReserveCar.getBackCarTime());
			row.createCell((short) 5).setCellValue(tdReserveCar.getBackCarPlace());
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean ImportDataSteadCar(List<TdReserveCar> tdReserveCarList, HSSFRow row, HSSFCell cell, HSSFSheet sheet) {

		for (int i = 0; i < tdReserveCarList.size(); i++) {

			row = sheet.createRow((int) i + 1);
			TdReserveCar tdReserveCar = tdReserveCarList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(tdReserveCar.getDriverName());
			row.createCell((short) 1).setCellValue(tdReserveCar.getName());
			row.createCell((short) 2).setCellValue(tdReserveCar.getPhone());
			row.createCell((short) 3).setCellValue(tdReserveCar.getEmail());
			row.createCell((short) 4).setCellValue(tdReserveCar.getQq());
			row.createCell((short) 5).setCellValue(tdReserveCar.getNote());
		}
		return true;
	}

	/**
	 * @author lc
	 * @注释：文件写入和下载
	 */
	public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp) {
		try {
			FileOutputStream fout = new FileOutputStream(exportUrl + "visitorCustom.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputStream os;
		try {
			os = resp.getOutputStream();
			File file = new File(exportUrl + "visitorCustom.xls");

			if (file.exists()) {
				try {
					resp.reset();
					resp.setHeader("Content-Disposition", "attachment; filename=" + "visitorCustom.xls");
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
