package com.shinhan.myapp.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpDTO;
import org.firstzone.myapp.emp.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.util.DateUtil;

@Controller
@RequestMapping("/emp")
public class EmpController {

	// @Autowired는 타입이 같으면 자동으로 Injection
	@Autowired
	EmpService eService;
	
	@Autowired
	DeptService dService;
	
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@RequestMapping("/empAll.do")
	public String empAll(Model model, Integer deptid, String jobid) {
		List<EmpDTO> emplist = null;
		
		// 부서선택인 경우
		// 직책선택인 경우
		// 전부조회인 경우
		if(deptid==null) deptid = 0;
		emplist = eService.selectByCondition(deptid, jobid, null, 0);
		
		model.addAttribute("emplist", emplist);
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());
		
		// view 이름이 return 된다.
		// ViewResolver가 접두사 + view 이름 + 접미사 붙여서 view를 찾는다.
		// view로 forward 된다. (주소는 바뀌지 않음)
		return "emp/emplist";
	}
	
	// deptid=0&jobid=all&hdate=2005-01-01&salary=5000
	// deptid=0 모든부서를 의미
	// jobid=all 모든직책을 의미
	@RequestMapping("/empAll2.do")
	public String empCondition(Model model, 
			HttpSession session,
			Integer deptid, String jobid, 
			@RequestParam(value = "hdate",
						  required = false,
						  defaultValue="1900-01-01") Date hdate, Integer salary) {
		//Date startDate = DateUtil.getSQLDate(hdate);
		
		if(salary == null) salary = 0;
		
		List<EmpDTO> emplist = eService.selectByCondition(deptid, jobid, hdate, salary);
		
		logger.info(emplist.size()+"건 조회됨");
		
		// 선택한 값들을 session에 저장하기
		session.setAttribute("deptid", deptid);
		session.setAttribute("jobid", jobid);
		session.setAttribute("hdate", hdate);
		session.setAttribute("salary", salary);
		
		model.addAttribute("emplist", emplist);
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());
		return "emp/emplist";
	}
	
	@GetMapping("/empDetail.do")
	public void detail(@RequestParam("empid") Integer empid, Model model) {
		model.addAttribute("emp", eService.selectById(empid));
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("mgrlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());
	}
	
	@PostMapping("/empDetail.do")
	public String update(EmpDTO emp) {
		eService.empUpdate(emp);
		return "redirect:empAll.do";
	}
	
	@GetMapping("/empInsert.do")
	public void showInsert(Model model) {
		model.addAttribute("deptlist", dService.selectAll());
		System.out.println("mgrlist="+eService.selectAllManager());
		model.addAttribute("mgrlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());
	}
	
	@PostMapping("/empInsert.do")
	public String insertDB(EmpDTO emp) {
		System.out.println("insert확인(JavaBean):"+emp);
		eService.empInsert(emp);
		return "redirect:empAll.do";
	}
	
	@GetMapping("/empDelete.do")
	public String delete(Integer empid) {
		eService.empDelete(empid);
		return "redirect:empAll.do";
	}
	
	@GetMapping("/empIdCheck.do")
	@ResponseBody
	public String test(Integer empid) {
		EmpDTO emp = eService.selectById(empid);
		
		if(emp == null) return "0";
		
		return "1";
	}
	
	// @ResponseBody
	//  => response.getWriter().append()
}
