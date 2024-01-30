<%@page import="ssg.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto login = (MemberDto)session.getAttribute("login");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
.center {
	margin: auto;
	width: 800px;
	text-align: center;
}

th {
	background: royalblue;
	color: white;
}
</style>
</head>
<body>
<h2>글쓰기</h2>
<div class="center">
<form id="frm" method="post">
<table class="table table-bordered">
<col width="200"/><col width="500"/>
<tbody>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="id" class="form-control" value="<%=login.getId() %>" readonly>
	</td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" id="title" name="title" class="form-control" placeholder="제목을 기입">
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="15" cols="50" id="content" class="form-control" name="content"></textarea>
	</td>
</tr>
</tbody>
</table>
<br/>
<button type="button" id="writeBtn" class="btn btn-primary">글쓰기</button>
</form>
</div>
<script>
$(document).ready(function(){
	$("#writeBtn").on("click", function(){
		// 제목이 비어있는지 체크
		if ($("#title").val().trim() === ""){
			alert("제목을 기입해 주십시오");
			return;
		}
		
		// 내용이 비어있는지 체크
		if ($("#content").val().trim() === ""){
			alert("내용을 기입해 주십시오")
			return;
		}
		
		$("#frm").attr("action", "bbswriteAf.do").submit();
	});
})

</script>
</body>
</html>