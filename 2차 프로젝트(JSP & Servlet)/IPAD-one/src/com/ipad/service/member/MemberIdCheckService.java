package com.ipad.service.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.member.MemberDao;
import com.ipad.service.Service;



public class MemberIdCheckService implements Service{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("MemberIdCheckService 진입 성공");
		
		String id = request.getParameter("id");
		
//		System.out.println("MemberIdCheckService - request.getParameter : ( " + id + ")");
		
		MemberDao mDao = MemberDao.getInstance();
		
		int result = mDao.confirmID(id);
		
//		System.out.println("MemberIdCheckService - int result :  ( " + result + ")");
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
//		System.out.println("MemberIdCheckService - request.getAttribute ( " + id + ")");
//		System.out.println("MemberIdCheckService - request.getAttribute ( " + result + ")");
		
		String viewPage = "/jsp/member/idCheck.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		
		try {
			dispatcher.forward(request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
