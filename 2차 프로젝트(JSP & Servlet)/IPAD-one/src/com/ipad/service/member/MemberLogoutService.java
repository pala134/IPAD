package com.ipad.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.ipad.service.Service;

public class MemberLogoutService implements Service{

	 @Override
	    public void execute(HttpServletRequest request, HttpServletResponse response) {
	        // 세션에서 로그인 정보 제거
		 
		 System.out.println("MemberLogoutService 입장 완료");
		 	
		 	HttpSession session = request.getSession();
		 	session.removeAttribute("loggedInUser");
		 	
		 	System.out.println(session.getId());
		 	System.out.println(session.getAttributeNames());
		 	
		 	try {
                // 성공적인 로그인 후 메인 페이지 또는 필요한 곳으로 리다이렉트
		 		response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>window.location.href='" + request.getContextPath() + "/main.do';</script>");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    }
	}