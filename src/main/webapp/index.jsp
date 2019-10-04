<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Image Management</title>
</head>
<body class="container">
	<h2 class="text-center text-info mt-2">
		<u><%=getServletContext().getInitParameter("app-title")%></u>
	</h2>
	<form action="LoginUser" method="post" class="p-4 border border-info">
		<h4>Please login to continue:</h4>
		<div class="form-group">
			<label>Email address</label> <input type="text" class="form-control" 
				name="username" placeholder="Enter email">
		</div>
		<div class="form-group">
			<label>Password</label> <input type="password" name="password" 
				class="form-control" placeholder="Password">
		</div>
		<div class="text-center">
			<button type="submit" class="btn btn-success btn-sm">Login</button>
			<button type="reset" class="btn btn-danger btn-sm">Reset</button>
			<a type="button" href="/AdvanceJava2/VerifyUserForm.jsp"
				class="btn btn-warning btn-sm">Reset password</a>
		</div>

	</form>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
