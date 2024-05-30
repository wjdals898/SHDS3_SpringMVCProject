package com.shinhan.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.model.AccountServiceImpl;

@Controller
public class AccountController {

	@Autowired
	AccountServiceImpl accService;
	
	@RequestMapping("/account/trans.do")
	@ResponseBody // ������ ���ڴ� response.getWriter().append("����")
				  // @ResponseBody�� ������ JSP�������� forward�ȴ�.
	public String f1() {
		accService.update();
		return "Account Transaction Test";
	}
}
