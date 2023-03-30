<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link rel="icon" href="../../../resources/img/dog.png" />
<link rel="apple-touch-icon" href="../../../resources/img/dog.png" />
</head>
<body>
	<h1 style="text-align: center">게시글 상세</h1>
	<table class="table table-hover" style="text-align: center">
		<tr>
			<td>제목</td>
			<td><input type="text" value="${board.boardTitle }"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" value="${board.boardWriter }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="50" rows="10">${board.boardContents }</textarea></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="text" value="${board.boardFilename }"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="수정"> <input
				type="reset" value="취소"></td>
		</tr>
	</table>

	<!-- 	댓글 영역 -->
	<!-- 	댓글 등록 -->
	<table class="table table-hover" style="text-align: center">
		<tr>
			<td>작성자</td>
			<td><input type="text" id="rWriter"></td>
		</tr>
		<tr>
			<td><textarea rows="3" cols="55" id="rContents"></textarea></td>
			<td><button id="rSubmit">등록하기</button>
		</tr>
	</table>
	<!-- 	댓글 목록 -->
	<table align="center" width="500" border="1" id="replyTable">
		<thead>
			<tr>
				<!-- 					댓글갯수 -->
				<td colspan="4"><b id="replyCount"></b></td>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	<script>
		getReplyList();
		function getReplyList() {
			const boardNo = "${board.boardNo }";
			$.ajax({
				url : "/board/reply/list",
				data : {
					"boardNo" : boardNo
				},
				type : "GET",
				success : function(data) {
					const tableBody = $("#replyTable tbody");
					let tr;
					let rWriter;
					let rContent;
					let rCreateDate;
					let btnArea;
					if(data.length > 0){
						for(let i in data){
							tr = $("<tr>");
							rWriter = $("<td>").text(data[i].replyWriter);
							rContent = $("<td>").text(data[i].replyContents);
							rCreateDate = $("<td>").text(data[i].rCreateDate);
							tr.append(rWriter);
							tr.append(rContent);
							tr.append(rCreateDate);
							tableBody.append(tr);
						}
					}
					
				},
				error : function() {
					alert("ajax처리 실패..");
				}
			});
		}

		$("#rSubmit").on("click", function() {
			const boardNo = "${board.boardNo }"
			const rContents = $("#rContents").val();
			const boardWriter = $("#rWriter").val();
			$.ajax({
				url : "/board/reply/register",
				data : {
					"refBoardNo" : boardNo,
					"replyWriter" : boardWriter,
					"replyContents" : rContents
				},
				type : "POST",
				success : function(result) {
					if (result == '1') {
						alert("댓글 작성 성공");
						$("#rContents").val('');
						$("#rWriter").val('');
					} else {
						alert("댓글 작성 실패[로그 확인 필요]");
					}
				},
				error : function() {
					alert("ajax처리 실패");
				}
			})
		});
	</script>
</body>
</html>