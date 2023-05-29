/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

/**
 *
 * @author schafler92
 */
/*import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class C3P0DataSourceExample {

    public static void main(String[] args) throws SQLException {
        try {
              ComboPooledDataSource cpds = new ComboPooledDataSource();
cpds.setDriverClass( "com.mysql.jdbc.Driver");//org.postgresql.Driver" ); //loads the jdbc driver            
cpds.setJdbcUrl( "jdbc:mysql://localhost/taller" );
cpds.setUser("root");                                  
cpds.setPassword("wasd");
cpds.setMinPoolSize(3);                                     
cpds.setAcquireIncrement(5);
cpds.setMaxPoolSize(20);

Connection con=cpds.getConnection();
            Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select count(1) from v_nac");
        if(rs.next()){
          System.out.print("\n"+rs.getInt(1));  
        }else{
            JOptionPane.showMessageDialog(null, "sin resultdo");
        }
        
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}*/