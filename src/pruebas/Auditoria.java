
package pruebas;

import frames.Login;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import config.conectarDB;

/**
 *
 * @author erodriguez
 */
public class Auditoria {
conectarDB DB= new conectarDB();
Statement st;
ResultSet rs;

  public void auditar(int accion, String des){
        try {
            InetAddress host = InetAddress.getLocalHost();
            host.getAddress();//ip
            host.getHostAddress();//ip host
            host.getHostName();
            //host.getLocalHost();
            
            String sql = "select emp_ci,user_cod from usuarios where user_alias like '%"+Login.user+"%'";
            st = DB.con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();                      
sql = "INSERT INTO auditorias (emp_ci,user_cod,suc_cod,eve_cod,aut_des,aut_date,aut_time, aut_host)"
      +" VALUES ('"+rs.getString(1)+"','"+rs.getString(2)+"',suc,'"+accion+"','"+des+"',CURDATE(),CURTIME(),'"+host.getHostName()+"')";
            st.executeUpdate(sql);                                                                                                                                                                                      //nombre de la pc
        } catch (SQLException | UnknownHostException ex) {
            JOptionPane.showMessageDialog( null, ex.getMessage(),"Error al ejecutar Auditoria", JOptionPane.ERROR_MESSAGE );
        }        
    }    
}
