<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<h1> 글 수정</h1>
 	<form action="writing-successEdit.do" name="writing" method="post">
 		<p> <input type = "hidden" name = "no" value="${writing.w_no}"></p>
	    <p> 제목 : <input type = "text" name = "title" value = "${writing.w_title}"></p>
		<p> 내용 : <input type = "text" name = "content" value = "${writing.w_content}"></p>
		<p> <input type="submit" value="수정완료"></p>
	</form>
</body>
</html>