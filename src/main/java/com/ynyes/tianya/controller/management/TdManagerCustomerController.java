package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emay.channel.httpclient.SDKHttpClient;
import com.ynyes.tianya.entity.TdAssets;
import com.ynyes.tianya.entity.TdCustomer;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.service.TdCustomerService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 客户管理控制器
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/Verwalter")
public class TdManagerCustomerController {

	@Autowired
	private TdCustomerService tdCustomerService;

	@Autowired
	private TdManagerService tdManagerService;

	@Autowired
	private TdManagerRoleService tdManagerRoleService;

	@Autowired
	private TdManagerLogService tdManagerLogService;

	@RequestMapping(value = "/customer/smsSend", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendSMS(String message, Long[] listId, Integer[] listChkId, HttpServletRequest req) {
		Map<String, Object> res = new HashMap<>();
		res.put("code", 1);

		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			res.put("msg", "请重新登录");
			return res;
		}

		moreSmsSend(listId, listChkId, message);
		tdManagerLogService.addLog("add", "选择发送短信", req);

		res.put("code", 0);
		res.put("msg", "发送成功!");
		return res;

	}

	@RequestMapping(value = "/customer/list")
	public String cusList(Integer page, Integer size, String start, String end, Long typeId, String __EVENTTARGET,
			String __EVENTARGUMENT, String __VIEWSTATE, Long[] listId, Integer[] listChkId, ModelMap map,
			String exportUrl,
			// String dateId,
			HttpServletResponse resp, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole tdManagerRole = null;

		if (null != tdManager.getRoleId()) {
			tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
		}

		if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}

