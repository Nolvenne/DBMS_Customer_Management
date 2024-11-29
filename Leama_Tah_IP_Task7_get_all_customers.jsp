<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Customers</title>
	</head>
	<body>
	<%@page import="Final_Project.DataHandlerP"%>
	<%@page import="java.sql.ResultSet"%>
	<%
		// We instantiate the data handler here, and get all the customers from the database
		final DataHandlerP handler1 = new DataHandlerP();
		final ResultSet customers = handler1.getAllCustomers();
	%>
	<!-- The table for displaying all the Customer records --> 
	<table  border="1">
		<tr> <!-- The table headers row -->
			<td align="center">
				<h4>Customer</h4>
			</td>
			<td align="center">
				<h4>Address</h4>
			</td>
			<td align="center">
				<h4>Category</h4>
		</tr>
		<%
			while(customers.next()) { // For each Customer record returned...
				// Extract the attribute values for every row returned
				final String Customer_Name = customers.getString("Customer_Name");
				final String C_address = customers.getString("C_address");
				final String Category = customers.getString("Category");
				out.println("<tr>"); // Start printing out the new table row
				out.println( // Print each attribute value
					"<td align=\"center\">" + Customer_Name +
					"</td><td align=\"center\"> " + C_address+
					"</td><td align=\"center\"> " + Category + "</td>");
				out.println("</tr>");
			}
			%>
		</table>
	</body>
</html>