package com.ipad.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;

public class MemberEditCheckService implements Service {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("MemberEditCheckService 입장 완료");

		
		MemberDao mDao = new MemberDao();
		MemberDto mDto = new MemberDto();
		HttpSession session = request.getSession();
		MemberDto loggedInUser = (MemberDto) session.getAttribute("loggedInUser");
//		String id = request.getParameter("id");
//		String pass1 = request.getParameter("pass1"); 
//		String pass = mDao.getPass(loggedInUser.getId());
		
		String id = loggedInUser.getId(); // 로그인 세션의 ID
		String pass = loggedInUser.getPass1(); // 로그인 세션의 비밀번호
		String pass1 = request.getParameter("pass1"); // 입력된 비밀번호
		
		System.out.println("MemberEditCheckService [id]" + id);
		System.out.println("MemberEditCheckService [pass]" + pass);
		System.out.println("MemberEditCheckService [pass1]" + pass1);

		if (pass.equals(pass1)) {
			try {
				MemberDto editSearch = mDao.editSearch(id, pass);
				session.setAttribute("editSearch", editSearch);
				System.out.println("MemberEditCheckService- editUpdate 넘어왓니?" + (editSearch.getId()));
								
				response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>window.location.href='" + request.getContextPath() + "/member/mypageEditForm.do';</script>");
                out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			session.setAttribute("editMessage", "*비밀번호가 틀렸습니다. 다시 입력해주세요.*");
			System.out.println("editMessage 속성이 설정되었습니다: " + session.getAttribute("editMessage"));
			try {
				response.sendRedirect(request.getContextPath() + "/member/mypageEdit.do");
			

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
