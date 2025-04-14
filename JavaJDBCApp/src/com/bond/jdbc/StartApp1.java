package com.bond.jdbc;


import java.sql.*;

public class StartApp1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		//Implementation 
		//Load and register Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Establish connection DB
		String url = "jdbc:mysql://localhost:3306/jdbcbyjava";
		String user = "root";
		String password = "root";
		Connection connection = DriverManager.getConnection(url, user, password);
		
		
		//Statement for DB
		Statement statement = connection.createStatement();
		
		//Execute the query
		
		//Inserting the values
		String sql = "Insert into BankInfo(Cid, UName, AccNo, UCity) VALUES (105, 'Bopal', '40098', 'HYD')  ";
		
		int rowAffected = statement.executeUpdate(sql);
		
		//Process the result
		if(rowAffected == 0) {
			System.out.println("Not inserted");
		}else {
			System.out.println("Rows Affected: " + rowAffected );
		}
		
		//Updating the values
//		int id = 103;
//		String sql1 = "UPDATE BankInfo set UCity = 'HYD' where cid = " + id;
//		int updateExecute = statement.executeUpdate(sql1);
//		System.out.println("Rows affected while update: "+ updateExecute);
		
		
		//Deleting the records
//		int id = 104;
//		String sqlDel = "delete from bankinfo where cid = " + id;
//		int delRecords = statement.executeUpdate(sqlDel);
//		System.out.println("Deleted records "+ delRecords);
		
		
		
		//Show the records
		ResultSet resultSet = statement.executeQuery("SELECT * FROM BankInfo");
		int i =1;
		while (resultSet.next()) {
		    System.out.println("User " + resultSet.getString("Cid") +":"+ resultSet.getString("UName") + " " + resultSet.getString("UCity"));
		    i++;
		}
		
		
		//Close all the connection (Closing the resources)
		statement.close();
		connection.close();

	}

}
