<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>사진 게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>

<body>
	<h1>사진 게시판</h1>
	<div id="photo-wrapper" style="padding: 100px"></div>
	<div id="photo-btn-container" style="text-align: center">
		<button>더보기</button>
	</div>
	<script>
		fn_more();
		function fn_more() {
			$.ajax({
				url : "/photo/more",
				data : {
					start : 1
				},
				type : "POST",
				success : function(data) {
					console.log(data);
				},
				error : function() {
					alert("ajax처리 실패");
				}
			})
		}
	</script>
</body>

</html>