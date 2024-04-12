package expense_income_tracker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	public static boolean validate(String Description, String Amount, String Category) {
		boolean status = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/viveek", "root", "viveek");

			PreparedStatement ps = con.prepareStatement("select * from banking where Description=? and Amount=? and Category=?");
			ps.setString(1, Description);
			ps.setString(2, Amount);
			ps.setString(3, Category);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
		
	}
}
