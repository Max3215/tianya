package com.ynyes.tianya.controller.front;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emay.channel.httpclient.SDKHttpClient;
import com.ynyes.tianya.entity.TdArticle;
import com.ynyes.tianya.entity.TdArticleCategory;
import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdOrderGoods;
import com.ynyes.tianya.entity.TdOrderVisitor;
import com.ynyes.tianya.entity.TdShippingAddress;
import com.ynyes.tianya.entity.TdTravelPraise;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserCollect;
import com.ynyes.tianya.entity.TdUserComment;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.entity.TdUserPoint;
import com.ynyes.tianya.entity.TdUserTravelComment;
import com.ynyes.tianya.entity.TdUserTravelNotes;
import com.ynyes.tianya.entity.TdUserVisitor;
import com.ynyes.tianya.service.TdArticleCategoryService;
import com.ynyes.tianya.service.TdArticleService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdDemandService;
import com.ynyes.tianya.service.TdDiySiteService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdOrderGoodsService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdOrderVisitorService;
import com.ynyes.tianya.service.TdShippingAddressService;
import com.ynyes.tianya.service.TdTravelPraiseService;
import com.ynyes.tianya.service.TdUserCashRewardService;
import com.ynyes.tianya.service.TdUserCollectService;
import com.ynyes.tianya.service.TdUserCommentService;
import com.ynyes.tianya.service.TdUserConsultService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserPointService;
import com.ynyes.tianya.service.TdUserRecentVisitService;
import com.ynyes.tianya.service.TdUserReturnService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.service.TdUserTravelCommentService;
import com.ynyes.tianya.service.TdUserTravelNotesService;
import com.ynyes.tianya.service.TdUserVisitorService;
import com.ynyes.tianya.util.ClientConstant;
import com.ynyes.tianya.util.VerifServlet;






/**
 * 用户中心
 * 
 * @author lulu
 *
 */
@Controller
public class TdUserController extends AbstractPaytypeController {
	@Autowired
	private TdUserTravelCommentService tdUserTravelCommentService;
	
	@Autowired
	private TdUserTravelNotesService tdUserTravelNotesService;
	
	@Autowired
	private TdUserVisitorService tdUserVisitorService;
	
	@Autowired
    private TdUserCashRewardService tdUserCashRewardService;
	
	@Autowired
    private TdArticleCategoryService tdArticleCategoryService;
	
	@Autowired
    private TdArticleService tdArticleService;
	
    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdTravelPraiseService tdTravelPraiseService;

    @Autowired
    private TdUserReturnService tdUserReturnService;

    @Autowired
    private TdOrderService tdOrderService;

    @Autowired
    private TdUserPointService tdUserPointService;

    @Autowired
    private TdCouponService tdCouponService;
    
    @Autowired
    private TdUserCollectService tdUserCollectService;

    @Autowired
    private TdUserConsultService tdUserConsultService;

    @Autowired
    private TdUserCommentService tdUserCommentService;

    @Autowired
    private TdDiySiteService tdDiySiteService;
    
    @Autowired
    private TdOrderVisitorService tdOrderVisitorService;
    
    @Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;

    @Autowired
    private TdShippingAddressService tdShippingAddressService;

    @Autowired
    private TdOrderGoodsService tdOrderGoodsService;
    
    @Autowired
    private TdUserLevelService tdUserLevelService;


    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdDemandService tdDemandService;
    
    
    
    @RequestMapping(value="/user/forgetPassword", method = RequestMethod.GET)
    public String forgetPassword(HttpServletRequest request, ModelMap map){
    	
    	// 基本信息
    	tdCommonService.setCommon(map, request);
    	return "/client/forget_password";
    }
    
