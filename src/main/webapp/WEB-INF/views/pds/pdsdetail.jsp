<%@page import="ssg.com.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PdsDto dto = (PdsDto)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>자료상세 보기</h2>
<br/>
<div align="center">
<table border="1">
<col width="150"><col width="500">
<tr>
	<th>아이디</th>
	<td><%=dto.getId() %></td>
</tr>
<tr>
	<th>다운로드 카운트</th>
	<td><%=dto.getDowncount() %></td>
</tr>
<tr>
	<th>조회수</th>
	<td><%=dto.getReadcount() %></td>
</tr>
<tr>
	<th>등록일</th>
	<td><%=dto.getRegdate() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%=dto.getTitle() %></td>
</tr>
<tr>
	<th>다운로드</th>
	<td>
		<%=dto.getFilename() %>&nbsp;&nbsp;
		<input type="button" value="다운로드" 
			onclick="filedownload(<%=dto.getSeq() %>, '<%=dto.getNewfilename() %>', '<%=dto.getFilename() %>')" />
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="5" cols="50"><%=dto.getContent() %></textarea>
	</td>
</tr>
</table>
</div>

<script>
function filedownload(seq, newfilename, filename){
	location.href = "filedownload.do?seq=" + seq + "&newfilename=" + newfilename + "&filename=" + filename;
}
</script>


</body>
</html>