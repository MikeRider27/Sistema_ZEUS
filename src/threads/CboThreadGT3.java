
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects3Data;

public class CboThreadGT3 extends Thread{

 private boolean continuar = true;
   String sql=null;
   JComboBox cbo=null;
   Connection con = null;
    PreparedStatement st;
   ResultSet rs;
   String texto_item_at_0;
   
  public CboThreadGT3(String sql,JComboBox cbo,Connection con,String textoAt0){
       this.sql=sql;
       this.cbo=cbo;
       this.con=con;
       this.texto_item_at_0=textoAt0;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
        System.out.print("\n -- THREAD -- "+sql);
      while (continuar){
          cbo.removeAllItems();
          cbo.addItem("Elegir");
          try {
              st=con.prepareStatement(sql);
              rs=st.executeQuery();//--------------------cod----------concat----------seg--------
              while(rs.next()){//---------------------------cod-------------des----------cant-------
                  cbo.addItem(new LoadWithObjects3Data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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