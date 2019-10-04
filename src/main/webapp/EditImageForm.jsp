<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="services.ImageManagement,models.Image"%>
<html>
<head>
<title>Update Image</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body class="container">
	<%
		if (session.getAttribute("uname") == null) {
			out.println("<div class='alert alert-danger text-center'>Please login first.</div>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		} else {
	%>
	<h2 class="text-center text-info m-2">
		<u><%=getServletContext().getInitParameter("app-title")%></u>
	</h2>
	<%
		long imageId = Long.parseLong(request.getParameter("id"));
			ImageManagement imageManagement = new ImageManagement();
			Image currentImage = imageManagement.getImageByImageId(imageId);
			String currentImagePath = currentImage.getImagePath();
			String fileName = currentImage.getImageName();
			String imageSrc = currentImage.getImagePath().substring(currentImagePath.lastIndexOf("\\") + 1);
			String fileNameWithoutExtension = (String) fileName.subSequence(0, fileName.lastIndexOf("."));
			String fileExtension = fileName.substring(fileName.lastIndexOf("."));
	%>
	<h3>
		<b><u>Update image here</u>-</b>
	</h3>
	<form action="EditImage" method="post" enctype="multipart/form-data">
		<img src="Images/<%=imageSrc%>" height="390px" width="600px" /><br>
		<b>Image Name:</b><input type="text"
			value="<%=fileNameWithoutExtension%>" name="imageName"><br>
		Choose a new Image: <input type="file" name="file" accept="image/*">
		<input type="hidden" name="extension" value="<%=fileExtension%>">
		<input type="hidden" name="id" value="<%=imageId%>"> <input
			type="submit" class="btn btn-success btn-sm m-2" value="Update Image">
		<input type="button" class="btn btn-warning btn-sm m-2"
			onclick="window.history.back()" value="Go Back">
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