		if (null != __EVENTTARGET) {
			if (__EVENTTARGET.equalsIgnoreCase("btnCancel")) {
				// btnCancel(listId, listChkId);
				tdManagerLogService.addLog("cancel", "取消订单", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnConfirm")) {
				// btnConfirm(listId, listChkId);
				tdManagerLogService.addLog("confirm", "确认订单", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnDelete")) {
				btnDelete(listId, listChkId);
				tdManagerLogService.addLog("delete", "删除客户", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("export")) {
				exportUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("export", "导出客户", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnPage")) {
				if (null != __EVENTARGUMENT) {
					page = Integer.parseInt(__EVENTARGUMENT);
				}
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}

		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
			;
		}

		Date startTime = null; // 起始时间
		Date endTime = null; // 截止时间
		if (null != start && !"".equals(start.trim())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startTime = sdf.parse(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (null != end && !"".equals(end.trim())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endTime = sdf.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("customer");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("手机号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("籍贯");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("地址");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("生日");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("QQ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("邮箱");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("证件号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("学历");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("婚姻状况");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("政治面貌");
		cell.setCellStyle(style);
		cell = row.createCell((short) 13);
		cell.setCellValue("民族");
		cell.setCellStyle(style);
		cell = row.createCell((short) 14);
		cell.setCellValue("单位");
		cell.setCellStyle(style);
		cell = row.createCell((short) 15);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);
		cell.setCellValue("添加时间");
		cell.setCellStyle(style);

		if (null == startTime) {
			if (null == endTime) {
				map.addAttribute("cus_page", tdCustomerService.findAll(page, size));
				if (null != exportUrl) {
					Page<TdCustomer> cusPage = tdCustomerService.findAll(page, size);
					if (ImportData(cusPage, row, cell, sheet)) {
						download(wb, username, resp);
					}
				}
			} else {
				map.addAttribute("cus_page", tdCustomerService.findByBirthdayBefore(endTime, page, size));
				if (null != exportUrl) {
					Page<TdCustomer> cusPage = tdCustomerService.findByBirthdayBefore(endTime, page, size);
					if (ImportData(cusPage, row, cell, sheet)) {
						download(wb, username, resp);
					}
				}
			}
		} else {
			if (null == endTime) {
				map.addAttribute("cus_page", tdCustomerService.findByBirthdayAfter(startTime, page, size));
				if (null != exportUrl) {
					Page<TdCustomer> cusPage = tdCustomerService.findByBirthdayAfter(startTime, page, size);
					if (ImportData(cusPage, row, cell, sheet)) {
						download(wb, username, resp);
					}
				}
			} else {
				map.addAttribute("cus_page", tdCustomerService.findByBirthdayBetween(startTime, endTime, page, size));
				if (null != exportUrl) {
					Page<TdCustomer> cusPage = tdCustomerService.findByBirthdayBetween(startTime, endTime, page, size);
					if (ImportData(cusPage, row, cell, sheet)) {
						download(wb, username, resp);
					}
				}
			}
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);
		// map.addAttribute("keywords", keywords);
		map.addAttribute("startime", startTime);
		map.addAttribute("endTime", endTime);
		map.addAttribute("typeId", typeId);
		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		return "/site_mag/customer_list";
	}

	@RequestMapping(value = "/customer/edit")
	public String assetsEdit(Long id, String __VIEWSTATE, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null != id) {
			map.addAttribute("customer", tdCustomerService.findOne(id));
		}

		return "/site_mag/customer_edit";
	}

	@RequestMapping(value = "/customer/save")
	public String save(TdCustomer tdCustomer, String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null != tdCustomer && null == tdCustomer.getCreateTime()) {
			tdCustomer.setCreateTime(new Date());
		}
		tdCustomerService.save(tdCustomer);

		return "redirect:/Verwalter/customer/list";
	}

	@ModelAttribute
	public void getModel(@RequestParam(value = "id", required = false) Long id, Model model) {
		if (null != id) {
			model.addAttribute("tdCustomer", tdCustomerService.findOne(id));
		}
	}

	@SuppressWarnings("deprecation")
	public boolean ImportData(Page<TdCustomer> tdCustomerPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet) {

		for (int i = 0; i < tdCustomerPage.getContent().size(); i++) {

			row = sheet.createRow((int) i + 1);
			TdCustomer customer = tdCustomerPage.getContent().get(i);

			// 获取用户信息
			row.createCell((short) 0).setCellValue(customer.getUsername());
			row.createCell((short) 1).setCellValue(customer.getMobile());
			row.createCell((short) 2).setCellValue(customer.getSex());
			if (null != customer.getDisctrict()) {
				row.createCell((short) 3).setCellValue(
						customer.getProvince() + "-" + customer.getCity() + "-" + customer.getDisctrict());
			} else {
				row.createCell((short) 3).setCellValue(customer.getProvince() + "-" + customer.getCity());
			}
			row.createCell((short) 4).setCellValue(customer.getDetailAddress());
			if (null != customer.getBirthday()) {
				cell = row.createCell((short) 5);
				cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirthday()));
			}
			row.createCell((short) 6).setCellValue(customer.getType());
			row.createCell((short) 7).setCellValue(customer.getQq());
			row.createCell((short) 8).setCellValue(customer.getEmail());
			row.createCell((short) 9).setCellValue(customer.getEducation());
			row.createCell((short) 10).setCellValue(customer.getIdentity());
			row.createCell((short) 11).setCellValue(customer.getMaritalStatus());
			row.createCell((short) 12).setCellValue(customer.getPolitical());
			row.createCell((short) 13).setCellValue(customer.getNation());
			row.createCell((short) 14).setCellValue(customer.getCompany());
			row.createCell((short) 15).setCellValue(customer.getRemark());
			if (null != customer.getCreateTime()) {
				cell = row.createCell((short) 16);
				cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreateTime()));
			}

		}
		return true;
	}

	public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp) {
		try {
			FileOutputStream fout = new FileOutputStream(exportUrl + "customer.xls");
			// OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputStream os;
		try {
			os = resp.getOutputStream();
			File file = new File(exportUrl + "customer.xls");

			if (file.exists()) {
				try {
					resp.reset();
					resp.setHeader("Content-Disposition", "attachment; filename=" + "customer.xls");
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

	private void btnDelete(Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];
				tdCustomerService.delete(id);
			}
		}
	}

	private void moreSmsSend(Long[] ids, Integer[] chkIds, String message) {
		if (null == ids || null == chkIds || ids.length < 1 || chkIds.length < 1) {
			return;
		}

		for (int i = 0; i < chkIds.length; i++) {
			if (null != chkIds[i]) {
				int chkId = chkIds[i];
				if (chkId >= 0 && ids.length > chkId) {
					Long id = ids[chkId];
					TdCustomer customer = tdCustomerService.findOne(id);
					if (null != customer && null != customer.getMobile()) {
						smsSend(customer.getMobile(), message);
					}
				}
			}
		}
	}

	public void smsSend(String mobile, String message) {
		String sn = "8SDK-EMY-6699-RFTQT";// 软件序列号,请通过亿美销售人员获取
		String password = "148954";// 密码,请通过亿美销售人员获取
		String key = "123456";// 序列号首次激活时自己设定
		String baseUrl = "http://219.239.91.114:8080/sdkproxy/";

		try {
			message = "【天涯国旅】" + message;
			message = URLEncoder.encode(message, "UTF-8");
			if (message.contains("回复TD退订")) {
				sn = "8SDK-EMY-6699-RFTRL";// 软件序列号,请通过亿美销售人员获取
				password = "559770";// 密码,请通过亿美销售人员获取
			}

			String code = "888";
			long seqId = System.currentTimeMillis();
			String param = "cdkey=" + sn + "&password=" + password + "&phone=" + mobile + "&message=" + message
					+ "&addserial=" + code + "&seqid=" + seqId;
			String url = baseUrl + "sendsms.action";
			String sms = SDKHttpClient.sendSMS(url, param);
			System.err.println(sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
