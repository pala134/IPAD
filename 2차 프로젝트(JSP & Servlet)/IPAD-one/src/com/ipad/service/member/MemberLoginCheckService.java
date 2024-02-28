package com.ipad.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipad.dao.member.MemberDao;
import com.ipad.dto.member.MemberDto;
import com.ipad.service.Service;


public class MemberLoginCheckService implements Service {

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("MemberLoginCheckService 진입 성공 =============");
		
		
     
	    // 요청 파라미터에서 사용자 자격 증명 가져오기
        String id = request.getParameter("id");
//        System.out.println("MemberLoginCheckService [id] : " + id);
        String pass1 = request.getParameter("pass1");
//        System.out.println("MemberLoginCheckService [pass1] " + pass1);

        // 사용자 인증 ( 데이터베이스와 비교)
        MemberDao mDao = new MemberDao();
        MemberDto loggedInUser = mDao.isLogin(id, pass1);
        
        

        if (loggedInUser != null) {
            // 세션에 인증된 사용자 설정
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", loggedInUser);
//            System.out.println("login 세션 확인" + session.getId());

            try {
                // 성공적인 로그인 후 메인 페이지 또는 필요한 곳으로 리다이렉트
            	response.setContentType("text/html; charset=UTF-8");
            	PrintWriter out = response.getWriter();
            	out.println("<script>window.location.href='" + request.getContextPath() + "/main.do';</script>");
            	out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 인증 실패 처리, 로그인 페이지로 리다이렉트하고 오류 메시지가 있는 경우 고려
            // 여기에 어떤 오류 처리 로직을 추가하는 것이 좋습니다.
            try {
//                response.sendRedirect(request.getContextPath() + "/member/loginPage.do");
                response.setContentType("text/html; charset=UTF-8");
        		PrintWriter out = response.getWriter();
        		out.println("<script>alert('아이디와 비밀번호가 틀렸습니다.'); location.href='loginPage.do';</script>");
        		out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		
    }
}