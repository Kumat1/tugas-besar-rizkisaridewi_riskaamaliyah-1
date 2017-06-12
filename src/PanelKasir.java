/**
 * Riska Amaliyah on 2/06/2017.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


public class PanelKasir extends JFrame {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/kereta_api";

	static Integer Integerjumlah;
	static long Integertiket;
	static int biaya=5000;
	String  kelas;
	BufferedImage bImage = null;
	private JPanel contentPane;
	private JTextField ktp,nama,jlhTiket;
	private StringBuffer results;
	private JButton btnPesan,Cetak,PesanBaru;
	private JComboBox combo,comboBox ;
	private JTextArea textArea,textArea_1;
	private JLabel picture,foto;
	private JFileChooser fc;
	private Statement statement;
	private Connection connection;

	public PanelKasir() {

		String country[] = { "Tanjung Balai", "Kisaran", "Sibolga", "Siantar", "Tebing Tinggi", "Perbaungan", "Batang Kuis" };
		String kelas[] = { "Bisnis","Ekonomi" };

		setResizable(false);
		setTitle("KASIR PEMESANAN TIKET KERETA API");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.white);
		panel_3.setBounds(0, 110, 570, 392);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		ktp = new JTextField();
		ktp.setBounds(99, 47, 179, 20);
		panel_3.add(ktp);
		ktp.setColumns(10);

		JLabel lblNoKtp = new JLabel("NO KTP");
		lblNoKtp.setForeground(Color.BLUE);
		lblNoKtp.setFont(new Font("Masuk", Font.BOLD, 13));
		lblNoKtp.setBounds(27, 48, 61, 17);
		panel_3.add(lblNoKtp);

		nama = new JTextField();
		nama.setBounds(99, 78, 179, 20);
		panel_3.add(nama);
		nama.setColumns(10);

		JLabel lblNama = new JLabel("NAMA");
		lblNama.setForeground(Color.BLUE);
		lblNama.setFont(new Font("Masuk", Font.BOLD, 13));
		lblNama.setBounds(27, 80, 46, 14);
		panel_3.add(lblNama);

		combo = new JComboBox(country);
		combo.setBounds(99, 109, 179, 20);
		panel_3.add(combo);
		combo.addActionListener(new ListenImage());

		JLabel lblTujuan = new JLabel("TUJUAN");
		lblTujuan.setForeground(Color.BLUE);
		lblTujuan.setFont(new Font("Masuk", Font.BOLD, 13));
		lblTujuan.setBounds(27, 112, 61, 14);
		panel_3.add(lblTujuan);

		JLabel lblDaftarHargaTiket = new JLabel("DAFTAR HARGA TIKET");
		lblDaftarHargaTiket.setForeground(Color.BLUE);
		lblDaftarHargaTiket.setFont(new Font("Masuk", Font.BOLD, 12));
		lblDaftarHargaTiket.setBounds(78, 137, 140, 20);
		panel_3.add(lblDaftarHargaTiket);

		JLabel lblKelas = new JLabel("KELAS");
		lblKelas.setForeground(Color.BLUE);
		lblKelas.setFont(new Font("Masuk", Font.BOLD, 13));
		lblKelas.setBounds(27, 290, 46, 14);
		panel_3.add(lblKelas);

		jlhTiket = new JTextField();
		jlhTiket.setBounds(99, 328, 180, 20);
		panel_3.add(jlhTiket);
		jlhTiket.setColumns(10);

		JLabel lblJumlahTiket = new JLabel("JLH TIKET");
		lblJumlahTiket.setForeground(Color.BLUE);
		lblJumlahTiket.setFont(new Font("Masuk", Font.BOLD, 12));
		lblJumlahTiket.setBounds(27, 331, 61, 14);
		panel_3.add(lblJumlahTiket);

		btnPesan = new JButton("PESAN");
		btnPesan.setFont(new Font("Masuk", Font.BOLD, 11));
		btnPesan.setBackground(Color.ORANGE);
		btnPesan.setBounds(99, 359, 179, 23);
		panel_3.add(btnPesan);
		btnPesan.addActionListener(new TampilHasil());

		JLabel lblFotoAnda = new JLabel("Foto Anda");
		lblFotoAnda.setForeground(Color.BLUE);
		lblFotoAnda.setFont(new Font("Masuk", Font.BOLD, 13));
		lblFotoAnda.setBounds(386, 11, 72, 14);
		panel_3.add(lblFotoAnda);

		foto = new JLabel();
		foto.setBackground(Color.white);
		foto.setBounds(347, 36, 159, 140);
		foto.setIcon(new ImageIcon("images/add_user.png"));
		panel_3.add(foto);

		JLabel lblNewLabel_1 = new JLabel("Kota Tujuan");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Masuk", Font.BOLD, 12));
		lblNewLabel_1.setBounds(386, 217, 78, 14);
		panel_3.add(lblNewLabel_1);

		JButton upload = new JButton("UPLOAD");
		upload.setBackground(Color.ORANGE);
		upload.setBounds(347, 183, 159, 23);
		upload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        foto.setIcon(new ImageIcon(ImageIO.read(file)));
                     } catch (IOException e) {
                    }
                }
            }
        });
		panel_3.add(upload);


		picture = new JLabel("");
		picture.setBounds(347, 246, 159, 136);
		panel_3.add(picture);
		updateLabel(country[combo.getSelectedIndex()]);

		JLabel lblFormPemesanan = new JLabel("FORM PEMESANAN");
		lblFormPemesanan.setForeground(Color.BLUE);
		lblFormPemesanan.setFont(new Font("Masuk", Font.BOLD, 12));
		lblFormPemesanan.setBounds(99, 12, 122, 24);
		panel_3.add(lblFormPemesanan);

		comboBox = new JComboBox(kelas);
		comboBox.setBounds(99, 288, 179, 20);
		panel_3.add(comboBox);


		try {
			
         System.setProperty( "db2j.system.home", "C:/Cloudscape_5.0" );

         Class.forName( JDBC_DRIVER );
         connection = DriverManager.getConnection( DATABASE_URL,  "root", "");
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery( "SELECT tujuan,bisnis,ekonomi FROM harga" );
		results = new StringBuffer();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();   
        for ( int i = 1; i <= numberOfColumns; i++ )
           results.append( metaData.getColumnName( i ) + "\t" );
        
        results.append( "\n" );
        
        while ( resultSet.next() ) {
           
           for ( int i = 1; i <= numberOfColumns; i++ )
              results.append( resultSet.getObject( i ) + "\t" );
           
           results.append( "\n" );
        }
		}
		 catch ( SQLException sqlException ) {                              
             JOptionPane.showMessageDialog( null, sqlException.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE );               
                                                                             
             System.exit( 1 );                                               
          }   
		catch ( ClassNotFoundException classNotFound ) {                    
            JOptionPane.showMessageDialog( null, classNotFound.getMessage(), 
               "Driver Not Found", JOptionPane.ERROR_MESSAGE );              
                                                                             
            System.exit( 1 );                                                
         }
		
		textArea_1 = new JTextArea(results.toString());
		textArea_1.setBounds(27, 154, 251, 123);
		panel_3.add(textArea_1);




		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.black);
		panel_1.setBounds(0, 0, 774, 112);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.ORANGE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new welcome().setVisible(true);
			}
		});
		btnBack.setBounds(0, 0, 77, 23);
		panel_1.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/2.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 110);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.black);
		panel_2.setBounds(580, 110, 194, 394);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 174, 253);
		textArea.setEditable(false);
		panel_2.add(textArea);
		
		Cetak = new JButton("CETAK");
		Cetak.setBackground(Color.ORANGE);
		Cetak.setBounds(10, 275, 174, 23);
		Cetak.setEnabled(false);
		panel_2.add(Cetak);
		Cetak.addActionListener(new Cetakan());
		
		PesanBaru = new JButton("Pesan Baru");
		PesanBaru.setBackground(Color.ORANGE);
		PesanBaru.setBounds(10, 309, 174, 23);
		PesanBaru.setEnabled(false);
		panel_2.add(PesanBaru);
		
		JLabel lblCreatedBykelompok = new JLabel("Created by :Rizki dan Amel");
		lblCreatedBykelompok.setBounds(34, 380, 160, 14);
		panel_2.add(lblCreatedBykelompok);
		PesanBaru.addActionListener(new Bersihkan());
	}
	//Hitungan harga tiket
		public static long total_bayar(long tkt, int jlh)
		{
			long totalbyr = (tkt*jlh)+biaya*jlh;
			return totalbyr;
		}
		
		
		public static long biaya_tiket(long tkt, int jlh)
		{
			long total = tkt*jlh;
			return total;
		}
		
		public static int retribusi(int jlh)
		{
			int biaya_retribusi = biaya*jlh;
			return biaya_retribusi;
		}

	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PanelKasir.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

	protected void updateLabel(String lokasi) {

        picture.setIcon(new ImageIcon("images/" + lokasi + ".jpg"));
        picture.setToolTipText("A drawing of a " + lokasi.toLowerCase());

    }

		//akhir hitungan
	private class TampilHasil implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Integerjumlah = Integer.parseInt(jlhTiket.getText());
            if (event.getSource() == btnPesan )
            {
				if((String)combo.getSelectedItem()== "Tanjung Balai")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 75000;

					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 50000;

					}
				}
				else if((String) combo.getSelectedItem()== "Kisaran")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 70000;

					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 45000;

					}

				}
				else if((String) combo.getSelectedItem()== "Sibolga")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 60000;

					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 40000;
					}

				}
				else if((String) combo.getSelectedItem()== "Siantar")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 65000;

					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 35000;

					}

				}
				else if((String) combo.getSelectedItem()== "Tebing Tinggi")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 50000;

					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 30000;
					}

				}
				else if((String) combo.getSelectedItem()== "Perbaungan")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 55000;
					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 25000;
					}

				}
				else if((String) combo.getSelectedItem()== "Batang Kuis")
				{
					if((String) comboBox.getSelectedItem() == "Bisnis")
					{
						Integertiket = 40000;
					}
					else if((String) comboBox.getSelectedItem() == "Ekonomi")
					{
						Integertiket = 20000;
					}
				}
				else{
					System.exit( 0 );
			}
			textArea.setText("Tiket Yang Anda Beli :\n"+
									"================================\n"+
									"Foto	:"+fc.getSelectedFile()+"\n"+
									"\nNO KTP		:  "+ktp.getText()+"\n"+
									"Nama   		:  "+nama.getText()+"\n"+
								   "Jumlah Tiket	:  "+jlhTiket.getText()+"\n"+
								   "Kelas			:  "+(String) comboBox.getSelectedItem()+"\n"+
								   "Tujuan			:  "+(String) combo.getSelectedItem()+"\n"+
								   "Retribusi		:  Rp. 5000 \n"+
								   "Biaya Tiket		:  Rp. "+biaya_tiket(Integertiket, Integerjumlah)+"\n"+
								   "Biaya Retribusi	:  Rp. "+retribusi( Integerjumlah)+"\n"+
								   "Biaya Total		:  Rp. "+total_bayar(Integertiket, Integerjumlah)+"\n"+
								   " \n"
								   );
			Cetak.setEnabled(true);
			PesanBaru.setEnabled(true);
			}
		}
	}
	
	 private class Cetakan implements ActionListener
	    {
	        public void actionPerformed(ActionEvent event)
	        {

	            if (event.getSource() == Cetak)
	            {
	                try
					{
						String str = "								Tiket Yang Anda Beli :\n"+
										"====================================================================\n\n\n\n"+
										"\nNama   		:  "+nama.getText()+"\n"+
									   "NO KTP			:  "+ktp.getText()+"\n"+
									   "Jumlah Tiket	:  "+jlhTiket.getText()+"\n"+
									   "Kelas			:  "+(String) comboBox.getSelectedItem()+"\n"+
									   "Tujuan			:  "+(String) combo.getSelectedItem()+"\n"+
									   "Retribusi		:  Rp. 5000 \n"+
									   "Biaya Tiket		:  Rp. "+biaya_tiket(Integertiket, Integerjumlah)+"\n"+
									   "Biaya Retribusi	:  Rp. "+retribusi( Integerjumlah)+"\n"+
									   "Biaya Total		:  Rp. "+total_bayar(Integertiket, Integerjumlah)+"\n"+
									   "\n \n \n"+
									   "Booking card ini hanya berlaku selama 2 hari,"+
									   " jika selama 2 hari tiket belum di tukarkan "+
									   "dengan yang asli maka booking card anda hangus"
									   ;
                        File newTextFile = new File("Bookingcard.doc");

                        FileWriter fw = new FileWriter(newTextFile);
                        fw.write(str);
                        fw.close();

						File file = fc.getSelectedFile();
						bImage = ImageIO.read(file);

						ImageIO.write(bImage, "png", new File("Bookingcard.png"));
                        fw = new FileWriter(newTextFile);
						fw.write(str);
						fw.close();

						JOptionPane.showMessageDialog(null,"Tiket Anda Sudah dicetak","Terima kasih", JOptionPane.PLAIN_MESSAGE);

					} catch (IOException iox) {
						iox.printStackTrace();
					}
	            }
	        }
	    }
	
	private class ListenImage implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			JComboBox cb = (JComboBox)event.getSource();
			String lokasi = (String)cb.getSelectedItem();
			updateLabel(lokasi);
		}
	}

	private class Bersihkan implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			ktp.setText("");
			nama.setText("");
			jlhTiket.setText("");
			textArea.setText("");
			Cetak.setEnabled(false);
			PesanBaru.setEnabled(false);
		}
	}
}
