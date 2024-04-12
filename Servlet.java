package expense_income_tracker;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/indexServlet")
public class Servlet extends HttpServlet {
 
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	 response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  HttpSession session = request.getSession();
  String description = request.getParameter("desription");
  session.setAttribute("description", description);
  String amount = request.getParameter("amount");
  String category = request.getParameter("category");
  session.setAttribute("category", category);
  
  if(LoginDao.validate(email, password)) {
	  out.print("You are successfully loggedin...");
	    request.getRequestDispatcher("Welcome").include(request, response);
	   } else {
	    out.println("Username or Password incorrect");
	    request.getRequestDispatcher("Login.html").include(request, response);
	   }  
  
 }
}