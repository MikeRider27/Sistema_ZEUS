
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects;

public class DoubleCboThreadGT extends Thread{

 private boolean continuar = true;
   String sql=null;
   Object data=null;
   JComboBox cbo1=null,cbo2=null;
   Connection con = null;
    PreparedStatement st;
   ResultSet rs;

  public DoubleCboThreadGT(String sql,JComboBox cbo1,JComboBox cbo2,Connection con){
       this.sql=sql;
       this.cbo1=cbo1;
       this.cbo2=cbo2;
       this.con=con;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
      while (continuar){
          try {
              st=con.prepareStatement(sql);
              rs=st.executeQuery();
              while(rs.next()){
                  data=new LoadWithObjects(rs.getString(1),rs.getString(2));
                  cbo1.addItem(data);
                  cbo2.addItem(data);
              }
 System.out.print("\n -- DTHREAD -- "+sql);
              stop_();
          } catch (SQLException e) {
              javax.swing.JOptionPane.showMessageDialog( null, e.getMessage(), "Error al cargar listas desplegables (Thread)",javax.swing.JOptionPane.ERROR_MESSAGE );
              stop_();
              System.exit(1);
  } 
 } 
} 
// public static void main(String args[]) {
//hilo elHilo = new hilo();
//elHilo.start();
//}
}