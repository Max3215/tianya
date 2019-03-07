package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emay.channel.httpclient.SDKHttpClient;
import com.ibm.icu.util.Calendar;
import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdCouponType;
import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.entity.TdUserConsult;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.entity.TdUserPoint;
import com.ynyes.tianya.entity.TdUserReturn;
import com.ynyes.tianya.entity.TdUserTravelComment;
import com.ynyes.tianya.entity.TdUserTravelNotes;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdCouponTypeService;
import com.ynyes.tianya.service.TdDemandService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdUserCashRewardService;
import com.ynyes.tianya.service.TdUserCollectService;
import com.ynyes.tianya.service.TdUserCommentService;
import com.ynyes.tianya.service.TdUserConsultService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserPointService;
import com.ynyes.tianya.service.TdUserRecentVisitService;
import com.ynyes.tianya.service.TdUserReturnService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.service.TdUserSuggestionService;
import com.ynyes.tianya.service.TdUserTravelCommentService;
import com.ynyes.tianya.service.TdUserTravelNotesService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 后台用户管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value = "/Verwalter/user")
public class TdManagerUserController {
	@Autowired
	TdCouponService tdCouponService;
	@Autowired
	TdCouponTypeService tdCouponTypeService;

	@Autowired
	TdUserTravelCommentService tdUserTravelCommentService;

	@Autowired
	TdUserService tdUserService;
	@Autowired
	TdUserTravelNotesService tdUserTravelNotesService;

	@Autowired
	TdUserLevelService tdUserLevelService;

	@Autowired
	TdUserConsultService tdUserConsultService;

	@Autowired
	TdUserCommentService tdUserCommentService;

	@Autowired
	TdUserSuggestionService tdUserSuggestionService; // add by zhangji

	@Autowired
	TdDemandService tdDemandService; // @zhangji 2015年7月30日11:25:00

	@Autowired
	TdUserReturnService tdUserReturnService;

	@Autowired
	TdUserCollectService tdUserCollectService;

	@Autowired
	TdUserPointService tdUserPointService;

	@Autowired
	TdUserRecentVisitService tdUserRecentVisitService;

	@Autowired
	TdUserCashRewardService tdUserCashRewardService;

	@Autowired
	TdManagerLogService tdManagerLogService;

	@Autowired
	TdManagerRoleService tdManagerRoleService;

	@Autowired
	TdManagerService tdManagerService;

	@Autowired
	private TdOrderService tdOrderService;

	// @Autowired
	// private TdGuessingGameService tdGuessingGameService;
	
	@RequestMapping(value = "/notes/detail")
	public String noteDetail(Long id, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		TdUserTravelNotes userTravelNotes = tdUserTravelNotesService.findOne(id);
		map.addAttribute("note", userTravelNotes);
		return "/site_mag/notes_detail";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(String param, Long id) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");

		if (null == param || param.isEmpty()) {
			res.put("info", "该字段不能为空");
			return res;
		}

		if (null == id) {
			if (null != tdUserService.findByUsername(param)) {
				res.put("info", "已存在同名用户");
				return res;
			}
		} else {
			if (null != tdUserService.findByUsernameAndIdNot(param, id)) {
				res.put("info", "已存在同名用户");
				return res;
			}
		}

		res.put("status", "y");

		return res;
	}

