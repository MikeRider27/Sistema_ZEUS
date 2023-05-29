 
package threads;

import config.MasterControl;
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

public class TblThread extends Thread{

 private boolean continuar = true;
   String sql=null;
   JTable tabla=null;
   Connection con = null;
   PreparedStatement pst;
   ResultSet rs;
   String[] parametros;
   int[] tipo_de_pst,tipos_de_get;
   int n=0;
   String[] columnas;
   DefaultTableModel modelo = new DefaultTableModel();

   JInternalFrame p;

  public TblThread(String sql,JTable tabla,int[] tipo_de_cst,String[] parametros,Connection con,JInternalFrame p,int[]tipos_de_get){
       this.sql=sql;       
       modelo=(DefaultTableModel)tabla.getModel();
       this.tabla=tabla;
       this.tipo_de_pst=tipo_de_cst;
       this.tipos_de_get=tipos_de_get;
       this.parametros=parametros;
       this.con=con;
       this.p=p;
   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
       tabla.setAutoCreateRowSorter(true);//colcando los tipos de datos correctos en la tabla se habilita que 
                                                //se puede ordenar sin necesidad de una conexion a la BD
      while (continuar){
Object datos[] = new Object[tabla.getColumnCount()];
System.out.print("\n -- FILLTBL -- \n#SQL="+sql);
int n=1;
             try {
                  pst = con.prepareStatement(sql);
                  if(tipo_de_pst!=null && parametros!=null){
             while(n<tipo_de_pst.length+1){
   System.out.print("\n   -  #PAR Nº="+n+" TIPO Nº="+tipo_de_pst[n-1]+" DATA="+parametros[n-1]);
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
                        case 6://time
                            pst.setTime(n, null);
                            break;
                    }
                n++;
            }   
                  }else{//no parameters
  System.out.print("\n   -  #PAR ARE NULL");
                  }
            rs = pst.executeQuery();
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    for (int i = 0; i < datos.length; i++){
                      switch(tipos_de_get[i]){
                        case 1://int
                            datos[i] = rs.getInt(i+1); 
                            break;
                        case 2://string
                            datos[i] = rs.getObject(i+1); 
                            break;
                        case 3://date
                            datos[i] = rs.getDate(i+1);
                            break;
                        case 4://boolean
                            datos[i] = rs.getBoolean(i+1); 
                            break;
                        case 5://double
                            datos[i] = rs.getDouble(i+1); 
                            break;
                        case 6://time
                            datos[i] = rs.getTime(i+1); 
                            break;
                      }
                     n++;
                    }
              modelo.addRow(datos);
            }
            }else{
    //new JoptionPaneContimeOut().visualizaDialogo(p,"El comando ingresado no produjo resultados","Atención",1500,2); 
    p.getToolkit().beep();
            }
            System.out.print("\n -- FILLTBL CLOSE --\n " );
            stop_();
        } catch (SQLException ex) {
               p.getToolkit().beep();
               JOptionPane.showMessageDialog(p, ex.getMessage()+"\nEn el comando: "+sql,"Error al cargar la tabla", JOptionPane.ERROR_MESSAGE );
               stop_();
               new MasterControl().borrarTemp();
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
   
   public final Date String_to_Time(String sTime){
        try {
        return new SimpleDateFormat("HH:mm:ss").parse(sTime);
    } catch (java.text.ParseException e) {
        JOptionPane.showMessageDialog(p,e.getMessage(),"Error al Parsear Hora", JOptionPane.ERROR_MESSAGE);
        return null;
    }     
}
}