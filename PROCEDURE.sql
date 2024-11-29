--CREATE PROCEDURES

-- Enter a new customer
CREATE PROCEDURE InsertCustomerInfo
    @Customer_name VARCHAR(20),
    @Address VARCHAR(100),
    @Phone_Number INT
AS
BEGIN

INSERT INTO customerInfo
(Customer_Name, C_address, Category)
VALUES (@Customer_Name, @C_address, @Category)
END
GO
-- Enter a new Department
CREATE PROCEDURE InsertDepartmentInfo
    @Department_ID VARCHAR(10),
    @Department_Data VARCHAR(100)
AS
BEGIN
    INSERT INTO Department
    (Department_ID, Department_Data)
    VALUES (@Department_ID, @Department_Data)
END
GO
--Enter a new assembly with its customer-name, assembly-details, assembly-id,
-- and date-ordered 
CREATE PROCEDURE InsertAssemblyInfo
    @Assembly_ID VARCHAR(10),
    @Date_ordered VARCHAR(10),
    @Assembly_details VARCHAR(100),
    @Customer_Name VARCHAR(20)
AS
BEGIN
    INSERT INTO Assembly1
    (
    Assembly_ID,Date_ordered, Assembly_details,Customer_Name)
    VALUES (@Assembly_ID, CAST (@Data_ordered as DATE), @Assembly_details, @Customer_Name)
END
GO

--Enter a new process-id and its department together 
--with its type and information relevant to the type 
CREATE PROCEDURE InsertProcessIDdept
    @Process_ID VARCHAR(10),
    @Process_Data VARCHAR(100),
    @Department_ID VARCHAR(10)
AS
BEGIN
    INSERT INTO Process1
    (Process_ID, Process_Data, Department_ID)
    VALUES (@Process_ID, @Process_Data, @Department_ID)
END
GO

CREATE PROCEDURE InsertProcessFit
    @Process_ID VARCHAR(10),
    @Fit_Type VARCHAR(10)
AS
BEGIN
    INSERT INTO Process_Fit
    (Process_ID, Fit_Type)
    VALUES (@Process_ID, @Fit_Type)
END

GO
CREATE PROCEDURE InsertProcessPaint
    @Process_ID VARCHAR(10),
    @Paint_Type VARCHAR(8),
    @Paint_Method VARCHAR(8)
AS
BEGIN
    INSERT INTO Process_Paint
    (Process_ID, Paint_Type, Paint_Method)
    VALUES (@Process_ID, @Paint_Type, @Paint_Method)
END
GO

CREATE PROCEDURE InsertProcessCut
    @Process_ID VARCHAR(10),
    @Cut_Type VARCHAR(8),
    @Machine_Type VARCHAR(10)
AS
BEGIN
    INSERT INTO Process_Cut
    (Process_ID, Cut_Type, Machine_Type)
    VALUES (@Process_ID, @Cut_Type, @Machine_Type)
END

GO
-- Create a new account and associate it with the 
-- process, assembly, or department to which it is applicable 
CREATE PROCEDURE InsertAccountDept
    @Account_No INT,
    @Details_2 FLOAT,
    @Department_ID VARCHAR(10)
AS
BEGIN
    INSERT INTO Department_acc
    (Account_No, Details_2, Department_ID)
    VALUES (@Account_No, @Details_2, @Department_ID)
END

GO
CREATE PROCEDURE InsertAccountAssembly
    @Account_No INT,
    @Details_1 FLOAT,
    @Assembly_ID VARCHAR(10)
AS
BEGIN
    INSERT INTO Assembly1_acc
    (Accout_No,Details_1, Assembly_ID)
    VALUES (@Acount_No, @Details_1, @Assembly_ID)
END

GO
CREATE PROCEDURE InsertAccountProcess
    @Account_No INT,
    @Details_3 FLOAT,
    @Process_ID VARCHAR(10)
AS
BEGIN
    INSERT INTO Process1_acc
    (Account_No, Details_3, Process_ID)
    VALUES (@Account_No, @Details_3, @Process_ID)
END

GO
-- Enter a new job, given its job-no, assembly-id, process-id, and date the job commenced 
CREATE PROCEDURE InsertJobInfo
    @Job_No INT,
    @Date_job_commenced VARCHAR(10),
    @Assembly_ID VARCHAR(10),
    @Process_ID VARCHAR(7)
AS
BEGIN
    INSERT INTO Job
    (Job_No, Date_job_commenced, Assembly_ID, Process_ID)
    VALUES (@Job_No, CAST(@Date_job_commenced as DATE), @Assembly_ID, @Process_ID)
END
GO

-- At the completion of a job, enter the date it completed 
--and the information relevant to the type of job 
CREATE PROCEDURE UpdateJobInfo
    @Job_No INT,
    @Date_job_completed VARCHAR(10)

AS
BEGIN
    UPDATE Job SET Date_job_completed = @Date_job_completed WHERE Job_No = @Job_No
END
GO

CREATE PROCEDURE InsertJobFitInfo
    @Job_No INT,
    @Labor_Time VARCHAR(10)
