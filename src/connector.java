/**
 * Created by Riska Amaliyah on 22/05/2017.
 */

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class connector {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/kereta_api","root","");
			return con;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
