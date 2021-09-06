<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${list2}" var="item">
	 <tr>
		<td style = "text-align:left;">${item.c_content}</td>
		<td>${item.tmpU_name}</td>
		<td>${item.c_time}</td>
		<c:if test="${sessionScope.user != null}">
			<td><button type="button" class="btn_reply">답글 쓰기</button></td>
		</c:if>
	</tr>
	<tr style="display: none;">
		<td colspan="4">
			<p> <input type = "text" name = "content"></p>
			<p> <input type = "button" value = "답글 쓰기" class="btnReplyPrc" group="${item.c_group}" depth="${item.c_depth}" order="${item.c_order}"></p>
		</td>
	</tr>
</c:forEach>
