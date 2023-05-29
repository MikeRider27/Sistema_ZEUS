 
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TblThread_wNoSqlCheclkbox extends Thread{

 private boolean continuar = true;
   String sql=null;
   JTable tabla=null;
   Connection con = null;
   PreparedStatement pst;
   ResultSet rs;
   String[] parametros;
   int[] tipo_de_pst;
   DefaultTableModel modelo;
   JInternalFrame p;
   int booleanColumn;
   

  public TblThread_wNoSqlCheclkbox(String sql,JTable tabla,int[] tipo_de_cst,String[] parametros,Connection con,JInternalFrame p,int booleanColumn){
       this.sql=sql;       
       modelo=(DefaultTableModel)tabla.getModel();
       this.tabla=tabla;
       this.tipo_de_pst=tipo_de_cst;
       this.parametros=parametros;
       this.con=con;
       this.p=p;
       this.booleanColumn=booleanColumn;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
      while (continuar){
          System.out.print("\njfjfodjfjd"+tabla.getColumnCount());
Object datos[] = new Object[tabla.getColumnCount()];
System.out.print("\n -- FILLTBL -- "+sql);
int n=1;
  try {
                  pst = con.prepareStatement(sql);
                  if(tipo_de_pst!=null && parametros!=null){
             while(n<tipo_de_pst.length+1){
   System.out.print("\n  --  Parameter Nº "+n+" has type Nº "+tipo_de_pst[n-1]+" with the data= "+parametros[n-1]);
                     switch(tipo_de_pst[n-1]){
                        case 1://int
                            pst.setInt(n,Integer.parseInt(parametros[n-1]));
                            break;
                        case 2://string
                            pst.setString(n, parametros[n-1]);
                            break;
                        case 3://date
                            pst.setDate(n,(java.sql.Date)String_to_Date(parametros[n-1]));
                            break;
                        case 4://boolean
                            pst.setBoolean(n,Boolean.parseBoolean(parametros[n-1]));
                            break;
                        case 5://double
                            pst.setDouble(n,Double.parseDouble(parametros[n-1]));
                            break;
                    }
                n++;
            }   
                  }else{//no parameters
  System.out.print("\n  --  Parameters are null ");
                  }
            rs = pst.executeQuery();
            if(rs.isBeforeFirst()){
             while(rs.next()){
                for (int i = 0; i < datos.length; i++){
                    System.out.print("\ndatos "+datos[i]+" "+i);
                    if(i==booleanColumn){//
                        datos[booleanColumn]=Boolean.FALSE;
                    }else{
                       datos[i]=rs.getObject(i+1); 
                    }
                  //   datos[i] = rs.getString(i-1);                        
                  }
              modelo.addRow(datos);
            }   
            }else{
                      new JoptionPaneContimeOut().visualizaDialogo(p,"La consulta no produjo resultados","Mensaje del Sistema",1500,2); 
            }
            System.out.print("\n -- FILLTBL CLOSE -- " );
            stop_();
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(p, ex.getMessage(),"Error al Cargar Tabla", JOptionPane.ERROR_MESSAGE );
               stop_();
               System.exit(1);
        } finally{
      try {
          if(pst!=null)pst.close();
          if(rs!=null)rs.close();
      } catch (SQLException e) {
      }
  }
 } 
} 

   public Date String_to_Date(String sDate){
    Date newDate;
        try {
        newDate=new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        return newDate;
    } catch (java.text.ParseException e) {
        JOptionPane.showMessageDialog(p,e.getMessage(),"Error al Parsear Fecha", JOptionPane.ERROR_MESSAGE);
        return null;
    }     
}
}