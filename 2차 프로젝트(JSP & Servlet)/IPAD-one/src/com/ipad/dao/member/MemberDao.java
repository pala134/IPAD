package com.ipad.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ipad.dto.member.MemberDto;



public class MemberDao {
	
	private static MemberDao instance = new MemberDao();
	private Connection con;	
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private DataSource dataSource;
	
	public static MemberDao getInstance() {
        return instance;
    }
	
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
	
	//회원가입 폼 저장
	public int insertMember(MemberDto mDto) {
		int result = -1;

		try {
			getCon();
			String sql = "insert into login values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPass1());
			pstmt.setString(3, mDto.getEmail());
			pstmt.setString(4, mDto.getName());
			pstmt.setString(5, mDto.getTel());
			pstmt.setString(6, mDto.getYear());
			pstmt.setString(7, mDto.getMap());

			pstmt.executeUpdate();
			System.out.println("회원가입 정보 저장 성공");
			result = 1; // 성공 시 1을 반환

		} catch (SQLException e) {
			System.out.println("회원가입 정보 저장 실패");
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
		return result;
	}
	
	//DB와 사용자 로그인 아이디, 패스워드 일치 확인
	public MemberDto isLogin(String id, String pass1) {
		MemberDto mDto = null;

		try {
			getCon();
			String sql = "SELECT name FROM login WHERE id='" + id + "' AND pass1='" + pass1 + "'";
			pstmt = con.prepareStatement(sql);

			System.out.println("MemberDao - sql : " + sql);

			resultSet = pstmt.executeQuery();

			System.out.println("MemberDao - executeQuery OK");

			if (resultSet.next()) {
				System.out.println("MemberDao - resultSet[name] : " + resultSet.getString("name"));
				mDto = new MemberDto();
				mDto.setName(resultSet.getString("name"));
				mDto.setId(id);
				mDto.setPass1(pass1);
			}
		} catch (SQLException e) {
			System.out.println("불러오기 실패");
			e.printStackTrace();
		} finally {
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

		return mDto;
	}


	//회원수정 , 탈퇴 패스워드  확인 메소드
	public String getPass(String id) {

		String pass = "";

		try {
			getCon();
			String sql = "select pass1 from login where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				pass = resultSet.getString(1);
			}

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return pass;

	}
	
	//회원 수정 메소드
	public MemberDto editSearch(String id, String pass1) {
		MemberDto mDto = null;
		try {
			getCon();
			String sql = "SELECT * FROM login WHERE id='" + id + "' AND pass1='" + pass1 + "'";
			pstmt = con.prepareStatement(sql);
//            System.out.println("1");
//            pstmt.setString(1, id);
//            System.out.println("2");
//			pstmt.setString(2, pass1);
//			System.out.println("3");

			System.out.println("editSearch sql : " + sql);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				mDto = new MemberDto();
				mDto.setId(resultSet.getString(1));
				mDto.setPass1(resultSet.getString(2));
				System.out.println("acac email : " + resultSet.getString(3));
				mDto.setEmail(resultSet.getString(3));
				mDto.setName(resultSet.getString(4));
				mDto.setTel(resultSet.getString(5));
				mDto.setYear(resultSet.getString(6));
				mDto.setMap(resultSet.getString(7));
			}
			
			System.out.println("editUdate 불러오기 완료");
		} catch (SQLException e) {
			System.out.println("editUdate 불러오기 실패");
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


		return mDto;

	}

	//회원 수정 업데이트 메소드
	public int editUpdate(MemberDto mDto) {
		int result = 0;

		try {
			getCon();
			String sql = "UPDATE login SET pass1=?, email=?, name=?, tel=?, year=?, map=? WHERE id=?";
			
			System.out.println("MemberDao - editUpdate");
			System.out.println("mDto.getId() : " + mDto.getId());
			System.out.println("mDto.getPass1() : " + mDto.getPass1());
			System.out.println("mDto.getEmail() : " + mDto.getEmail());
			System.out.println("mDto.getName() : " + mDto.getName());
			System.out.println("mDto.getTel() : " + mDto.getTel());
			System.out.println("mDto.getYear() : " + mDto.getYear());
			System.out.println("mDto.getMap() : " + mDto.getMap());
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mDto.getPass1());
			pstmt.setString(2, mDto.getEmail());
			pstmt.setString(3, mDto.getName());
			pstmt.setString(4, mDto.getTel());
			pstmt.setString(5, mDto.getYear());
			pstmt.setString(6, mDto.getMap());
			pstmt.setString(7, mDto.getId());
			

//			System.out.println("editUpdate sql : " +  pstmt);
			result = pstmt.executeUpdate();
			
			

		}

		catch (Exception e) {
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
	
	//회원 탈퇴 메소드
	public int delete(String id, String pass1) {
        int result = 0;
        try {
            getCon();
            String sql = "DELETE FROM login WHERE id=? AND pass1=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pass1);

            result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("회원 탈퇴 성공");
            } else {
                System.out.println("회원 탈퇴 실패");
            }

            
        } catch (SQLException e) {
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
	
	//아이디 중복 확인 메소드
	public int confirmID(String id) {
		int result = -1;
	
		try {
			getCon();
			String sql = "select id from login where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				result =  1;
		    }else {
		    	result = -1;
		    }
			
		}catch(Exception e) {
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

	

	

