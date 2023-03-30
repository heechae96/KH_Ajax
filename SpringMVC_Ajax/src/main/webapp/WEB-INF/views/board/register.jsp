<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link rel="icon" href="../../../resources/img/dog.png" />
<link rel="apple-touch-icon" href="../../../resources/img/dog.png" />
</head>
<body>
	<h1 style="text-align: center">게시글 등록 페이지</h1>
	<form action="/board/register" method="post">
		<table class="table table-hover" style="text-align: center">
			<tr>
				<td>제목</td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="boardWriter"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="50" rows="10" name="boardContents"></textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="boardFilename"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>