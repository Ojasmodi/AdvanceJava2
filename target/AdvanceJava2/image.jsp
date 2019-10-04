<%@page import="utilities.SizeConversion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,models.Image,services.ImageManagement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>Image Management</title>
</head>
<body class="container">
	<%
		if (session.getAttribute("uname") == null) {
			out.println("<div class='alert alert-danger text-center'>Please login first.</div>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		} else {
	%>
	<h2 class="text-center text-info mt-2">
		<u><%=getServletContext().getInitParameter("app-title")%></u>
		<%!double totalSizeOfImages = 0.0;%>
	</h2>
	<div>
		<span class="text-capitalize"><h3>
				Welcome
				<%
			String currentUser = (String) session.getAttribute("uname");
				ImageManagement imageManagement = new ImageManagement();
				SizeConversion sizeConversion = new SizeConversion();
				List<Image> listOfImages = imageManagement.getAllImagesByUserName(currentUser);
		%>
				<b><%=currentUser%></b>
				<h3></h3></span> <span class="float-right">
			<button type="button" onclick="window.location.href = 'image.jsp'"
				class=" btn btn-outline-dark btn-sm mr-2">Refresh</button> <a
			class="btn btn-outline-info btn-sm" href="Logout">Logout</a>
		</span>
	</div>
	<br>
	<br>
	<h5>Please choose an image to upload:(*only .jpg, .jpeg and .png
		are allowed.)</h5>
	<div class="m-2">
		<form action="ImageUpload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" accept="image/*" multiple> <br>
			<input class="btn btn-success btn-sm mt-2" type="submit"
				value="Upload"> <input class="btn btn-dark btn-sm mt-2 ml-2"
				type="reset" value="Clear">
		</form>
	</div>
	<%
		if (!listOfImages.isEmpty()) {
				totalSizeOfImages = 0.0;
	%><hr>
	<h3>
		<u>Uploaded Images:</u>
	</h3>
	<table class="table m-2">
		<thead class="thead-dark">
			<tr>
				<th scope="col">ImageId</th>
				<th scope="col">ImageName</th>
				<th scope="col">Size(MB)</th>
				<th scope="col">Image</th>
				<th scope="col">Delete</th>
				<th scope="col">Edit</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Image image : listOfImages) {
							String imageSrc = image.getImagePath().substring(image.getImagePath().lastIndexOf("\\") + 1);
							totalSizeOfImages += image.getImageSize();
			%>
			<tr>
				<td><%=image.getImageId()%></td>
				<td><%=image.getImageName()%></td>
				<td><%=sizeConversion.convertFromBytesToMegaBytes(image.getImageSize())%></td>
				<td><img src="Images/<%=imageSrc%>" height="120px"
					width="100px" />
				<td><a class="btn btn-danger btn-sm"
					href="DeleteImage?id=<%=image.getImageId()%>">Delete</a></td>
				<td><a class="btn btn-warning btn-sm"
					href="/AdvanceJava2/EditImageForm.jsp?id=<%=image.getImageId()%>">Edit</a></td>
			</tr>
			<%
				}
					} else {
			%>
			<span class="text-black-50 ml-2">No images found.</span>
			<%
				}
			%>

		</tbody>
	</table>
	<h5>
		Total size of uploaded images:&nbsp;<%=sizeConversion.convertFromBytesToMegaBytes(totalSizeOfImages)%>MB
	</h5>
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