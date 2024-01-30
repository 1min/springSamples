<%@page import="ssg.com.a.dto.BbsParam"%>
<%@page import="util.BbsUtil"%>
<%@page import="ssg.com.a.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BbsDto> list = (List<BbsDto>)request.getAttribute("list");
	int pageBbs = (Integer)request.getAttribute("pageBbs");
	
	BbsParam param = (BbsParam)request.getAttribute("param");
	int pageNumber = param.getPageNumber();
	String choice = param.getChoice();
	String search = param.getSearch();
	
	if(search == null || search.equals("")){
		search = "";
		choice = "start";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="./js/jquery.twbsPagination.min.js"></script>
<style>
.center {
	margin: auto;
	width: 1000px;
	text-align: center;
}

th {
	background: royalblue;
	color: white;
}

tr{
	line-height: 12px;
}
</style>
</head>
<body>
<h2>게시판</h2>
<br/>
<a href="pdslist.do">자료실</a>

<div class="center">
<table class="table table-hover">
<col width="70"/><col width="600"/><col width="100"/><col width="150"/>
<thead>
	<tr>
		<th>번호</th><th>제목</th><th>조회수</th><th>작성자</th>
	</tr>
</thead>
<tbody>
<%
if(list == null || list.size() == 0){
	%>
	<tr>
		<td colspan="4">작성된 글이 없습니다</td>
	</tr>
	<%
}else{
	for(int i=0;i <list.size();i++){
		BbsDto bbs = list.get(i);
		%>
		<tr>
			<td><%=i + 1 %></td>
			<td style = "text-align:left;">
			<%
			if(bbs.getDel() == 0){
				%>
				<a href="bbsdetail.do?seq=<%=bbs.getSeq() %>">
					<%=BbsUtil.arrow(bbs.getDepth()) %>
					<%=BbsUtil.dot3(bbs.getTitle()) %>
				</a>
				<%
			}else{
				%>
				<%=BbsUtil.arrow(bbs.getDepth()) %>
				<font color="#ff0000">***** 이 글은 작성자에 의해서 삭제되었습니다 *****</font>
				<%
			}
			%>
			</td>
			<td><%=bbs.getReadcount() %></td>
			<td><%=bbs.getId() %></td>
		</tr>
		<%
	}
}

%>
</tbody>
</table>
<br>
<div class="container">
    <nav aria-label="Page navigation">
    	<!-- flex라서 justify-content:center로 중앙정렬해야함 -->
        <ul class="pagination" id="pagination" style="justify-content:center"></ul>
    </nav>
</div>
<br/>
<div class="form-row align-items-center d-flex justify-content-center align-items-center container">
	<select id="choice" class="form-control" style="width:auto;">
		<option value="start">검색</option>
		<option value="title">제목</option>
		<option value="content">컨텐츠</option>
		<option value="writer">작성자</option>
	</select>
	
	<!-- size 조절하는 bootstrap 클래스 col-sm-3 my-1 -->
	<div class="col-sm-3 my-1" style="width:auto;">
		<input type="text" class="form-control" id="search" value="<%=search%>">
	</div>
	<button type="button" class="btn btn-primary" onclick="searchBtn()">검색</button>
</div>

<br/>
<a href="bbswrite.do">글쓰기</a>
</div>
<script>
let search = "<%=search%>";
if(search != null){
	let choice = document.getElementById("choice");
	choice.value = "<%=choice %>";
	choice.setAttribute("selected","selected");
}

function searchBtn() {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	
	location.href = "bbslist.do?choice=" + choice + "&search=" + search;
}

$("#pagination").twbsPagination({
	startPage: <%=pageNumber+1 %>,
	// 위에서 작성한 페이지 총수
	totalPages: <%=pageBbs %>,
	// << 이전{여기}다음 >> 사이에 보이는 최대 페이지수
	visiblePages: 10,
	// 맨 처음 페이지로 가게함 '<<'
	first: '<span sris-hidden="true">«</span>',
	prev:"이전",
	next:"다음",
	// 맨 마지막 페이지로 가게함 '>>'
	last:'<span sris-hidden="true">»</span>',
	// 이거 안해주면, 클릭 안하더라도 onPageClick에 바인드된 함수가 페이지 로드될때 한번 실행됨
	initiateStartPageClick:false, // 처음 실행시에 자동실행 되지 않도록
	onPageClick:function(event, page){
		let choice = document.getElementById("choice").value;
		let search = document.getElementById("search").value;
		
		//																						백엔드쪽에서 0이 page1이라서 -1해줌	
		location.href = "./bbslist.do?choice=" + choice + "&search=" + search + "&pageNumber=" + (page - 1);
	}
})
</script>
</body>
</html>