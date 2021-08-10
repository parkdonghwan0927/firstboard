<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 결과</title>
</head>
<style>
	table {
		border-collaspe : collapse;
		border : 2px solid;
		margin : 40px auto;
		width : 1000px;
	}
	
	table tr td {
		border : 1px solid;
	}
	
</style>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String context = request.getParameter("context");
	String writer = request.getParameter("writer");
%>
	<table>
		<tr style = "width : 800px; height : 100px; text-align : center;">
			<td><%=title %></td>
		</tr>
		<tr  style = "height : 50px; text-align : right;">
			<td>작성자 : <%=writer %></td>
		</tr>
		<tr  style = "height : 500px; text-align : center;">
			<td><%=context %></td>
		</tr>
		<tr style="height:50px; text-align : right;">
			<td style="border:none;"><span>수정<span><span style="padding-left: 20px;">삭제</span></td>		
		</tr>	
	</table>
</body>
</html>