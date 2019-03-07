package com.ynyes.tianya.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserService;

@Controller
public class TdAccreditLoginController {

	@Autowired
    private TdUserService tdUserService;
	
	@Autowired
	TdCommonService tdCommonService;
	
	@Autowired
	private TdUserLevelService tdUserLevelService;
	
	@RequestMapping(value="/accred/user",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> accredUser(String openId,String username,String type,
			String password,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("code", 0);
		
		if(null == username || "".equals(username.trim())){
			res.put("msg", "请输入用户名");
			return res;
		}
		
		TdUser user = tdUserService.findByUsername(username);
		if(null == user)
		{
			res.put("msg", "用户名不存在");
			return res;
		}
		
		if(null == password)
		{
			res.put("msg", "请输入密码");
			return res;
		}
		
		if(!password.equalsIgnoreCase(user.getPassword()))
		{
			res.put("msg", "密码错误，请重新输入");
			return res;
		}
		
		if("wx".equals(type))
		{
			user.setWxOpenId(openId); // 微信openId
		}else{
			user.setQqUserId(openId); // 储存QQ用户openId
		}
		user = tdUserService.save(user);
		
		req.getSession().setAttribute("username", user.getUsername()); // 保存登录状态
		res.put("msg", "绑定成功");
		res.put("code", 1);
		return res;
	}
	
	@RequestMapping(value="/accred/reg",method = RequestMethod.POST)
	public String accredReg(String openId,String username,String type,
			String password,String mobile,String email,HttpServletRequest req){
		
		TdUser user = new TdUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setStatusId(0L);
		Date date = new Date();
		user.setRegisterTime(date);
		user.setIsEmailValidated(false);
		user.setIsMobileValidated(false);
		user.setTotalPoints(0L);//初始化积分
		
		if("wx".equals(type))
		{
			user.setWxOpenId(openId); // 微信openId
		}else{
			user.setQqUserId(openId); // 储存QQ用户openId
		}
		
		//初始化等级
		List<TdUserLevel> tdUserLevelList = tdUserLevelService.findByIsEnableTrueOrderByLevelIdAsc();
		for(int i = tdUserLevelList.size() - 1; i >= 0; i --){
			Long requriedPoints = tdUserLevelList.get(i).getRequiredPoints();
			if(requriedPoints != null && user.getTotalPoints() >= requriedPoints){
				user.setUserLevelId(tdUserLevelList.get(i).getId());
				user.setUserLevelTitle(tdUserLevelList.get(i).getTitle());
				break;
			}
		}
		//若没有找到相应的等级
		if(user.getUserLevelId() == null){
			user.setUserLevelId(-1L);
			user.setUserLevelTitle("无");
		}
		
		user = tdUserService.save(user);
		
		req.getSession().setAttribute("username", user.getUsername());
		
		return "redirect:/";
	}
	
			
			
}
