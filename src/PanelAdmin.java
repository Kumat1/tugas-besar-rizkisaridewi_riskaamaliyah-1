/**
 * Created by Riska Amaliyah on 30/05/2017.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelAdmin extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PanelAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRetribusi = new JButton("RETRIBUSI");
		btnRetribusi.setFont(new Font("Console", Font.BOLD, 21));
		btnRetribusi.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnRetribusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new admin2().setVisible(true);
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new welcome().setVisible(true);
			}
		});
		btnLogout.setBounds(383, 19, 89, 23);
		contentPane.add(btnLogout);
		btnRetribusi.setBounds(253, 191, 185, 46);
		contentPane.add(btnRetribusi);
		
		JButton btnHargaTiket = new JButton("HARGA TIKET");
		btnHargaTiket.setFont(new Font("Console", Font.BOLD, 21));
		btnHargaTiket.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnHargaTiket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new admin1().setVisible(true);
			}
		});
		btnHargaTiket.setBounds(31, 191, 190, 46);
		contentPane.add(btnHargaTiket);
		
		JLabel lblAdministrator = new JLabel("ADMINISTRATOR");
		lblAdministrator.setBackground(UIManager.getColor("Button.light"));
		lblAdministrator.setForeground(new Color(0, 0, 0));
		lblAdministrator.setFont(new Font("Arial Black", Font.BOLD, 26));
		lblAdministrator.setBounds(31, 0, 301, 46);
		contentPane.add(lblAdministrator);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 482, 312);
		label.setIcon(new ImageIcon("images/1.jpg"));
		contentPane.add(label);
	}
}
