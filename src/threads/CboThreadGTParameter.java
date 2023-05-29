
package threads;

/**
 *
 * @author schafler92
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects;

public class CboThreadGTParameter extends Thread{

 private boolean continuar = true;
   String sql=null;
   JComboBox cbo=null;
   Connection con = null;
   PreparedStatement pst;
   ResultSet rs;
   String[] parameters;
   int[] tipo_de_pst;

  public CboThreadGTParameter(String sql,JComboBox cbo,int[]tipo_de_pst,String[]parametros,Connection con){
       this.sql=sql;
       this.cbo=cbo;
       this.con=con;
       this.parameters=parametros;
       this.tipo_de_pst=tipo_de_pst;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
       int n=1;
        System.out.print("\n -- THREAD -- "+sql);
      while (continuar){
          try {
              pst=con.prepareStatement(sql);
                          while(n<parameters.length+1){
  System.out.print("\n  --  Parameter Nº "+n+" has type Nº "+tipo_de_pst[n-1]+" with the data= "+parameters[n-1]);
                     if(tipo_de_pst[n-1]==1){
                         pst.setInt(n,Integer.parseInt(parameters[n-1]));
                     }else{
                         pst.setString(n,parameters[n-1]);
                     }
                n++;
            }
              rs=pst.executeQuery();
              while(rs.next()){
                  cbo.addItem(new LoadWithObjects(rs.getString(1),rs.getString(2)));
              }
              System.out.print("\n -- THREAD END SUCCEFULLY -- \n");
              stop_();
          } catch (SQLException e) {
              javax.swing.JOptionPane.showMessageDialog( null, e.getMessage(), "Error al cargar lista desplegable (Thread)",javax.swing.JOptionPane.ERROR_MESSAGE );
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
