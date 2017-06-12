/**
 * Created by Rizki Sari Dewi on 16/05/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAwal extends JFrame {

    public FrameAwal(){
        JPanel panel = (JPanel) this.getContentPane();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);

        JLabel tulisan = new JLabel("Sistem Informasi Kerete Api");
        JLabel tulisan2 = new JLabel("Create By Rizki Sari Dewi dan Rizka Amaliyah");
        JButton tombol = new JButton("Informasi Tiket");
        JButton tombol1 = new JButton("Informasi Jadwal");
       // JButton tombo3 = new JButton("Informasi "); //membuat tombol

        tombol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FrameAwal.this,"Informasi Mengenai Tiket Kerete Api", "Harga Tiket",
                        JOptionPane.INFORMATION_MESSAGE);
                if(JOptionPane.showConfirmDialog(FrameAwal.this, "Apakah Anda Ingin  Membeli Tiket",
                        "Harga Tiket",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                {
                    String message = JOptionPane.showInputDialog(FrameAwal.this,"Tulislah berapa jumlah tiket yang anda akan beli","Tuliskan Jumlahnya");

                    System.out.println(message);
                }
            }
        });
        panel.add(tulisan, BorderLayout.NORTH);
        panel.add(tombol, BorderLayout.CENTER);
        panel.add(tulisan2, BorderLayout.SOUTH);

        panel.setBackground(Color.lightGray);

        this.pack();
        this.setMinimumSize(this.getPreferredSize());



        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sistem Informasi Kereta Api");
    }

    public static void main(String args[]){
        FrameAwal frameAwal = new FrameAwal();
    }
}
