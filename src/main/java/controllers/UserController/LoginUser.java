package controllers.UserController;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.UserManagement;

@WebServlet("/UserController/LoginUser")
public class LoginUser extends HttpServlet {

	// method for user login
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserManagement userManagement = new UserManagement();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {

			if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
				out.println("<div class='alert alert-danger text-center'>Username or password can't be blank.</div>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}

			else {
				if (userManagement.isUserExists(username, password)) {
					session.setAttribute("uname", username);
					request.getRequestDispatcher("/image.jsp").forward(request, response);

				} else {
					out.print("<div class='alert alert-danger text-center'>Sorry UserName or Password Error!</div>");
					request.getRequestDispatcher("/index.jsp").include(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
