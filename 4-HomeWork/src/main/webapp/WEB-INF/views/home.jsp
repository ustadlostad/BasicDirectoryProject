<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div>
			<h1 style="text-align: center;">Login Page</h1>
			<br>
		</div>
			<form action='<s:url value="/"></s:url>' method="post">
				<input required="required" name="uemail" type="email" class="form-control" placeholder="Email"/>
				<br>
				<input required="required" name="upassword" type="password" class="form-control" placeholder="Password" />
				<br>
					<div style="text-align: center;">
						<input type="submit" value="Send" class="btn-success"/>
					</div>
					<br>
					<div style="text-align: center;">
					<a class="btn btn-danger" href='<s:url value="/registerPage"></s:url>' role="button">Register</a>
					</div>		
			</form>
			
			<br>
			<c:if test="${not empty loginFail}">
				<div class="alert alert-warning alert-dismissible fade show" role="alert">
  					<strong>${loginFail}</strong> 
  						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    						<span aria-hidden="true">&times;</span>
  						</button>
				</div>
			</c:if>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

</html>