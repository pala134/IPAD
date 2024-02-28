package com.ipad.dao.board;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.board.BoardDto;



public class BoardDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;

	//DB 연결 
	public void getCon() {
		try {

			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/Oracle11g");
			con = dataSource.getConnection();
			System.out.println("DB연결성공");
		} catch (Exception e) {
			System.out.println("DB연결살패");
			e.printStackTrace();

		}
	}

	// 전체 게시글의 개수를 리턴하는 메소드
	public int getAllCount() {
		int count = 0;
		try {
			getCon();
			String sql = "select count(*) from memberBoard";
			pstmt = con.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			System.out.println("getAllCount 실행");

		} catch (Exception e) {
			System.out.println("getAllCount 실행 실패");
			e.printStackTrace();

		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// 화면에 보여질 데이터를 10개씩 추출해서 리턴하는 메소드
	public ArrayList<BoardDto> getAllBoard(int startRow, int endRow) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();

		try {
			getCon();
			String sql = "select * from (select A.*, Rownum Rnum from (select * from memberBoard order by ref desc, re_step asc)A) where Rnum >= ? and Rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			System.out.println("getAllBoard : " + startRow);
			pstmt.setInt(2, endRow);
			System.out.println("getAllBoard : " + endRow);

			resultSet = pstmt.executeQuery();

			System.out.println(resultSet);

			while (resultSet.next()) {
				BoardDto bDto = new BoardDto();
				bDto.setNum(resultSet.getInt(1));
				bDto.setWriter(resultSet.getString(2));
				bDto.setEmail(resultSet.getString(3));
				bDto.setSubject(resultSet.getString(4));
				bDto.setPassword(resultSet.getString(5));
				bDto.setReg_date(resultSet.getDate(6).toString());
				bDto.setReadcount(resultSet.getInt(7));
				bDto.setContent(resultSet.getString(8));
				bDto.setRef(resultSet.getInt(9));
				bDto.setRe_step(resultSet.getInt(10));
				bDto.setRe_level(resultSet.getInt(11));

				list.add(bDto);

				System.out.println("getAllBoard 실행");
			}

		} catch (Exception e) {
			System.out.println("getAllBoard 실패");
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	//정보를 저장하는 메소드
	public void insertBoard(BoardDto bDto) {
		int ref = 0;
		int re_step = 1; // 새 글이라 1값을 줌
		int re_level = 1;

		try {
			getCon();
			String refsql = "select max(ref) from memberBoard";
			pstmt = con.prepareStatement(refsql);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				ref = resultSet.getInt(1) + 1;
			}

			// 데이터를 insert 하는 쿼리
			String sql = "insert into memberBoard values(memberBoard_seq.NEXTVAL,?,?,?,?,TO_CHAR(sysdate,'YYYY-MM-DD'),0,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
						
			pstmt.setString(1, bDto.getWriter());
			System.out.println("insertBoard  1" +  bDto.getWriter());
			pstmt.setString(2, bDto.getEmail());
			System.out.println("insertBoard  2" +  bDto.getEmail());
			pstmt.setString(3, bDto.getSubject());
			System.out.println("insertBoard  3" +  bDto.getSubject());
			pstmt.setString(4, bDto.getPassword());
			System.out.println("insertBoard  4" +  bDto.getPassword());
			pstmt.setString(5, bDto.getContent());
			System.out.println("insertBoard  5" +  bDto.getContent());
			pstmt.setInt(6, ref);
			System.out.println("insertBoard  6" +  ref);
			pstmt.setInt(7, re_step);
			System.out.println("insertBoard  7" +  re_step);
			pstmt.setInt(8, re_level);
			System.out.println("insertBoard  8" +  re_level);

			pstmt.executeUpdate();

			System.out.println("insertBoard - BoardWrite 저장 성공");

		} catch (Exception e) {
			System.out.println("insertBoard - BoardWrite 저장 실패");
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 하나의 게시글을 읽어드리는 메소드 작성
	public BoardDto getOneBoard(int num) {
		BoardDto bDto = null;

		try {
			getCon();
			// 하나의 게시글 조회수 증가
			String countSql = "update memberBoard set readcount = readcount+1 where num=?";
			pstmt = con.prepareStatement(countSql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			// 한게시글에 대한 정보를 리턴해주는 쿼리
			String sql = "select * from memberBoard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				bDto = new BoardDto();
				bDto.setNum(resultSet.getInt(1));
				bDto.setWriter(resultSet.getString(2));
				bDto.setEmail(resultSet.getString(3));
				bDto.setSubject(resultSet.getString(4));
				bDto.setPassword(resultSet.getString(5));
				bDto.setReg_date(resultSet.getDate(6).toString());
				bDto.setReadcount(resultSet.getInt(7));
				bDto.setContent(resultSet.getString(8));
				bDto.setRef(resultSet.getInt(9));
				bDto.setRe_step(resultSet.getInt(10));
				bDto.setRe_level(resultSet.getInt(11));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bDto;

	}

	// 조회수를 증가하지 않는 하나의 게시글을 리턴하는 메소드
	public BoardDto getoneUpdateBoard(int num) {
		BoardDto bDto = null;
		try {
			getCon();
			// 한게시글에 대한 정보를 리턴해주는 쿼리
			String sql = "select * from memberBoard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				bDto = new BoardDto();
				bDto.setNum(resultSet.getInt(1));
				bDto.setWriter(resultSet.getString(2));
				bDto.setEmail(resultSet.getString(3));
				bDto.setSubject(resultSet.getString(4));
				bDto.setPassword(resultSet.getString(5));
				bDto.setReg_date(resultSet.getDate(6).toString());
				bDto.setReadcount(resultSet.getInt(7));
				bDto.setContent(resultSet.getString(8));
				bDto.setRef(resultSet.getInt(9));
				bDto.setRe_step(resultSet.getInt(10));
				bDto.setRe_level(resultSet.getInt(11));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bDto;

	}

	// 하나의 게시글을 수정하는 메소드
	public int updateBoard(BoardDto bDto) {
		int result = 0;
		

		try {
			getCon();
			String sql = "update memberBoard set subject= ? , content= ? where num = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bDto.getSubject());
			pstmt.setString(2, bDto.getContent());
			pstmt.setInt(3, bDto.getNum());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	
	//게시글 삭제 메소드
	public int deleteBoard(int num) {
		int result = 0;
		
		try {
			getCon();
			String sql = "delete from memberBoard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원탈퇴성공");
				
			}else {
				System.out.println("회원탈퇴실패");
			}
			
			
			
		}catch (Exception e) {
			  System.out.println("회원 탈퇴 중 오류 발생");
			e.printStackTrace();
		}finally {
			try {
				// 자원 해제
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
	

}