AS
BEGIN
    INSERT INTO Job_Fit
    (Job_No, Labor_Time)
    VALUES (@Job_No, CAST(@Labor_Time as TIME))
END
GO

CREATE PROCEDURE InsertJobPaintInfo
    @Job_No INT,
    @Color VARCHAR(10),
    @Volume INT,
    @Paint_Labor_Time VARCHAR(10)
AS
BEGIN
    INSERT INTO Job_Paint
    (Job_No, Color, Volume, Paint_Labor_Time)
    VALUES (@Job_No, @Color, @Volume, CAST(@Paint_Labor_Time as TIME))
END
GO

CREATE PROCEDURE InsertJobCutInfo
    @Job_No INT,
    @Job_Machine_Type VARCHAR(10),
    @Machine_Time VARCHAR(10),
    @Material VARCHAR(6),
    @Cut_Labor_Time VARCHAR(10)
AS
BEGIN
    INSERT INTO Job_cut
    (Job_No, Job_Machine_Type, Machine_Time, Material, Cut_Labor_Time)
    VALUES (@Job_No, @Job_Machine_Type, CAST(@Machine_Time as TIME), @Material, CAST(@Cut_Labor_Time as TIME))
END
GO

-- Enter a transaction-no and its sup-cost and update all the costs (details) of the affected 
--accounts by adding sup-cost to their current values of details 
CREATE PROCEDURE InsertTransactionInfo
    @Transaction_No VARCHAR(10),
    @Given_Cost FLOAT,
    @Job_No INT
AS
BEGIN
    DECLARE @Dept_acc_no INT,
            @Assembly_acc_no INT,
            @Process_acc_no INT;

    SET @Dept_acc_no = (SELECT Account_No
                    FROM Department_acc, Process1, Job1
                    WHERE Process1.Process_ID = Job1.Process_ID AND Job1.Job_No = @Job_No AND Department_acc.Department_ID = Process1.Department_ID);
    
	SET @Assembly_acc_no = (SELECT Account_No
                    FROM Assembly1_acc, Job1
                    WHERE Assembly1_acc.Assembly_ID = Job1.Assembly_ID AND Job1.Job_No = @Job_No);

	SET @Process_acc_no = (SELECT Account_No
                    FROM Process1_acc, Job1
                    WHERE Process1_acc.Process_ID = Job1.Process_ID AND Job1.Job_No = @Job_No);

    INSERT INTO C_Transaction
    (Transaction_No, Given_Cost, Job_No, Department_No, Assembly_acc, Process1_acc)
    VALUES (@Transaction_No, @Given_Cost, @Job_No, @Dept_acc_no, @Assembly_acc_no, @Process_acc_no);

    UPDATE Department_acc
    SET Details_2 = Details_2 + @Given_Cost
    WHERE Account_No = @Dept_acc_no;

    UPDATE Assembly1_acc
    SET Details_1 = Details_1 + @Given_Cost
    WHERE Account_No = @Assembly_acc_no;

    UPDATE Process1_acc
    SET Details_3 = Details_3 + @Given_Cost
    WHERE Account_No = @Process_acc_no;
END
GO

--Retrieve the cost incurred on an assembly_ID
CREATE PROCEDURE RetriveCost
    @Assembly_ID VARCHAR(10)

AS
BEGIN
    SELECT Details_1 FROM Assembly1_acc WHERE Assembly1_acc.Assembly_ID = @Assembly_ID;
END

GO
-- Retrieve the total labor time within a department for jobs completed
-- in the department during a given date
CREATE PROCEDURE RetrieveLaboreTime
    @Department_ID VARCHAR(10),
    @Date_job_completed VARCHAR(10)

AS
BEGIN
    DECLARE @Fit_Labor_Time FLOAT,
            @Paint_Labor_Time FLOAT,
            @Cut_Labor_Time FLOAT,
            @Total_Labor_Time FLOAT;
    SET @Fit_Labor_Time = (SELECT  SUM(( DATEPART(hh, Labor_Time) * 3600 ) + ( DATEPART(mi,Labor_Time) * 60 ) + DATEPART(ss,Labor_time))/60 as minute
FROM Job_Fit WHERE Job_Fit.Job_No in (
SELECT distinct(Job_No) FROM Job1
WHERE Job1.Process_ID in (SELECT distinct(Process_ID) FROM Process1 WHERE Process1.Department_ID = @Department_ID) AND Job1.Date_job_completed = CAST(@Date_job_completed as DATE)));

IF @Fit_Labor_Time IS NULL SET @Fit_Labor_Time = 0; 

    SET @Paint_Labor_Time = (SELECT  SUM(( DATEPART(hh, Paint_Labor_Time) * 3600 ) + ( DATEPART(mi,  Paint_Labor_Time) * 60 ) + DATEPART(ss,  Paint_Labor_Time))/60 as minute
FROM Job_Paint WHERE Job_Paint.Job_No in (
SELECT distinct(Job_No) FROM Job1
WHERE Job1.Process_ID in (SELECT distinct(Process_ID) FROM Process1 WHERE Process1.Department_ID = @Departemt_ID) AND Job1.Date_job_completed =CAST(@Date_job_completed as DATE)));

