/**
 * Created by Riska Amaliyah on 31/05/2017.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class admin2 extends JFrame {

	Connection connection=null;
	private JPanel contentPane;
	private JTextField biaya;
	private JTable biayaLama;
	
	public admin2() {
		connection = connector.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBiayaRetribusi = new JLabel("Biaya Retribusi");
		lblBiayaRetribusi.setForeground(Color.BLUE);
		lblBiayaRetribusi.setFont(new Font("Biaya", Font.BOLD, 29));
		lblBiayaRetribusi.setBounds(128, 11, 222, 50);
		contentPane.add(lblBiayaRetribusi);
		
		biaya = new JTextField();
		biaya.setFont(new Font("Masuk", Font.PLAIN, 30));
		biaya.setBounds(104, 72, 246, 40);
		contentPane.add(biaya);
		biaya.setColumns(10);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					String query="Update retribusi set biaya='"+biaya.getText()+"'";
					
	                PreparedStatement pst=connection.prepareStatement(query);
	                pst.execute();
	                
	                    JOptionPane.showMessageDialog(null, "Biaya Retribusi sekarang '"+biaya.getText()+"'");
	                    reload();
	                    pst.close();
	                    
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			
			}
		});
		btnNewButton.setFont(new Font("Masuk", Font.BOLD, 16));
		btnNewButton.setBounds(166, 141, 120, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblBiayaRetribusiYang = new JLabel("Biaya retribusi yang dipakai");
		lblBiayaRetribusiYang.setForeground(Color.blue);
		lblBiayaRetribusiYang.setBounds(34, 212, 200, 27);
		contentPane.add(lblBiayaRetribusiYang);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new PanelAdmin().setVisible(true);
			}
		});
		btnBack.setBounds(10, 11, 65, 23);
		contentPane.add(btnBack);
		
		biayaLama = new JTable();
		biayaLama.setFont(new Font("Masuk", Font.PLAIN, 16));
		biayaLama.setBounds(211, 216, 75, 16);
		contentPane.add(biayaLama);
		reload();
		
	}
	public void reload()
	{
		try 
		{
			String query= "select biaya from retribusi";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			//biayaLama.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
