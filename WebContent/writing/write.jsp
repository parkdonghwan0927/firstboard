<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기2</title>
</head>
<body>
	<h2>글쓰기</h2>
	<form action = "writing-successWrite.do" name = "writing" method = "post">
		<p> <input type = "hidden" name = "no" value = "${w_no}"></p>
		<p> 제목 : <input type = "text" name = "title"></p>
		<p> 내용 : <input type = "text" name = "content"></p>
		<p> <input type = "hidden" name = "group" value = "${w_group}"></p>
		<p> <input type = "hidden" name = "order" value = "${w_order}"></p>
		<p> <input type = "hidden" name = "depth" value = "${w_depth}"></p>
		<p> <input type="submit" value="작성하기"></p>
	</form>
</body>
</html>