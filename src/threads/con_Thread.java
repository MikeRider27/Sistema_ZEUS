
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects;

public class con_Thread extends Thread{

   private boolean continuar = true;
   String sql=null;
   JComboBox cbo=null;
   Connection con = null;
   PreparedStatement st;
   ResultSet rs;
   String texto_item_at_0;

  public con_Thread(String sql,JComboBox cbo,Connection con,String textoAt0){
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
if(!texto_item_at_0.isEmpty())cbo.addItem(texto_item_at_0);//texto que va ser colocado en el primer lugar del cbo, si no es nulo, comunmente "Elegir"
          try {
              st=con.prepareStatement(sql);
              rs=st.executeQuery();
              while(rs.next())cbo.addItem(new LoadWithObjects(rs.getString(1),rs.getString(2)));
              stop_();
          } catch (SQLException e) {
              javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Error al cargar lista (Thread)",javax.swing.JOptionPane.ERROR_MESSAGE );
              stop_();
              System.exit(1);
} 
} 
} 
}