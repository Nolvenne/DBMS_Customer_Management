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
		final DataHandlerP handler = new DataHandlerP();
	// Get the attribute values passed from the input form.
		String lower_b = request.getParameter("Lower_b");
		String upper_b = request.getParameter("Upper_b");
	
		if (lower_b.equals("")||upper_b.equals("")) {
			response.sendRedirect("retrieve_customer_form.jsp");
		} else {
		int Lower_b1 = Integer.parseInt(lower_b);
		int Upper_b1 = Integer.parseInt(upper_b);
		// Now perform the query with the data from the form.		
		final ResultSet customers = handler.retrieveCustomers(Lower_b1, Upper_b1);
	%> 
	<!-- The table for displaying all the Customer records --> 
	<table  border="1">
		<tr> <!-- The table headers row -->
			<td align="center">
				<h4>Customer</h4>
			</td>
			<td align="center">
				<h4>category</h4>
			</td>
		<%
			 while(customers.next()) { // For each Customer record returned...
				// Extract the attribute values for every row returned
				final String Customer_Name = customers.getString("Customer_Name");
				final String Category = customers.getString("Category");
				out.println("<tr>"); // Start printing out the new table row
				out.println( // Print each attribute value
					"<td align=\"center\">" + Customer_Name + 
					"</td><td align=\"center\"> " + Category + "</td>");
				out.println("</tr>");
			}
		} 
			%>
		</table>
	</body>
</html>