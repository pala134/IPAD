package com.ipad.service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.dao.board.BoardDao;
import com.ipad.dto.board.BoardDto;
import com.ipad.service.Service;

public class BoardWriteEidtService implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//해당번호
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//데이터베이스에서 하나의 게시글에 대한 정보를 리턴 호출
		BoardDao bDao = new BoardDao();
		BoardDto bDto = bDao.getoneUpdateBoard(num);
		
		//request에 데이터 넣음
		request.setAttribute("bDto", bDto);
		
		String viewPage = "/board/boardWriteUpdate.do";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
