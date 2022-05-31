<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.GuestBookVo" %>
<%@ page import = "com.javaex.dao.GuestBookDao" %>

<%
	GuestBookDao guestBookDao = new GuestBookDao();
	List<GuestBookVo> guestList = guestBookDao.guestSelect();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="./gbc" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" id="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="65" rows="10" id="content" name="content" ></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add">
	</form>
	<br>
	
	<% for(int i = 0; i<guestList.size(); i++){%>
		<table border="1">
			<tr>
				<td><%=guestList.get(i).getNo()%>
				<td><%=guestList.get(i).getName()%></td>
				<td><%=guestList.get(i).getRegDate()%></td>
				<td><a href="./gbc?action=deleteForm&no=<%=guestList.get(i).getNo()%>">삭제</a>
			</tr>
			<tr>
				<td colspan="4"><%=guestList.get(i).getContent() %></td>
			</tr>
		</table>
		<br>
	<% } %>
	

</body>
</html>