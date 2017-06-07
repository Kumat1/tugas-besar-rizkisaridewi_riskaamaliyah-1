/**
 * Created by Riska Amaliyah on 25/05/2017.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class welcome extends JFrame {

	private JPanel contentPane;

	public welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 419);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnKasir = new JButton("KASIR");
		btnKasir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new PanelKasir().setVisible(true);
			}
		});
		btnKasir.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnKasir.setBounds(354, 345, 139, 25);
		contentPane.add(btnKasir);

		JButton btnAdmin = new JButton("ADMIN");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new login().setVisible(true);
			}
		});
		btnAdmin.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnAdmin.setBounds(215, 345, 129, 25);
		contentPane.add(btnAdmin);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/KAI1.jpg"));
		lblNewLabel.setBounds(0, 0, 503, 316);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(11, 356, 194, 14);
		contentPane.add(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome frame = new welcome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
