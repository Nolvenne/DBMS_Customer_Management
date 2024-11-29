DROP TABLE IF EXISTS customerInfo;
DROP TABLE IF EXISTS Assembly1;
DROP TABLE IF EXISTS Department;
DROP TABLE IF EXISTS Process1;
DROP TABLE IF EXISTS Process_Fit;
DROP TABLE IF EXISTS Process_Paint;
DROP TABLE IF EXISTS Process_Cut;
DROP TABLE IF EXISTS Job1;
DROP TABLE IF EXISTS Job_Fit;
DROP TABLE IF EXISTS Job_Cut;
DROP TABLE IF EXISTS Job_Paint;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Process1_acc;
DROP TABLE IF EXISTS Department_acc1;
DROP TABLE IF EXISTS Assembly1_acc;
DROP TABLE IF EXISTS C_Transaction;

-- Create Customer table
CREATE TABLE customerInfo (
    Customer_Name VARCHAR(20),
    C_address VARCHAR(100),
    Category INT,
    CONSTRAINT Category CHECK (Category BETWEEN 1 AND 10),
    PRIMARY KEY (Customer_Name)
);
SELECT*FROM customerInfo;

-- Create Assembly table
CREATE TABLE Assembly1 (
    Assembly_ID VARCHAR(10),
    Date_ordered DATE,
    Assembly_details VARCHAR(100),
    Customer_Name VARCHAR(20),
    PRIMARY KEY (Assembly_ID),
    FOREIGN KEY (Customer_Name) REFERENCES CustomerInfo(Customer_Name)
);
SELECT*FROM Assembly1;

--Create Department Table
CREATE TABLE Department (
    Department_ID VARCHAR(10),
    Department_Data VARCHAR(100),
    PRIMARY KEY (Department_ID)
);
SELECT*FROM Department;

--Create Process Table
CREATE TABLE Process1 (
    Process_ID VARCHAR(10) PRIMARY KEY,
    Process_Data VARCHAR(100),
    Department_ID VARCHAR(10) FOREIGN KEY REFERENCES Department(Department_ID)
);
SELECT*FROM Process1;

--Create Process_Fit Table
CREATE TABLE Process_Fit (
    Process_ID VARCHAR(10) FOREIGN KEY REFERENCES Process1(Process_ID),
    Fit_Type VARCHAR(10),
    PRIMARY KEY (Process_ID)
);
SELECT*FROM Process_Fit;

--Create Process_Paint Table
CREATE TABLE Process_Paint (
    Process_ID VARCHAR(10) FOREIGN KEY REFERENCES Process1(Process_ID),
    Paint_Type VARCHAR(8),
    Paint_Method VARCHAR(8),
    PRIMARY KEY (Process_ID)
);
SELECT*FROM Process_Paint;

--Create Process_Cut Table
CREATE TABLE Process_Cut (
    Process_ID VARCHAR(10) FOREIGN KEY REFERENCES Process1(Process_ID),
    Cut_Type VARCHAR(8),
    Machine_type VARCHAR(10),
    PRIMARY KEY (Process_ID)
);
SELECT*FROM Process_Cut;

--Create Job Table
CREATE TABLE Job1 (
    Job_No INT PRIMARY KEY,
    Date_job_commenced DATE,
    Date_job_completed DATE,
    Assembly_ID VARCHAR(10) FOREIGN KEY REFERENCES Assembly1(Assembly_ID),
    Process_ID VARCHAR(10) FOREIGN key REFERENCES Process1(Process_ID)
);
SELECT*FROM Job1;

--Create Job_Fit Table
CREATE TABLE Job_Fit (
    Job_No INT FOREIGN KEY REFERENCES Job1(Job_No),
    Labor_Time TIME,
    PRIMARY KEY (Job_No) 
);
SELECT*FROM Job_Fit;

--Create Job_Paint Table
CREATE TABLE Job_Paint (
    Job_No INT FOREIGN KEY REFERENCES Job1(Job_No),
    Color VARCHAR(10),
    Volume INT,
    Paint_Labor_Time TIME,
    PRIMARY KEY (Job_No)
);
SELECT*FROM Job_Paint;

--Create Job_Cut Table
CREATE TABLE Job_Cut (
    Job_No INT FOREIGN KEY REFERENCES Job1(Job_No),
    Job_Machine_Type VARCHAR(10),
    Machine_Time TIME,
    Material VARCHAR(6),
    Cut_Labor_Time TIME,
    PRIMARY KEY (Job_No)
);
SELECT*FROM Job_Cut;

--Create Department Account Table
CREATE TABLE Department_acc1(
    Account_No INT, 
    Established_Date DATE,
    details_2 FLOAT,
    Department_ID VARCHAR(10) FOREIGN KEY REFERENCES Department(Department_ID) UNIQUE,
PRIMARY KEY (Account_No)
);
SELECT*FROM Department_acc1;

--Create Assembly Account
CREATE TABLE Assembly1_acc (
    Account_No INT,
    Established_Date DATE,
    Details_1 FLOAT,
    Assembly_ID VARCHAR(10) FOREIGN KEY REFERENCES Assembly1(Assembly_ID) UNIQUE,
    PRIMARY KEY (Account_No)
);
SELECT*FROM Assembly1_acc;

--Create Process Account
CREATE TABLE Process1_acc (
    Account_No INT,
    Established_Date DATE,
    Details_3 FLOAT,
    Process_ID VARCHAR(10) FOREIGN KEY REFERENCES Process1(Process_ID) UNIQUE,
    PRIMARY KEY (Account_No)
);
SELECT*FROM Process1_acc;

--Create Customer Transaction Table
CREATE TABLE C_Transaction (
    Transaction_No VARCHAR(10) PRIMARY KEY,
    Given_Cost FLOAT,
    Job_No INT FOREIGN KEY REFERENCES Job1(Job_No),
    Department_acc INT FOREIGN KEY REFERENCES Department_acc1(Account_No),
    Assembly1_acc INT FOREIGN KEY REFERENCES Assembly1_acc(Account_No),
    Process1_acc INT FOREIGN KEY REFERENCES Process1_acc(Account_No)
);
SELECT*FROM C_Transaction;

/*Create Index
*/

-- Create index on Category - customerInfo table
CREATE INDEX customer_Category ON customerInfo(Category);

-- Create index on Department_No - Process1 table
CREATE INDEX process_department ON Process1(Department_ID);

-- Create index on Assembly_ID - Job table
CREATE INDEX Assembly_Job ON job1(Assembly_ID);

-- Create index on Department_No - Department_acc table
CREATE INDEX Department_No_Acc ON Department_acc(Department_ID);

-- Create index on Assembly_ID - Assembly_acc table
CREATE INDEX Assembly_ID_acc ON Assembly1_acc(Assembly_ID);

-- Create index on process_ID - Process_acc table
CREATE INDEX Process_ID_acc ON Process1_acc(Process_ID);






