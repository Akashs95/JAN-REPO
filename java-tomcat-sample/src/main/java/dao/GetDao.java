/*
The Data Access Object (DAO) pattern is a structural pattern that allows to isolate the application/business layer from the persistence layer (usually a relational database ) using an abstract API.
*/

package dao;

import java.sql.*;
import model.Users;

public class GetDao
{
	public Users getUser(int emp_no)
	{
		Users a = new Users();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String ssm_parameter_name = "SSM_PARAMETER_NAME";
			//Connection con = DriverManager.getConnection("jdbc:mysql://database-2023.ctsdvy14yp5u.us-east-1.rds.amazonaws.com:3306/db","admin","12345678");
			 //Connection con = DriverManager.getConnection("jdbc:mysql://endpoint:3306/db","username","DB_PASSWORD_HERE");
			
			db_username=java ssm.getparameter('dev_rds_creds');
			db_password=java ssm.getparameter('dev_rds_creds');
			db_hostname=java ssm.getparameter('dev_rds_creds');
			
				Connection con = DriverManager.getConnection("jdbc:mysql://db_hostname:3306/db","db_username","db_password");
		
			Statement st = con.createStatement();
			// ResultSet rs = st.executeQuery("select * from employees where emp_no=" + emp_no);
			ResultSet rs = st.executeQuery("SELECT employees.emp_no,employees.first_name, employees.last_name, titles.title, employees.hire_date, titles.from_date, titles.to_date FROM employees left JOIN titles ON employees.emp_no=titles.emp_no where employees.emp_no=" + emp_no);
			if(rs.next())
			{
				a.setEmpno(rs.getInt("emp_no"));
				a.setFirstname(rs.getString("first_name"));
				a.setLastname(rs.getString("last_name"));
				a.setTitle(rs.getString("title"));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return a;
	}
}