IF @Paint_Labor_Time IS NULL SET @Paint_Labore_Time = 0;

    SET @Cut_Labor_Time = (SELECT  SUM(( DATEPART(hh, Cut_Labor_Time) * 3600 ) + ( DATEPART(mi, Cut_Labor_Time) * 60 ) + DATEPART(ss, Cut_Labor_Time))/60 as minute
FROM Job_Cut WHERE Job_Cut.Job_No in (
SELECT distinct(Job_No) FROM Job1
WHERE Job1.Process_ID in (SELECT distinct(Process_ID) FROM Process1 WHERE Process1.Department_ID = @Department_ID) AND Job1.Date_job_completed =CAST(@Date_job_completed as DATE)));

IF @Cut_Labor_Time IS NULL SET @Cut_Labor_Time = 0;

    SET @Total_Labor_Time = @Fit_Labor_Time + @Paint_Labor_Time + @Cut_Labor_Time
    SELECT  @Total_Labor_Time
END
GO

-- Retrieve the processes through which a given assembly-id has passed so far (in date_job-commenced order) and the department responsible for each process 
CREATE PROCEDURE RetrieveProcessAssembly
    @Assembly_ID VARCHAR(10)
AS
BEGIN
    SELECT Job1.Date_job_commenced, Job1.Process_ID, Process1.Department_ID
    FROM Job1, Process1
    WHERE Job1.Assembly_ID = @Assembly_ID AND Process1.Process_ID = Job1.Process_ID 
    ORDER BY 1 ;

END

GO
-- Retrieve the jobs (together with their type information and assembly-id) completed during a given date in a given department 
CREATE PROCEDURE RetrieveJobAssemblyFit
    @Date_job_completed VARCHAR(10),
    @Department_ID VARCHAR(10)
AS
BEGIN
    SELECT DISTINCT(Job1.Job_No), Job1.Assembly_ID, Job_Fit.Labor_Time
    FROM Job1, Job_Fit
    WHERE Date_job_completed = @Date_job_completed and Job1.Process_ID in (SELECT Process1.Process_ID FROM Process1 WHERE Department_ID = @Department_ID) AND Job_Fit.Job_No = Job1.Job_No;

END
GO

CREATE PROCEDURE RetrieveJobAssemblyPaint
    @Date_job_completed VARCHAR(10),
    @Department_No VARCHAR(10)
AS
BEGIN
    SELECT DISTINCT(Job1.Job_No), Job1.Assembly_ID, Job_Paint.Color, Job_Paint.Volume, Job_Paint.Paint_Labor_Time
    FROM Job1, Job_Paint
    WHERE Date_job_completed = @Date_job_completed and Job1.Process_ID in (SELECT process_ID FROM Process1 WHERE Department_ID = @Department_ID) AND Job_Paint.Job_No = Job1.Job_No;

END
GO
CREATE PROCEDURE RetrieveJobAssemblyCut
    @Date_job_completed VARCHAR(10),
    @Department_ID VARCHAR(10)
AS
BEGIN
    SELECT DISTINCT(Job1.Job_No), Job1.Assembly_ID, Job_Cut.Job_Machine_Type, Job_Cut.Machine_Time, Job_Cut.Material, Job_Cut.Cut_Labor_Time
    FROM Job1, Job_Cut
    WHERE Date_job_completed = @Date_job_completed and Job1.Process_ID in (SELECT Process1.Process_ID FROM Process1 WHERE Department_ID = @Department_ID) AND Job_Cut.Job_No = Job1.Job_No;

END

GO
-- Retrieve the customers (in name order) whose category is in a given range 
CREATE PROCEDURE RetrieveCustomerByCategory
    @Lower_b INT,
    @Upper_b INT
AS
BEGIN
    SELECT Customer_Name, Category AS Name FROM customerInfo
    WHERE Category >= @Lower_b AND Category <= @Upper_b
    ORDER BY 1 ;
END

GO
-- Delete all cut-jobs whose job-no is in a given range 
CREATE PROCEDURE DeleteCutJob
    @Lower_b INT,
    @Upper_b INT
AS
BEGIN
    DELETE FROM Job_Cut WHERE Job_No >= @Lower_b AND Job_No <= @Upper_b
END

GO
-- Change the color of a given paint 
CREATE PROCEDURE ChangePaintColor
    @Job_No INT,
    @Color VARCHAR(10)
AS
BEGIN
    UPDATE Job_Paint SET Color = @Color WHERE Job_No = @Job_No;
END
GO


CREATE PROCEDURE DeleteJobCutIdentified
    --@lower_b INT,
   -- @upper_b INT
AS
BEGIN
    DECLARE @JB INT
    
    SET @JB = (SELECT DISTINCT( Job_Cut.Job_No ) FROM Job_Cut WHERE Job_No >= 1 AND Job_No <= 10 )
    --DELETE FROM Job_cut WHERE job_no >= @lower_b AND job_no <= @upper_b
    SELECT @JB;
END