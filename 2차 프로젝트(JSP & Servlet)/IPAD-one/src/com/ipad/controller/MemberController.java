package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;
import com.ipad.service.member.MemberEditCheckService;
import com.ipad.service.member.MemberEditFormCheckService;
import com.ipad.service.member.MemberExitCheckService;
import com.ipad.service.member.MemberIdCheckService;
import com.ipad.service.member.MemberLoginCheckService;
import com.ipad.service.member.MemberLogoutService;
import com.ipad.service.member.MemberjoinFormCheckService;

public class MemberController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		System.out.println("멤버 컨트롤러 입장==============");
		String viewPage = "";
		Service service = null;

		
		if (com.equals("/member/assent.do")) { //약관동의 페이지 
			viewPage = "/jsp/member/assent.jsp";
//			System.out.println("assent / 약관동의 진입===========");
			
		} else if(com.equals("/member/joinForm.do")) { //회원가입 폼  페이지
			viewPage = "/jsp/member/joinForm.jsp";
//			System.out.println("joinForm / 회원가입폼 진입===========");
			
		}else if(com.equals("/member/joinFormCheck.do")) { // 회원가입 폼 action 
			service = new MemberjoinFormCheckService();
			service.execute(request, response);
//			System.out.println("joinFormCheck / 회원가입 action 진입");
			
		}else if(com.equals("/member/loginPage.do")) { //로그인 페이지 화면
			viewPage = "/jsp/member/loginPage.jsp";
//			System.out.println("loginPage / 로그인페이지  진입");	
			
		}else if(com.equals("/member/loginPageCheck.do")) { //로그인 페이지 action
			service = new MemberLoginCheckService();
			service.execute(request, response);
//			System.out.println("loginPageCheck / 로그인  진행 체크완료");
//			
		}else if(com.equals("/member/logout.do")) { //로그아웃 action
			service = new MemberLogoutService(); 
			service.execute(request, response);
//			System.out.println("logout / 로그아웃 완료 =============");
	
		}else if(com.equals("/member/mypage.do")) { //마이페이지 
			viewPage = "/jsp/member/mypage.jsp";
//			System.out.println("mypage / 마이페이지진입  =============");
			
		}else if(com.equals("/member/mypageEdit.do")) { // 회원 수정 페이지
			viewPage = "/jsp/member/mypageEdit.jsp";
//			System.out.println("mypageEdit / 회원수정ㅍ진입  =============");
			
		}else if(com.equals("/member/MemberEditCheck.do")) { // 회원 수정 action
			service = new MemberEditCheckService(); 
			service.execute(request, response);
//			System.out.println("MemberEditCheck / 회원수정 action 진입  =============");
			
		}else if(com.equals("/member/mypageEditForm.do")) { //회원 수정 폼 페이지
			viewPage = "/jsp/member/mypageEditForm.jsp";
//			System.out.println("mypageEditForm / 회원 수정 form  진입  =============");
			
		}else if(com.equals("/member/mypageEditFormCheck.do")) { //회원 수정 action
			service = new MemberEditFormCheckService(); 
			service.execute(request, response);
//			System.out.println("mypageEditFormCheck / 회원 수정 action 진입  =============");
			
		}else if(com.equals("/member/mypageExit.do")) { // 회원탈퇴 페이지 화면
			viewPage = "/jsp/member/mypageExit.jsp";
//			System.out.println("mypageExit / 회원 탈퇴 ==============");
		}else if(com.equals("/member/mypageExitCheck.do")) { // 회원탈퇴  action
			service = new MemberExitCheckService(); 
			service.execute(request, response);
//			System.out.println("mypageExitCheck / 회원수정 action 진입  =============");
		}else if(com.equals("/member/idCheck.do")) {
			service = new MemberIdCheckService(); 
			service.execute(request, response);
//			System.out.println("idCheck / 약관동의 진입  =============");

		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
}
}


