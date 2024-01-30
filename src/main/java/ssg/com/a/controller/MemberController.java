package ssg.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method=RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login " + new Date());
		
		// views안에 member 폴더안에 있어서 prefix로 member/붙임
		// /member이렇게 쓰지말것 prefix /WEB-INF/views/로 되있음
		return "member/login";
	}
	
	@GetMapping("regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());
		
		return "member/regi";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "idcheck.do", 
//					method=RequestMethod.GET, 
//					produces = "application/String; charset=utf-8")
//	public String idcheck(String id) {
//		System.out.println(id);
//		boolean exist = service.idcheck(id);
//		
//		String existStr = "YES";
//		
//		if(exist) {
//			// 이미 아이디 있어서 사용불가
//			existStr = "NO";
//		}
//		
//		return existStr;
//	}
	
	@ResponseBody
	// Get/PostMapping 사용하면
	// 순수 문자열 리턴할 때
	// RequestMapping에서 사용했던
	// produces = "application/String; charset=utf-8"파라미터 이거 없어도됨
	@GetMapping("idcheck.do")
	public String idcheck(String id) {
		System.out.println("MemberController idcheck " + new Date());
		
		boolean isS = service.idcheck(id);
		
		if(isS) {
			return "NO";
		}
		
		return "YES";
	}
	
	@PostMapping("regiAf.do")
	public String regiAf(MemberDto mem, Model model) {
		System.out.println("MemberController regiAf " + new Date());
		System.out.println(mem);
		
		boolean isS = service.addmember(mem);
		String regiMsg = "MEMBER_YES";
		
		if(!isS) {
			regiMsg = "MEMBER_NO";
		}
		
		model.addAttribute("regiMsg", regiMsg);
		
		return "message";
	}
	
	@PostMapping("loginAf.do")
	// parameter가 MemberDto의 필드와 매칭되어 들어간다.
	// Model처럼 HttpServletRequest도 파라미터로 불러올 수 있다.
	public String login(MemberDto mem, Model model, HttpServletRequest request) {
		System.out.println("MemberController loginAf " + new Date());
		System.out.println(mem);
		
		MemberDto dto = service.login(mem);
		
		String loginMsg = "LOGIN_NO";
		
		// 로그인 성공했을 때
		if(dto != null) {
			request.getSession().setAttribute("login", dto);
			// 따로 설정 안하면 무제한
			// request.getSession().setMaxInactiveInterval(60 * 60 * 24);
			loginMsg = "LOGIN_SUCCESS";
		}
		
		model.addAttribute("loginMsg", loginMsg);
		return "message";
	}
	
}
