import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LaunchClassForNameEx {

	public static void main(String[] args) {
		
		int ids[] = {109,110};
		String name[] = {"Santosh", "Sriram"};
		String city[] = {"PUN", "BLR"};
		int AccNo[] = {55055, 55077};
		
		try {
		Records rec = new Records();
		rec.insertRecord(ids, name, city, AccNo);
//		rec.updateRecord(104);
//		rec.deleteRecord(106);
		rec.showRecords();
		}
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}


class Records{
	
	private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
	
	public Records() throws ClassNotFoundException, SQLException{
	
			//Implementation 
			//Load and register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish connection DB
			String url = "jdbc:mysql://localhost:3306/jdbcbyjava";
			String user = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, user, password);
			
			
			//Statement for DB
			statement = connection.createStatement();
			
	}
	
	public void insertRecord(int id[], String name[],String city[], int AccNo[]) throws SQLException {
		
		int n = id.length;
		int insertedRecords = 0 ;
		for(int i=0;i<n;i++) {
		String insertQuery = "Insert Into bankinfo(cid, Uname, UCity, AccNo) VALUES (?,?,?,?) ";
		preparedStatement = connection.prepareStatement(insertQuery);
		preparedStatement.setInt(1, id[i]);
		preparedStatement.setString(2, name[i]);
		preparedStatement.setString(3, city[i]);
		preparedStatement.setInt(4, AccNo[i]);
		
		preparedStatement.executeUpdate();
		insertedRecords++;
		}
		System.out.println("Inserted Records: "+ insertedRecords);
		
	}
	
	
	
	
	public void showRecords() throws SQLException {
		//Show the records
		ResultSet resultSet = statement.executeQuery("SELECT * FROM BankInfo");
		while (resultSet.next()) {
			System.out.println("User " + resultSet.getString("Cid") +":"+ resultSet.getString("UName") + " " + resultSet.getString("UCity"));
			}
	}
	
	
	public void updateRecord(int id) {
		
		
		
		try {
		String sql1 = "UPDATE BankInfo set UCity = 'BLR' where cid = ? ";
		preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		int updateExecute = preparedStatement.executeUpdate();
		System.out.println("Rows affected while update: "+ updateExecute);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void deleteRecord(int id) throws SQLException {
		String deleteQueryById = "Delete from bankinfo where cid = ?";
		preparedStatement = connection.prepareStatement(deleteQueryById);
		preparedStatement.setInt(1, id);
		int recordDeleted = preparedStatement.executeUpdate();
		System.out.println("Deleted Record: "+ recordDeleted);
	}
	
	
	
}
