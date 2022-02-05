import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
//class untuk koneksi db mysql
public class db {
    //deklarasi variable
    Connection koneksi=null;
    //buat method koneksi dengan statement trycatch
    public static Connection koneksiDB(){
    //try untuk koneksi, catch untuk gagal/exception
    try { 
        Class.forName("com.mysql.jdbc.Driver");
        Connection koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
        return koneksi;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
  }       
}
