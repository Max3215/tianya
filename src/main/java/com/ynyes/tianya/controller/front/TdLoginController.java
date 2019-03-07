package com.ynyes.tianya.controller.front;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdUserService;

import net.sf.json.JSONObject;

/**
 * 登录
 *
 */
@Controller
public class TdLoginController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdCommonService tdCommonService;

	@RequestMapping(value = "/weixinLogin", method = RequestMethod.GET)
	public String weixinLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		return "redirect:https://open.weixin.qq.com/connect/qrconnect?appid=wx3b5c1c6427bb7e3e&redirect_uri="
				+ URLEncoder.encode("http://tyjourney.com/weixin_login_return", "utf-8")
				+ "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
	}

	@RequestMapping(value = "/weixin_login_return", method = RequestMethod.GET)
	public String weixnLogonReturn(String code, String state, HttpServletRequest request, ModelMap map)
			throws Exception {

		if (null != code) {
			Map<String, String> res = getAccessToken(code);
			String accessToken = res.get("access_token");
			String openId = res.get("openid");
			System.err.println("lichong_________________accessToken__" + accessToken);
			System.err.println("lichong_________________openId__" + openId);

			Map<String, String> wxInfo = getWxinfo(accessToken, openId);

			TdUser user = tdUserService.findByWxOpenId(openId);
			if (null == user) {
				map.addAttribute("openId", openId);
				map.addAttribute("qqName", wxInfo.get("nickname"));
				map.addAttribute("type", "wx");

				tdCommonService.setCommon(map, request);

				return "/client/accredit_login";
			} else {
				request.getSession().setAttribute("username", user.getUsername());
			}
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest req, ModelMap map, String customData) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("rem")) {
					String s = cookie.getValue();
					String[] ss = s.split(":");
					map.addAttribute("username", ss[0]);
					map.addAttribute("password", ss[1]);
				}
			}
		}

		String username = (String) req.getSession().getAttribute("username");
		String referer = req.getHeader("referer");

		// 基本信息
		tdCommonService.setCommon(map, req);

		if(customData != null){
			map.addAttribute("customData", customData);
		}
		if (null == username) {
			return "/client/login";
		}

		if (null == referer) {
			referer = "/";
		}
		return "redirect:" + referer;
	}

	/*
	 * @RequestMapping("/login") public String login(HttpServletRequest req,
	 * ModelMap map) { String username = (String)
	 * req.getSession().getAttribute("username"); tdCommonService.setCommon(map,
	 * req); return "/client/login";; }
	 */

	@RequestMapping(value = "/login/check/{type}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> validateForm(@PathVariable String type, String param, HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();

		res.put("status", "n");

		if (null == type) {
			res.put("info", "参数错误");
			return res;
		}

		if (type.equalsIgnoreCase("username")) {
			if (null == param || param.isEmpty()) {
				res.put("info", "用户名不能为空");
				return res;
			}

			TdUser user = tdUserService.findByUsername(param);

			if (null == user) {
				res.put("info", "该用户不存在");
				return res;
			} else {

			}
		}

		/*
		 * if (type.equalsIgnoreCase("password")) { if (null == param ||
		 * param.isEmpty()) { res.put("info", "密码不能为空"); return res; }
		 * 
		 * TdUser user = tdUserService.findByPassword(param);
		 * 
		 * if (null == user) { res.put("info", "密码错误"); return res; } }
		 */

		res.put("status", "y");

		return res;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, String isremember, HttpServletRequest request,
			ModelMap map, HttpServletResponse response) {
		Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);

		TdUser user1 = tdUserService.findByUsername(username);
		if (user1.getStatusId() == 2L) {
			res.put("msg", "该用户已被禁用，请您咨询天涯相关人员处理。");
			return res;
		}
		if (!password.equals(user1.getPassword())) {
			res.put("msg", "密码错误");
			res.put("username", username);
			return res;
		}

		Date date = new Date();
		user1.setLastLoginTime(date);
		user1 = tdUserService.save(user1);
		request.getSession().setAttribute("username", username);

		res.put("code", 0);
		if (isremember != null && isremember.equals("yes")) {
			Cookie cookie = new Cookie("rem", username + ":" + password);
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		}
		return res;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, ModelMap map) {

		HttpSession session1 = request.getSession();
		session1.invalidate();
		tdCommonService.setCommon(map, request);

		return "redirect:/login";
	}

	/**
	 * @author lc
	 * @注释：微信登录通过code 获取 access_token,用户openid, refresh_token,
	 */
	public Map<String, String> getAccessToken(String code) {

		Map<String, String> res = new HashMap<String, String>();

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3b5c1c6427bb7e3e&secret=b404d403f5311657983c554a23b841a7&code="
				+ code + "&grant_type=authorization_code";
		String accessToken = null;
		String expiresIn = null;
		String refresh_token = null;
		String openid = null;
		try {

			URL urlGet = new URL(url);

			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求

			http.setRequestProperty("Content-Type",

					"application/x-www-form-urlencoded");

			http.setDoOutput(true);

			http.setDoInput(true);

			http.connect();

			InputStream is = http.getInputStream();

			int size = is.available();

			byte[] jsonBytes = new byte[size];

			is.read(jsonBytes);

			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);

			accessToken = demoJson.getString("access_token");
			expiresIn = demoJson.getString("expires_in");
			refresh_token = demoJson.getString("refresh_token");
			openid = demoJson.getString("openid");

			res.put("access_token", accessToken);
			res.put("expires_in", expiresIn);
			res.put("refresh_token", refresh_token);
			res.put("openid", openid);
			System.out.println("accessToken====" + accessToken);
			System.out.println("expiresIn===" + expiresIn);

			// System.out.println("====================获取token结束==============================");

			is.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @author lc
	 * @注释：通过accessToken 和 openId 拉取用户信息并存储
	 */
	public Map<String, String> getWxinfo(String accessToken, String openId) throws Exception {
		Map<String, String> res = new HashMap<String, String>();
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
				+ "&lang=zh_CN";
		String nickname = null;
		String headimgurl = null;
		String sex = null;
		String province = null;
		String city = null;

		URL urlGet = new URL(url);

		HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

		http.setRequestMethod("GET"); // 必须是get方式请求

		http.setRequestProperty("Content-Type",

				"application/x-www-form-urlencoded");

		http.setDoOutput(true);

		http.setDoInput(true);

		http.connect();

		InputStream is = http.getInputStream();

		int size = is.available();

		byte[] jsonBytes = new byte[size];

		is.read(jsonBytes);

		String message = new String(jsonBytes, "UTF-8");

		JSONObject demoJson = JSONObject.fromObject(message);

		nickname = demoJson.getString("nickname");
		res.put("nickname", nickname);
		headimgurl = demoJson.getString("headimgurl");
		res.put("headimgurl", headimgurl);
		sex = demoJson.getString("sex");
		res.put("sex", sex);
		province = demoJson.getString("province");
		res.put("province", province);
		city = demoJson.getString("city");
		res.put("city", city);
		is.close();
		return res;

	}

}