	@RequestMapping(value = "/list")
	public String setting(Integer page, Integer size, String keywords,
			Long roleId, String __EVENTTARGET, String __EVENTARGUMENT,
			String __VIEWSTATE, Long[] listId, Integer[] listChkId,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		Page<TdUser> userPage = null;
		if (null == keywords) {
			keywords = "";
		}
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		List<TdCouponType> couponTypeList = tdCouponTypeService
				.findAllOrderBySortIdAsc();
		map.addAttribute("coupon_type_list", couponTypeList);

		List<TdUserLevel> userLevelList = tdUserLevelService.findAll();
		map.addAttribute("user_level_list", userLevelList);

		// 管理员角色
		TdManager tdManager = tdManagerService
				.findByUsernameAndIsEnableTrue(username);
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
				btnDelete("user", listId, listChkId, map);
				tdManagerLogService.addLog("delete", "删除用户", req);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnLastLoginOrder")) {
				if (null != __EVENTARGUMENT) {
					if ("asc".equalsIgnoreCase(__EVENTARGUMENT.trim())) {
						map.addAttribute("orderType", "desc");
						userPage = tdUserService
								.findByUsernameContainingOrMobileContainingOrEmailContainingOrderByLastLoginTimeAsc(
										keywords, page, size);
					}
					if ("desc".equalsIgnoreCase(__EVENTARGUMENT.trim())) {
						map.addAttribute("orderType", "asc");
						userPage = tdUserService
								.findByUsernameContainingOrMobileContainingOrEmailContainingOrderByLastLoginTimeDesc(
										keywords, page, size);
					}
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

		if (null != keywords) {
			keywords = keywords.trim();
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);
		map.addAttribute("keywords", keywords);
		map.addAttribute("roleId", roleId);
		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == roleId
				&& !("btnLastLoginOrder".equalsIgnoreCase(__EVENTTARGET))) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				userPage = tdUserService.findAllOrderBySortIdAsc(page, size);
			} else {
				userPage = tdUserService.searchAndOrderByIdDesc(keywords, page,
						size);
			}
		} else if (null != roleId && null == __EVENTTARGET) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				userPage = tdUserService.findByRoleIdOrderByIdDesc(roleId,
						page, size);
			} else {
				userPage = tdUserService.searchAndFindByRoleIdOrderByIdDesc(
						keywords, roleId, page, size);
			}
		}

		map.addAttribute("user_page", userPage);

		return "/site_mag/user_list";
	}

	@RequestMapping(value = "/edit")
	public String orderEdit(Long id, Long roleId, String action,
			String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		map.addAttribute("roleId", roleId);
		List<TdUserLevel> userLevel = tdUserLevelService.findAll();
		map.addAttribute("user_level", userLevel);
		if (null != id) {
			TdUser tdUser = tdUserService.findOne(id);
			map.addAttribute("user", tdUser);
			map.addAttribute("user_total_orders",
					tdOrderService.countByUsername(tdUser.getUsername()));
		}
		return "/site_mag/user_edit";
	}
	

	/**
	 * @author lc
	 * @注释：手动修改粮草
	 */
	@RequestMapping(value = "/param/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> paramEdit(Long userId, Long totalPoints,
			String data, String type, Boolean isBackgroundShow, ModelMap map,
			HttpServletRequest req) {

		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			res.put("message", "请重新登录");
			return res;
		}
		if (null != userId && null != type && !type.isEmpty()
				&& null != isBackgroundShow) {
			TdUser tdUser = tdUserService.findOne(userId);

			if (type.equalsIgnoreCase("editPoint")) {
				if (null != totalPoints) {
					tdUser.setTotalPoints(totalPoints);
					TdUserPoint userPoint = new TdUserPoint();

					userPoint.setIsBackgroundShow(isBackgroundShow);
					userPoint.setTotalPoint(totalPoints);
					userPoint.setUsername(tdUser.getUsername());
					userPoint.setPoint(totalPoints);
					if (null != data) {
						userPoint.setDetail(data);
					}
					userPoint = tdUserPointService.save(userPoint);

					res.put("code", 0);
					return res;
				}
			}
		}

		return res;
	}
	
	/**
	 * @author 给朱朝路揩屁股的苦逼
	 * @注释：手动修改粮草
	 */
	@RequestMapping(value = "/param/edit2", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> paramEdit2(Long userId, Long totalPoints,
			String data, String type, Boolean isBackgroundShow, ModelMap map,
			HttpServletRequest req) {

		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			res.put("message", "请重新登录");
			return res;
		}
		if (null != userId) {
			TdUser tdUser = tdUserService.findOne(userId);
			if (null != totalPoints) {
				tdUser.setTotalPoints(totalPoints);
				TdUserPoint userPoint = new TdUserPoint();
				userPoint.setTotalPoint(totalPoints);
				userPoint.setUsername(tdUser.getUsername());
				userPoint.setPoint(totalPoints);
				if (null != data) {
					userPoint.setDetail(data);
				}
				userPoint = tdUserPointService.save(userPoint);

				res.put("code", 0);
				return res;
			}
		}

		return res;
	}

	@RequestMapping(value = "/save")
	public String orderEdit(TdUser tdUser, Long totalPoints, Long totalPoints1,
			String totalPointsRemarks, String __VIEWSTATE, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		/**
		 * @author lc
		 * @注释：手动修改用户积分
		 */
		if (null != totalPoints) {
			tdUser.setTotalPoints(totalPoints);
			TdUserPoint userPoint = new TdUserPoint();

			userPoint.setTotalPoint(totalPoints);
			userPoint.setUsername(tdUser.getUsername());
			userPoint.setPoint(totalPoints);
			if (null != totalPointsRemarks) {
				userPoint.setDetail(totalPointsRemarks);
			}
			userPoint = tdUserPointService.save(userPoint);
		}
		
		if(totalPoints1 != null){
			tdUser.setTotalPoints(totalPoints1);
		}
		if (null == tdUser.getId()) {
			tdManagerLogService.addLog("add", "修改用户", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户", req);
		}
		List<TdUserLevel> userLevelList = tdUserLevelService.findAllOrderByRequiredPointsDesc();
		for(int i = 0; i < userLevelList.size(); i ++){
			if(totalPoints1 > userLevelList.get(i).getRequiredPoints()){
				tdUser.setUserLevelTitle(userLevelList.get(i).getTitle());
				break;
			}else{
				tdUser.setUserLevelTitle(userLevelList.get(userLevelList.size() - 1).getTitle());
			}
		}
		
		
		tdUserService.save(tdUser);
		

		return "redirect:/Verwalter/user/list/";
	}

	@RequestMapping(value = "/level/edit")
	public String edit(Long id, String __VIEWSTATE, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null != id) {
			map.addAttribute("userLevelId", id);
			map.addAttribute("user_level", tdUserLevelService.findOne(id));
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		return "/site_mag/user_level_edit";
	}

	@RequestMapping(value = "/level/save")
	public String levelSave(TdUserLevel tdUserLevel, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		if (null == tdUserLevel.getId()) {
			tdManagerLogService.addLog("add", "添加用户等级", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户等级", req);
			// 同步调整td_user的字段:user_level_title
			// List<TdUser> tdUserList =
			// tdUserService.findByUserLevelId(tdUserLevel.getLevelId());
			// for(TdUser tdUser : tdUserList){
			// tdUser.setUserLevelTitle(tdUserLevel.getTitle());
			// }
			Long id = tdUserLevel.getId();
			if (id != null) {
				List<TdUser> tdUserList = tdUserService.findByUserLevelId(id);
				if (tdUserList != null) {
					for (TdUser tdUser : tdUserList) {
						tdUser.setUserLevelTitle(tdUserLevel.getTitle());
					}
				}
			}

		}

		tdUserLevelService.save(tdUserLevel);

		return "redirect:/Verwalter/user/level/list";
	}

	@RequestMapping(value = "/level/check/{type}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(@PathVariable String type,
			String param, Long id) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");
		res.put("info", "通过");

		if (null != type) {
			if (type.equalsIgnoreCase("levelId")) {
				if (null == param || param.isEmpty()) {
					res.put("info", "该字段不能为空");
					return res;
				}

				if (null == id) {
					if (null != tdUserLevelService.findByLevelId(Long
							.parseLong(param))) {
						res.put("info", "该用户等级已存在");
						return res;
					}
				} else {
					if (null != tdUserLevelService.findByLevelIdAndIdNot(
							Long.parseLong(param), id)) {
						res.put("info", "该用户等级已存在");
						return res;
					}
				}

				res.put("status", "y");
			} else if (type.equalsIgnoreCase("title")) {
				if (null == param || param.isEmpty()) {
					res.put("info", "该字段不能为空");
					return res;
				}

				if (null == id) {
					if (null != tdUserLevelService.findByTitle(param)) {
						res.put("info", "该等级用户名称已存在");
						return res;
					}
				} else {
					if (null != tdUserLevelService.findByTitleAndIdNot(param,
							id)) {
						res.put("info", "该等级用户名称已存在");
						return res;
					}
				}

				res.put("status", "y");
			}
		}

		return res;
	}

	@RequestMapping(value = "/consult/edit")
	public String consultEdit(Long id, Long statusId, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null != id) {
			map.addAttribute("userConsultId", id);
			map.addAttribute("user_consult", tdUserConsultService.findOne(id));
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		map.addAttribute("statusId", statusId);

		return "/site_mag/user_consult_edit";
	}

	@RequestMapping(value = "/consult/save")
	public String consultSave(TdUserConsult tdUserConsult, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdUserConsult.getIsReplied()
				|| !tdUserConsult.getIsReplied()) {
			tdUserConsult.setIsReplied(true);
			tdUserConsult.setReplyTime(new Date());
		}

		if (null == tdUserConsult.getId()) {
			tdManagerLogService.addLog("add", "修改用户咨询", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户咨询", req);
		}

		tdUserConsultService.save(tdUserConsult);

		return "redirect:/Verwalter/user/consult/list?statusId=" + __VIEWSTATE;
	}

	@RequestMapping(value = "/comment/edit")
	public String commentEdit(Long id, Long statusId, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null != id) {
			map.addAttribute("userCommentId", id);
			map.addAttribute("user_comment", tdUserCommentService.findOne(id));
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		map.addAttribute("statusId", statusId);

		return "/site_mag/user_comment_edit";
	}

	@RequestMapping(value = "/commenttravel/edit")
	public String commenttravelEdit(Long id, Long statusId, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null != id) {
			map.addAttribute("userTravelCommentId", id);
			map.addAttribute("user_comment",
					tdUserTravelCommentService.findOne(id));
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		map.addAttribute("statusId", statusId);

		return "/site_mag/user_comment_travel_edit";
	}

	@RequestMapping(value = "/comment/save")
	public String commentSave(TdUserComment tdUserComment, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdUserComment.getIsReplied()
				|| !tdUserComment.getIsReplied()) {
			tdUserComment.setIsReplied(true);
			tdUserComment.setReplyTime(new Date());
		}

		if (null == tdUserComment.getId()) {
			tdManagerLogService.addLog("add", "修改用户评论", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户评论", req);
		}

		tdUserCommentService.save(tdUserComment);

		return "redirect:/Verwalter/user/comment/list?statusId=" + __VIEWSTATE;
	}

	@RequestMapping(value = "/commenttravel/save")
	public String commenttravelSave(TdUserTravelComment tdUserTravelComment,
			String __VIEWSTATE, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdUserTravelComment.getIsReplied()
				|| !tdUserTravelComment.getIsReplied()) {
			tdUserTravelComment.setIsReplied(true);
			tdUserTravelComment.setReplyTime(new Date());
		}

		if (null == tdUserTravelComment.getId()) {
			tdManagerLogService.addLog("add", "修改用户文章评论", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户文章评论", req);
		}

		tdUserTravelCommentService.save(tdUserTravelComment);

		return "redirect:/Verwalter/user/commenttravel/list?statusId="
				+ __VIEWSTATE;
	}

	@RequestMapping(value = "/return/edit")
	public String returnEdit(Long id, Long statusId, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		if (null != id) {
			map.addAttribute("userReturnId", id);
			map.addAttribute("user_return", tdUserReturnService.findOne(id));
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);
		map.addAttribute("statusId", statusId);

		return "/site_mag/user_return_edit";
	}

	@RequestMapping(value = "/return/save")
	public String returnSave(TdUserReturn tdUserReturn, String __VIEWSTATE,
			ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdUserReturn.getId()) {
			tdManagerLogService.addLog("add", "修改用户退换货", req);
		} else {
			tdManagerLogService.addLog("edit", "修改用户退换货", req);
		}

		tdUserReturnService.save(tdUserReturn);

		return "redirect:/Verwalter/user/return/list?statusId=" + __VIEWSTATE;
	}

	@RequestMapping(value = "/{type}/list")
	public String list(@PathVariable String type, Integer page, Integer size,
			Long userId, Long statusId, String exportUrl, String exportAllUrl,
			String keywords, String __EVENTTARGET, String __EVENTARGUMENT,
			String __VIEWSTATE, Long[] listId, Integer[] listChkId,
			Long[] listSortId, ModelMap map, HttpServletRequest req,
			HttpServletResponse resp) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		// 管理员角色
		TdManager tdManager = tdManagerService
				.findByUsernameAndIsEnableTrue(username);
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
				btnDelete(type, listId, listChkId, map);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnSave")) {
				btnSave(type, listId, listSortId);
			} else if (__EVENTTARGET.equalsIgnoreCase("btnVerify")) {
				btnVerify(type, listId, listChkId);
			} else if (__EVENTTARGET.equalsIgnoreCase("exportAll")) {
				exportAllUrl = SiteMagConstant.backupPath;
				tdManagerLogService.addLog("exportAll", "导出全部竞猜记录", req);
			}
		}

		if (null == page || page < 0) {
			page = 0;
		}

		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

		if (null != keywords) {
			keywords = keywords.trim();
		}

		map.addAttribute("page", page);
		map.addAttribute("size", size);
		map.addAttribute("userId", userId);
		map.addAttribute("statusId", statusId);
		map.addAttribute("keywords", keywords);
		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null != type) {
			if (type.equalsIgnoreCase("point")) // 积分
			{
				if (null == userId) {
					return "/site_mag/error_404";
				}

				TdUser user = tdUserService.findOne(userId);

				if (null == user || null == user.getUsername()) {
					return "/site_mag/error_404";
				}

				map.addAttribute("user_point_page", tdUserPointService
						.findByUsername(user.getUsername(), page, size));
				return "/site_mag/user_point_list";
			} else if (type.equalsIgnoreCase("collect")) // 关注
			{
				if (null == userId) {
					return "/site_mag/error_404";
				}

				TdUser user = tdUserService.findOne(userId);

				if (null == user || null == user.getUsername()) {
					return "/site_mag/error_404";
				}

				map.addAttribute("user_collect_page", tdUserCollectService
						.findByUsername(user.getUsername(), page, size));
				return "/site_mag/user_collect_list";
			} else if (type.equalsIgnoreCase("recent")) // 最近浏览
			{
				if (null == userId) {
					return "/site_mag/error_404";
				}

				TdUser user = tdUserService.findOne(userId);

				if (null == user || null == user.getUsername()) {
					return "/site_mag/error_404";
				}

				map.addAttribute("user_recent_page", tdUserRecentVisitService
						.findByUsernameOrderByVisitTimeDesc(user.getUsername(),
								page, size));
				return "/site_mag/user_recent_list";
			} else if (type.equalsIgnoreCase("reward")) // 返现
			{
				if (null == userId) {
					return "/site_mag/error_404";
				}

				TdUser user = tdUserService.findOne(userId);

				if (null == user || null == user.getUsername()) {
					return "/site_mag/error_404";
				}

				map.addAttribute("user_reward_page", tdUserCashRewardService
						.findByUsernameOrderByIdDesc(user.getUsername(), page,
								size));
				return "/site_mag/user_reward_list";
			} else if (type.equalsIgnoreCase("level")) // 用户等级
			{
				map.addAttribute("user_level_page",
						tdUserLevelService.findAllOrderBySortIdAsc(page, size));
				return "/site_mag/user_level_list";
			} else if (type.equalsIgnoreCase("consult")) // 用户咨询
			{
				map.addAttribute("user_consult_page",
						findTdUserConsult(statusId, keywords, page, size));
				return "/site_mag/user_consult_list";
			} else if (type.equalsIgnoreCase("comment")) // 用户评论
			{
				map.addAttribute("user_comment_page",
						findTdUserComment(statusId, keywords, page, size));
				return "/site_mag/user_comment_list";
			} else if (type.equalsIgnoreCase("commenttravel")) // 游记评论
			{
				map.addAttribute("user_comment_page",
						findTdUserTravelComment(statusId, keywords, page, size));
				return "/site_mag/user_comment_travel_list";
			} else if (type.equalsIgnoreCase("diary")) // 攻略游记
			{
				map.addAttribute("user_diary_page",
						findTdUserTravelNotes(statusId, keywords, page, size));
				return "/site_mag/user_notes_list";
			} else if (type.equalsIgnoreCase("return")) // 退换货
			{
				map.addAttribute("user_return_page",
						findTdUserReturn(statusId, keywords, page, size));
				return "/site_mag/user_return_list";
			} else if (type.equalsIgnoreCase("guessing")) // 竞猜
			{
				/**
				 * @author lc
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
				cell.setCellValue("用户名");
				cell.setCellStyle(style);
				cell = row.createCell((short) 1);
				cell.setCellValue("电话");
				cell.setCellStyle(style);
				cell = row.createCell((short) 2);
				cell.setCellValue("竞猜金额");
				cell.setCellStyle(style);
				cell = row.createCell((short) 3);
				cell.setCellValue("竞猜时间");
				cell.setCellStyle(style);
				cell = row.createCell((short) 4);

				// //
				// if (null != exportAllUrl) {//导出全部
				// List<TdGuessingGame> tdGuessingGames =
				// tdGuessingGameService.findAll();
				// if (ImportAllData(tdGuessingGames, row, cell, sheet)) {
				// download(wb, username, resp);
				// }
			}
			// map.addAttribute("user_guessing_page",
			// tdGuessingGameService.findAll(page, size));
			return "/site_mag/user_guessing_list";
		}
		// }

		return "/site_mag/error_404";
	}
	
	@RequestMapping(value="/note/save",method=RequestMethod.POST)
	public String noteSave(TdUserTravelNotes tdUserTravelNotes,
					String __VIEWSTATE,
					HttpServletRequest req,ModelMap map)
	{
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		if (null == tdUserTravelNotes.getId()) {
			tdManagerLogService.addLog("add", "修改攻略游记", req);
		} else {
			tdManagerLogService.addLog("edit", "修改攻略游记", req);
		}
		
		tdUserTravelNotesService.save(tdUserTravelNotes);
		
		return "redirect:/Verwalter/user/diary/list";
	}

	/**
	 * @author lulu
	 * @注释 发放优惠劵
	 */
	@RequestMapping(value = "/coupon/send", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> coupon(ModelMap map, HttpServletRequest req,
			String[] listChkId1, Long[] listId, Long typeId, Long type,
			String code1) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);
		if (null == typeId) {
			res.put("msg", "优惠劵不存在！");
			return res;
		}
		if (null != code1 && code1.equals("11")) {
			TdUserLevel userLevel = tdUserLevelService.findOne(type);
			List<TdUser> users = tdUserService.findByStatusId(1L);
			TdCoupon coupon = tdCouponService.findByTypeId(typeId);
			for (int i = 0; i < users.size(); i++) {
				if (null == coupon || coupon.getLeftNumber() < i) {
					res.put("msg", "优惠劵数量不够！");
					return res;
				}
			}
			for (TdUser user : users) {
				if (user.getUserLevelId() == userLevel.getId()) {

					TdCoupon newCoupon = new TdCoupon();
					// 得到数量
					newCoupon.setGetNumber(1L);
					// 得到时间
					Date date = new Date();
					
					//截止时间 coupon.getTotalDays() 20天,date
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					String s = coupon.getTotalDays().toString();
					c.add(Calendar.DATE, Integer.parseInt(s));
					Date date1=c.getTime();
					newCoupon.setExpireTime(date1);
					
					
					newCoupon.setGetTime(date);
					newCoupon.setIsDistributted(true);
					newCoupon.setIsUsed(false);
					newCoupon.setMobile(user.getMobile());
					newCoupon.setPrice(coupon.getPrice());
					newCoupon.setTypeCategoryId(coupon.getTypeCategoryId());
					newCoupon.setTypeDescription(coupon.getTypeDescription());
					newCoupon.setTypeId(coupon.getTypeId());
					newCoupon.setTypePicUri(coupon.getTypePicUri());
					newCoupon.setTypeTitle(coupon.getTypeTitle());
					newCoupon.setUsername(user.getUsername());
					newCoupon.setTotalDays(coupon.getTotalDays());
					newCoupon.setCanUsePrice(coupon.getCanUsePrice());
					tdCouponService.save(newCoupon);
					Long count1 = coupon.getGetNumber();
					Long count = coupon.getLeftNumber();
					count--;
					count1++;
					coupon.setLeftNumber(count);
					coupon.setGetNumber(count1);
					tdCouponService.save(coupon);
				}
			}
		} else {
			if (listChkId1.length < 1) {
				res.put("msg", "请选择要发送优惠劵的会员！");
				return res;
			}
			for (int i = 0; i < listChkId1.length; i++) {

				TdCoupon coupon = tdCouponService.findByTypeId(typeId);
				if (null == coupon || coupon.getLeftNumber() < i) {
					res.put("msg", "优惠劵数量不够！");
					return res;
				}
			}
			for (int i = 0; i < listChkId1.length; i++) {
				String id1 = listChkId1[i];
				if (id1 != null && !id1.equals("")) {
					long id = Long.parseLong(id1);
					TdUser user = tdUserService.findOne(id);
					TdCoupon coupon = tdCouponService.findByTypeId(typeId);
					TdCoupon newCoupon = new TdCoupon();
					// 得到数量
					newCoupon.setGetNumber(1L);
					// 得到时间
					Date date = new Date();
					
					//截止时间 coupon.getTotalDays() 20天,date
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					String s = coupon.getTotalDays().toString();
					c.add(Calendar.DATE, Integer.parseInt(s));
					Date date1=c.getTime();
					newCoupon.setExpireTime(date1);
					
					newCoupon.setGetTime(date);
					newCoupon.setIsDistributted(true);
					newCoupon.setIsUsed(false);
					newCoupon.setMobile(user.getMobile());
					newCoupon.setPrice(coupon.getPrice());
					newCoupon.setTypeCategoryId(coupon.getTypeCategoryId());
					newCoupon.setTypeDescription(coupon.getTypeDescription());
					newCoupon.setTypeId(coupon.getTypeId());
					newCoupon.setTypePicUri(coupon.getTypePicUri());
					newCoupon.setTypeTitle(coupon.getTypeTitle());
					newCoupon.setUsername(user.getUsername());
					newCoupon.setTotalDays(coupon.getTotalDays());
					newCoupon.setCanUsePrice(coupon.getCanUsePrice());
					tdCouponService.save(newCoupon);
					Long count = coupon.getLeftNumber();
					count--;
					coupon.setLeftNumber(count);
					tdCouponService.save(coupon);
				}
			}
		}
		res.put("code", 0);
		return res;
	}
	
	/**
	 * 短信发送
	 * @author Max
	 * 
	 */
	@RequestMapping(value="/smsSend",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendSMS(
				String type,
				String message,
				Long levelId,
				Long[] listId,
				Integer[] listChkId,
				HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            res.put("msg", "请重新登录");
            return res;
        }
		
        if(null != type && !type.isEmpty())
        {
        	if(type.equalsIgnoreCase("LevelSend"))// 等级发送
        	{
        		if(null != levelId)
        		{
        			List<TdUser> levelList = tdUserService.findByUserLevelId(levelId);
        			for (TdUser tdUser : levelList) {
						if(null != tdUser && null != tdUser.getMobile())
						{
							smsSend(tdUser.getMobile(), message);
							tdManagerLogService.addLog("add", "等级发送短信", req);
						}
					}
        		}
        	}else if(type.equalsIgnoreCase("sendSMS")) { // 选择发送
        		moreSmsSend(listId,listChkId,message);
        		tdManagerLogService.addLog("add", "选择发送短信", req);
        	}
        	
	    	 res.put("code", 0);
	         res.put("msg", "发送成功!");
	         return res;
        }
        
        res.put("msg", "参数错误!");
		return res;
	}

	/**
	 * @author lc
	 * @注释：文件写入和下载
	 */
	public Boolean download(HSSFWorkbook wb, String exportUrl,
			HttpServletResponse resp) {
		try {
			FileOutputStream fout = new FileOutputStream(exportUrl
					+ "guessingUser.xls");
			// OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputStream os;
		try {
			os = resp.getOutputStream();
			File file = new File(exportUrl + "guessingUser.xls");

			if (file.exists()) {
				try {
					resp.reset();
					resp.setHeader("Content-Disposition",
							"attachment; filename=" + "guessingUser.xls");
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
			e.printStackTrace();
		}
		return true;
	}

	@ModelAttribute
	public void getModel(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "userLevelId", required = false) Long userLevelId,
			@RequestParam(value = "userConsultId", required = false) Long userConsultId,
			@RequestParam(value = "userCommentId", required = false) Long userCommentId,
			@RequestParam(value = "userTravelCommentId", required = false) Long userTravelCommentId,
			@RequestParam(value = "userReturnId", required = false) Long userReturnId,
			@RequestParam(value = "noteId", required = false) Long noteId,
			Model model) {
		if (null != userId) {
			model.addAttribute("tdUser", tdUserService.findOne(userId));
		}

		if (null != userLevelId) {
			model.addAttribute("tdUserLevel",
					tdUserLevelService.findOne(userLevelId));
		}

		if (null != userConsultId) {
			model.addAttribute("tdUserConsult",
					tdUserConsultService.findOne(userConsultId));
		}

		if (null != userCommentId) {
			model.addAttribute("tdUserComment",
					tdUserCommentService.findOne(userCommentId));
		}
		if (null != userTravelCommentId) {
			model.addAttribute("tdUserTravelComment",
					tdUserTravelCommentService.findOne(userTravelCommentId));
		}

		if (null != userReturnId) {
			model.addAttribute("tdUserReturn",
					tdUserReturnService.findOne(userReturnId));
		}
		if(null != noteId){
			model.addAttribute("tdUserTravelNotes", tdUserTravelNotesService.findOne(noteId));
		}
	}

	private Page<TdUserConsult> findTdUserConsult(Long statusId,
			String keywords, int page, int size) {
		Page<TdUserConsult> dataPage = null;

		if (null == statusId) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserConsultService
						.findAllOrderByIdDesc(page, size);
			} else {
				dataPage = tdUserConsultService.searchAndOrderByIdDesc(
						keywords, page, size);
			}
		} else {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserConsultService.findByStatusIdOrderByIdDesc(
						statusId, page, size);
			} else {
				dataPage = tdUserConsultService
						.searchAndFindByStatusIdOrderByIdDesc(keywords,
								statusId, page, size);
			}
		}

		return dataPage;
	}

	private Page<TdUserComment> findTdUserComment(Long statusId,
			String keywords, int page, int size) {
		Page<TdUserComment> dataPage = null;

		if (null == statusId) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserCommentService
						.findAllOrderByIdDesc(page, size);
			} else {
				dataPage = tdUserCommentService.searchAndOrderByIdDesc(
						keywords, page, size);
			}
		} else {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserCommentService.findByStatusIdOrderByIdDesc(
						statusId, page, size);
			} else {
				dataPage = tdUserCommentService
						.searchAndFindByStatusIdOrderByIdDesc(keywords,
								statusId, page, size);
			}
		}

		return dataPage;
	}

	/**
	 * lulu 攻略游记评论
	 * 
	 */
	private Page<TdUserTravelComment> findTdUserTravelComment(Long statusId,
			String keywords, int page, int size) {
		Page<TdUserTravelComment> dataPage = null;

		if (null == statusId) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserTravelCommentService.findAllOrderByIdDesc(
						page, size);
			} else {
				dataPage = tdUserTravelCommentService.searchAndOrderByIdDesc(
						keywords, page, size);
			}
		} else {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserTravelCommentService
						.findByStatusIdOrderByIdDesc(statusId, page, size);
			} else {
				dataPage = tdUserTravelCommentService
						.searchAndFindByStatusIdOrderByIdDesc(keywords,
								statusId, page, size);
			}
		}

		return dataPage;
	}

	private Page<TdUserTravelNotes> findTdUserTravelNotes(Long statusId,
			String keywords, int page, int size) {
		Page<TdUserTravelNotes> dataPage = null;

		if (null == statusId) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				//dataPage = tdUserTravelNotesService.findAllOrderByIdDesc(page,
					//	size);
				//排序列出 gl
				dataPage =  tdUserTravelNotesService.findAllOrderBySortId(page, size);
			} else {
				dataPage = tdUserTravelNotesService.searchAndOrderByIdDesc(
						keywords, page, size);
			}
		} else {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserTravelNotesService
						.findByStatusIdOrderByIdDesc(statusId, page, size);
			} else {
				dataPage = tdUserTravelNotesService
						.searchAndFindByStatusIdOrderByIdDesc(keywords,
								statusId, page, size);
			}
		}

		return dataPage;
	}

	private Page<TdUserReturn> findTdUserReturn(Long statusId, String keywords,
			int page, int size) {
		Page<TdUserReturn> dataPage = null;

		if (null == statusId) {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserReturnService.findAllOrderBySortIdAsc(page,
						size);
			} else {
				dataPage = tdUserReturnService.searchAndOrderBySortIdAsc(
						keywords, page, size);
			}
		} else {
			if (null == keywords || "".equalsIgnoreCase(keywords)) {
				dataPage = tdUserReturnService.findByStatusIdOrderBySortIdAsc(
						statusId, page, size);
			} else {
				dataPage = tdUserReturnService
						.searchAndFindByStatusIdOrderBySortIdAsc(keywords,
								statusId, page, size);
			}
		}

		return dataPage;
	}

	private void btnSave(String type, Long[] ids, Long[] sortIds) {
		if (null == ids || null == sortIds || ids.length < 1
				|| sortIds.length < 1 || null == type || "".equals(type)) {
			return;
		}

		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];

			if (type.equalsIgnoreCase("user")) // 用户
			{
				TdUser e = tdUserService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserService.save(e);
					}
				}
			} else if (type.equalsIgnoreCase("level")) // 用户等级
			{
				TdUserLevel e = tdUserLevelService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserLevelService.save(e);
					}
				}
			} 
			else if (type.equalsIgnoreCase("diary")) // 游记攻略
			{
				TdUserTravelNotes tutn = tdUserTravelNotesService.findOne(id);
				
				if (null != tutn) {
					if (sortIds.length > i) {
						tutn.setSortId(sortIds[i]);
						tdUserTravelNotesService.save(tutn);
					}
				}
			} else if (type.equalsIgnoreCase("consult")) // 咨询
			{
				TdUserConsult e = tdUserConsultService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserConsultService.save(e);
					}
				}
			} else if (type.equalsIgnoreCase("comment")) // 评论
			{
				TdUserComment e = tdUserCommentService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserCommentService.save(e);
					}
				}
			} else if (type.equalsIgnoreCase("commenttravel")) // 游记 评论
			{
				TdUserTravelComment e = tdUserTravelCommentService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserTravelCommentService.save(e);
					}
				}
			} else if (type.equalsIgnoreCase("return")) // 退换货
			{
				TdUserReturn e = tdUserReturnService.findOne(id);

				if (null != e) {
					if (sortIds.length > i) {
						e.setSortId(sortIds[i]);
						tdUserReturnService.save(e);
					}
				}
			}
		}
	}

	private void btnDelete(String type, Long[] ids, Integer[] chkIds, ModelMap map) {
		if (null == ids || null == chkIds || ids.length < 1
				|| chkIds.length < 1 || null == type || "".equals(type)) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				if (type.equalsIgnoreCase("user")) // 用户
				{
					tdUserService.delete(id);
				} else if (type.equalsIgnoreCase("level")) // 用户等级
				{
					// 判断该等级下是否有用户
					if (existUser(id)) {
						map.addAttribute("existUser", "yes");
					}else{
						tdUserLevelService.delete(id);
					}
				} else if (type.equalsIgnoreCase("consult")) // 咨询
				{
					tdUserConsultService.delete(id);
				} else if (type.equalsIgnoreCase("comment")) // 评论
				{
					tdUserCommentService.delete(id);
				} else if (type.equalsIgnoreCase("commenttravel")) // 游记评论
				{
					//评论对应的攻略评论数量减一
					TdUserTravelNotes utn = tdUserTravelNotesService.findOne(tdUserTravelCommentService.findOne(id).getCommentId());
					if(utn != null && utn.getCountComment() != null && utn.getCountComment() > 0){
						utn.setCountComment(utn.getCountComment()-1);
					}
					tdUserTravelCommentService.delete(id);
					
					
				} else if (type.equalsIgnoreCase("diary")) // 评论
				{
					tdUserTravelNotesService.delete(id);
				} else if (type.equalsIgnoreCase("suggestion")) // 投诉 @ by
																// zhangji
				{
					tdUserSuggestionService.delete(id);
				} else if (type.equalsIgnoreCase("return")) // 退换货
				{
					tdUserReturnService.delete(id);
				} else if (type.equalsIgnoreCase("guessing")) // 竞猜
				{
					// tdGuessingGameService.delete(id);
				}
			}
		}
	}

	private void btnVerify(String type, Long[] ids, Integer[] chkIds) {
		if (null == ids || null == chkIds || ids.length < 1
				|| chkIds.length < 1 || null == type || "".equals(type)) {
			return;
		}

		for (int chkId : chkIds) {
			if (chkId >= 0 && ids.length > chkId) {
				Long id = ids[chkId];

				if (type.equalsIgnoreCase("consult")) // 咨询
				{
					TdUserConsult e = tdUserConsultService.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdUserConsultService.save(e);
					}
				} else if (type.equalsIgnoreCase("comment")) // 评论
				{
					TdUserComment e = tdUserCommentService.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdUserCommentService.save(e);
					}
				} else if (type.equalsIgnoreCase("commenttravel")) // 游记攻略评论
				{
					TdUserTravelComment e = tdUserTravelCommentService
							.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdUserTravelCommentService.save(e);
					}
				} else if (type.equalsIgnoreCase("diary")) // 文章
				{
					TdUserTravelNotes e = tdUserTravelNotesService.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdUserTravelNotesService.save(e);
					}
				} else if (type.equalsIgnoreCase("demand")) // 团购要求 @zhangji
															// 2015年7月30日11:23:51
				{
					TdDemand e = tdDemandService.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdDemandService.save(e);
					}

				} else if (type.equalsIgnoreCase("return")) // 退换货
				{
					TdUserReturn e = tdUserReturnService.findOne(id);

					if (null != e) {
						e.setStatusId(1L);
						tdUserReturnService.save(e);
					}
				}
			}
		}
	}

	private boolean existUser(Long id) {
		List<TdUser> tdUserList = tdUserService.findByUserLevelId(id);
		if (tdUserList != null && tdUserList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void moreSmsSend(Long[] ids, Integer[] chkIds, String message) {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < chkIds.length; i++) {
			if(null != chkIds[i])
			{
				int chkId = chkIds[i];
				if (chkId >=0 && ids.length > chkId)
		        {
	                Long id = ids[chkId];
		            TdUser user = tdUserService.findOne(id);
		            if(null != user && null != user.getMobile())
		            {
		            	smsSend(user.getMobile(), message);
		            }
		        }
			}
		}
	}
	
	public void smsSend(String mobile,String message)
	{
		String sn = "8SDK-EMY-6699-RFTQT";// 软件序列号,请通过亿美销售人员获取
		String password = "148954";// 密码,请通过亿美销售人员获取
		String key = "123456";// 序列号首次激活时自己设定
		String baseUrl = "http://219.239.91.114:8080/sdkproxy/";
		
		try {
			message = "【天涯国旅】"+message;
			message = URLEncoder.encode(message, "UTF-8");
			if(message.contains("回复TD退订"))
			{
				sn = "8SDK-EMY-6699-RFTRL";// 软件序列号,请通过亿美销售人员获取
				password = "559770";// 密码,请通过亿美销售人员获取
			}
			
			String code = "888";
			long seqId = System.currentTimeMillis();
			String param = "cdkey=" + sn + "&password=" 
						+ password + "&phone=" + mobile + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
			String url = baseUrl + "sendsms.action";
			String sms = SDKHttpClient.sendSMS(url, param);
			System.err.println(sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
