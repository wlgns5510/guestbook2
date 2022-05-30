package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
    public GuestbookController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//post방식일때 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		//action 파라미터 꺼내기
		String action = request.getParameter("action");
		
		if("addList".equals(action)) {
			//데이터 가져오기
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getList();
			
			//requset에 데이터 추가
			request.setAttribute("guestList", guestList);
			
			//데이터 + html --> jsp 시키기
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");
			rd.forward(request, response);
		}
		else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			guestbookDao.insert(guestbookVo);
			
			response.sendRedirect("/.gbc?action=addList");
		}
		else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("number"));
			String password = request.getParameter("password");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			String pw = guestbookDao.get
		}
		else if("deleteForm".equals(action)) {
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
