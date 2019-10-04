<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Image Management</title>
</head>
<body class="container">
	<%
		if (session.getAttribute("tempusername") == null) {
			out.println("<div class='alert alert-danger text-center'>Please login first.</div>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		} else {
	%>
	<h2 class="text-center text-info mt-2">
		<u><%=getServletContext().getInitParameter("app-title")%></u>
	</h2>
	<form action="ResetPassword" method="post" class="p-4 border border-info">
		<h4>Set your password:</h4>
		<div class="form-group">
			<label>Password</label> <input type="password" class="form-control"
				name="password" placeholder="Enter password">
		</div>
		<div class="form-group">
			<label>Re-enter password</label> <input type="password"
				name="duplicatePassword" class="form-control"
				placeholder="Re-enter password">
		</div>
		<div class="text-center">
			<button type="submit" class="btn btn-success btn-sm">Change
				Password</button>
			<button type="reset" class="btn btn-danger btn-sm">Reset</button>
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
	<%
		}
	%>
</body>
</html>