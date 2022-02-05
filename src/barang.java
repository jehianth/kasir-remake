import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
//frame barang
public class barang extends javax.swing.JFrame {
    //deklarasi variable
    Connection koneksi;
    PreparedStatement pst;
    ResultSet rst;
    public barang() {
        initComponents();
        koneksi=db.koneksiDB();
        update_tabel();
        delay();
    }
    
    public void delay(){
    Thread clock=new Thread(){
        public void run(){
            for(;;){
                Calendar cal=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
                txtwaktu.setText(format.format(cal.getTime()));
                 txttgl.setText(format2.format(cal.getTime()));
                
            try { sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
    clock.start();
    }
    
    public void update_tabel(){
       try {
        String sql="select * from barang";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        tbdatabarang.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);}        
    }
    
    private void clsr(){
        txtnamabarang.setText("");
        txtkodebarang.setText("");
        txtstok.setText("");
        txtharga.setText("");
        txtsatuan.setText("");
    }
    
    private void simpan(){
     try {
            String sql="insert into barang (Kode_Barang,Nama_Barang,Stok,Satuan,Harga) value (?,?,?,?,?)";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, txtkodebarang.getText());
            pst.setString(2, txtnamabarang.getText());
            pst.setString(3, txtstok.getText());
            pst.setString(4, txtsatuan.getText());
            pst.setString(5, txtharga.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
           update_tabel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelkiri = new javax.swing.JPanel();
        btnlogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbbarang = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbtransaksi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblaporan = new javax.swing.JLabel();
        lbbarang1 = new javax.swing.JLabel();
        lbbarang2 = new javax.swing.JLabel();
        txtkodebarang = new javax.swing.JTextField();
        btnsave = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        lbbarang3 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        lbbarang4 = new javax.swing.JLabel();
        txtstok = new javax.swing.JTextField();
        lbbarang5 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        lbbarang6 = new javax.swing.JLabel();
        txtsatuan = new javax.swing.JTextField();
        btnupdate = new javax.swing.JToggleButton();
        btndelete = new javax.swing.JToggleButton();
        lbbarang7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdatabarang = new javax.swing.JTable();
        txttgl = new javax.swing.JTextField();
        txtwaktu = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelkiri.setBackground(java.awt.Color.black);

        btnlogout.setText("LOGOUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.darkGray);

        lbbarang.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbbarang.setText("Data Barang");

        jPanel3.setBackground(java.awt.Color.gray);

        lbtransaksi.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbtransaksi.setText("Transaksi");
        lbtransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbtransaksiMouseClicked(evt);
            }
        });

        jPanel4.setBackground(java.awt.Color.gray);

        lblaporan.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblaporan.setText("Laporan");
        lblaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblaporanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblaporan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblaporan)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbtransaksi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbtransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbbarang)
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbbarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelkiriLayout = new javax.swing.GroupLayout(panelkiri);
        panelkiri.setLayout(panelkiriLayout);
        panelkiriLayout.setHorizontalGroup(
            panelkiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelkiriLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnlogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelkiriLayout.setVerticalGroup(
            panelkiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelkiriLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbbarang1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbbarang1.setText("Input Data Barang");

        lbbarang2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang2.setText("Kode Barang");

        btnsave.setText("save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel1.setText("jumlah");

        lbbarang3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang3.setText("Nama Barang");

        lbbarang4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang4.setText("Stok");

        lbbarang5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang5.setText("Harga");

        lbbarang6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang6.setText("Satuan");

        btnupdate.setText("update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        lbbarang7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang7.setText("Data Barang");

        tbdatabarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbdatabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatabarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbdatabarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelkiri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbbarang2)
                                    .addComponent(txtkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbarang3)
                                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbarang4)
                                    .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbarang5)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbarang6)
                                    .addComponent(txtsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(239, 239, 239)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbarang7)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbbarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtwaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(1040, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelkiri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbbarang1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtwaktu, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txttgl))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbbarang2)
                    .addComponent(lbbarang7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbbarang3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbbarang4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbbarang5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbbarang6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btndelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(367, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(247, 247, 247)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        new login().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void lblaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblaporanMouseClicked
        new subpopup().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblaporanMouseClicked

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        simpan();
        clsr();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        try {
            String value1=txtnamabarang.getText();
            String value2=txtkodebarang.getText();
            String value3=txtstok.getText();
            String value4=txtsatuan.getText();
            String value5=txtharga.getText();
            String sql="update barang set Nama_Barang='"+value1+"' ,Stok='"+value3+"' ,Satuan='"+value4+"' ,Harga='"+value5+"' where Kode_Barang='"+value2+"'";
            pst=koneksi.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        update_tabel();
        clsr();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        try { 
            String sql="delete from barang where Kode_Barang=?";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, txtkodebarang.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Terhapus");
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);} 
            update_tabel();
            clsr();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void tbdatabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatabarangMouseClicked
    try {
        int row=tbdatabarang.getSelectedRow();
        String tabel_klik=(tbdatabarang.getModel().getValueAt(row, 0).toString());
        String sql="select * from barang where Kode_Barang='"+tabel_klik+"'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        if (rst.next()) {
            String data1=rst.getString(("Kode_Barang"));
            txtkodebarang.setText(data1);
            String data2=rst.getString(("Nama_Barang"));
            txtnamabarang.setText(data2);
            String data3=rst.getString(("Stok"));
            txtstok.setText(data3);
            String data4=rst.getString(("Satuan"));
            txtsatuan.setText(data4);
            String data5=rst.getString(("Harga"));
            txtharga.setText(data5);
        }
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbdatabarangMouseClicked

    private void lbtransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbtransaksiMouseClicked
        new transaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_lbtransaksiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btndelete;
    private javax.swing.JButton btnlogout;
    private javax.swing.JToggleButton btnsave;
    private javax.swing.JToggleButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbbarang;
    private javax.swing.JLabel lbbarang1;
    private javax.swing.JLabel lbbarang2;
    private javax.swing.JLabel lbbarang3;
    private javax.swing.JLabel lbbarang4;
    private javax.swing.JLabel lbbarang5;
    private javax.swing.JLabel lbbarang6;
    private javax.swing.JLabel lbbarang7;
    private javax.swing.JLabel lblaporan;
    private javax.swing.JLabel lbtransaksi;
    private javax.swing.JPanel panelkiri;
    private javax.swing.JTable tbdatabarang;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtkodebarang;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtsatuan;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txttgl;
    private javax.swing.JTextField txtwaktu;
    // End of variables declaration//GEN-END:variables
}
