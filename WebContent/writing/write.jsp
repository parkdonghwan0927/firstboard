<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h2>글쓰기</h2>
	<form action = "writeresult.jsp" name = "writing" method = "post">
		<p> 제목 : <input type = "text" name = "title"></p>
		<p> 내용 : <input type = "text" name = "context"></p>
		<p> 작성자 : <input type = "text" name = "writer"></p>
		<p> <input type="submit" value="작성하기"></p>
	</form>
</body>
</html>