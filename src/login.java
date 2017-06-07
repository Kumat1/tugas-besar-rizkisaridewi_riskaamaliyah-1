/**
 * Created by Rizki Sari Dewi on 22/05/2017.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JLabel user,pass;
	private JButton btnMasuk;
	private JButton btnBack;
	private JLabel label;
	private JLabel label_1;

	public login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new welcome().setVisible(true);
			}
		});
		btnBack.setBounds(0, 0, 75, 23);
		contentPane.add(btnBack);
		
		username = new JTextField();
		username.setFont(new Font("Masuk", Font.PLAIN, 14));
		username.setBounds(167, 71, 166, 32);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Masuk", Font.PLAIN, 14));
		password.setBounds(167, 125, 170, 32);
		contentPane.add(password);
		
		user = new JLabel("Username");
		user.setForeground(Color.BLACK);
		user.setFont(new Font("login", Font.BOLD, 15));
		user.setBounds(72, 78, 85, 14);
		contentPane.add(user);
		
		pass = new JLabel("Password");
		pass.setForeground(Color.BLACK);
		pass.setFont(new Font("login", Font.BOLD, 15));
		pass.setBounds(72, 132, 81, 14);
		contentPane.add(pass);
		
		JButton btnBatal = new JButton("BATAL");
		btnBatal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username.setText("");
				password.setText("");
			}
		});
		btnBatal.setBounds(223, 177, 110, 23);
		contentPane.add(btnBatal);
		
		JLabel lblPemesananTiketKereta = new JLabel("LOGIN ADMINISTRATOR");
		lblPemesananTiketKereta.setForeground(Color.BLACK);
		lblPemesananTiketKereta.setFont(new Font("login", Font.BOLD, 15));
		lblPemesananTiketKereta.setBounds(127, 11, 235, 32);
		contentPane.add(lblPemesananTiketKereta);
		
		btnMasuk = new JButton("MASUK");
		btnMasuk.setFont(new Font("Masuk", Font.PLAIN, 13));
		btnMasuk.setBounds(108, 177, 105, 23);
		contentPane.add(btnMasuk);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("images/Kecil.jpg"));
		label.setBounds(333, 0, 101, 67);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("images/admin_icon.jpg"));
		label_1.setBounds(0, 103, 136, 159);
		contentPane.add(label_1);
		btnMasuk.addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e)

	    {
	        showData();
	    }
	private void showData()
	{

		try
		{
			 PanelAdmin f1 = new PanelAdmin();

		        String str1 = username.getText();
		        char[] p = password.getPassword();

		        String str2 = new String(p);

		        try

		        {
		            Class.forName("com.mysql.jdbc.Driver");

		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kereta_api", "root", "");

		            PreparedStatement ps = con.prepareStatement("select name from login where name=? and pass=?");
		            ps.setString(1, str1);
		            ps.setString(2, str2);
		            ResultSet rs = ps.executeQuery();
		            
		            if (rs.next())
		            {
		            	dispose();
		            	f1.setVisible(true);
		            }
		            else
		            {
		                JOptionPane.showMessageDialog(null,
		                   "Incorrect email-Id or password..Try Again with correct detail");
		            }
		        }
		        catch (Exception ex)
		        {
		            System.out.println(ex);
		        }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}