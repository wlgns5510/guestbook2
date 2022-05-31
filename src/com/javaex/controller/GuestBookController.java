package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;
import com.javaex.util.WebUtil;


@WebServlet("/gbc")
public class GuestBookController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;

	//디폴트 생성자
	public GuestBookController() {
    }

	//메소드 gs
	
	//메소드 일반
	//get방식 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post방식일때, 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		// action 파라미터 꺼내기
		String action = request.getParameter("action");
		
		
		if("addList".equals(action)) { //action이 addList일때
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		}else if("add".equals(action)) { //action이 add일때
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo guestBookVo = new GuestBookVo(name, password, content);
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookInset(guestBookVo);
			
			//리다이렉트 list
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
		}else if("delete".equals(action)) { //action이 delete일때
			
			int no = Integer.parseInt(request.getParameter("number"));
			String password = request.getParameter("password");
			
			GuestBookDao guestBookDao = new GuestBookDao();
			String pw = guestBookDao.getPassword(no);
			
			
			
			if(password.equals(pw)){
				guestBookDao.guestBookDelete(no);
			}
			
			response.sendRedirect("./gbc?action=addList");
			
		}else if("deleteForm".equals(action)) { //action이 deleteFrom일때
			
			//포워드
			WebUtil.forward(request, response,"/WEB-INF/deleteForm.jsp");	
		}
		

	}
	//post방식 호출메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}