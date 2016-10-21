package com.myapp.scrapbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myapp.scrapbook.*;

/**
 * Servlet implementation class ScrapLoader
 */
@WebServlet(description = "For saving and reloading scraps", urlPatterns = { "/ScrapLoader" })
public class ScrapLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String time;
		String user;
		String message;

		java.sql.Connection con = null;
		java.sql.Statement st = null;
		java.sql.ResultSet rs = null;
		DBConnector dbConnector = new DBConnector();
		String sql = null;

		
		user = (request.getParameter("user") == "") ? null : request.getParameter("user");
		message = (request.getParameter("message") == "") ? null : request.getParameter("message");
		// PrintWriter pw = response.getWriter();
		System.out.println(user + " " + message);
		if (user != null && message != null) {
			System.out.println("1");
			sql = "insert into scraps (username,message,instants) values('"+user+ "','" + message + "', now());";

			try {
				con = dbConnector.getConnection();
			} catch (Exception e) {
				System.out.println("Error : Could not connect to database" + e);
			}
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				System.out.println("Success");
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				
				System.out.println(e+sql);
			}
		}
		else{
			String error = "Please enter your name and message";
			response.sendRedirect("index.jsp?error="+error);
	}}
}
