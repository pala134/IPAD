package com.ipad.service.member;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;




public class MemberjoinFormCheckService implements Service{
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("MemberLoginService 입장 완료");
		
		String id = request.getParameter("id");
		String pass1 = request.getParameter("pass1");
		String email = request.getParameter("email");
		String name =  request.getParameter("name");
		String tel = request.getParameter("tel");
		String year = request.getParameter("year");
		String map = request.getParameter("map");
		MemberDto mDto = new MemberDto();
	
		mDto.setId(id);
		mDto.setPass1(pass1);
		mDto.setEmail(email);
		mDto.setName(name);
		mDto.setTel(tel);
		mDto.setYear(year);
		mDto.setMap(map);
		MemberDao mDao = new MemberDao();
		
	
		
		int result = mDao.insertMember(mDto);	
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("name", mDto.getName());
			request.setAttribute("messge", "반갑습니다.");			
			
		}else {
			request.setAttribute("messge", "회원가입에 실패하였습니다.");
		}
		
		String viewPage = "/jsp/member/joinFinish.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
		
}


