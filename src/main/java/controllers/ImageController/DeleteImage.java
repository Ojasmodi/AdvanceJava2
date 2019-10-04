package controllers.ImageController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ImageManagement;

@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {

	// method to delete image from the database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		long imageId = Long.parseLong(request.getParameter("id"));
		ImageManagement imageManagement = new ImageManagement();
		imageManagement.deleteImage(imageId);
		out.println("<div class='alert alert-success text-center'>Image deleted successfully.</div>");
		request.getRequestDispatcher("/image.jsp").include(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
