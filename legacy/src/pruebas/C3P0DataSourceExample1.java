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
/*import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0DataSourceExample1 {

    public static void main(String[] args)    {
        long st=System.currentTimeMillis();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
         
        try {
           
            long sts=System.currentTimeMillis();
             connection = DataSource.getInstance().getConnection(); 
            System.out.print ("  con "+ (System.currentTimeMillis()-sts )); 
            resultSet = connection.createStatement().executeQuery("select * from nacionalidad");
              while (resultSet.next()) {
                  System.out.println( resultSet.getString(1));
                  System.out.println( resultSet.getString(2));
              }
        } catch (SQLException   | IOException | PropertyVetoException e) {
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {}
            if (statement != null) try { statement.close(); } catch (SQLException e) {}
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }  
       System.out.print ("   tardo  "+ (System.currentTimeMillis()-st )); 
    }
}*/