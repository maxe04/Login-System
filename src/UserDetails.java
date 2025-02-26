import java.sql.*;

public class UserDetails {

	//Store PostgreSQL url, username and password.
	//Enter your url, username and password here.
	
	//To use without changing any more variables, call your postgres table "userDetails"
	//with columns: id, username, password, email.
	private static final String url = "jdbc:postgresql://localhost:5432/**YOUR DATABASE NAME**";
	private static final String username = "**YOUR USERNAME** (default: postgres)";
	private static final String password = "**YOUR PASSWORD**";
	
	
	//Connect to database containing userDetails table
	private static Connection getConnection() throws SQLException {
		try {
			Connection con = DriverManager.getConnection(url, username, password);	
			return con;
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("Database driver not found");
		}
		
	}

	//Check if password inputted by the user exists for given username in userDetails table
	public static boolean checkPassword(String user, String pass) throws SQLException {
		
		Statement st = null;
		ResultSet passwordRS = null;
		Connection con = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			passwordRS = st.executeQuery("select password from userdetails where username=" + "'" + user + "'");
			passwordRS.next();
			return passwordRS.getString(1).equals(pass);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Database error: " + e.getMessage());
		
		// Close resources
		}finally {
			try {
                if (passwordRS != null) passwordRS.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		
	}
	
	//Check if username inputted by the user exists in userDetails table
	public static boolean checkUsername(String user) throws SQLException{
		
		Statement st = null;
		ResultSet usernameRS = null;
		Connection con = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			usernameRS = st.executeQuery("select username from userdetails where username=" + "'" + user + "'");
			return usernameRS.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Database error: " + e.getMessage());
		
		// Close resources
		}finally {
			try {
                if (usernameRS != null) usernameRS.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
	}
	
	//Get number of rows in userDetails table
	public static int getRowCount() throws SQLException{
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		
		
		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM userDetails");
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SQLException("Database error: " + e.getMessage());
		}
		
		// Close resources
		finally {
			try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		
	}
	
	
	//Add user details to table
	public static void addUser(String user, String password, String email) {
		int id;
		PreparedStatement st = null;
		Connection con = null;
		
		try {
			con = getConnection();
			String sql = "INSERT INTO userDetails (id, username, password, email) VALUES (?, ?, ?, ?)";
			
			//Get current number of rows to get current ID
			id = getRowCount();
			
			st = con.prepareStatement(sql);
			
			//Increment new ID by 1
			st.setInt(1, id+1);
			
			st.setString(2, user);
			st.setString(3, password);
			st.setString(4, email);
			
			int rowsInserted = st.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User details added successfully!");
            }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		// Close resources	
		}finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	

		
		
		
		
		
		
	

}

