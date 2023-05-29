
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects2Data;

public class CboThreadGT2 extends Thread{

 private boolean continuar = true;
   String sql=null;
   JComboBox cbo=null;
   Connection con = null;
   PreparedStatement st;
   ResultSet rs;

  public CboThreadGT2(String sql,JComboBox cbo,Connection con){
       this.sql=sql;
       this.cbo=cbo;
       this.con=con;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
        System.out.print("\n -- THREAD -- "+sql+"\n");
        cbo.removeAllItems();
        cbo.addItem("Elegir");
      while (continuar){
          try {
              st=con.prepareStatement(sql);
              rs=st.executeQuery();//--------------------cod----------concat----------seg--------
              while(rs.next()){//---------------------------cod-------------des----------cant-------
                  cbo.addItem(new LoadWithObjects2Data(rs.getString(1),rs.getString(2),rs.getString(3)));
              }
              stop_();
          } catch (SQLException e) {
              javax.swing.JOptionPane.showMessageDialog( null, e.getMessage(), "Error al cargar lista desplegable (Thread)",javax.swing.JOptionPane.ERROR_MESSAGE );
              stop_();
              System.exit(1);
  } 
 } 
} 
}