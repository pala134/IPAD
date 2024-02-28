package com.ipad.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;



public class MemberEditFormCheckService  implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//고정값 제외 pass1, map, email 수정 시 DB값 변경 
		//pass1, map 변경은 필요 없어 안 적을 경우 null값으로 넘어오는 경우 session에 로그인된 pass, map을 넣어준다.
		//넘어온 값들을 Dto에 담아서 Dao 메소드로 호출할 거다.
		//정보 저장, 실패에 대한 결과 메시지 안내
		//그 다음 마이페이지 화면을 다시 보여준다.		
		
		MemberDao mDao = new MemberDao();
		MemberDto editUpdate = new MemberDto();
		
		
		HttpSession session = request.getSession();
		
		
		
		editUpdate.setId(request.getParameter("id"));
		if(request.getParameter("pass1")=="") {
			MemberDto loggedInUser = (MemberDto) session.getAttribute("loggedInUser");
			System.out.println("loggedInUser.getPass1() : " + loggedInUser.getPass1());
			editUpdate.setPass1(loggedInUser.getPass1());
		}
		else
		{
			editUpdate.setPass1(request.getParameter("pass1"));
			System.out.println("테스트중입니다" + editUpdate.getPass1());
		}
		editUpdate.setEmail(request.getParameter("email"));
		editUpdate.setName(request.getParameter("name"));
		editUpdate.setTel(request.getParameter("tel"));
		editUpdate.setYear(request.getParameter("year"));
		editUpdate.setMap(request.getParameter("map"));
		
		int result = mDao.editUpdate(editUpdate);
		 if (result > 0) {
				try {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원정보 수정이 완료되었습니다.'); location.href='mypage.do';</script>");
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

		    } else {
		        // 수정이 실패한 경우
		        session.setAttribute("editFormMesseage", "회원수정이 실패되었습니다.");
		    }
		 	String viewPage = "/member/mypage.do";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		System.out.println("잘들어왔나여???? " + result);
		
		
	   
			
	}

}
