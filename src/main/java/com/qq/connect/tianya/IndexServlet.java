package com.qq.connect.tianya;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping("/qqLogin")
public class IndexServlet{
	
	@RequestMapping
	public String qqLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "redirect:" + new Oauth().getAuthorizeURL(request);
	}
}
