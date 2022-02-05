import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import java.util.Date;
//frame kedua/transaksi
public class transaksi extends javax.swing.JFrame {
    //deklarasi variable
    Connection koneksi;
    PreparedStatement pst, pst2;
    ResultSet rst;
    int istok, istok2, iharga, ijumlah, kstok, tstok;
    String harga, barang, dbarang, KD, jam, tanggal,ssub;
    public transaksi() {
        initComponents();
        //koneksi db untuk table barang
        koneksi=db.koneksiDB();
        delay();
        detail();    
        autonumber();
        sum();
    }
    //method simpan untuk menyimpan record yg dipanggil
    private void simpan(){
        String tgl=txttgl.getText();
        String jam=txtwaktu.getText();
      try {
            String sql="insert into transaksi (Kode_Transaksi,Kode_Detail,Tanggal,Jam,Total) value (?,?,?,?,?)";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, txtkodetransaksi.getText());
            pst.setString(2, KD);
            pst.setString(3, tgl);
            pst.setString(4, jam);
            pst.setString(5, txttotal.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
    }
    //method total menjumlahkan inputan
    private void total(){
        int total, bayar, kembali;
            total= Integer.parseInt(txtbayar.getText());
            bayar= Integer.parseInt(txttotal.getText());
            kembali=total-bayar;
            String ssub=String.valueOf(kembali);
            txtkembali.setText(ssub);
    }
    //method clsr
    public void clsr(){
        txtjumlah.setText("");
        txtdiskon.setText("");
    }
    //method cari untuk mencari barang
    public void cari(){
        try {
            String sql="select * from barang where Nama_Barang LIKE '%"+txtnamabarang.getText()+"%'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            tbnamabarang.setModel(DbUtils.resultSetToTableModel(rst));
           } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }
    //method kurangi_stok untuk mengurangi stok lalu total juga berubah
    public void kurangi_stok(){
        int qty;
        qty=Integer.parseInt(txtjumlah.getText());
        kstok=istok-qty;
    }
    //method subtotal untuk menjumlahkan dengan diskon
    private void subtotal(){
        int diskon, jumlah, sub;
                if (txtdiskon.getText().equals("")) {diskon=0;}
                else {diskon= Integer.parseInt(txtdiskon.getText());}
             jumlah= Integer.parseInt(txtjumlah.getText());
             sub=(jumlah*iharga)-diskon;
             ssub=String.valueOf(sub);     
    }
    //method tambah_stok untuk menambah stok lalu total juga berubah
    public void tambah_stok(){
        tstok=ijumlah+istok2;
            try {
            String update="update barang set Stok='"+tstok+"' where Kode_Barang='"+barang+"'";
            pst2=koneksi.prepareStatement(update);
            pst2.execute();
            }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    //method ambil_stock untuk belanja lagi
    public void ambil_stock(){
            try {
            String sql="select * from barang where Kode_Barang='"+barang+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            if (rst.next()) {    
            String stok=rst.getString(("Stok"));
            istok2= Integer.parseInt(stok);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }
    //method sum
    public void sum(){
        int totalBiaya = 0;
        int subtotal;
        DefaultTableModel dataModel = (DefaultTableModel) tbdatabarang.getModel();
        int jumlah = tbdatabarang.getRowCount();
        for (int i=0; i<jumlah; i++){
        subtotal = Integer.parseInt(dataModel.getValueAt(i, 5).toString());
        totalBiaya += subtotal;
        }
        txttotal.setText(String.valueOf(totalBiaya));
    }
    //method autonumber
    public void autonumber(){
        try{
            String sql = "SELECT MAX(RIGHT(Kode_Transaksi,3)) AS NO FROM transaksi";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            while (rst.next()) {
                    if (rst.first() == false) {
                        txtkodetransaksi.setText("TRX001");
                    } else {
                        rst.last();
                        int auto_id = rst.getInt(1) + 1;
                        String no = String.valueOf(auto_id);
                        int NomorJual = no.length();
                        for (int j = 0; j < 3 - NomorJual; j++) {
                            no = "0" + no;
                        }
                        txtkodetransaksi.setText("TRX" + no);
                    }
                }
            rst.close();
            }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    //method detail
    public void detail(){
        try {
            String Kode_detail=txtkodetransaksi.getText();
            String KD="D"+Kode_detail;
            String sql="select * from detail_barang where Kode_Detail='"+KD+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            tbdatabarang.setModel(DbUtils.resultSetToTableModel(rst));
           } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }
    //method delay
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
        txttgl = new javax.swing.JTextField();
        txtwaktu = new javax.swing.JTextField();
        lbbarang2 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        btncari = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnamabarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtjumlah = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdiskon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btntambah = new javax.swing.JButton();
        lbbarang3 = new javax.swing.JLabel();
        txtkodetransaksi = new javax.swing.JTextField();
        lbbarang4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdatabarang = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnhapus = new javax.swing.JButton();
        lbbarang5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        lbbarang6 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        lbbarang7 = new javax.swing.JLabel();
        txtkembali = new javax.swing.JTextField();
        btnnew = new javax.swing.JButton();
        btnbayar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelkiri.setBackground(java.awt.Color.black);

        btnlogout.setText("LOGOUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.gray);

        lbbarang.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbbarang.setText("Data Barang");
        lbbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbbarangMouseClicked(evt);
            }
        });

        jPanel3.setBackground(java.awt.Color.darkGray);

        lbtransaksi.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbtransaksi.setText("Transaksi");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbbarang1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbbarang1.setText("Form Transaksi");

        lbbarang2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang2.setText("Masukan nama barang");

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        tbnamabarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbnamabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnamabarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnamabarang);

        jLabel1.setText("jumlah");

        jLabel2.setText("diskon");

        jLabel3.setText("Tambahkan");

        btntambah.setText("+");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        lbbarang3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang3.setText("Kode transaksi");

        lbbarang4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang4.setText("Data barang");

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
        jScrollPane2.setViewportView(tbdatabarang);

        jLabel4.setText("Hapus");

        btnhapus.setText("-");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        lbbarang5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbbarang5.setText("Total");

        lbbarang6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang6.setText("Bayar");

        lbbarang7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbbarang7.setText("Kembali");

        btnnew.setText("NEW");

        btnbayar.setText("BAYAR");
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(lbbarang2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelkiri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncari))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbbarang3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbbarang4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel1))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbbarang5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbbarang6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbbarang7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(btnhapus)
                                            .addComponent(jLabel4))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnbayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                    .addComponent(jLabel3)
                                                    .addComponent(btntambah)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtwaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbbarang1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelkiri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbbarang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtwaktu)
                    .addComponent(txttgl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbbarang2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncari, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(txtnamabarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntambah))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbbarang3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbbarang4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(4, 4, 4)
                                .addComponent(btnhapus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbbarang5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbbarang6)
                            .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbbarang7))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        new login().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void lbbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbbarangMouseClicked
        new barang().setVisible(true);
        dispose();
    }//GEN-LAST:event_lbbarangMouseClicked

    private void lblaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblaporanMouseClicked
        new subpopup().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblaporanMouseClicked

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        subtotal();
        kurangi_stok();
        try {
        String diskon;
            if (txtdiskon.getText().equals("")) {diskon="0";}
            else {diskon=txtdiskon.getText();}
        String Kode_detail=txtkodetransaksi.getText();
        KD="D"+Kode_detail;
            String sql="insert into detail_barang (Kode_Detail,Kode_Barang,Harga,Jumlah,Discount,Subtotal) value (?,?,?,?,?,?)";
            String update="update barang set Stok='"+kstok+"' where Kode_Barang='"+barang+"'";
            pst=koneksi.prepareStatement(sql);
            pst2=koneksi.prepareStatement(update);
            pst.setString(1, KD);
            pst.setString(2, barang);
            pst.setString(3, harga);
            pst.setString(4, txtjumlah.getText());
            pst.setString(5, diskon);
            pst.setString(6, ssub);
            pst.execute();
            pst2.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
        detail();
            sum();
            cari();
            clsr();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try {
            String sql="delete from detail_barang where Kode_Barang=?";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, dbarang);
            pst.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        detail();
        sum();
        tambah_stok();
        cari();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
        total();
      simpan();
      autonumber();
      detail();
      txttotal.setText("");
      txtbayar.setText("");
      txtkembali.setText("");
      txtnamabarang.setText("");
      cari();
    }//GEN-LAST:event_btnbayarActionPerformed

    private void tbnamabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnamabarangMouseClicked
        try {
            int row=tbnamabarang.getSelectedRow();
            String tabel_klik=(tbnamabarang.getModel().getValueAt(row, 0).toString());
            String sql="select * from barang where Kode_Barang='"+tabel_klik+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
                if (rst.next()) {
                barang=rst.getString(("Kode_Barang"));    
                String stok=rst.getString(("Stok"));
                istok= Integer.parseInt(stok);
                harga=rst.getString(("Harga"));
                iharga= Integer.parseInt(harga);
                }
            }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_tbnamabarangMouseClicked

    private void tbdatabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatabarangMouseClicked
        try {
            int row=tbdatabarang.getSelectedRow();
            dbarang=(tbdatabarang.getModel().getValueAt(row, 1).toString());
            String sql="select * from detail_barang where Kode_Barang='"+dbarang+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            if (rst.next()) {   
            String jumlah=rst.getString(("Jumlah"));
            ijumlah= Integer.parseInt(jumlah);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
             ambil_stock();
    }//GEN-LAST:event_tbdatabarangMouseClicked

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbayar;
    private javax.swing.JToggleButton btncari;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTable tbnamabarang;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtdiskon;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txtkodetransaksi;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txttgl;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtwaktu;
    // End of variables declaration//GEN-END:variables
}
