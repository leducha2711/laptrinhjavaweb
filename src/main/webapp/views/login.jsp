<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
						${message}
					</div>
				</c:if>
				<form action='<c:url value="/dang-nhap"/>' id="formLogin" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="inputEmail" 
							name="username" id="username"placeholder="Tên đăng nhập">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="inputPassword" 
							name="password" id="password" placeholder="Mật khẩu">
					</div>
					<input type="hidden" value="login" name="action">
					<button type="submit" class="btn btn-primary">Đăng nhập</button>
				</form>
			</div>
			
		</div>
	</div>
</body>
</html>