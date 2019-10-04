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

@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserManagement userManagement = new UserManagement();
		String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		try {

			if (username == null || answer == null || username.isEmpty() || answer.isEmpty()) {
				out.println("<div class='alert alert-danger text-center'>Username or answer can't be blank.</div>");
				request.getRequestDispatcher("/VerifyUserForm.jsp").include(request, response);
			}

			else if (userManagement.isUserExists(username)) {
				session.setAttribute("tempusername", username);
				if (userManagement.isUserQuestionAndAnswerRight(username, question, answer)) {
					request.getRequestDispatcher("/ResetPasswordForm.jsp").forward(request, response);
				} else {
					out.print(
							"<div class='alert alert-danger text-center'>Secret question or answer is incorrect.!</div>");
					request.getRequestDispatcher("/VerifyUserForm.jsp").include(request, response);
				}

			} else {
				out.print("<div class='alert alert-danger text-center'>Username doesn't exist!</div>");
				request.getRequestDispatcher("/VerifyUserForm.jsp").include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
