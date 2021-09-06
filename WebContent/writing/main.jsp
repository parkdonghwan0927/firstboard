<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판(메인)</title>
<style>
	 h1 {
		text-align:center;
	}	
	table {
		text-align:center;
	}
	table tr th {
		font-weight : 100px;
		height : 50px;
	}
	table tr th {
		font-weight : 80px;
		height : 40px;
	}
	ul {
		width:400px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}
</style>
</head>
<body>
	<h1>게시판</h1>
	<table>
		<tr style="height : 70px;">
			<td colspan = "6">전체 게시글 수 : ${pagination.writingCount}</td>
		</tr> 
		<tr>
			<th style = "width:200px;"></th>
			<th style = "width:1000px;">제목</th>
			<th style = "width:500px;">작성자</th>
			<th style = "width:500px;">작성일</th>
			<th style = "width:300px;">조회</th>
			<th style = "width:200px;"></th>
		</tr>
		<c:forEach items="${list}" var="item">
			 <tr>
				<td><a href="writing-readWriting.do?w_no=${item.w_no}">${item.rownum}</a></td>
				<td style = "text-align:left;">${item.w_title}</td>
				<td>${item.tmpU_name}</td>
				<td>${item.w_time}</td>
				<td>${item.w_views}</td>
				<td><a href="writing-backWrite.do?w_no=${item.w_no}&w_group=${item.w_group}&w_depth=${item.w_depth}&w_order=${item.w_order}">답글 쓰기</a></td>
			</tr>
		</c:forEach>
		<tr style = "height : 50px;">
		</tr>
		<c:if test="${sessionScope.user == null}">
			<tr>
			<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th style = "width : 200px; font-weight : 120; background-color : #818181; color : #fff;"><a href="user-insert-user.do">회원가입</a></th>
			</tr>
			<tr>
			<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th style = "width : 200px; font-weight : 120; background-color : #818181; color : #fff;"><a href="user-login.do">로그인</a></th>
			</tr>
			</c:if>
			<c:if test="${sessionScope.user != null}">
			<tr>
			<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th style = "width : 200px; font-weight : 120; background-color : #818181; color : #fff;"><a href="writing-write.do">글쓰기</a></th>
			</tr>
			<tr>
			<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th> </th>
				<th style = "width : 200px; font-weight : 120; background-color : #818181; color : #fff;"><a href="writing-logout.do">로그아웃</a></th>
			</tr>
			</c:if>
		<tr>
			<c:if test="${sessionScope.user == null}">
				
			</c:if>
		</tr>
	</table>
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage > 0}">
					<li>
						<a href="writing-main.do?currentPage=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
					<c:choose>
						<c:when test="${ pagination.currentPage eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.currentPage ne i }">
							<li>
								<a href="writing-main.do?currentPage=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">
						<a href="writing-main.do?currentPage=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>