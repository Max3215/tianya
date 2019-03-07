package com.ynyes.tianya.controller.front;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserTravelNotes;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdArticleCategoryService;
import com.ynyes.tianya.service.TdArticleService;
import com.ynyes.tianya.service.TdBrandService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdSiteLinkService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.service.TdUserTravelNotesService;

import net.sf.json.JSONObject;

/**
 * 前端首页控制
 *
 */
@Controller
@RequestMapping("/")
public class TdIndexController {

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdArticleService tdArticleService;

	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdSiteLinkService tdSiteLinkService;

	@Autowired
	private TdUserTravelNotesService tdUserTravelNotesService;

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;

	@Autowired
	private TdBrandService tdBrandService;
	
	@Autowired
	TdUserService tdUserService; 

	@RequestMapping
	public String index(HttpServletRequest req, Device device, ModelMap map,
			Integer page){
		tdCommonService.setCommon(map, req);

		if (null == page) {
			page = 0;
		}
		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("首页轮播广告");

		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页主题活动类别
		TdProductCategory tpc = tdProductCategoryService.findByTitle("主题活动");

		if (null != tpc) {
			map.addAttribute("index_product_category", tpc);
			map.addAttribute("activity_list", tdProductCategoryService
					.findByParentIdOrderBySortIdAsc(tpc.getId()));
		}

		// 首页婚礼蜜月版块上层横幅广告
		adType = tdAdTypeService.findByTitle("首页婚礼蜜月版块上层横幅广告");

		if (null != adType) {
			map.addAttribute("f1_up_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页婚礼蜜月版块右侧竖栏广告
		adType = tdAdTypeService.findByTitle("首页婚礼蜜月版块右侧竖栏广告");

		if (null != adType) {
			map.addAttribute("f1_right_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页婚礼蜜月版块下层左侧广告
		adType = tdAdTypeService.findByTitle("首页婚礼蜜月版块下层左侧广告");

		if (null != adType) {
			map.addAttribute("f1_down_left_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页婚礼蜜月版块下层右侧广告
		adType = tdAdTypeService.findByTitle("首页婚礼蜜月版块下层右侧广告");

		if (null != adType) {
			map.addAttribute("f1_down_right_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		// 首页甄选之旅版块上层横幅广告
		adType = tdAdTypeService.findByTitle("首页甄选之旅版块上层横幅广告");

		if (null != adType) {
			map.addAttribute("f2_up_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页甄选之旅版块右侧竖栏广告
		adType = tdAdTypeService.findByTitle("首页甄选之旅版块右侧竖栏广告");

		if (null != adType) {
			map.addAttribute("f2_right_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页甄选之旅版块下层左侧广告
		adType = tdAdTypeService.findByTitle("首页甄选之旅版块下层左侧广告");

		if (null != adType) {
			map.addAttribute("f2_down_left_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}

		// 首页甄选之旅版块下层右侧广告
		adType = tdAdTypeService.findByTitle("首页甄选之旅版块下层右侧广告");

		if (null != adType) {
			map.addAttribute("f2_down_right_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		// 首页4个限时特惠
		adType = tdAdTypeService.findByTitle("限时特惠");
		if (null != adType) {
			map.addAttribute("limit_time_product_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		// 首页最下面 攻略游记展示
		//Page<TdUserTravelNotes> tdUserTravelNotes = tdUserTravelNotesService
		//		.findByStatusIdOrderByIdDesc(1L, page, 4);
		Page<TdUserTravelNotes> tdUserTravelNotes = tdUserTravelNotesService.
				findByStatusIdOrderBySortIdASC
		(1L, page, 4);
		map.addAttribute("travel_notes_page", tdUserTravelNotes);

		// 特产商城
		TdProductCategory specialtyCat = tdProductCategoryService
				.findByTitle("特产商城");
		
		// 跳转特产商城需要 它的id才能跳转
		if (null != specialtyCat) {
			map.addAttribute("specialtyId", specialtyCat.getId());
		}
		return "/client/index";
//		map.addAttribute("openId", 22313);
//		map.addAttribute("qqName", "三少");
//		map.addAttribute("type", "qq");
//		
//		return "/client/accredit_login";
	}
	
	@RequestMapping("/qq_logon_return")
	public String afterQQLogin(HttpServletRequest req, Device device, ModelMap map,
			Integer page, String code, String state)  throws Exception  {
		tdCommonService.setCommon(map, req);

//			req.getSession().setAttribute("isQQLogin", "false");
			if(code != null && !code.equals("") && state != null && !state.equals("")){
				Map<String, String> aer = getQqAccessToken(code, state);
				String access_token = aer.get("access_token");
				String qqOpenid = null;
				String nickname = null;
				if(access_token == null || access_token.equals("")){
					System.err.println("获取access_token失败");
				}else{
					qqOpenid = getQqOpenid(access_token);
				}
				if(qqOpenid == null){
					System.err.println("获取qqOpenid失败");
				}else{
					Map<String, String> userInfos = getQqUserInfo(access_token, "101296269", qqOpenid);
					nickname = userInfos.get("nickname");
				}
				if(nickname == null){
					System.err.println("获取用户昵称失败");
				}
				//保存QQ登录用户
				if(qqOpenid != null){
					TdUser user = tdUserService.findByQqUserId(qqOpenid);
					//判断是否以前登陆过，登陆过则不添加用户
					if(null != user){
						req.getSession().setAttribute("username", user.getUsername());
						return "redirect:/";
					}else{
						map.addAttribute("openId", qqOpenid);
						map.addAttribute("qqName", nickname);
						map.addAttribute("type", "qq");
						
						return "/client/accredit_login";
					}
				}
			}else{
				System.err.println("获取code或state失败!");
			}
		
		//--------------------/李冲--------------------------------
		return "/client/index";
	}
	
	//获取access_token, expires_in, refresh_token
	/**
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 李冲
	 */
	private Map<String, String> getQqAccessToken(String code, String state) throws UnsupportedEncodingException{
		Map<String, String> res = new HashMap<String, String>();
		if(null == state || state.equals("STATE")){
			return res;
		}
		
		String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="
									+"101296269"+"&client_secret="+"0a7bb5cf8ebdd4113b99e35bafcc9f38"
									+"&code="+code
									+"&redirect_uri="+URLEncoder.encode("http://www.tyjourney.com/qq_logon_return","utf-8");
		

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
            StringBuffer sb = new StringBuffer();
            InputStreamReader isreader = null;
            try {
                isreader = new InputStreamReader(is, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(isreader);
            String temp = null;
  
                while ((temp = bufferedReader.readLine()) != null) {
                    sb.append(temp);
                }
                bufferedReader.close();
                isreader.close();
                is.close();
                is = null;

            String token =  sb.toString();
            System.out.println("0314---------GET TOKEN :" + token);
            String[] strs = token.split("=");
    	    res.put("access_token", strs[1].substring(0, strs[1].indexOf("&")));
    	    res.put("expires_in", strs[2].substring(0, strs[2].indexOf("&")));
    	    res.put("refresh_token", strs[3]);
        } catch (Exception e) {  
            e.printStackTrace(); 
        }  
		return res;
	}
	
	//获取用户的openid
	/**
	 * 
	 * @param access_token
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 李冲
	 */
	private String getQqOpenid(String access_token) throws UnsupportedEncodingException{
		String client_id = null;
		String qqOpenid = null;
		String url = "https://graph.qq.com/oauth2.0/me?access_token="+access_token;

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
            String json = message.substring(message.indexOf("(")+1, message.indexOf(")"));
            System.out.println("获取openid-----------JSON："+json);
            JSONObject demoJson = JSONObject.fromObject(json);  
            qqOpenid = demoJson.getString("openid");  
            is.close();  

        } catch (Exception e) {  
            e.printStackTrace(); 
        }  
		return qqOpenid;
	}
	
	//获取用户信息
	/**
	 * 
	 * @param access_token
	 * @param oauth_consumer_key appID
	 * @param openid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 李冲
	 */
	private Map<String, String> getQqUserInfo(String access_token, String oauth_consumer_key, String openid) throws UnsupportedEncodingException{
		Map<String, String> userInfo = new HashMap<String, String>();
		
		
		String url = "https://graph.qq.com/user/get_user_info?access_token="+access_token+"&oauth_consumer_key="+oauth_consumer_key+"&openid="+openid;
		String msg = null;
		String nickname = null;
		String figureurl_qq_1 = null;
		String gender = null;

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
            System.out.println("0314-------MESSAGE:"+message);
            JSONObject demoJson = JSONObject.fromObject(message);  
            
            msg = demoJson.getString("msg");  
            nickname = demoJson.getString("nickname");  
            figureurl_qq_1 = demoJson.getString("figureurl_qq_1");
            gender = demoJson.getString("gender");
            
            userInfo.put("msg", msg);
            userInfo.put("nickname", nickname);
            userInfo.put("figureurl_qq_1", figureurl_qq_1);
            userInfo.put("gender", gender);
            is.close();  

        } catch (Exception e) {  
            e.printStackTrace(); 
        }  
		return userInfo;
	}
	
}
