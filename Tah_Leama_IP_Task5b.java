package Final_Project;
import java.io.*;
//Importing the necessary Packages
import java.util.Scanner;
import java.sql.Connection; 
import java.sql.Statement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.DriverManager;
import java.io.File;
import java.io.FileWriter;

public class Performed_Queries {

	public static void main(String[] args) throws SQLException { 
		// Connect to database 
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		final String hostName = "tah0001.database.windows.net";
		final String dbName = "cs_dsa_4513_F23"; 
		final String user = "tah0001"; 
		final String password = "******";
		final String url =
String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;host NameInCertificate=*.database.windows.net;loginTimeout=30;",
		hostName, dbName, user, password);
	
		try (final Connection connection = DriverManager.getConnection(url)) {
			final String schema = connection.getSchema(); 
			System.out.println("Successful connection - Schema:" + schema);  
			System.out.println("========================================="); 
			
			

			//Scanner myScan = new Scanner(System.in);
			int Choice = 0;
			while(Choice != 18) 
			{
			
			// printing out the available choices for the User to choose.
			System.out.println("Choose one Option:");
			System.out.println("1. Enter a new Customer"); 
			System.out.println("2. Enter a new Department"); 
			System.out.println("3. Enter a new assembly"); 
			System.out.println("4. Enter a new process and information related to type");
			System.out.println("5. Create a new account and associate it with the one of the 3 type");
			System.out.println("6. Enter a new Job");
			System.out.println("7. Enter the date job is completed and the type of job");
			System.out.println("8. Enter a transaction details and update all accounts");
			System.out.println("9. Retrieve the cost for an assembly-id");
			System.out.println("10. Retrieve the total labor time,a department ID for jobs completed during a given date");
			System.out.println("11. Retrieve the processes through which a given assembly-id has passed with the department ID");
			System.out.println("12. Retrieve all jobs completed during the given date by a given department");
			System.out.println("13. Retrieve the customers whitin a range of category");
			System.out.println("14. Delete all cut-jobs whitin a range Job-No.");
			System.out.println("15. Change the color of paint job.");
			System.out.println("16. Import new customers detail from a data file.");
			System.out.println("17. Retrieve and export customers record whitin a range of category.");
			System.out.println("18. QUIT"); 
			System.out.println("=========================================");
			
			 
			try (// initializing Scanner object
			Scanner myScan = new Scanner(System.in)) {
				//reading the input given by the user
				Choice = myScan.nextInt();
				
				//Depending on the choice made by the user, we are defining the operations to be done
				
				switch (Choice) {
				
					case 1:
						// try and catch are used to not terminate loop in case of error.
						try {
							
						//Declaring the variables
						int Category;
						String Customer_Name, C_address;
						
						//Taking customer name from user
						System.out.println("Enter the Customer Name");
						Customer_Name = myScan.next();
						
						// Taking Customer Address from the user
						System.out.println("Enter the Customer Address"); 
						C_address = myScan.next();
						
						// Taking customer category from the user
						System.out.println("Enter the Customer Category"); 
						Category = myScan.nextInt();
						
						// Executing procedure for Query 1.
						final String Sql1 = "EXEC InsertCustomerInfo @Customer_Name = '"+Customer_Name+"', @C_address = '"+C_address+"', @Category = '"+Category+"';";
						
						final Statement statement1 = connection.createStatement(); 
							statement1.executeUpdate(Sql1);
							System.out.println("Customer details inserted successfully.");
							System.out.println("=========================================");
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 2:
						// try and catch are used to not terminate loop in case of error.
						try {
							
						// Declaring variables
						String Department_ID, Department_Data;
						
						// Taking Department Number from user.
						System.out.println("Enter the Department ID\n");
						Department_ID = myScan.next();
						
						// Taking Department data from user.
						System.out.println("Enter the Department Data\n");
						Department_Data = myScan.next();
						
						// Executing Procedure for Query 2.
						final String Sql2 = "EXEC InsertDepartmentInfo @Department_ID = '"+Department_ID+"', @Department_Data = '"+Department_Data+"';";
						
						final Statement statement2 = connection.createStatement(); 
							statement2.executeUpdate(Sql2);
					
							System.out.println("Department details inserted successfully.");
							System.out.println("=========================================");
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 3:
						// try and catch are used to not terminate loop in case of error.
						try {
							// Declaring Variables
						String Assembly_ID, Assembly_details, Customer_Name;
						String Date_ordered;
						
						// Taking Assembly-id from user
						System.out.println("Enter Assembly ID");
						Assembly_ID = myScan.next();
						
						// Taking date ordered from user
						System.out.println("Enter Date Ordered in yyyy-mm-dd format");
						Date_ordered = myScan.next();
						
						// Taking Assembly details from user
						System.out.println("Enter Assembly Details");
						Assembly_details = myScan.next();
						
						// Taking customer name from user
						System.out.println("Enter the Customer Name");
						Customer_Name = myScan.next();
						
						// Executing procedure for Query 3
						final String Sql3 = "EXEC InsertAssemblyInfo @Assembly_ID = '"+Assembly_ID+"', @Date_ordered = '"+Date_ordered+"'," + 
									"@Assembly_details = '"+Assembly_details+"', @Customer_Name = '"+Customer_Name+"';";
						
						final Statement statement3 = connection.createStatement(); 
							statement3.executeUpdate(Sql3);
					
							System.out.println("Assembly details inserted successfully.");
							System.out.println("=========================================");
							
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 4: 
						// try and catch are used to not terminate loop in case of error.
						try {
							
						// Declaring Variables
						String Process_ID, Process_Data, Department_ID;
						
						// Taking process-id from the user
						System.out.println("Enter Process ID");
						Process_ID = myScan.next();
						
						// Taking process data from the user
						System.out.println("Enter Process Data");
						Process_Data = myScan.next();
						
						// Taking Department-no from the user
						System.out.println("Enter Department ID");
						Department_ID = myScan.next();
						
						//Executing Procedure for Query 4
						final String Sql4 = "EXEC InsertProcessIDdept @Process_ID = '"+Process_ID+"'," + 
								" @Process_Data = '"+Process_Data+"', @Department_ID = '"+Department_ID+"';";
						
						final Statement statement4 = connection.createStatement(); 
							statement4.executeUpdate(Sql4);
					
							System.out.println("Process details inserted successfully.");
							System.out.println("=========================================");
						
						// Asking user to enter the type of procedure
						System.out.println("Choose one of the following type of process:\n 1.Fit\n 2. Paint\n 3.Cut\n");
						int choice1;
						// Taking the type of process from the user
						choice1 = myScan.nextInt();
						
						if (choice1 ==1) {
							
							// Declaring Variables
							String Fit_Type;
							
							// Taking fit type from user
							System.out.println("Enter fit type\n");
							Fit_Type = myScan.next();
							
							// Executing Procedure to insert data in Process_fit table
							final String Sql4_1 = "EXEC InsertProcessFit @Process_ID = '"+Process_ID+"',  @Fit_Type = '"+Fit_Type+"';";

							final Statement statement4_1 = connection.createStatement();
								statement4_1.executeUpdate(Sql4_1);
								
								System.out.println("Process_Fit details inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice1 ==2) {
							
							// Declaring Variables
							String Paint_Type, Paint_Method;
							
							//Taking paint type from the user
							System.out.println("Enter paint type");
							Paint_Type = myScan.next();
							
							// Taking paint method from the user
							System.out.println("Enter paint method");
							Paint_Method = myScan.next();
							
							// Executing procedure to insert data into Process_paint table
							final String Sql4_2 = "EXEC InsertProcessPaint @Process_ID = '"+Process_ID+"', " +
									" @Paint_Type = '"+Paint_Type+"', @Paint_Method = '"+Paint_Method+"';";

							final Statement statement4_2 = connection.createStatement();
								statement4_2.executeUpdate(Sql4_2);
								
								System.out.println("Process_Paint details inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice1 ==3) {
							
							// Declaring Variables
							String Cut_Type, Machine_Type;
							
							//Taking cut type from the user
							System.out.println("Enter cutting type");
							Cut_Type = myScan.next();
							
							// Take machine type from user
							System.out.println("Enter machine type");
							Machine_Type = myScan.next();
							
							//Executing Procedure to insert data into Process_cut table.
							final String Sql4_3 = "EXEC InsertProcessCut @Process_ID = '"+Process_ID+"', " + 
									" @Cut_Type = '"+Cut_Type+"', @Machine_Type = '"+Machine_Type+"';";

							final Statement statement4_3 = connection.createStatement();
								statement4_3.executeUpdate(Sql4_3);
								
								System.out.println("Process_Cut record inserted successfully.");
								System.out.println("=========================================");
						}
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 5: 
						// try and catch are used to not terminate loop in case of error.
						try {
						
							// Declaring Variables
						int Account_No, choice2;
						
						// Taking account number from user
						System.out.println("Enter account number");
						Account_No = myScan.nextInt();
					
						
						// Asking user to provide the type of account
						System.out.println("Choose one of the following type of account:\n 1. Department Account.\n 2. Assembly Account\n 3.Process Account\n");
						choice2 = myScan.nextInt();
						
						
						if (choice2 == 1) {
							// Declaring Variables
							float details_2;
							String Department_ID;
							String Established_Date;
							
							// Taking details-2 from the user
							System.out.println("Enter account details");
							details_2 = myScan.nextFloat();
							
							// Taking date account established from user
							System.out.println("Enter date account established");
							Established_Date = myScan.next();
							
							// Taking department no from the user
							System.out.println("Enter Department Number of the account");
							Department_ID = myScan.next();
							
							// Executing procedure to insert data into Department Account Table
							final String Sql5_1 = "EXEC InsertAccountDept5 @Account_No = '"+Account_No+"', @Established_Date = '"+Established_Date+"',"+
									" @details_2 = '"+details_2+"', @Department_ID = '"+Department_ID+"';";

							final Statement statement5_1 = connection.createStatement();
								statement5_1.executeUpdate(Sql5_1);
								
								System.out.println("Department_account record inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice2 == 2) {
							
							// Declaring Variables
							float Details_1;
							String Assembly_ID;
							String Established_Date;
							
							// Taking details1 from user
							System.out.println("Enter account details");
							Details_1 = myScan.nextFloat();
							
							// Taking date account established from user
							System.out.println("Enter date account established");
							Established_Date = myScan.next();
							
							// Taking Assembly-ID from user
							System.out.println("Enter Assembly id of the account");
							Assembly_ID = myScan.next();
							
							// Executing procedure to insert data into Assembly account
							final String Sql5_2 = "EXEC InsertAccountAssembly4 @Account_No = '"+Account_No+"',@Established_Date = '"+Established_Date+"',"+
									" @Details_1 = '"+Details_1+"',@Assembly_ID = '"+Assembly_ID+"';";

							final Statement statement5_2 = connection.createStatement();
								statement5_2.executeUpdate(Sql5_2);
								
								System.out.println("Assembly_acc record inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice2 == 3) {
							
							// Declaring Variables
							float Details_3;
							String Process_ID;
							String Established_Date;
							
							
							// Taking details from the user
							System.out.println("Enter account details");
							Details_3 = myScan.nextFloat();
							
							// Taking date account established from user
							System.out.println("Enter date account established");
							Established_Date = myScan.next();
							
							// Taking process-id from the user
							System.out.println("Enter Process id of the account");
							Process_ID = myScan.next();
							
							// Executing the procedure to insert data into Process account
							final String Sql5_3 = "EXEC InsertAccountProcess2 @Account_No = '"+Account_No+"',@Established_Date = '"+Established_Date+"',"+
									" @Details_3 = '"+Details_3+"',  @Process_ID = '"+Process_ID+"';";

							final Statement statement5_3 = connection.createStatement();
								statement5_3.executeUpdate(Sql5_3);
								
								System.out.println("Process_acc record inserted successfully.");
								System.out.println("=========================================");
						}
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 6:
						// try and catch are used to not terminate loop in case of error.
						try {
							
							// Declaring Variables
						String Date_job_commenced, Assembly_ID, Process_ID;
						int Job_No;
						
						// Taking Job-no from the user
						System.out.println("Enter Job-No for a new job");
						Job_No = myScan.nextInt();
						
						// Taking assembly-id from the user
						System.out.println("Enter assembly-ID for the job");
						Assembly_ID = myScan.next();
						
						// Taking process-id from user
						System.out.println("Enter process-ID for the job");
						Process_ID = myScan.next();
						
						// Taking job commenced from user
						System.out.println("Enter the commenced date for the job");
						Date_job_commenced = myScan.next();
						
						// Executing Procedure for Query 6
						final String Sql6 = "EXEC InsertJobInfo1 @Job_No = '"+Job_No+"', @Date_job_commenced = '"+Date_job_commenced+"',"+
								" @Assembly_ID = '"+Assembly_ID+"', @Process_ID = '"+Process_ID+"';";
						
						final Statement statement6 = connection.createStatement(); 
							statement6.executeUpdate(Sql6);
					
							System.out.println("Job record inserted successfully.");
							System.out.println("=========================================");
							
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
						
					case 7:
						// try and catch are used to not terminate loop in case of error.
						try {
							// Declaring Variables
						String Date_job_completed;
						int Job_No;
						
						// Taking Job-no from user
						System.out.println("Enter Job-No for the completed job");
						Job_No = myScan.nextInt();
						
						// Taking date completed from user
						System.out.println("Enter job completed date in yyyy-mm-dd format");
						Date_job_completed = myScan.next();
						
						// Executing the Procedure for Query 7
						final String Sql7 = "EXEC UpdateJobInfo1 @Job_No = '"+Job_No+"', @Date_job_completed = '"+Date_job_completed+"';";
						
						final Statement statement7 = connection.createStatement(); 
							statement7.executeUpdate(Sql7);
					
							System.out.println("Job record Updated successfully.");
							System.out.println("=========================================");
						
							// Declaring Variables
						int choice3;
						
						// Asking the type of job from the user
						System.out.println("Choose one of the following type of job:\n 1. Fit Job.\n 2. Paint Job\n 3.Cut Job\n");
						choice3 = myScan.nextInt();
						
						
						if (choice3 == 1) {
						  	
							// Declaring Variables
							String Labor_Time;
							
							// Taking fit labor time from user
							System.out.println("Enter the fit labor time fo Job in HH:MM:SS format.");
							Labor_Time = myScan.next();
							
							// Executing procedure to insert data into Job_fit table
							final String Sql7_1 = "EXEC InsertJobFitInfo @Job_No = '"+Job_No+"', @Labor_Time = '"+Labor_Time+"';";
							
							final Statement statement7_1 = connection.createStatement(); 
								statement7_1.executeUpdate(Sql7_1);
								
								System.out.println("Fit Job record inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice3 == 2) {
							
							// Declaring Variables
							String Color, Paint_Labor_Time;
							int Volume;
							
							// Taking color from user
							System.out.println("Enter the paint color.");
							Color = myScan.next();
							
							// Taking labor time from user
							System.out.println("Enter the paint job labor time in HH:MM:SS format.");
							Paint_Labor_Time = myScan.next();
							
							// Taking volume of paint from user
							System.out.println("Enter the volume of paint.");
							Volume = myScan.nextInt();
							
							// Executing the procedure to insert data into Job paint table
							final String Sql7_2 = "EXEC InsertJobPaintInfo @Job_No = '"+Job_No+"', @Color = '"+Color+"', "+
									" @Volume = '"+Volume+"', @Paint_Labor_Time = '"+Paint_Labor_Time+"';";
							
							final Statement statement7_2 = connection.createStatement(); 
								statement7_2.executeUpdate(Sql7_2);
								
								System.out.println("Paint Job record inserted successfully.");
								System.out.println("=========================================");
						}
						
						if (choice3 == 3) {
							
							// Declaring Variables
							String Job_Machine_Type, Machine_Time, Material, Cut_Labor_Time;
							
							// Taking machine type from user
							System.out.println("Enter the Job Machine Type.");
							Job_Machine_Type = myScan.next();
							
							// Taking machine time from user 
							System.out.println("Enter the cut job machine time in HH:MM:SS format.");
							Machine_Time = myScan.next();
							
							// Taking material used from the user
							System.out.println("Enter the material used.");
							Material = myScan.next();
							
							// Taking labor time from the user
							System.out.println("Enter the cut job labor time in HH:MM:SS format.");
							Cut_Labor_Time = myScan.next();
							
							// Executing Procedure to insert data into Job_cut table
							final String Sql7_3 = "EXEC InsertJobCutInfo @Job_No = '"+Job_No+"', @Job_Machine_Type= '"+Job_Machine_Type+"', "+
							" @Machine_Time = '"+Machine_Time+"', @Material = '"+Material+"', @Cut_Labor_Time = '"+Cut_Labor_Time+"';";
							
							final Statement statement7_3 = connection.createStatement(); 
								statement7_3.executeUpdate(Sql7_3);
								
								System.out.println("Cut Job record inserted successfully.");
								System.out.println("=========================================");
						}
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
						break;
				case 8:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					String Transaction_No;
					int Job_No;
					float Given_Cost;
					
					// Taking transaction number from the user
					System.out.println("Enter the Transaction Number.");
				    Transaction_No = myScan.next();
					
					// Taking sup-cost from the user
					System.out.println("Enter the cost of the transaction.");
					Given_Cost= myScan.nextFloat();
					
					// Taking job-No from the user
					System.out.println("Enter the Job number related to transaction.");
					Job_No = myScan.nextInt();
					
					// Executing procedure for Query 8.
					final String Sql8 = "EXEC InsertTransactionInfo5 @Transaction_No = '"+Transaction_No+"', @Given_Cost = '"+Given_Cost+"', @Job_No = '"+Job_No+"';";
					
					final Statement statement8 = connection.createStatement(); 
						statement8.executeUpdate(Sql8);
				
						System.out.println("Transaction record inserted and related accounts updated successfully.");
						System.out.println("=========================================");
						
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
				
				case 9:
					// try and catch are used to not terminate loop in case of error.
					try {
						// Declaring Variables
					String Assembly_ID3;
					
					// Taking assembly-id from the user
					System.out.println("Enter Assembly Id to retrieve the cost.");
					Assembly_ID3 = myScan.next();
					
					try {
						
					// Executing procedure for Query 9
					final String Sql9 = "EXEC RetriveCost @Assembly_ID = '"+Assembly_ID3+"';";
					
					try (final Statement statement9 = connection.createStatement(); 
							final ResultSet resultSet1 = statement9.executeQuery(Sql9)) {
						
							System.out.println(String.format("Cost incurred on Assembly-ID %s:", Assembly_ID3)); 
							while (resultSet1.next()) {
								System.out.println(String.format("%f",
									resultSet1.getFloat(1)));
									
								}
							}
					} catch (Exception e) {
						System.out.println("error!. Please try again.");
					}
					
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
					
				case 10:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					String Department_No2,Date_job_completed1;
					
					// Taking department-no from user
					System.out.println("Enter Department Number to get the total labor time.");
					Department_No2 = myScan.next();
					
					// Taking job-completed date from user
					System.out.println("Enter Job completed date to get the total labor time.");
					Date_job_completed1 = myScan.next();
					
					// Executing the procedure for Query 10
					final String Sql10 = "EXEC RetrieveLaboreTime @Department_ID = '"+Department_No2+"', @Date_job_completed = '"+Date_job_completed1+"';";
					
					try (final Statement statement10 = connection.createStatement(); 
							final ResultSet resultSet2 = statement10.executeQuery(Sql10)) {
						
							System.out.println(String.format("Total labor-time in minutes for department number %s and date" +
									" job completed %s:", Department_No2, Date_job_completed1)); 
							while (resultSet2.next()) {
								System.out.println(String.format("%s",
									resultSet2.getInt(1)));
									
								}
							}
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
					
				case 11:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					String Assembly_ID4;
					
					// Taking assembly-id from user
					System.out.println("Enter the Assembly Id to retrieve the processes.");
					Assembly_ID4 = myScan.next();
					
					// Executing procedure for Query 11d
					final String Sql11 = "EXEC RetrieveProcessAssembly @Assembly_ID = '"+Assembly_ID4+"';";
					
					try (final Statement statement11 = connection.createStatement(); 
							final ResultSet resultSet3 = statement11.executeQuery(Sql11)) {
						
							System.out.println(String.format("The processes through which Assembly ID %s passed so far:", Assembly_ID4)); 
							while (resultSet3.next()) {
								System.out.println(String.format("%s | %s | %s",
									resultSet3.getString(1),
									resultSet3.getString(2),
									resultSet3.getString(3)));
									
								}
							}
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
				
				case 12:
					// try and catch are used to not terminate loop in case of error.
					try {
					
						// Declaring Variables
					String Date_job_completed2, Department_No3;
					
					// Taking date completed from user
					System.out.println("Enter the date job is completed.");
					Date_job_completed2 = myScan.next();
					
					// Taking department number from user
					System.out.println("Enter the department to retrieve the jobs.");
					Department_No3 = myScan.next();
					
					// Executing Procedure to retrieve fit jobs 
					final String Sql12_1 = "EXEC RetrieveJobAssemblyFit @Date_job_completed = '"+Date_job_completed2+"', @Department_ID = '"+Department_No3+"';";
					
					try (final Statement statement12_1 = connection.createStatement(); 
							final ResultSet resultSet5 = statement12_1.executeQuery(Sql12_1)) {
						
							System.out.println(String.format("The fit jobs completed on date %s in department %s:", Date_job_completed2, Department_No3)); 
							while (resultSet5.next()) {
								System.out.println(String.format("%s | %s | %s",
									resultSet5.getString(1),
									resultSet5.getString(2),
									resultSet5.getString(3)));
									
								}
							}
					// Executing procedure to retrieve paint jobs 
					final String Sql12_2 = "EXEC RetrieveJobAssemblyPaint @Date_job_completed = '"+Date_job_completed2+"', @Department_ID = '"+Department_No3+"';";
					
					try (final Statement statement12_2 = connection.createStatement(); 
							final ResultSet resultSet6 = statement12_2.executeQuery(Sql12_2)) {
						
							System.out.println(String.format("The Paint jobs completed on date %s in department %s:", Date_job_completed2, Department_No3)); 
							while (resultSet6.next()) {
								System.out.println(String.format("%s | %s | %s | %s | %s",
									resultSet6.getString(1),
									resultSet6.getString(2),
									resultSet6.getString(3),
									resultSet6.getString(4),
									resultSet6.getString(5)));
									
								}
							}
					
					
					// Executing procedure to retrieve cut jobs
					final String Sql12_3 = "EXEC RetrieveJobAssemblyCut @Date_job_completed = '"+Date_job_completed2+"', @Department_ID = '"+Department_No3+"';";
					
					try (final Statement statement12_3 = connection.createStatement(); 
							final ResultSet resultSet7 = statement12_3.executeQuery(Sql12_3)) {
						
							System.out.println(String.format("The Cut jobs completed on date %s in department %s:", Date_job_completed2, Department_No3)); 
							while (resultSet7.next()) {
								System.out.println(String.format("%s | %s | %s | %s | %s | %s",
									resultSet7.getString(1),
									resultSet7.getString(2),
									resultSet7.getString(3),
									resultSet7.getString(4),
									resultSet7.getString(5),
									resultSet7.getString(6)));
									
								}
							}
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					
					break;
					
				case 13:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					int Lower_b, Upper_b;
					
					// Taking lower bound of category from user
					System.out.println("Enter the lower bound of the category.");
					Lower_b = myScan.nextInt();
					
					// Taking upper bound of category from user
					System.out.println("Enter the upper bound of the category.");
					Upper_b = myScan.nextInt();
					
					// Executing procedure for Query 13
					final String Sql13 = "EXEC RetrieveCustomerByCategory @Lower_b = '"+Lower_b+"', @Upper_b = '"+Upper_b+"';";
					
					try (final Statement statement13 = connection.createStatement(); 
							final ResultSet resultSet4 = statement13.executeQuery(Sql13)) {
						
							System.out.println("The customers in the given range of category are"); 
							while (resultSet4.next()) {
								System.out.println(String.format("%s | %s",
									resultSet4.getString(1),
									resultSet4.getString(2)));
									
								}
							}
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
					
				case 14:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					int lower_b1, upper_b1;
					
					// Taking lower bound job-no from user
					System.out.println("Enter the lower bound of the cut job-no.");
					lower_b1 = myScan.nextInt();
					
					// Taking upper bound job-no from user
					System.out.println("Enter the upper bound of the cut job-no.");
					upper_b1 = myScan.nextInt();
					
					// Executing procedure for Query 14
					final String Sql14 = "EXEC DeleteCutJob @Lower_b = '"+lower_b1+"', @Upper_b = '"+upper_b1+"';";
					
					final Statement statement14 = connection.createStatement(); 
						statement14.executeUpdate(Sql14);
				
						System.out.println("Deleted all cut jobs in the given range of Job-No.");
						System.out.println("=========================================");
						
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
					
				case 15:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					String Color;
					int Job_No3;
					
					// Taking job-no from user
					System.out.println("Enter the paint Job-No.");
					Job_No3 = myScan.nextInt();

					// Taking color from user
					System.out.println("Enter the new color for the job-no.");
					Color = myScan.next();
					
					// Executing procedure for Query 15
					final String Sql15 = "EXEC ChangePaintColor @Job_No = '"+Job_No3+"', @Color = '"+Color+"';";
					
					final Statement statement15 = connection.createStatement(); 
						statement15.executeUpdate(Sql15);
				
						System.out.println("Changed the color of a given paint job.");
						System.out.println("=========================================");
						
					} catch (Exception e) {
						System.out.println("Error!. Returning to main menu");
					}
					break;
					
				case 16:
					// try and catch are used to not terminate loop in case of error.
					try {
						// Declaring Variables
					String file_name, line;
					
					// Taking Input file name from user
					System.out.println("Enter the file-name to Import data.");
					file_name = myScan.next();
					
					// Creating new File object
					File file = new File(file_name);
					
					// Creating new Scanner Object
					Scanner sc = new Scanner(file);
					
					// While loop to read all lines in the input file
					while(sc.hasNextLine()) {
						line = sc.nextLine();
						
						// Dividing line to parts separated by Delimiter (",")
						String[] parts = line.split(",");
						
							
							String Customer_Name= parts[0];
							String C_address = parts[1];
							int Category = Integer.parseInt(parts[2]);
							
							// Execute procedure for Query 16
							final String Sql16 = "EXEC InsertCustomerInfo @Customer_Name = '"+Customer_Name+"', @C_address = '"+C_address+"', @Category = '"+Category+"';";
							
							final Statement statement16 = connection.createStatement(); 
								statement16.executeUpdate(Sql16); 
						}
					sc.close();
					}
					
		
				catch (Exception e) {
						System.out.print("Error!. Returning to main menu: ");
					}
				
					break;
				case 17:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
						String File_name1, Lower_b2, Upper_b2;
						
						// Enter the filename to output result
						System.out.println("Enter the file-name to Export data.");
						File_name1 = myScan.next();
						
						// Taking lower bound of category from user
						System.out.println("Enter the lower bound of category.");
						Lower_b2 = myScan.next();
						
						// Taking upper bound of category from user
						System.out.println("Enter the upper bound of category.");
						Upper_b2 = myScan.next();
						
						// Creating new file writer Object
						FileWriter fw = new FileWriter(File_name1);
						
						// Executing Procedure(for Query 13) to get output
						final String Sql17 = "EXEC RetrieveCustomerByCategory1 @Lower_b2 = '"+Lower_b2+"', @Upper_b2 = '"+Upper_b2+"';";
						
						
						try (final Statement statement17 = connection.createStatement(); 
								final ResultSet resultSet4 = statement17.executeQuery(Sql17)) {
							
								//System.out.println("The customers in the given range of category are"); 
								while (resultSet4.next()) {
									
									//fw.write(String.format("%s,%s", resultSet4.getString(1), resultSet4.getString(2)));
									fw.write(resultSet4.getString(1) + "," + resultSet4.getString(2) + "\n");	
									}
								fw.close();
								} catch (SQLException e) {
									e.getCause().getMessage();
								}
						
						
						} catch (Exception e) {
							System.out.println("Error!. Returning to main menu");
						}
					break;
					
				case 18:
					System.out.println("You choose to Quit!");

				}
			}
		}
	}
	}
}