    // 用户判断私家定制时是否已登录
    @RequestMapping(value="/user/isPrivateLogin", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> isPrivateLogin(HttpServletRequest request){
    	Map<String, String> info = new HashMap<>();
    	info.put("isPrivateLogin", "no");
    	String username = (String)request.getSession().getAttribute("username");
    	if(username != null && !username.equals("")){
    		info.put("isPrivateLogin", "yes");
    	}
    	return info;
    }
    
    @RequestMapping(value="/user/getMobileValidCode", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getMobileValidCode(String username, String mobile, HttpServletRequest request){
    	Map<String, String> info = new HashMap<>(); 
    	TdUser tu = tdUserService.findByUsernameAndMobile(username, mobile);
    	if(tu != null){
    		info.put("status", "ok");
    		//产生四位随机数
    		Random random = new Random();
    		String smscode = String.format("%04d", random.nextInt(9999));
    		request.getSession().setAttribute("SMSCODE", smscode);
    		//设置重设密码的用户Id
    		request.getSession().setAttribute("user_id", tu.getId());
    		//发送短信验证码
    		
    		String sn = "8SDK-EMY-6699-RFTRL";// 软件序列号,请通过亿美销售人员获取
    		String password = "559770";// 密码,请通过亿美销售人员获取
    		String key = "123456";// 序列号首次激活时自己设定
    		String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
    		
    		try {
    			String message = "【天涯国旅】您的验证码为："+smscode+"，请输入找回密码。";
    			message = URLEncoder.encode(message, "UTF-8");
    			String code = "888";
    			long seqId = System.currentTimeMillis();
    			String param = "cdkey=" + sn + "&password=" 
    						+ password + "&phone=" + mobile + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
    			String url = baseUrl + "sendsms.action";
    			//SDKHttpClient.sendSMS(url, param);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else{
    		info.put("status", "no");
    		info.put("data", "手机号与用户名不匹配!");
    	}
    	return info;
    }
    
    @RequestMapping(value="/user/resetPassword", method=RequestMethod.GET)
    public String resetPassword(ModelMap map, HttpServletRequest request){
    	tdCommonService.setCommon(map, request);
    	// 基本信息
    	tdCommonService.setCommon(map, request);
    	return "/client/reset_password";
    }
    
    @RequestMapping(value="/user/resetPassword2", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> resetPassword2(String username, String mobile, String mobileValideCode, HttpServletRequest request){
    	Map<String,Object> res = new HashMap<>();
    	res.put("code", 0);
    	TdUser tu = tdUserService.findByUsernameAndMobile(username, mobile);
    	if(tu == null){
    		res.put("msg", "用户名与手机号不匹配");
    		return res;
    	}
    	String smscode = (String)request.getSession().getAttribute("SMSCODE");
    	if(!smscode.equals(mobileValideCode)){
    		res.put("msg", "手机验证码错误");
    		return res;
    	}
    	request.getSession().setAttribute("user_id", tu.getId());
    	res.put("code", 1);
    	return res;
    	
    	
    }
    
    @RequestMapping(value="/user/saveResetPassword", method=RequestMethod.POST)
    public String saveRestPassword(String password, HttpServletRequest request){
    	if(request.getSession().getAttribute("user_id") == null){
    		return "redirect:/login";
    	}else{
    		TdUser tu = tdUserService.findOne(Long.parseLong(request.getSession().getAttribute("user_id").toString()));
    		tu.setPassword(password);
    		tdUserService.save(tu);
    		request.getSession().removeAttribute("user_id");
    		request.getSession().setAttribute("username", tu.getUsername());
    		return "redirect:/user/0 ";
    	}
    }

    @RequestMapping(value = "/user/{type}")
    public String user(@PathVariable Long type,HttpServletRequest req, ModelMap map, Integer page) {
        String username = (String) req.getSession().getAttribute("username");
        /*
        Boolean isQQLogin = Boolean.parseBoolean((String)req.getSession().getAttribute("isQQLogin"));
        if (null == username || (isQQLogin != null && isQQLogin)) {
            return "redirect:/login";
        }
        */
        if(null == username){
        	return "redirect:/login";
        }
        
        
        //设置网站基本信息
        tdCommonService.setCommon(map, req);
        map.addAttribute("server_ip", req.getLocalName());
        map.addAttribute("server_port", req.getLocalPort());
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        if (null == tdUser) {
        	return "/client/error_404";
        }

        TdUserLevel tul = tdUserLevelService.findOne(tdUser.getUserLevelId());
        
//        List<TdUserLevel> tulList =  tdUserLevelService.findAll();
//        Long maxId = -1L;
//        Long maxPoint;
//        if(tdUser.getTotalPoints() == null){
//        	maxPoint = 0L;
//        }else{
//        	maxPoint = tdUser.getTotalPoints();
//        }
//        
//        for(TdUserLevel t : tulList){
//        	if(t.getRequiredPoints() < maxPoint){
//        		//maxPoint = t.getRequiredPoints();
//        		maxId = t.getId();
//        	}
//        }
//        if(maxId > -1){
//        	tdUser.setUserLevelTitle(tdUserLevelService.findOne(maxId).getTitle());
//        }
        if(tdUserLevelService.findOne(tdUser.getUserLevelId()) != null && !tdUserLevelService.findOne(tdUser.getUserLevelId()).equals("")){
        	tdUser.setUserLevelTitle(tdUserLevelService.findOne(tdUser.getUserLevelId()).getTitle());
        }else{
        	tdUser.setUserLevelTitle("无");
        }
        //tdUser.setUserLevelTitle(userLevelTitle);
       
        if (null == page)
        {
            page = 0;
        }
        map.addAttribute("user", tdUser);
       // map.addAttribute("Comment_count",tdUserCommentService.countByUsername(username));
        
        if(null == type  || type==0){
        	Page<TdOrder> orderPage = tdOrderService.findByUsername(username ,page, ClientConstant.pageSize);
            map.addAttribute("order_page", orderPage);
            map.addAttribute("total_unpayed",
                    tdOrderService.countByUsernameAndStatusId(username, 2));
            map.addAttribute("total_unreceived",
                    tdOrderService.countByUsernameAndStatusId(username, 4));
            map.addAttribute("code", type);
            return "/client/user_index";
        }
        if(type==2){
        	Page<TdOrder> orderPage = tdOrderService.findByUsernameAndStatusId(username,2L ,page, ClientConstant.pageSize);
            map.addAttribute("order_page", orderPage);
            map.addAttribute("total_unpayed",
                    tdOrderService.countByUsernameAndStatusId(username, 2));
            map.addAttribute("total_unreceived",
                    tdOrderService.countByUsernameAndStatusId(username, 4));
            map.addAttribute("code", type);
            return "/client/user_index";
        }
        if(type==4){
        	Page<TdOrder> orderPage = tdOrderService.findByUsernameAndStatusId(username,4L ,page, ClientConstant.pageSize);
            map.addAttribute("order_page", orderPage);
            map.addAttribute("total_unpayed",
                    tdOrderService.countByUsernameAndStatusId(username, 2));
            map.addAttribute("total_unreceived",
                    tdOrderService.countByUsernameAndStatusId(username, 4));
            map.addAttribute("code", type);
            return "/client/user_index";
        }
        
        map.addAttribute("total_unpayed",
                tdOrderService.countByUsernameAndStatusId(username, 2));
        map.addAttribute("total_unreceived",
                tdOrderService.countByUsernameAndStatusId(username, 4));
        
        return "/client/user_index";
    }
    
  //跳转修改资料页面
    @RequestMapping(value = "/user/information/edit", method = RequestMethod.GET)
    public String userInformationEdit(HttpServletRequest req,ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        
        TdUser user = tdUserService.findByUsername(username);
        map.addAttribute("user", user);
    
        return "/client/user_information_edit";
    }
    
    //资料修改保存
    @RequestMapping(value = "/user/information/save", method = RequestMethod.POST)
    public String userInformationSave(HttpServletRequest req,ModelMap map,String realName,String sex,String qq,String birthday) throws ParseException {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        
        TdUser user = tdUserService.findByUsername(username);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        if(birthday != null && !birthday.equals("")){
        	Date d=sim.parse(birthday);
        	user.setBirthday(d);
        }
    	
        user.setRealName(realName);
        user.setSex(sex);
        user.setQq(qq);
        tdUserService.save(user);
    
        return "redirect:/user/0";
    }
    
   
    
    //用户中心首页订单删除
    @RequestMapping(value = "/index/order/delete/{type}")
	public String indexOrderDelete(HttpServletRequest req, ModelMap map,Long id,@PathVariable Long type) {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        tdOrderService.delete(id);
        
        return "redirect:/user/"+type;
    }
    
    //修改密码
    @RequestMapping(value = "/user/password", method = RequestMethod.GET)
    public String userPassword(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setCommon(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_change_password";
    }
    
    @RequestMapping(value = "/user/password/success", method = RequestMethod.GET)
    public String userPasswordSuccess(HttpServletRequest req, ModelMap map) {
    	HttpSession session1 = req.getSession(); 
    	session1.invalidate();
    	tdCommonService.setCommon(map, req);
        return "/client/login";
    }
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword,
            String newPassword, ModelMap map) {
    	Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
        }

        map.addAttribute("user", tdUserService.save(user));

        res.put("code", 0);
        return res;
    }
    /**
     * 
     * 我的点评
     */
    @RequestMapping(value="/user/my_comment")
    public String my_comments(HttpServletRequest req,ModelMap map,Integer page){
    	String username = (String) req.getSession().getAttribute("username");
    	if (null == username) {
            return "redirect:/login";
        }
    	if(null==page){
    		page=0;
    	}
    	tdCommonService.setCommon(map, req);
    	//TdUserComment userComment=new TdUserComment();
    	Page<TdUserTravelComment> list=tdUserTravelCommentService.findByUsername(username,page,10);
    	
    	map.addAttribute("comment_page", list);
    	return "/client/my_comments";
    }
    /**
     * 删除点评
     */
    @RequestMapping(value="/user/delete_comment")
    public String delete_comment(Long id){
    	tdUserTravelCommentService.delete(id);
    	return "redirect:/user/my_comment";
    }
    
    @RequestMapping(value = "/user/check/mobileValidecode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> mobileValidecodeCheck(HttpServletRequest req, String mobile){
//    	Map<String, String> backInfo = new HashMap<String, String>();
//    	String codeBack = (String) request.getSession().getAttribute("SMSCODE");
//    	
//    	if (!codeBack.equalsIgnoreCase(mobile))
//    	{
//    		backInfo.put("info", "验证码错误");
//    		backInfo.put("code", "0");
//            return backInfo;
//    	}else{
//    		backInfo.put("code", "1");
//    	}
//    	return backInfo;
    	
    	
    	Map<String,Object> res = new HashMap<>();
    	res.put("code", "0");
    	Random random = new Random();
        
        String smscode = String.format("%04d", random.nextInt(9999));
        req.getSession().setAttribute("SMSCODE", smscode);
        
        String sn = "8SDK-EMY-6699-RFTRL";// 软件序列号,请通过亿美销售人员获取
		String password = "559770";// 密码,请通过亿美销售人员获取
		String key = "123456";// 序列号首次激活时自己设定
		//String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
		String baseUrl  =  "http://219.239.91.114:8080/sdkproxy/";
		
		try {
			String message = "【天涯国旅】您的验证码为："+smscode+"，请输入找回密码。";
			message = URLEncoder.encode(message, "UTF-8");
			String code = "888";
			long seqId = System.currentTimeMillis();
			String param = "cdkey=" + sn + "&password=" 
						+ password + "&phone=" + mobile + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId + "&smspriority=5";
			String url = baseUrl + "sendsms.action";
			String isSuccess =  SDKHttpClient.sendSMS(url, param);
			System.out.println(isSuccess);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        res.put("code", "1");
        return res;
    }
    
    @RequestMapping(value = "/user/check/{type}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(@PathVariable String type, String param,HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();

        res.put("status", "n");
        
        if (null == type)
        {
        	res.put("info", "参数错误");
            return res;
        }
        
        
        if (type.equalsIgnoreCase("code"))
        {
        	if (null == param || param.isEmpty()) {
                res.put("info", "验证不能空");
                return res;
            }
        	
        	String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        	
        	if (!codeBack.equalsIgnoreCase(param))
        	{
        		res.put("info", "验证码错误");
                return res;
        	}
        }
        
        res.put("status", "y");

        return res;
    }
    
    @RequestMapping(value = "/user/visitor", method = RequestMethod.GET)
    public String visitor(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setCommon(map, req);
        
        List<TdUserVisitor> visitorUserList = tdUserVisitorService.findByUsername(username);

        map.addAttribute("visitor_user_list", visitorUserList);

        return "/client/user_visitor";
    }
    //修改跳转
    @RequestMapping(value = "/user/visitor/update", method = RequestMethod.GET)
    public String visitorUpdate(HttpServletRequest req, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setCommon(map, req);
        
        List<TdUserVisitor> visitorUserList = tdUserVisitorService.findByUsername(username);

        map.addAttribute("visitor_user_list", visitorUserList);
        
        TdUserVisitor tdUserVisitor = tdUserVisitorService.findById(id);
        map.addAttribute("tdUserVisitor", tdUserVisitor);

        return "/client/user_visitor";
    }
    
    
    //联系人增加和修改
    @RequestMapping(value = "/user/visitor/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> visitorAdd(HttpServletRequest req, ModelMap map,Long id, TdUserVisitor tdUserVisitor) {
    	Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 0);
        String username = (String) req.getSession().getAttribute("username");
        
        if(null!=id){
        	TdUserVisitor tdUserVisitor2 = tdUserVisitorService.findById(id);
        	tdUserVisitor2.setCertificateCardCode(tdUserVisitor.getCertificateCardCode());
        	tdUserVisitor2.setCertificateType(tdUserVisitor.getCertificateType());
        	tdUserVisitor2.setSex(tdUserVisitor.getSex());
        	tdUserVisitor2.setTelephone(tdUserVisitor.getTelephone());
        	tdUserVisitor2.setVisitorName(tdUserVisitor.getVisitorName());
        	tdUserVisitorService.save(tdUserVisitor2);
        	res.put("code", 1);
            res.put("msg", "修改成功！");
            
            return res;
        }
        
        
        tdUserVisitor.setUsername(username);
        
        tdUserVisitorService.save(tdUserVisitor);
        res.put("code", 1);
        res.put("msg", "保存成功！");
        
        return res;
    }
    //联系人删除
    @RequestMapping(value = "/user/visitor/delete")
    public String visitorDelete(HttpServletRequest req, ModelMap map, Long id) {
    	tdUserVisitorService.delete(id);
    	
    	return "redirect:/user/visitor";
    }
    @RequestMapping(value = "/user/save/address")
    public String saveAddress(HttpServletRequest req, ModelMap map) {
    	String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
    	return "/client/user_address_edit";
    }
    
    @RequestMapping(value = "/address/save", method = RequestMethod.POST)
    public String addressSubmit(HttpServletRequest req,ModelMap map,TdShippingAddress tdShippingAddress,Long goodsId){
    	String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        TdUser user = tdUserService.findByUsername(username);
        
        if(null!=tdShippingAddress.getIsDefaultAddress()&&tdShippingAddress.getIsDefaultAddress()==true){
        	List<TdShippingAddress> tdShippingAddressList=tdShippingAddressService.findAll();
        	for(TdShippingAddress sa : tdShippingAddressList){
        		if(sa.getIsDefaultAddress()==true){
        			sa.setIsDefaultAddress(false);
        			tdShippingAddressService.save(sa);
        		}
        	}
        	TdShippingAddress shippingAddress = new TdShippingAddress();
        	shippingAddress.setDetailAddress(tdShippingAddress.getDetailAddress());
        	shippingAddress.setIsDefaultAddress(true);
        	shippingAddress.setProvince(tdShippingAddress.getProvince());
        	shippingAddress.setReceiverMobile(tdShippingAddress.getReceiverMobile());
        	shippingAddress.setReceiverName(tdShippingAddress.getReceiverName());
        	shippingAddress.setUserId(user.getId());
        	tdShippingAddressService.save(shippingAddress);
        }
        
        if(tdShippingAddress.getIsDefaultAddress()==null){
        	TdShippingAddress shippingAddress = new TdShippingAddress();
        	shippingAddress.setDetailAddress(tdShippingAddress.getDetailAddress());
        	shippingAddress.setIsDefaultAddress(false);
        	shippingAddress.setProvince(tdShippingAddress.getProvince());
        	shippingAddress.setReceiverMobile(tdShippingAddress.getReceiverMobile());
        	shippingAddress.setReceiverName(tdShippingAddress.getReceiverName());
        	shippingAddress.setUserId(user.getId());
        	tdShippingAddressService.save(shippingAddress);
        }
    	
    	return "redirect:/order/specialty?id="+goodsId;
    }
    
    
    @RequestMapping(value = "/user/address/{method}")
    public String address(HttpServletRequest req, @PathVariable String method,
            Long id, TdShippingAddress tdShippingAddress, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setCommon(map, req);

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        if (null != user) {
            List<TdShippingAddress> addressList = user.getShippingAddressList();

            if (null != method && !method.isEmpty()) {
                if (method.equalsIgnoreCase("edit")) {
                    if (null != id) {
                        // map.addAttribute("address", s)
                        for (TdShippingAddress add : addressList) {
                            if (add.getId().equals(id)) {
                                map.addAttribute("address", add);
                                return "/client/user_address_edit";
                            }
                        }
                    }
                } else if (method.equalsIgnoreCase("delete")) {
                    if (null != id) {
                        for (TdShippingAddress add : addressList) {
                            if (add.getId().equals(id)) {
                                addressList.remove(id);
                                user.setShippingAddressList(addressList);
                                tdShippingAddressService.delete(add);
                                return "redirect:/user/address/list";
                            }
                        }
                    }
                } else if (method.equalsIgnoreCase("save")) {
                	// 修改
                    if (null != tdShippingAddress.getId()) {
                    	TdShippingAddress shippingAddress=tdShippingAddressService.findById(tdShippingAddress.getId());
                    	if(shippingAddress.getIsDefaultAddress()==true){
                    		tdShippingAddress.setUserId(user.getId());
                    		tdShippingAddress.setIsDefaultAddress(true);
                    		tdShippingAddressService.save(tdShippingAddress);
                    	}else{
	                    	tdShippingAddress.setUserId(user.getId());
	                    	tdShippingAddress.setIsDefaultAddress(false);
	                        tdShippingAddressService.save(tdShippingAddress);
                    	}
                    }
                    // 新增
                    else {
                    	TdShippingAddress address1 = new TdShippingAddress();
                    	address1.setIsDefaultAddress(false);
                    	address1.setDetailAddress(tdShippingAddress.getDetailAddress());
                    	address1.setProvince(tdShippingAddress.getProvince());
                    	address1.setReceiverName(tdShippingAddress.getReceiverName());
                    	address1.setReceiverMobile(tdShippingAddress.getReceiverMobile());
                    	address1.setUserId(user.getId());
                    	tdShippingAddressService.save(address1);
                    }
                    return "redirect:/user/address/list";
                }else if(method.equalsIgnoreCase("default")){
                	if(null != id){
                		for (TdShippingAddress address : addressList) {
							if(address.getId().equals(id)){
								address.setIsDefaultAddress(true);
								address.setUserId(user.getId());
								tdShippingAddressService.save(address);
							}else{
								address.setIsDefaultAddress(false);
								address.setUserId(user.getId());
								tdShippingAddressService.save(address);
							}
						}
                	}
                	
                	return "redirect:/user/address/list";
                }else if(method.equalsIgnoreCase("list")){
                	TdShippingAddress defaultAddress = tdShippingAddressService.findByUserIdAndIsDefaultAddressTrue(user.getId());
                	map.addAttribute("defaultAddress", defaultAddress);
                	List<TdShippingAddress> list = tdShippingAddressService.findByUserIdAndIsDefaultAddressFalse(user.getId());
                    map.addAttribute("address_list", list);
                }
            }
            
        }

        return "/client/user_address_list";
    }
    
    //跳转旅行感受
    @RequestMapping(value = "/user/travel/notes", method = RequestMethod.GET)
    public String travelNotes(HttpServletRequest req, ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        if(null==type){
        	type=0L;
        }
        tdCommonService.setCommon(map, req);
        Page<TdUserTravelNotes> tdUserTravelNotesList = tdUserTravelNotesService.findByUsername(username,type,page,5);
        
        map.addAttribute("user_travel_notes_page", tdUserTravelNotesList);

        return "/client/user_travel_notes";
    }
    
    //跳转旅行攻略
    @RequestMapping(value = "/user/travel/notes2", method = RequestMethod.GET)
    public String strategyNotes(HttpServletRequest req, ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        if(null== type){
        	type=1L;
        }
        
        tdCommonService.setCommon(map, req);
        Page<TdUserTravelNotes> tdUserStrategyNotesList = tdUserTravelNotesService.findByUsername(username,type,page,5);
        
        map.addAttribute("user_strategy_notes_page", tdUserStrategyNotesList);

        return "/client/user_strategy_notes";
    }
    
    //旅行图片
    @RequestMapping(value = "/user/travel/notes3", method = RequestMethod.GET)
    public String pictureNotes(HttpServletRequest req, ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        if(null== type){
        	type=2L;
        }
        
        tdCommonService.setCommon(map, req);
        Page<TdUserTravelNotes> tdUserPictureNotesList = tdUserTravelNotesService.findByUsername(username,type,page,5);
        
        map.addAttribute("user_picture_notes_page", tdUserPictureNotesList);

        return "/client/user_picture_notes";
    }
    
  //旅行图片
    @RequestMapping(value = "/user/travel/notes4", method = RequestMethod.GET)
    public String recommendNotes(HttpServletRequest req, ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        if(null== type){
        	type=3L;
        }
        
        tdCommonService.setCommon(map, req);
        Page<TdUserTravelNotes> tdUserRecommendNotesList = tdUserTravelNotesService.findByUsername(username,type,page,5);
        
        map.addAttribute("user_recommend_notes_page", tdUserRecommendNotesList);

        return "/client/user_recommend_notes";
    }
    
    
    
    //游记删除
    @RequestMapping(value = "/user/notes/delete", method = RequestMethod.GET)
    public String notesDelete(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        Long commentId=id;
        //先删除日志关联的评论
        	//由Id得到相关的所有的评论
        List<TdUserTravelComment> travelComments = tdUserTravelCommentService.findByCommentIdAndTypeId(commentId,0L);
        	//遍历删除评论
        for(TdUserTravelComment comment : travelComments){
        	tdUserTravelCommentService.delete(comment.getId());
        }
        //删除这篇日志
        tdUserTravelNotesService.delete(id);
        return "redirect:/user/travel/notes";
    }
    //攻略删除
    @RequestMapping(value = "/user/notes2/delete", method = RequestMethod.GET)
    public String notes2Delete(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        Long commentId=id;
        //先删除攻略关联的评论
        	//由Id得到相关的所有的评论
        List<TdUserTravelComment> travelComments = tdUserTravelCommentService.findByCommentIdAndTypeId(commentId,1L);
        	//遍历删除评论
        for(TdUserTravelComment comment : travelComments){
        	tdUserTravelCommentService.delete(comment.getId());
        }
        //删除这篇日志
        tdUserTravelNotesService.delete(id);
        return "redirect:/user/travel/notes2";
    }
    
    //gl 攻略游记查询
    @RequestMapping(value = "/user/notes/search", method = RequestMethod.POST)
    public String notesSearch(HttpServletRequest req, String title, String classify,ModelMap map,Integer page, Long cate) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if(null==page){
        	page=0;
        }
       
        tdCommonService.setCommon(map, req);
        
        map.addAttribute("title", title);
        map.addAttribute("classify", classify);
        
        Page<TdUserTravelNotes> tdUserTravelNotesList;
        if(title.equals("") && classify.equals("")){
        	tdUserTravelNotesList = tdUserTravelNotesService.findByUsername(username,cate,page,5);
        }else if(!title.equals("") && classify.equals("")){
        	tdUserTravelNotesList = tdUserTravelNotesService.findByUsernameAndTypeAndTitleLike(username, cate, title, page, 5);
        }else if(title.equals("") && !classify.equals("")){
        	tdUserTravelNotesList = tdUserTravelNotesService.findByUsernameAndTypeAndClassifyLike(username, cate, classify, page, 5);
        }else{
        	tdUserTravelNotesList = tdUserTravelNotesService.findByUsernameAndTypeAndTitleLikeAndClassifyLike(username, cate, title, classify, page, 5);
        }
        String c = cate.toString();
        switch(c){
	        case "0":
	        	map.addAttribute("user_travel_notes_page", tdUserTravelNotesList);
	            return "/client/user_travel_notes";
	        case "1":
	        	map.addAttribute("user_strategy_notes_page", tdUserTravelNotesList);
	            return "/client/user_strategy_notes";
	        case "2":
	        	map.addAttribute("user_picture_notes_page", tdUserTravelNotesList);
	            return "/client/user_picture_notes";
	        default:
	        	map.addAttribute("user_recommend_notes_page", tdUserTravelNotesList);
	            return "/client/user_recommend_notes";
        }
    }
    
    
    //游记查询
    //@RequestMapping(value = "/user/notes/search", method = RequestMethod.POST)
    public String userInfo(HttpServletRequest req, String title, String classify,ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if(null==page){
        	page=0;
        }
        if(null==type){
        	type=0L;
        }
        //第一种情况
        if(null==title||title.trim().equals("")){
        	if(null==classify||classify.trim().equals("")){
        		return "redirect:/user/travel/notes";
        	}
        	if(null!=classify||!classify.trim().equals("")){
        		 Page<TdUserTravelNotes> tdUserTravelNotesList = tdUserTravelNotesService.findByClassify(classify,type,page,5);
        		 map.addAttribute("classify", classify);
        		 map.addAttribute("user_travel_notes_page", tdUserTravelNotesList);
        		
        	}
        }
        //第二种情况
        if(null!=title&&!title.trim().equals("")){
        	if(null==classify||classify.trim().equals("")){
        		Page<TdUserTravelNotes> tdUserTravelNotesList = tdUserTravelNotesService.findByTitle(title,type,page,5);
        		map.addAttribute("title", title);
       		 	map.addAttribute("user_travel_notes_page", tdUserTravelNotesList);
        	}
        	if(null!=classify||!classify.trim().equals("")){
        		Page<TdUserTravelNotes> tdUserTravelNotesList = tdUserTravelNotesService.findByTitleAndClassify(title,classify,type,page,5);
        		map.addAttribute("title", title);
        		map.addAttribute("classify", classify);
       		 	map.addAttribute("user_travel_notes_page", tdUserTravelNotesList);
        	}
        }

        return "/client/user_travel_notes";
    }
    
  //攻略查询
    @RequestMapping(value = "/user/notes2/search", method = RequestMethod.POST)
    public String userInfo2(HttpServletRequest req, String title, String classify,ModelMap map,Integer page,Long type) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if(null==page){
        	page=0;
        }
        
        if(null==type){
        	type=1L;
        }
        //第一种情况
        if(null==title||title.trim().equals("")){
        	if(null==classify||classify.trim().equals("")){
        		return "redirect:/user/travel/notes2";
        	}
        	if(null!=classify||!classify.trim().equals("")){
        		 Page<TdUserTravelNotes> tdUserStategyNotesList = tdUserTravelNotesService.findByClassify(classify,type,page,5);
        		 map.addAttribute("classify", classify);
        		 map.addAttribute("user_strategy_notes_page", tdUserStategyNotesList);
        		
        	}
        }
        //第二种情况
        if(null!=title&&!title.trim().equals("")){
        	if(null==classify||classify.trim().equals("")){
        		Page<TdUserTravelNotes> tdUserStrategyNotesList = tdUserTravelNotesService.findByTitle(title,type,page,5);
        		map.addAttribute("title", title);
       		 	map.addAttribute("user_strategy_notes_page", tdUserStrategyNotesList);
        	}
        	if(null!=classify||!classify.trim().equals("")){
        		Page<TdUserTravelNotes> tdUserStrategyNotesList = tdUserTravelNotesService.findByTitleAndClassify(title,classify,type,page,5);
        		map.addAttribute("title", title);
        		map.addAttribute("classify", classify);
       		 	map.addAttribute("user_strategy_notes_page", tdUserStrategyNotesList);
        	}
        }

        return "/client/user_strategy_notes";
    }
    
    //跳转发表游记页面
    @RequestMapping(value = "/user/travel/write", method = RequestMethod.GET)
    public String userTravelWrite(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        tdCommonService.setCommon(map, req);

        if(null!=id){
        	TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
        	map.addAttribute("tdUserTravelNotes", tdUserTravelNotes);
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        

        return "/client/user_travel_write";
    }
    

    //跳转发表攻略页面
    @RequestMapping(value = "/user/travel/write2", method = RequestMethod.GET)
    public String userStrategyWrite(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        
        if(null!=id){
        	TdUserTravelNotes tdUserStrategyNotes = tdUserTravelNotesService.findById(id);
        	map.addAttribute("tdUserStrategyNotes", tdUserStrategyNotes);
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_strategy_write";
    }
    
  //跳转发表旅行图片
    @RequestMapping(value = "/user/travel/write3", method = RequestMethod.GET)
    public String userPictureWrite(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        
        if(null!=id){
        	TdUserTravelNotes tdUserPictureNotes = tdUserTravelNotesService.findById(id);
        	map.addAttribute("tdUserPictureNotes", tdUserPictureNotes);
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_picture_write";
    }
    
  //跳转发表旅行推荐
    @RequestMapping(value = "/user/travel/write4", method = RequestMethod.GET)
    public String userRecommendWrite(HttpServletRequest req, ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        
        if(null!=id){
        	TdUserTravelNotes tdRecommendPictureNotes = tdUserTravelNotesService.findById(id);
        	map.addAttribute("tdRecommendPictureNotes", tdRecommendPictureNotes);
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);

        return "/client/user_recommend_write";
    }
    
    
    
    //跳转到攻略游记列表页面
    @RequestMapping(value = "/user/travel/list", method = RequestMethod.GET)
    public String userTravelList(HttpServletRequest req, ModelMap map,Integer page1, Integer page2, Integer page3,String keyword, Long cate) {
    	tdCommonService.setCommon(map, req);
    	
    	if(null==page1){
    		page1=0;
    	}
    	if(null==page2){
    		page2=0;
    	}
    	if(null==page3){
    		page3=0;
    	}
    	
    	if(null==keyword){
    		keyword="";
    	}
    	
    	map.addAttribute("keyword", keyword);
    	map.addAttribute("page1", page1);
    	map.addAttribute("page2", page2);
    	map.addAttribute("page3", page3);
    	
    	//旅行攻略
    	Page<TdUserTravelNotes> travel_strategy_page = tdUserTravelNotesService.findByTypeAndStatusIdOrderBySortIdASC(1L, 1L, 0, 5);
		map.addAttribute("travel_strategy_page", travel_strategy_page);
		if(keyword.equals("")){
			//旅行感受
			Page<TdUserTravelNotes> travel_feeling_page = tdUserTravelNotesService.findByTypeAndStatusIdOrderBySortIdASC(0L, 1L, page1, 5);
			map.addAttribute("travel_feeling_page", travel_feeling_page);
			//旅行图片
			Page<TdUserTravelNotes> travel_picture_page = tdUserTravelNotesService.findByTypeAndStatusIdOrderBySortIdASC(2L, 1L, page2, 5);
			map.addAttribute("travel_picture_page", travel_picture_page);
			//旅行推荐
			Page<TdUserTravelNotes> travel_recommend_page = tdUserTravelNotesService.findByTypeAndStatusIdOrderBySortIdASC(3L, 1L, page3, 5);
			map.addAttribute("travel_recommend_page", travel_recommend_page);			
		}else{
			//旅行感受
			Page<TdUserTravelNotes> travel_feeling_page = tdUserTravelNotesService.findByTypeAndStatusIdAndTitleLikeOrderBySortIdASC(0L, 1L, keyword, page1, 5);
			map.addAttribute("travel_feeling_page", travel_feeling_page);
			//旅行图片
			Page<TdUserTravelNotes> travel_picture_page = tdUserTravelNotesService.findByTypeAndStatusIdAndTitleLikeOrderBySortIdASC(2L, 1L, keyword, page2, 5);
			map.addAttribute("travel_picture_page", travel_picture_page);
			//旅行推荐
			Page<TdUserTravelNotes> travel_recommend_page = tdUserTravelNotesService.findByTypeAndStatusIdAndTitleLikeOrderBySortIdASC(3L, 1L, keyword, page3, 5);
			map.addAttribute("travel_recommend_page", travel_recommend_page);
		}
		
    	return "/client/travel_notes_list";
    }
    
  //跳转查看游记页面（即游记文章页）
    @RequestMapping(value = "/user/travel/search", method = RequestMethod.GET)
    public String userTravelSearch(HttpServletRequest req, ModelMap map, Long id,Integer page) {
        String username = (String) req.getSession().getAttribute("username");

        if(null==page){
        	page=0;
        }
        
        tdCommonService.setCommon(map, req);
    
    	TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
    	map.addAttribute("tdUserTravelNotes", tdUserTravelNotes);
    	//评论列表显示
    	Page<TdUserTravelComment> tdUserTravelCommentList = null;
    	if(tdUserTravelNotes.getType() == 0L){
    		tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,0L,1L,page,10);
    	}else if(tdUserTravelNotes.getType() == 1L){
    		tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,1L,1L,page,10);
    	}else if(tdUserTravelNotes.getType() == 2L){
    		tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,2L,1L,page,10);
    	}else if(tdUserTravelNotes.getType() == 3L){
    		tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,3L,1L,page,10);
    	}
    	if(tdUserTravelCommentList != null){
    		map.addAttribute("travel_comment_page", tdUserTravelCommentList);    		
    	}
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        List<TdGoods> goodsList = tdGoodsService.findTop4ByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc("[2]");
        map.addAttribute("goods_list111", goodsList);
        
        map.addAttribute("user", user);
        if(tdUserTravelNotes.getType()!=null){
	        if(tdUserTravelNotes.getType()==0L){
	        	map.addAttribute("head_title", "旅行感受");
	        	return "/client/user_travel_article";
	        }
	        if(tdUserTravelNotes.getType()==1L){
	        	map.addAttribute("head_title", "旅行攻略");
	        	return "/client/user_travel_article";
	        	//return "/client/user_strategy_article";
	        }
	        if(tdUserTravelNotes.getType()==2L){
	        	map.addAttribute("head_title", "旅行图片");
	        	return "/client/user_travel_article";
	        	//return "/client/user_strategy_article";
	        }
	        if(tdUserTravelNotes.getType()==3L){
	        	map.addAttribute("head_title", "旅行推荐");
	        	return "/client/user_travel_article";
	        	//return "/client/user_strategy_article";
	        }
        }
        return "/client/error_404";
    }
    
    
    //游记评论添加
    @RequestMapping(value="/user/travel/comment", method = RequestMethod.POST)
    public String comment( ModelMap map, HttpServletRequest req,Long id,String content,String title){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
        TdUserTravelComment tdUserTravelComment = new TdUserTravelComment();
        tdUserTravelComment.setContent(content);
        tdUserTravelComment.setTitle(title);
        Date date = new Date();
        tdUserTravelComment.setCommentTime(date);
        tdUserTravelComment.setUsername(username);
        tdUserTravelComment.setUserHeadUri(user.getHeadImageUri());
        tdUserTravelComment.setCommentId(id);
        tdUserTravelComment.setTypeId(tdUserTravelNotesService.findOne(id).getType());
        tdUserTravelComment.setStatusId(0L);
        tdUserTravelCommentService.save(tdUserTravelComment);
        
        TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
        Long count = tdUserTravelNotes.getCountComment();
        if(count==null){
        	count=0L;
        }
        count++;
        tdUserTravelNotes.setCountComment(count);
        tdUserTravelNotesService.save(tdUserTravelNotes);
        
        return "redirect:/user/travel/search?id="+id;
    }
    
