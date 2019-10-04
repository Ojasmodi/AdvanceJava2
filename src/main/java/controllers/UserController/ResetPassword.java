package controllers.UserController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.UserManagement;

@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserManagement userManagement = new UserManagement();
		String username = (String) session.getAttribute("tempusername");
		if (username == null) {
			out.println(
					"<div class='alert alert-danger text-center'>Please login or reset your password to continue.</div>");
			request.getRequestDispatcher("/index.jsp").include(request, response);
		} else {
			String password = request.getParameter("password");
			String duplicatePassword = request.getParameter("duplicatePassword");
			try {

				if (password == null || duplicatePassword == null || password.isEmpty()
						|| duplicatePassword.isEmpty()) {
					out.println("<div class='alert alert-danger text-center'>Passwords can't be blank.</div>");
					request.getRequestDispatcher("/ResetPasswordForm.jsp").include(request, response);
				}

				else if (!duplicatePassword.equals(password)) {
					out.println("<div class='alert alert-danger text-center'>Both passwords didn't match.</div>");
					request.getRequestDispatcher("/ResetPasswordForm.jsp").include(request, response);
				}

				else {
					if (userManagement.resetPassword(username, password)) {
						session.removeAttribute("tempusername");
						out.println(
								"<div class='alert alert-success text-center'>Your password has been reset successfully.</div>");
						request.getRequestDispatcher("/index.jsp").include(request, response);

					} else {
						out.print(
								"<div class='alert alert-danger text-center'>Some error occured while processing your request! Please login to continue.</div>");
						request.getRequestDispatcher("/ResetPasswordForm.jsp").include(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
