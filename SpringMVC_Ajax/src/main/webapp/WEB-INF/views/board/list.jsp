<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link rel="icon" href="../../../resources/img/dog.png" />
<link rel="apple-touch-icon" href="../../../resources/img/dog.png" />
</head>

<body>
	<h1 style="text-align: center">게시글 목록</h1>
	<table class="table table-hover" style="text-align: center">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${bList }" var="list" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td><a href="/board/detail/${list.boardNo} ">${list.boardTitle }</a></td>
				<td>${list.boardWriter }</td>
				<td>${list.bCreateDate }</td>
				<td>${list.boardCount }</td>
				<td>${list.boardFilename }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6"><button onclick="location.href='/board/register'">게시글 작성</button></td>
		</tr>
	</table>
</body>

</html>