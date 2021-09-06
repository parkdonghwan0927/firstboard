<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
<body>
	<table>
		<tr style = "width : 800px; height : 100px; text-align : center;">
			<td>${writing.w_title}</td>
		</tr>
		<tr  style = "height : 50px; text-align : right;">
			<td>${writing.tmpU_name}</td>
		</tr>
		<tr  style = "height : 500px; text-align : center;">
			<td>${writing.w_content}</td>
		</tr>
		<tr style="height:50px;">
			<c:if test="${sessionScope.user.u_idx == writing.w_writer}">
				<td style="border:none;">
					<a href="writing-editWriting.do?w_no=${writing.w_no}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >수정</a>
				</td>
			</c:if>
			<c:if test="${sessionScope.user.u_role == 10 or sessionScope.user.u_idx == writing.w_writer}">
				<td style="border:none;">
					<a href="writing-deleteWriting.do?w_no=${writing.w_no}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
				</td>
			</c:if>
		</tr>
	</table>
	<table id="cmtList">
		<c:forEach items="${list}" var="item">
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
	</table>
	<c:if test="${sessionScope.user != null}">
		<table>
			<tr style = "height : 50px;">
				<td colspan="4">
						<p> <input type = "text" name = "content"></p>
						<p> <input type = "button" value = "댓글 쓰기" class="btnReplyPrc2"></p>
				</td>
			</tr>
		</table>
	</c:if>
<script>
$(document).on('click', '.btn_reply', function () {
	$(this).parent().parent().next().show();
});

$(document).on('click', '.btnReplyPrc', function () {
	let group = $(this).attr('group');
	let depth = $(this).attr('depth');
	let order = $(this).attr('order');
	let content = $(this).parent().prev().find('input[name="content"]').val();
	
	$.ajax({
	  method: "POST",
	  url: "comments-process-writeComments.do",
	  data: { w_no: ${writing.w_no}, master: ${writing.w_group}, group: group, depth: depth, order: order, content: content }
	})
	  .done(function( data ) {
	    	$('#cmtList').html(data)
	  });
});

$(document).on('click', '.btnReplyPrc2', function () {
	let $cntnt = $(this).parent().prev().find('input[name="content"]');
	let content = $cntnt.val();
	
	$.ajax({
	  method: "POST",
	  url: "comments-process-writeComments.do",
	  data: { w_no: ${writing.w_no}, master: ${writing.w_group}, content: content }
	})
	  .done(function( data ) {
	    	$('#cmtList').html(data)
	    	$cntnt.val("");
	  });
});
</script>
</body>
</html>