  //攻略评论添加
    @RequestMapping(value="/user/travel/comment2", method = RequestMethod.POST)
    public String comment2( ModelMap map, HttpServletRequest req,Long id,String content,String title){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
        TdUserTravelComment tdUserTravelComment = new TdUserTravelComment();
        tdUserTravelComment.setContent(content);
        tdUserTravelComment.setTitle(title);
        Date date = new Date();
        tdUserTravelComment.setCommentTime(date);
        tdUserTravelComment.setUsername(username);
        tdUserTravelComment.setUserHeadUri(user.getHeadImageUri());
        tdUserTravelComment.setCommentId(id);
        tdUserTravelComment.setTypeId(1L);
        tdUserTravelComment.setStatusId(0L);
        tdUserTravelCommentService.save(tdUserTravelComment);
        
        TdUserTravelNotes tdUserStrategyNotes = tdUserTravelNotesService.findById(id);
        Long count = tdUserStrategyNotes.getCountComment();
        if(count==null){
        	count=0L;
        }
        count++;
        tdUserStrategyNotes.setCountComment(count);
        tdUserTravelNotesService.save(tdUserStrategyNotes);
        
        
        
        return "redirect:/user/travel/search?id="+id;
    }
    //游记点赞添加
    @RequestMapping(value="/user/travel/praise", method = RequestMethod.GET)
    public String praise( ModelMap map, HttpServletRequest req,Long id){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", user);
        
        TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
        Long count = tdUserTravelNotes.getCountPraise();
        if(count==null){
        	count=0L;
        }
        count++;
        tdUserTravelNotes.setCountPraise(count);
        tdUserTravelNotesService.save(tdUserTravelNotes);
        
        
        
        return "redirect:/user/travel/search?id="+id;
    }
    
