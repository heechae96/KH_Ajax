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
	<table class="table table-hover" style="text-align: center"
		id="replyTable">
		<thead>
			<tr>
				<th>작성자</th>
				<th>댓글</th>
				<th>날짜</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<tr>
				<!-- 					댓글갯수 -->
				<td colspan="5"><b id="replyCount"></b></td>
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
					$("#replyCount").html('댓글 (' + data.length + ')');
					const tableBody = $("#replyTable tbody");
					/* 의도가 뭘까...? */
					tableBody.html("");
					let tr;
					let rWriter;
					let rContent;
					let rCreateDate;
					let btnArea;
					if (data.length > 0) {
						for ( let i in data) {
							tr = $("<tr>");
							rWriter = $("<td>").text(data[i].replyWriter);
							rContent = $("<td>").text(data[i].replyContents);
							rCreateDate = $("<td>").text(data[i].rCreateDate);
							btnArea1 = $("<td>").append(
									'<a href="javascript:void(0)" onclick = "modifyReply(this, \''
											+ data[i].replyContents + '\', '
											+ data[i].replyNo + ')">선택</a>')
							btnArea2 = $("<td>").append(
									'<a href="javascript:void(0)" onclick = "deleteReply('
											+ data[i].replyNo + ')">선택</a>');
							tr.append(rWriter);
							tr.append(rContent);
							tr.append(rCreateDate);
							tr.append(btnArea1);
							tr.append(btnArea2);
							tableBody.append(tr);
						}
					}

				},
				error : function() {
					alert("ajax처리 실패");
				}
			});
		}

		function modifyReply(obj, replyContents, replyNo) {
			let trModify = $("<tr>");
			trModify
					.append("<td colspan='4'><input type='text' id='modifyContent' value='"+replyContents+"'></td>");
			trModify.append("<td><button onclick='modifyReplyContents("
					+ replyNo + ")'>수정 완료</button></td>");
			$(obj).parent().parent().after(trModify);
			/* console.log($(obj).parent().parent());
			console.log($(obj).parent());
			console.log(obj); */
		}

		function modifyReplyContents(replyNo) {
			const modifiedContent = $("#modifyContent").val();
			$.ajax({
				url : "/board/reply/modify",
				data : {
					"replyContents" : modifiedContent,
					"replyNo" : replyNo
				},
				type : "POST",
				success : function(result) {
					if (result == '1') {
						alert("댓글 수정 성공");
						getReplyList();
					} else {
						alert("댓글 수정 실패[로그 확인 필요]");
					}
				},
				error : function() {
					alert("ajax처리 실패");
				}
			})
		};

		function deleteReply(replyNo) {
			if (confirm("정말로 삭제하시겠습니까?")) {
				$.ajax({
					url : "/board/reply/delete",
					data : {
						"replyNo" : replyNo
					},
					type : "GET",
					success : function(result) {
						if (result == '1') {
							alert("댓글 삭제 성공");
							getReplyList();
						} else {
							alert("댓글 삭제 실패[로그 확인 필요]");
						}
					},
					error : function() {
						alert("ajax처리 실패");
					}
				})
			}
		};

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