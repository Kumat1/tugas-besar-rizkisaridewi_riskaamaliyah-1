/**
 * Created by Riska Amaliyah on 31/05/2017.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class admin1 extends JFrame {

	Connection connection=null;
	private JPanel contentPane;
	private JTextField kode_kota;
	private JTextField asal;
	private JTextField tujuan;
	private JTextField bisnis;
	private JTextField ekonomi;
	private JTable table;
	public admin1() {

		connection = connector.dbConnector();
		reloadtable();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 470);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDaftarHarga = new JLabel("Daftar Harga Tiket Kereta Api");
		lblDaftarHarga.setFont(new Font("Masuk", Font.BOLD, 14));
		lblDaftarHarga.setBounds(188, 11, 209, 30);
		contentPane.add(lblDaftarHarga);

		JLabel lblNewLabel = new JLabel("Kode Kota");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Masuk", Font.BOLD, 12));
		lblNewLabel.setBounds(97, 60, 62, 22);
		contentPane.add(lblNewLabel);

		kode_kota = new JTextField();
		kode_kota.setBounds(188, 61, 182, 20);
		contentPane.add(kode_kota);
		kode_kota.setColumns(10);

		JLabel lblAsal = new JLabel("Asal");
		lblAsal.setForeground(Color.BLUE);
		lblAsal.setFont(new Font("Masuk", Font.BOLD, 12));
		lblAsal.setBounds(96, 94, 50, 22);
		contentPane.add(lblAsal);

		asal = new JTextField();
		asal.setBounds(188, 94, 182, 20);
		contentPane.add(asal);
		asal.setColumns(10);

		JLabel lblTujuan = new JLabel("Tujuan");
		lblTujuan.setForeground(Color.BLUE);
		lblTujuan.setFont(new Font("Masuk", Font.BOLD, 12));
		lblTujuan.setBounds(97, 132, 50, 17);
		contentPane.add(lblTujuan);

		tujuan = new JTextField();
		tujuan.setBounds(188, 133, 182, 20);
		contentPane.add(tujuan);
		tujuan.setColumns(10);


		JLabel lblBisnis = new JLabel("Bisnis");
		lblBisnis.setForeground(Color.BLUE);
		lblBisnis.setFont(new Font("Masuk", Font.BOLD, 12));
		lblBisnis.setBounds(97, 160, 50, 22);
		contentPane.add(lblBisnis);

		bisnis = new JTextField();
		bisnis.setBounds(188, 164, 182, 20);
		contentPane.add(bisnis);
		bisnis.setColumns(10);

		JLabel lblEkonomi = new JLabel("Ekonomi");
		lblEkonomi.setForeground(Color.BLUE);
		lblEkonomi.setFont(new Font("Masuk", Font.BOLD, 12));
		lblEkonomi.setBounds(97, 193, 50, 22);
		contentPane.add(lblEkonomi);

		ekonomi = new JTextField();
		ekonomi.setBounds(188, 195, 182, 17);
		contentPane.add(ekonomi);
		ekonomi.setColumns(10);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

						try{
							int row = table.getSelectedRow();
							String kode_kotaku=(table.getModel().getValueAt(row,0)).toString();

							String query = "select * from harga where kode_kota='"+kode_kotaku+"'";
							PreparedStatement pst = connection.prepareStatement(query);

							ResultSet rs = pst.executeQuery();

							while(rs.next())
							{
								kode_kota.setText(rs.getString("kode_kota"));
								asal.setText(rs.getString("asal"));
								tujuan.setText(rs.getString("tujuan"));
								bisnis.setText(rs.getString("bisnis"));
								ekonomi.setText(rs.getString("ekonomi"));
							}
							pst.close();
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}

			}
		});

		table.setBounds(36, 244, 461, 148);
		reloadtable();
		contentPane.add(table);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try{
					String query= "insert into harga values(?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,kode_kota.getText());
					pst.setString(2,asal.getText());
					pst.setString(3,tujuan.getText());
					pst.setString(4,bisnis.getText());
					pst.setString(5,ekonomi.getText());

					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Inserted successfully");
					pst.close();
					reloadtable();
					kode_kota.setText("");
					asal.setText("");
					tujuan.setText("");
					bisnis.setText("");
					ekonomi.setText("");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(394, 60, 89, 23);
		contentPane.add(btnInsert);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{

					String query="Update harga set kode_kota='"+kode_kota.getText()+"',asal='"+asal.getText()+"'"
	                            +",tujuan='"+tujuan.getText()+"',bisnis='"+bisnis.getText()+"',ekonomi='"+ekonomi.getText()+"'where kode_kota='"+kode_kota.getText()+"'";


	                    PreparedStatement pst=connection.prepareStatement(query);


	                    pst.execute();

	                    JOptionPane.showMessageDialog(null, "Data Saved successfully");

	                    pst.close();
	                    reloadtable();
	                    kode_kota.setText("");
						asal.setText("");
						tujuan.setText("");
						bisnis.setText("");
						ekonomi.setText("");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}
		});
		btnUpdate.setBounds(394, 94, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{

					String query="delete from harga where kode_kota='"+kode_kota.getText()+"'";


	                    PreparedStatement pst=connection.prepareStatement(query);


	                    pst.execute();

	                    JOptionPane.showMessageDialog(null, "Data Deleted");

	                    pst.close();
	                    reloadtable();
	                    kode_kota.setText("");
						asal.setText("");
						tujuan.setText("");
						bisnis.setText("");
						ekonomi.setText("");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(394, 124, 89, 23);
		contentPane.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 244, 461, 148);
		table.add(scrollPane);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new PanelAdmin().setVisible(true);
			}
		});
		btnBack.setBounds(10, 11, 74, 30);
		contentPane.add(btnBack);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 482, 312);
		label.setIcon(new ImageIcon(""));
		contentPane.add(label);

	}
	
	public void reloadtable()
	{
		try
		{
			String query= "select * from harga";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			//table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
