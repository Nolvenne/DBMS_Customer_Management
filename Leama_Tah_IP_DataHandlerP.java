package Final_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHandlerP {

		private Connection conn;
	// Azure SQL connection credentials
	private String server = "tah0001.database.windows.net";
	private String database = "cs_dsa_4513_F23";
	private String username = "tah0001";
	private String password = "*****";
	// Resulting connection string 
	final private String url =
	String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;host NameInCertificate=*.database.windows.net;loginTimeout=30;",
	server, database, username, password);
	// Initialize and save the database connection
	private void getDBConnection() throws SQLException {
	if (conn != null) {
	return; }
	this.conn = DriverManager.getConnection(url); }
	
	// Return the result of selecting everything from the Customer table
	public ResultSet getAllCustomers() throws SQLException {
		getDBConnection(); // Prepare the database connection
		// Prepare the SQL statement
		final String sqlQuery = "SELECT * FROM CustomerInfo;";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		// Execute the query
return stmt.executeQuery();
	}
	
	// Return the result of selecting Customers with their Category in the given range from Customer table
	public ResultSet retrieveCustomers(int Lower_b, int Upper_b) throws SQLException {
			getDBConnection(); // Prepare the database connection
		
			// Prepare the SQL statement
			final String sqlQuery = "SELECT Customer_Name, Category FROM customerInfo" + 
					"    WHERE Category >= ? AND Category <= ? ORDER BY 1 ;";
			final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
			// Replace the '?' in the above statement with the given attribute values
			stmt.setInt(1, Lower_b);
			stmt.setInt(2, Upper_b);
			// Execute the query
	return stmt.executeQuery();
		}
	// Inserts a record into the CustomerInfo table with the given attribute values
	public boolean addCustomer(
			String Customer_Name, String C_address, int Category) 
					throws SQLException {
		getDBConnection(); // Prepare the database connection
		// Prepare the SQL statement
		final String sqlQuery =
							"INSERT INTO customerInfo " +
									"(Customer_Name, C_address, Category) " +
										"VALUES " +
										"(?, ?, ?)";
		final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
		// Replace the '?' in the above statement with the given attribute values
		stmt.setString(1, Customer_Name);
		stmt.setString(2, C_address);
		stmt.setInt(3, Category);
		// Execute the query, if only one record is updated, then we indicate success by returning true
		return stmt.executeUpdate() == 1;
	}
}