  //点赞添加
    @RequestMapping(value="/user/travel/praise2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> praise2( ModelMap map, HttpServletRequest req,Long id){
    	Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
        	res.put("code", 2);
        	res.put("msg","亲，此操作需要先登录哦！");
            return res;
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        map.addAttribute("user", user);
        Date date = new Date();
        
        TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
        TdTravelPraise travelPraise = tdTravelPraiseService.findByNameAndTravelNotesId(username,id);
        if(null == travelPraise){
        	TdTravelPraise travelPraise1 = new TdTravelPraise();
        	travelPraise1.setName(username);
        	travelPraise1.setTravelNotesId(id);
        	travelPraise1.setPraiseDate(date);
        	tdTravelPraiseService.save(travelPraise1);
        	
        	Long count = tdUserTravelNotes.getCountPraise();
            if(count==null){
            	count=0L;
            }
            count++;
            tdUserTravelNotes.setCountPraise(count);
            tdUserTravelNotesService.save(tdUserTravelNotes);
        }else{
        	Date praiseTime = travelPraise.getPraiseDate();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        	String strPraise=sdf.format(praiseTime);
        	String strNow=sdf.format(date);
        	
        	String[] ss = strPraise.split("-");
        	String strPraise11 = ss[0]+ss[1]+ss[2];
        	Long praise11 = Long.parseLong(strPraise11);
        	
        	String[] sss = strNow.split("-");
        	String strNow11 = sss[0]+sss[1]+sss[2];
        	Long now11 = Long.parseLong(strNow11);
        	
        	if(praise11.equals(now11)){
        		res.put("msg", "亲，每篇文章您一天只能赞一次哦！");
        		return res;
        	}
        	
        	travelPraise.setPraiseDate(date);
        	tdTravelPraiseService.save(travelPraise);
        	
        	Long count = tdUserTravelNotes.getCountPraise();
            if(count==null){
            	count=0L;
            }
            count++;
            tdUserTravelNotes.setCountPraise(count);
            tdUserTravelNotesService.save(tdUserTravelNotes);
        	
        }
        
        res.put("code", 0);
        return res;
    }
    
  
    
