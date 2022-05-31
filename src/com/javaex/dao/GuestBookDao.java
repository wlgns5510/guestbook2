package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDao {

	// 필드
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 메소드 gs

	// 메소드 일반
	
	//드라이버 불러오기
	public void getConnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//자원정리
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//추가하기
	public int guestBookInset(GuestBookVo guestBookVo) {
		int count = 0;
		getConnection();
		
		try {
						
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, guestBookVo.getName());
			pstmt.setString(2, guestBookVo.getPassword());
			pstmt.setString(3, guestBookVo.getContent());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			
			System.out.println(count + "건이 입력되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return count;
	}
	
	//삭제하기
	public int guestBookDelete(int no) {
		int count = 0;
		getConnection();
		
		try {
						
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return count;
	}
	
	//전체리스트
	public List<GuestBookVo> guestSelect() {

		// 리스트로 만들기
		List<GuestBookVo> guestList = new ArrayList<GuestBookVo>();
		getConnection();

		try {
			// SQL문 준비
			String query = "";
			query += " select no,";
			query += " 		  name,";
			query += " 		  password,";
			query += " 		  content,";
			query += " 		  reg_date";
			query += " from guestbook ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			// resultSet 가져오기
			rs = pstmt.executeQuery();

			// 결과처리
			// 반복문으로 Vo만들어 List에 추가하기
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestBookVo guestBookVo = new GuestBookVo(no, name, password, content, regDate);

				guestList.add(guestBookVo);

			}

			for (int i = 0; i < guestList.size(); i++) {
				guestList.get(i).toString();
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();

		return guestList;

	}
	
	//특정 비밀번호
	public String getPassword(int no) {
		String pw ="";
		getConnection();
		try {
						
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// SQL문 준비
			String query = "";
			query += " select password ";
			query += " from guestbook ";
			query += " where no = ? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			
			while(rs.next()) {
				
				pw = rs.getString("password");
				
			}
			System.out.println(pw);

			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return pw;
		
	}

}