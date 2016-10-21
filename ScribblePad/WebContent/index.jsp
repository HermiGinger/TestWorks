<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.myapp.scrapbook.DBConnector"%>
<%@page import="java.sql.DriverManager"%>


<%
	java.sql.Connection con = null;
	java.sql.Statement st = null;
	java.sql.ResultSet rs = null;
	DBConnector dbConnector = new DBConnector();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
	

	try {
		con = dbConnector.getConnection();
	} catch (Exception e) {
		System.out.println("Error : Could not connect to database" + e);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scrap Pad</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js-libraries/angular.js"></script>
 
</head>
<body ng-app>
{{10+20}}
	<div id="container">
		<header>
		<h1>Scrap here!</h1>
		</header>
		<%
			String sql = "Select * from scraps";
			try {
				st = con.createStatement();
				rs = st.executeQuery(sql);
		%>
		<div id="scraps">
				<ul>
				<%
					while (rs.next()) {
				%>
				<li class="scrap"><span><%=LocalTime.parse(rs.getString("instants"),DateTimeFormatter.ISO_LOCAL_TIME) %> - </span><b><%=rs.getString("username")%></b>
					: <%=rs.getString("message")%></li>
				<%
					}
					} catch (Exception e) {
						System.out.println(e);
					}
				%>
			</ul>
		</div>
		<div id="input">
		<%if(request.getParameter("error")!=null){
			String error = request.getParameter("error");
		
			%>
		<div class="error"><%=error%></div>
		<%} %>
			<form method="post" action="scrapLoader">
				<input type="text" name="user" placeholder="Enter your Name" /> 
				<input type="text" name="message" placeholder="Scrap here!" /> 
				</br> 
				<input class="scrap-button" type="submit" name="submit" value="Scrap!" />
			</form>
		</div>
	</div>
</body>
</html>