  //我的点评查看
    @RequestMapping(value = "/user/comment/search", method = RequestMethod.GET)
    public String userCommentSearch(HttpServletRequest req, ModelMap map, Long id,Long typeId,Integer page) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        tdCommonService.setCommon(map, req);
        
        if(null!=id){
        	if(null!=typeId){
        		if(typeId==1L){
	            	TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
	            	map.addAttribute("tdUserTravelNotes", tdUserTravelNotes);
	            	//评论列表显示
	            	Page<TdUserTravelComment> tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,1L,1L,page,10);
	            	map.addAttribute("travel_comment_page", tdUserTravelCommentList);
	            	
	            	TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

	                map.addAttribute("user", user);

	                return "/client/user_strategy_article";
        		}
        		if(typeId==0L){
	            	TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
	            	map.addAttribute("tdUserTravelNotes", tdUserTravelNotes);
	            	//评论列表显示
	            	Page<TdUserTravelComment> tdUserTravelCommentList = tdUserTravelCommentService.findByCommentIdAndTypeIdAndStatusId(id,0L,1L,page,10);
	            	map.addAttribute("travel_comment_page", tdUserTravelCommentList);
	            	
	            	TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

	                map.addAttribute("user", user);

	                return "/client/user_travel_article";
        		}
        	}       	
        }
        return "/client/error_404";
    }
    //发表旅行攻略保存
    @RequestMapping(value="/user/notes/save", method = RequestMethod.POST)
    public String save(TdUserTravelNotes article, ModelMap map, HttpServletRequest req,Long id){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        
        if(null==id){
	        article.setUsername(username);
	        article.setType(0L);
	        tdUserTravelNotesService.save(article);
        }
        
        if(null!=id){
        	TdUserTravelNotes tdUserTravelNotes = tdUserTravelNotesService.findById(id);
        	tdUserTravelNotes.setUsername(username);
        	tdUserTravelNotes.setTitle(article.getTitle());
        	tdUserTravelNotes.setImgUrl(article.getImgUrl());
        	tdUserTravelNotes.setContent(article.getContent());
        	tdUserTravelNotes.setClassify(article.getClassify());
        	tdUserTravelNotes.setType(0L);
        	tdUserTravelNotes.setStatusId(0L);
        	tdUserTravelNotesService.save(tdUserTravelNotes);
        }
        
        
        return "redirect:/user/travel/notes";
    }
    
    //发表旅行感受保存
    @RequestMapping(value="/user/notes2/save", method = RequestMethod.POST)
    public String save2(TdUserTravelNotes article, ModelMap map, HttpServletRequest req,Long id){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        
        if(null==id){
	        article.setUsername(username);
	        article.setType(1L);
	        tdUserTravelNotesService.save(article);
        }
        
        if(null!=id){
        	TdUserTravelNotes tdUserStrategyNotes = tdUserTravelNotesService.findById(id);
        	tdUserStrategyNotes.setUsername(username);
        	tdUserStrategyNotes.setTitle(article.getTitle());
        	tdUserStrategyNotes.setImgUrl(article.getImgUrl());
        	tdUserStrategyNotes.setContent(article.getContent());
        	tdUserStrategyNotes.setClassify(article.getClassify());
        	tdUserStrategyNotes.setType(1L);
        	tdUserStrategyNotes.setStatusId(0L);
        	tdUserTravelNotesService.save(tdUserStrategyNotes);
        }
        
        return "redirect:/user/travel/notes2";
    }
    
    //发表旅行图片保存
    @RequestMapping(value="/user/notes3/save", method = RequestMethod.POST)
    public String save3(TdUserTravelNotes article, ModelMap map, HttpServletRequest req,Long id){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        
        if(null==id){
	        article.setUsername(username);
	        article.setType(2L);
	        tdUserTravelNotesService.save(article);
        }
        
        if(null!=id){
        	TdUserTravelNotes tdUserPictureNotes = tdUserTravelNotesService.findById(id);
        	tdUserPictureNotes.setUsername(username);
        	tdUserPictureNotes.setTitle(article.getTitle());
        	tdUserPictureNotes.setImgUrl(article.getImgUrl());
        	tdUserPictureNotes.setContent(article.getContent());
        	tdUserPictureNotes.setClassify(article.getClassify());
        	tdUserPictureNotes.setType(2L);
        	tdUserPictureNotes.setStatusId(0L);
        	tdUserTravelNotesService.save(tdUserPictureNotes);
        }
        
        return "redirect:/user/travel/notes3";
    }
    
  //发表旅行推荐保存
    @RequestMapping(value="/user/notes4/save", method = RequestMethod.POST)
    public String save4(TdUserTravelNotes article, ModelMap map, HttpServletRequest req,Long id){
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        
        if(null==id){
	        article.setUsername(username);
	        article.setType(3L);
	        tdUserTravelNotesService.save(article);
        }
        
        if(null!=id){
        	TdUserTravelNotes tdUserRecommendNotes = tdUserTravelNotesService.findById(id);
        	tdUserRecommendNotes.setUsername(username);
        	tdUserRecommendNotes.setTitle(article.getTitle());
        	tdUserRecommendNotes.setImgUrl(article.getImgUrl());
        	tdUserRecommendNotes.setContent(article.getContent());
        	tdUserRecommendNotes.setClassify(article.getClassify());
        	tdUserRecommendNotes.setType(3L);
        	tdUserRecommendNotes.setStatusId(0L);
        	tdUserTravelNotesService.save(tdUserRecommendNotes);
        }
        
        return "redirect:/user/travel/notes4";
    }
    
    
    
    
    
    
    //跳转编辑头像页面
    @RequestMapping(value = "/user/header/img", method = RequestMethod.GET)
    public String headerImg(HttpServletRequest req,ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
    
        return "/client/user_header_img";
    }
    
    
    //头像上传
    @RequestMapping(value = "/user/header/img/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> headerImgSave(HttpServletRequest req,ModelMap map,String img) {
    	Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
        	res.put("msg", "请先登录！");
            return res;
        }
        TdUser user = tdUserService.findByUsername(username);
        user.setHeadImageUri(img);
        tdUserService.save(user);
        //code=0表示成功
        res.put("code", 0);
        return res;
    }
    
    //用户收藏
    @RequestMapping(value = "/user/collect", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userCollect(HttpServletRequest req,ModelMap map,Long goodsId) {
    	Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
        	res.put("code", 3);
        	res.put("msg", "请先登录！");
            return res;
        }
        String referer = req.getHeader("referer");
        TdGoods tdGoods = tdGoodsService.findByGoodsId(goodsId);
        TdUserCollect collect = tdUserCollectService.findByUsernameAndGoodsId(username, goodsId);
        if(collect!=null){
        	res.put("msg","不能重复收藏商品！");
        	return res;
        }
        TdUserCollect tdUserCollect = new TdUserCollect();
        Date date = new Date();
        tdUserCollect.setCollectTime(date);
        tdUserCollect.setGoodsCoverImageUri(tdGoods.getCoverImageUri());
        tdUserCollect.setGoodsId(goodsId);
        tdUserCollect.setGoodsSalePrice(tdGoods.getSalePrice());
        tdUserCollect.setGoodsTitle(tdGoods.getTitle());
        tdUserCollect.setUsername(username);
        tdUserCollect.setClassify(tdGoods.getCategoryTitle());
        tdUserCollect.setLinkUri(referer);
        tdUserCollectService.save(tdUserCollect);
        //code=0表示成功
        res.put("code", 0);
        return res;
    }
    
    //跳转收藏页面
    @RequestMapping(value = "/user/collect/list", method = RequestMethod.GET)
    public String collect(HttpServletRequest req,ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if (null == page)
        {
            page = 0;
        }
        
        Page<TdUserCollect> collectList = tdUserCollectService.findByUsername(username,page,10);
        map.addAttribute("user_collect_page", collectList);
    
        return "/client/user_collect";
    }
    
    
  //跳转私家定制页面
    @RequestMapping(value = "/user/demand/list", method = RequestMethod.GET)
    public String demand(HttpServletRequest req,ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if (null == page)
        {
            page = 0;
        }
        //map.addAttribute("page", page);
        Page<TdDemand> tdDemandList = tdDemandService.findByNameOrderByTimeDesc(username, page, 10);
        //Page<TdDemand> tdDemandList = tdDemandService.findAllOrderByTimeDesc(page, 10);
        //tdDemandService.findByName
        map.addAttribute("user_demand_page", tdDemandList);
        
        //Page<TdUserCollect> collectList = tdUserCollectService.findByUsername(username,page,10);
        //map.addAttribute("user_collect_page", collectList);
    
        return "/client/user_demand";
    }
    
    
    
  //跳转私家定制页面
    @RequestMapping(value = "/user/rentcar2/list", method = RequestMethod.GET)
    public String rentcar2(HttpServletRequest req,ModelMap map,Integer page) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if (null == page)
        {
            page = 0;
        }
        
        //Page<TdOrder> user_rentcar2_page = tdOrderService.findByUsernameAndStatusId(username, 0, page, 10);
        Page<TdOrder> user_rentcar2_page = tdOrderService.findByUsernameAndOrderType(username, "客车包租", page, 10);
        //map.addAttribute("page", page);
        //Page<TdGoods> user_rentcar2_page = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,10);
        //Page<TdDemand> tdDemandList = tdDemandService.findAllOrderByTimeDesc(page, 10);
        map.addAttribute("user_rentcar2_page", user_rentcar2_page);
        
        //Page<TdUserCollect> collectList = tdUserCollectService.findByUsername(username,page,10);
        //map.addAttribute("user_collect_page", collectList);
    
        return "/client/user_rentcar2";
    }
    
    
    
    @RequestMapping(value = "/user/collect/delete", method = RequestMethod.GET)
    public String collectDelete(HttpServletRequest req,ModelMap map, Long id) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        tdUserCollectService.delete(id);
    
        return "redirect:/user/collect/list";
    }
    //跳转我的优惠劵
    @RequestMapping(value = "/user/coupon/list")
	public String couponList(HttpServletRequest req, Integer page, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setCommon(map, req);
        
        if (null == page)
        {
            page = 0;
        }
        
        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        
        map.addAttribute("user", tdUser);
        
        Page<TdCoupon> coupanPage =tdCouponService.findByUsername(username,page,5);
        
        map.addAttribute("coupan_page", coupanPage);
        
        return "/client/user_coupon_list";
    }
    //删除优惠劵
    @RequestMapping(value = "/user/coupon/delete")
	public String couponDelete(HttpServletRequest req, Integer page, ModelMap map,Long id) {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setCommon(map, req);
        
        if (null == page)
        {
            page = 0;
        }
        
        tdCouponService.delete(id);
        
        return "redirect:/user/coupon/list";
    }
    
    //跳转我的积分页面
    @RequestMapping(value = "/user/point/list")
    public String pointList(HttpServletRequest req, Integer page, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }

        tdCommonService.setCommon(map, req);

        if (null == page) {
            page = 0;
        }

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);

        map.addAttribute("user", tdUser);

        Page<TdUserPoint> pointPage = null;
        List<TdUserPoint> userPointList = tdUserPointService.findByUsername(username);
        Long total =0L;
        for(TdUserPoint point:userPointList){
        	total = total + point.getTotalPoint();
        }
        map.addAttribute("total", total);
        pointPage = tdUserPointService.findByUsernameAndisBackgroundShowfalse(username, page,
                10);

        map.addAttribute("point_page", pointPage);

        return "/client/user_point_list";
    }
    
    //跳转积分规则页面
    @RequestMapping(value = "/user/point/rule")
    public String pointRule(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/login";
        }
        
        TdArticleCategory tac = tdArticleCategoryService.findByTitle("积分规则");
        // 积分规则只有一个，客户应该只建一个
        List<TdArticle> taList = tdArticleService.findByCategoryId(tac.getId());
        if(taList != null && taList.size() > 0){
        	map.addAttribute("pointRule", taList.get(0));
        }
        
        tdCommonService.setCommon(map, req);

        

        TdUser tdUser = tdUserService.findByUsernameAndIsEnabled(username);
        

        map.addAttribute("user", tdUser);

        return "/client/user_point_rule";
    }
    //商品评价
    @RequestMapping(value = "/user/goods/comment", method = RequestMethod.POST)
    public String userGoodsComment(HttpServletRequest req, ModelMap map,Long goodsStar,Long serviceStar,Long skillStar,String content,Long id,Long type,Integer type2) {
        
        String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        map.addAttribute("user", tdUserService.save(user));

        TdGoods goods = tdGoodsService.findOne(id);
        
        TdUserComment tdUserComment = new TdUserComment();
        tdUserComment.setGoodsStar(goodsStar);
        tdUserComment.setServiceStar(serviceStar);
        tdUserComment.setSkillStar(skillStar);
        tdUserComment.setContent(content);
        tdUserComment.setGoodsId(id);
        if(null != goods)
        {
        	tdUserComment.setGoodsCoverImageUri(goods.getCoverImageUri());
        	tdUserComment.setGoodsTitle(goods.getTitle());
        }
        tdUserComment.setStatusId(0L);
        Date date = new Date();
        tdUserComment.setCommentTime(date);
        tdUserComment.setUsername(username);
        tdUserComment.setUserHeadUri(user.getHeadImageUri());
        tdUserCommentService.save(tdUserComment);
        
        return "redirect:/detail/"+id;
    }
    
    //订单评价
    @RequestMapping(value = "/user/order/comment", method = RequestMethod.POST)
    public String userOrderComment(HttpServletRequest req, ModelMap map,Long goodsStar,Long serviceStar,Long skillStar,String content,Long id,Long type,Integer type2) {
        
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
        	map.addAttribute("msg", "请先登录！");
            return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        map.addAttribute("user", tdUserService.save(user));

        TdOrder tdOrder = tdOrderService.findById(id);
        tdOrder.setStatusId(6L);
        tdOrderService.save(tdOrder);
        TdUserComment tdUserComment = new TdUserComment();
        tdUserComment.setGoodsStar(goodsStar);
        tdUserComment.setServiceStar(serviceStar);
        tdUserComment.setSkillStar(skillStar);
        tdUserComment.setContent(content);
        Date date = new Date();
        tdUserComment.setCommentTime(date);
        tdUserComment.setUsername(username);
        tdUserComment.setUserHeadUri(user.getHeadImageUri());
        tdUserCommentService.save(tdUserComment);
        
        if(type2==111){
        	return "redirect:/user/"+type;
        }
        
        return "redirect:/user/order/list/"+type;
    }
    
    //我的订单页面
    @RequestMapping(value = "/user/order/list/{statusId}")
	public String orderList(@PathVariable Long statusId,HttpServletRequest req, Integer page, ModelMap map,String orderNumber,String orderType) throws ParseException {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if (null == page)
        {
            page = 0;
        }
        
        map.addAttribute("total_unpayed",tdOrderService.countByUsernameAndStatusId(username, 2));
        map.addAttribute("total_server",tdOrderService.countByUsernameAndStatusId(username, 3));
        map.addAttribute("total_unreceived",tdOrderService.countByUsernameAndStatusId(username, 4));
        map.addAttribute("total_common",tdOrderService.countByUsernameAndStatusId(username, 5));
        map.addAttribute("total_connel",tdOrderService.countByUsernameAndStatusId(username, 6));
        
        map.addAttribute("order_page", tdOrderService.findAll(username,statusId, orderNumber, orderType, page, 9));
        
        map.addAttribute("orderNumber", orderNumber);
        map.addAttribute("statusId",statusId);
        map.addAttribute("orderType", orderType);
        map.addAttribute("page", page);
        
        return "/client/order_list";
    }
    
    //订单删除
    @RequestMapping(value = "/user/order/delete/{type}")
	public String orderDelete(HttpServletRequest req, ModelMap map,Long id,@PathVariable Long type) {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        tdOrderService.delete(id);
        
        return "redirect:/user/order/list/"+type;
    }
    
    //查看订单详情
    @RequestMapping(value = "/user/look/order")
	public String lookOrder(HttpServletRequest req, ModelMap map,Long orderId) {
        String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/login";
        }
        
        tdCommonService.setCommon(map, req);
        
        TdOrder order = tdOrderService.findById(orderId);
        map.addAttribute("order", order);
        TdGoods goods = tdGoodsService.findByGoodsId(order.getGoodsId());
        map.addAttribute("goods", goods);
        List<TdOrderGoods> orderGoods_list = tdOrderGoodsService.findByTdOrderId(order.getId());
        map.addAttribute("order_goods_list", orderGoods_list);
        List<TdOrderVisitor> orderVisitor_list = tdOrderVisitorService.findByTdOrderId(order.getId());
        map.addAttribute("order_visitor_list", orderVisitor_list);
        return "/client/look_order";
    }
    
    
    
    //验证手机号
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
    @RequestMapping(value = "/code/1",method = RequestMethod.GET)
    public void verify(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifServlet randomValidateCode = new VerifServlet();
        randomValidateCode.getRandcode(request, response);
//        QRCodeUtils qr = new QRCodeUtils();
//        qr.getQRCode("weixin://wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D", 125, response);
    }
    
    
}
