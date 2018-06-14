package com.database.tolist;
import java.sql.*;
import java.util.*;

public class DBToList {

	static final String dbUrl = "jdbc:mysql://localhost:3306/test";
	static final String username = "root";
	static final String password = "root";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public static void main(String[] args) throws SQLException 
	{
		try {
			conn = DriverManager.getConnection(dbUrl, username, password);
			if(conn.isClosed())
				System.out.println("Connection to MySQL Database failed");
			else {
				stmt = conn.createStatement();
				String select = "SELECT * FROM STUDENTS";
				rs = stmt.executeQuery(select);
				
				//Create a List object that will store the data from the resultSet
				List<String> list = new ArrayList<String>();
				while(rs.next())
					list.add(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
//				This print statement was to test if the result set was returning the correct data	
//				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				System.out.println(list);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs != null && !rs.isClosed())
			{
				rs.close();
			}
			if(stmt != null && !stmt.isClosed())
			{
				stmt.close();
			}
			conn.close();
		}
	}
}