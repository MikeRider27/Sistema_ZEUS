
package config;

import threads.JoptionPaneContimeOut;
import com.toedter.calendar.JDateChooser;
import frames.Menu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.Logger;

public final class conectarDB {

   public Connection con = null;
   public ResultSet rs;
   public Statement st;
   public PreparedStatement pst;
   public CallableStatement cst=null;
   long start=System.currentTimeMillis();
  // private final static Logger log = Logger.getLogger(conectarDB.class);
  // int a;

 public conectarDB(){
   // PropertyConfigurator.configure(this.getClass().getResource("/procesos/log4j.properties"));
  //  a=a+a;
  //  System.out.print("\nCycles per time "+a+1);
             final Properties p= new jProp().getProperties();
String user,pass;
//             if(Menu.DBuser!=null){//existe un usuario y se procede a iniciar con el
//                 user=new Crypto().d(Menu.DBuser).toLowerCase();
//                 pass=new Crypto().d(Menu.DBpass).toLowerCase();
//             }else{//no existe usuario, inicia con el default
//                 user=new Crypto().d(p.getProperty("dflt_usr")).toLowerCase();
//                 pass=new Crypto().d(p.getProperty("dflt_pwd")).toLowerCase();
//                // System.out.print(""+new Crypto().e("jtaller")+" "+new Crypto().e("123"));
//             }             
          user="postgres";pass="root";//poor login 
     try{
      Class.forName(p.getProperty("Driver"));
      con = DriverManager.getConnection("jdbc:"+p.getProperty("DBa")+"://"+p.getProperty("ip_host")+":"+p.getProperty("port")+"/"+p.getProperty("DB"),user,pass);//poor boot
      System.out.print("\nCONEXIÓN A LA BASE DE DATOS EN "+(System.currentTimeMillis()-start)+" ms");
            } 
   catch (ClassNotFoundException | SQLException e){
//log.fatal("Fallo en la Conexión con la Base de Datos\nContacte con el Administrador");
JOptionPane.showMessageDialog(null,"Fallo en la Conexión con la Base de Datos\nContacte con el Administrador\n"+e.getMessage(),
                                          "Error Fatal del Sistema",JOptionPane.ERROR_MESSAGE);
//Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
System.exit(1);
    }
}

/*public conectarDB(){
                 final Properties p= new jProp().getProperties();
String user,pass;
             if(Menu.DBuser!=null){
                 user=new Crypto().d(Menu.DBuser).toLowerCase();
                 pass=new Crypto().d(Menu.DBpass).toLowerCase();
             }else{
                 user=new Crypto().d(p.getProperty("dflt_usr")).toLowerCase();
                 pass=new Crypto().d(p.getProperty("dflt_pwd")).toLowerCase();
             }   
            try {
ComboPooledDataSource cpds = new ComboPooledDataSource();
cpds.setDriverClass( "com.mysql.jdbc.Driver");//org.postgresql.Driver" ); //loads the jdbc driver            
cpds.setJdbcUrl( "jdbc:mysql://localhost/taller");
cpds.setUser("root");                                  
cpds.setPassword("wasd");
cpds.setMinPoolSize(3);                                     
cpds.setAcquireIncrement(5);
cpds.setMaxPoolSize(20);

con=cpds.getConnection(); 
 
//System.out.print("\nCONEXIÓN A LA BASE DE DATOS CON EL USER= "+user+" Y PASS= "+pass  );
 } catch (PropertyVetoException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
}*/

public final String getDBcod(String field,String view){
    //System.out.print("\n -- CONSULT -- "+"SELECT IFNULL(MAX("+field+"),0)+1 FROM "+view);
    System.out.print("\n -- CONSULT -- "+"SELECT coalesce(MAX("+field+"),0)+1 FROM "+view);
  try {
      st=con.createStatement();
      rs=st.executeQuery("SELECT coalesce(MAX("+field+"),0)+1 FROM "+view);
            if (rs.next()) {
                return rs.getString(1);
            }else{
                JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",JOptionPane.WARNING_MESSAGE);
                return null;
            }  
        } catch (SQLException ex){
               JOptionPane.showMessageDialog( Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage(),"Error al consultar código", JOptionPane.ERROR_MESSAGE );
               shutdown();
               System.exit(1);
               return null;
        } finally{
      close_objects(1);
  }   
}

 public final void consultPst(String sql,int[] tipos_de_datos,String[] parameters,Object[] campos){
System.out.print("\n -- CONSULT -- "+sql);
int n=1;
  try {
            pst = con.prepareStatement(sql);
                        while(n<tipos_de_datos.length+1){
  System.out.print("\n    -  #PAR Nº="+n+" TIPO Nº="+tipos_de_datos[n-1]+" DATA="+parameters[n-1]);
                     switch(tipos_de_datos[n-1]){
                        case 1://int
                            pst.setInt(n,Integer.parseInt(parameters[n-1]));
                            break;
                        case 2://string
                            pst.setString(n,parameters[n-1]);
                            break;
                        case 3://date
                            pst.setDate(n,(java.sql.Date)String_to_Date(parameters[n-1]));
                            break;
                        case 4://boolean
                            pst.setBoolean(n,Boolean.parseBoolean(parameters[n-1]));
                            break;
                        case 5://double
                            pst.setDouble(n,Double.parseDouble(parameters[n-1]));
                            break;
                    }
                n++;
            }
            rs = pst.executeQuery();
            if (rs.next()){
                
 for (int i = 0; i < campos.length; i++){//----:VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
           if (campos[i] instanceof JTextField ){ //pregunta si dicho Object es la instancia de un txt
     ((JTextField)campos[i]).setText(rs.getString(i+1));
           }else{
               if(campos[i] instanceof JDateChooser){//pregunta si es jdatechooser
     ((JDateChooser)campos[i]).setDate(rs.getDate(i+1));       
           }else{//si no es entonces es combo, ya que yo no uso otro objeto mas
     ((JComboBox)campos[i]).setSelectedItem(new LoadWithObjects(getCod(rs.getString(i+1)),
getDes(rs.getString(i+1))));     
              }
           }
         }//--------------------------fin de :VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
            }else{
               // JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",JOptionPane.WARNING_MESSAGE);
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            }
        } catch (SQLException ex) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage()+
                       "\nEn el Comando:\n"+sql,"Error al Consultar (Pst)", JOptionPane.ERROR_MESSAGE );
               
               shutdown();
               System.exit(1);
        }  finally{
      close_objects(2);
                 }  
}
 
 public final String consultPst(String sql){
System.out.print("\n -- CONSULT -- "+sql);
int n=1;
  try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString(1);
            }else{ 
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
                //JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (SQLException ex) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage()+
                       "\nEn el Comando:\n"+sql,"Error al Consultar (Pst_String)", JOptionPane.ERROR_MESSAGE );
               shutdown();
               
               System.exit(1);
               return null;
        }  finally{
      close_objects(2);
                 }  
}

public final int executeCst(String sql,int[] arrayOfTypes,Object[] arrayOfParameters,boolean commit){ 
    int n=1,i=0;
               try {//error no value specified to parameter x ----> falta parametro o el tipo de dato del paramentro
        con.setAutoCommit(commit);
        cst = con.prepareCall("{ ? = CALL "+sql+" }");
        cst.registerOutParameter(1, Types.INTEGER);//se configura el output segun tipo en mi caso devuelvo 0 o 1
  System.out.print("\n -- EXECUTE IN COMMIT "+commit+" -- "+sql);            
            while(n<arrayOfTypes.length+1){            
  System.out.print("\n    -  #PAR Nº="+n+" TIPO Nº="+arrayOfTypes[n-1]+" DATA="+arrayOfParameters[n-1]);
                     switch(arrayOfTypes[n-1]){
                        case 1://int
                            cst.setInt(n+1,Integer.parseInt(arrayOfParameters[n-1].toString()));
                            break;
                        case 2://string
                            cst.setString(n+1,(String)arrayOfParameters[n-1]);
                            break;
                        case 3://date
                            cst.setDate(n+1,(java.sql.Date)utilDate_to_sqlDate((Date)arrayOfParameters[n-1]));
                            break;
                        case 4://boolean
                            cst.setBoolean(n+1,(Boolean)arrayOfParameters[n-1]);
                            break;
                        case 5://double
                            cst.setDouble(n+1,Double.parseDouble(arrayOfParameters[n-1].toString()));
                            break;
                        case 6://time -- see this other day ._.
                          //  cst.setTime(n+1,(java.sql.Time)String_to_Time(arrayOfParameters[n-1]));
                            break;
                        case -1://null
                            cst.setNull(n+1, java.sql.Types.NULL);
                            break;
                    }
                n++;
            }
     cst.execute();//executeQuery
            if(!cst.getWarnings().getMessage().equals("NOPE")){//si es NOPE el msg q recibe de la bd, esta todo ok de lo contrario cae aca
              new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(),cst.getWarnings().getMessage(),"Alerta en la interacción con la BD (Cst)",2000,3);
          }
            i=cst.getInt(1);
          System.out.print("\n -- EXECUTE CLOSE --"); 
          
   return i;
         } catch (SQLException e) {
             Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
             JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage()+
                     //"\nEn el Comando: "+sql+
//                     "\nPróxima Excepción: "+e.getNextException().toString()+
                     "\nCódigo de Error: "+e.getErrorCode(),"Error en la interacción con la BD (Cst)",JOptionPane.ERROR_MESSAGE);
             
//             rollback();
             i=1;
   return i;
         }finally{
                //   if(i==0)EmitirMensaje(Menu.jDesktopPane1.getSelectedFrame(), Integer.parseInt(arrayOfParameters[0].toString()));
                   //emitir el mensaje desde aca arruinaria la estetica de los movimientos
                   close_objects(3);
               }
  }

public final int executeCstDetails(String sql,int[] tipo_de_cst,String[][]parametros){
        int[]returns = new int[parametros.length];
        int n=0,//index de determinado index (columns)
        i=0,//index de los parametros (rows)
        total_de_detalles=parametros.length;//no hace falta explicar
//detecte un posible falla, con la actualizacion de detalles, es muy factible que extraiga TODOS los datos
        //del jtable correspondiente por mas que, por ejemplo, solo 1 sea el que se edito
                //de esta manera ejecutaria sin ningun resultado todos los otros registros ya que no poseen
                        //bandera en el array det_ban[], teniendo exito, obviamente el unico que posee bandera
        try {
        con.setAutoCommit(false);
        cst = con.prepareCall("{ call "+sql+" }");
        //cst.registerOutParameter(1, Types.INTEGER);
System.out.print("\n -- EXECUTE BATCH IN COMMIT false -- "+sql+" FOR "+total_de_detalles+" DETAIL/S");   
        while(i!=total_de_detalles){
           while(n<tipo_de_cst.length){
              // if(parametros[i][0].equals("0")){//---------------------------
                 //  n=tipo_de_cst.length-1;
//System.out.print("\n    -  #PAR Nº="+n+" DEL INDEX "+i+" TIPO Nº="+tipo_de_cst[n]+" DATA="+parametros[i][n]);
//System.out.print("\n    -  #PAR IS AN INVALID FLAG, JUMPING TO THE NEXT ROW");
         //      }//--------------------------------------------------------------solucion pobre al problema arriba mencionado
                //de esta manera, salta toda la row con bandera==0
System.out.print("\n    -  #PAR Nº="+n+" DEL INDEX "+i+" TIPO Nº="+tipo_de_cst[n]+" DATA="+parametros[i][n]);
                              switch(tipo_de_cst[n]){
                        case 1://int
                            cst.setInt(n+1,Integer.parseInt(parametros[i][n]));
                            break;
                        case 2://string
                            cst.setString(n+1,parametros[i][n]);
                            break;
                        case 3://date
                            cst.setDate(n+1,(java.sql.Date)String_to_Date(parametros[i][n]));
                            break;
                        case 4://boolean
                            cst.setBoolean(n+1,Boolean.parseBoolean(parametros[i][n]));
                            break;
                        case 5://double
                            cst.setDouble(n+1,Double.parseDouble(parametros[i][n]));
                            break;
                        case 6://time
                            cst.setTime(n+1,(java.sql.Time)String_to_Time(parametros[i][n]));
                            break;
                        case -1://null
                            cst.setNull(n+1, java.sql.Types.NULL);
                            break;                               
                    }
              n++;
              
              
        }//-------------------------------while de columns----------------------------------------------------- 
           cst.addBatch();
           System.out.print("\n -- EXECUTE BATCH CLOSE FOR DETAIL "+i);  
           n=0;
           i++;
    }//-------------------------------while de rows------------------------------------------------------------
//        returns[0]=99;
        returns=cst.executeBatch();
        for (int x = 0; x < returns.length; x++) {//--------lee los int de returns
            if(returns[x]==CallableStatement.EXECUTE_FAILED){//-----encontro un error
                System.out.print("\n -- res "+returns[x]);
//JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"Falló la ejecución de uno o mas detalles","Error en la interacción con la BD (Cst_Details)",JOptionPane.ERROR_MESSAGE);
//                       for (int m = 0; m < parametros[x].length; m++) {
//                             System.out.print("\n ---- EXECUTION ERROR IN DETAIL Nº"+x+" PARAMETERS "+m+" DATA "+parametros[x][m]);
//                          }
                       return 1;
            }//---------------------------------------------------------------------
              // System.out.print("\n -- RESULT CODE FOR DETAIL Nº"+x+"= "+returns[x]);               
         }
        System.out.print("\n -- EXECUTE BATCH CLOSE");
        return 0;
        } catch (SQLException e) {
            if(e.getNextException()==null){//un error puto
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
                JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage()//+"\nEn el Comando: "+sql        
       // + "\nPróxima Excepción: "+e.getNextException().toString()
        + "\nCódigo de Error: "+e.getErrorCode(),
        "Error en la interacción con la BD (Cst_det)",JOptionPane.ERROR_MESSAGE);
//                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            }else{
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage()//+"\nEn el Comando: "+sql        
        + "\nPróxima Excepción: "+e.getNextException().toString()
        + "\nCódigo de Error: "+e.getErrorCode(),
        "Error en la interacción con la BD (Cst_det)",JOptionPane.ERROR_MESSAGE); 
//               Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            }
            rollback();//por las dudas
            return 1;
        }finally{
            close_objects(3);
        }
    }

public final void setDataFromJtable(JTable madre,JTable hija){
    DefaultTableModel modeloHija=(DefaultTableModel)hija.getModel();
    DefaultTableModel modeloMadre=(DefaultTableModel)madre.getModel();
    int col=modeloMadre.getColumnCount(),rows=modeloMadre.getRowCount();
    limpiarTabla(hija);
    Object fila [] = new Object [col];        
for (int i=0;i<rows; i++) {
    for (int j=0; j<col; j++){
          fila[j] = modeloMadre.getValueAt(i,j);
    }
    modeloHija.addRow(fila);
}                        
}

public final void limpiarTabla(JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount() != 0)modelo.removeRow(0);
}
       
public final void shutdown(){
        try {
con.close();
System.out.print("\nCIERRA CONEXIÓN CON LA BASE DE DATOS");
        } catch (SQLException ex) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),ex.getMessage(),"Error al Cerrar Conexión", JOptionPane.ERROR_MESSAGE );
//            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
        }
   }

public final void FillTable(String sql,JTable tabla,int[] tipo_de_cst,String[] parametros){
    DefaultTableModel modelo= (DefaultTableModel)tabla.getModel();
    String datos[] = new String[tabla.getColumnCount()];
System.out.print("\n -- FILLTBL -- "+sql);
int n=1;
  try {
            pst = con.prepareStatement(sql);
            if(tipo_de_cst!=null && parametros!=null){
                                     while(n<tipo_de_cst.length+1){
  System.out.print("\n --  Parameter Nº "+n+" has type Nº "+tipo_de_cst[n-1]+" with the data= "+parametros[n-1]);
                     switch(tipo_de_cst[n-1]){
                        case 1://int
                            pst.setInt(n,Integer.parseInt(parametros[n-1]));
                            break;
                        case 2://string
                            pst.setString(n,parametros[n-1]);
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
            }else{
        System.out.print("\n --  Parameters are null ");        
            }
            rs = pst.executeQuery();
            if(rs.isBeforeFirst()){
                            while(rs.next()) {
                for (int i = 0; i < datos.length; i++) {
                 datos[i] = rs.getString(i+1);                        
                  }
              modelo.addRow(datos);
            }
            }else{
            // new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",1500,2);    
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            }
        } catch (SQLException ex) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage()+
                       "\nEn el Comando:\n"+sql,
                       "Error al cargar la tabla", JOptionPane.ERROR_MESSAGE );
//               Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               shutdown();
               System.exit(1);
        } finally{
      close_objects(2);
  }
        } 

public final void FillTable_wSelectionCheckbox(String sql,JTable tabla,int[] tipo_de_cst,String[] parametros){
    DefaultTableModel modelo= (DefaultTableModel)tabla.getModel();
    Object datos[] = new Object[5];//this method work ONLY for eOrdTrab and ped
System.out.print("\n -- FILLTBL SPECIAL -- "+sql);
int n=1;
  try {
            pst = con.prepareStatement(sql);
            if(tipo_de_cst!=null && parametros!=null){
                                     while(n<tipo_de_cst.length+1){
  System.out.print("\n --  Parameter Nº "+n+" has type Nº "+tipo_de_cst[n-1]+" with the data= "+parametros[n-1]);
                     switch(tipo_de_cst[n-1]){
                        case 1://int
                            pst.setInt(n,Integer.parseInt(parametros[n-1]));
                            break;
                        case 2://string
                            pst.setString(n,parametros[n-1]);
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
            }else{
        System.out.print("\n --  Parameters are null ");        
            }
            rs = pst.executeQuery();
            if(rs.isBeforeFirst()){
                         while(rs.next()) {
                             datos[0]=rs.getString(1);
                             datos[1]=rs.getString(2);
                             datos[2]=rs.getString(3);
                             datos[3]=rs.getString(4);
                             datos[4]=Boolean.FALSE;//default false
              modelo.addRow(datos);
            }   
            }else{
               // new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",1500,2); 
                Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            }
            System.out.print("\n -- FILLTBL SPECIAL CLOSE -- "+sql);
        } catch (SQLException ex) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage(),"Error al cargar la tabla", JOptionPane.ERROR_MESSAGE );
               shutdown();
//               Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
               System.exit(1);
        } finally{
      close_objects(2);
  }
        }  
        
public final void toUpperCaseState(javax.swing.JTextField txt,java.awt.event.KeyEvent evt){
if(evt.getKeyCode()!=32 && evt.getKeyCode()!=8 && evt.getKeyCode()!=16 && evt.getKeyCode()!=127 && evt.getKeyCode()!=39 && evt.getKeyCode()!=37 && evt.getKeyCode()!=36 && evt.getKeyCode()!=35 && !txt.getText().trim().isEmpty()){
    txt.setText(txt.getText().toUpperCase());
}
}   

public final void fieldControl(int tipo_de_bloqueo,java.awt.event.KeyEvent evt){
    if(tipo_de_bloqueo==1){//permite solo letras alfabeticas
char c=evt.getKeyChar();
if(Character.isDigit(c) || (!Character.isISOControl(c) && !Character.isLetter(c) && !Character.isWhitespace(c) && c!='.' /*&& c!=','*/ && c!='%')) { 
   evt.consume(); 
   Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();    
          }
    }
    if(tipo_de_bloqueo==2){//permite solo numeros sin espacios, sin '-' y . y ,
char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c))) { 
   evt.consume(); 
   Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();   
          }
    }
    if(tipo_de_bloqueo==3){//permite alfanumericos y espacios con ciertos caracteres agregados (direcciones) o concats
        char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-' && c!='.' && c!=',' && c!='/' && c!='%') { 
   evt.consume(); 
   Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();   
          }
    }
    if(tipo_de_bloqueo==4){//permite numeros sin espacios y ademas con '-'
        char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c) && c!='-')) { 
   evt.consume(); 
   Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();    
          }
    }
        if(tipo_de_bloqueo==5){//permite alfanumericos sin espacios y ademas con '-' (chapa)
char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-') { 
   evt.consume(); 
   Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep(); 
          }
    }
         if(tipo_de_bloqueo==6){//permite solo numeros sin espacios, sin '-' con . o ,
char c=evt.getKeyChar();
//if(){
 //if(Character.isLetter(c) && !Character.isISOControl(c) && !Character.isDigit(c)) { 
 if(Character.isLetter(c) || !Character.isISOControl(c) && !Character.isDigit(c) && c!='.' && c!=',') { 
     evt.consume(); 
     Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();   
         // }   
}
    } 
           if(tipo_de_bloqueo==7){//permite solo numeros sin espacios, sin '-' y , pero con . (Decimales)
char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c) && c!='.')) { 
   evt.consume(); 
  Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
          }
    }
}

public final String getDataBeforeSpace(String dato){
       String res="";boolean space=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    c = vector_de_char[i];
        if(c!=' ' && !space){
            res=res+c;
           
        }else{
             space=true;
        }
}
System.out.print("\n -  FINDDATABEFORESPACE  - ("+dato+").getDataBeforeSpace="+res+"");
return res;
}

public final String getDataAfterSpace(String dato){
       String res="";boolean space=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    c = vector_de_char[i];
        if(c==' '){
            space=true;
        }
        if(space && c!=' '){
        res=res+c;
        }
}
System.out.print("\n -  FINDDATAAFTERSPACE  - ("+dato+").getDataAfterSpace="+res+"");
return res;
}

public final String getCod(String dato){
       String res="";boolean guion=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
     c= vector_de_char[i];
    if(Character.isDigit(c) && !guion){
        res=res+c;
    }else{
        if(c=='-')guion=true;
    }
}
System.out.print("\n -  FINDCOD  - ("+dato+").getCod="+res+"");
return res;
}

public final String getPoint(String dato){
       String res="";boolean guion=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
     c= vector_de_char[i];
    if(Character.isDigit(c) && !guion){
        res=res+c;
    }else{
        if(c=='.')guion=true;
    }
}
System.out.print("\n -  FINDPOINT  - ("+dato+").getPoint="+res+"");
return res;
}

public final String getDes(String dato){
    String res="";boolean guion=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
     c = vector_de_char[i];
    if(c=='-'){
        guion=true;
    }else{
        if(guion){
          //       if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isDigit(c) || c=='.'){
        res=res+c;
 //   }  
       // }else{
            //   if(Character.isLetter(c) || Character.isWhitespace(c) || c=='.'){
       // res=res+c;
    //}    
        } 
    }
}
System.out.print("\n -  FINDDES  - ("+dato+").getDes="+res.trim()+"");
return res.trim();
}

public final String ClientDate(int type) {
    if(type==1){//date
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    if(type==2){//datetime
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    } 
    if(type==3){//datetime
        return new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
    }
    return null;
}

public final Date String_to_Date(String sDate){
        try {
        return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(),"Error al parsear fecha", JOptionPane.ERROR_MESSAGE);
        return null;
    }     
}

public final Date utilDate_to_sqlDate(Date sDate){
    System.out.print("ss"+sDate);
    java.sql.Date sqlDate = new java.sql.Date(sDate.getTime());
    System.out.println("utilDate:" + sDate);
    System.out.println("sqlDate:" + sqlDate);
    return new java.sql.Date(sDate.getTime());
}

public final boolean is_A_valid_Date(Date sDate){
       //Compara si la fecha seleccionada es mayor o igual a la fecha actual
     if(sDate!=null){
         return sDate.getTime()<=new Date().getTime();
     }else{
         return false;
     }
    }

public final Date String_to_Time(String sTime){
        try {
        return new SimpleDateFormat("HH:mm:ss").parse(sTime);
    } catch (java.text.ParseException e) {
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(),"Error al parsear hora", JOptionPane.ERROR_MESSAGE);
        return null;
    }     
}

public final void consultPstSimple(String sql,JTextField txtCod,JTextField txt){
    try {
        pst=con.prepareStatement(sql);
        if(txtCod!=null)pst.setInt(1, Integer.parseInt(getCod(txtCod.getText().trim())));
        rs=pst.executeQuery();
        if(txtCod!=null){
           if(rs.next()){
            txtCod.setText(rs.getString(1));
            if(txt!=null)txt.setText(rs.getString(2));
        }else{
           // new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",1500,2);
               Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
        } 
        }else{//
          if(rs.next()){
           // txtCod.setText(rs.getString(1));
            txt.setText(rs.getString(1));
        }else{
           // new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(),"El comando ingresado no produjo resultados","Atención",1500,2);
              Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
        }  
        }
        
    } catch (SQLException e) {
        Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(),"Error al Consultar (PstS)", JOptionPane.ERROR_MESSAGE);        
        shutdown();
        System.exit(1);
    }finally{
        close_objects(2);
    } 
}

public final String[][] getComboArray(JComboBox cbo){
    String[][]sData=new String[cbo.getItemCount()-1][2];
            System.out.print("\n -- GET_COMBO ARRAY -- CBO# items "+(cbo.getItemCount()-1)); 
            LoadWithObjects sCbo;
                  for (int i = 0; i < cbo.getItemCount()-1; i++) {
                      sCbo=(LoadWithObjects)cbo.getItemAt(i+1);
                      sData[i][0]=sCbo.getCod();
                      sData[i][1]=sCbo.getDes();
                  }
                  return sData;
}

public final void setComboArray(JComboBox cbo,String[][] array){
            System.out.print("\n -- SET_COMBO ARRAY -- CBO# items "+(cbo.getItemCount()-1)); 
            cbo.removeAllItems();
            cbo.addItem("Elegir");
                  for (int i = 0; i < array.length; i++) {
                   cbo.addItem(new LoadWithObjects(array[i][0],array[i][1]));   
                  } 
}

public final int CargarCombo(String sql,JComboBox cbo){
    int n=0;
            System.out.print("\n -- NON-THREAD CARGAR_COMBO -- SQL# "+sql); 
          try {
              cbo.removeAllItems();
              cbo.addItem("Elegir");
              pst=con.prepareStatement(sql);
              rs=pst.executeQuery();
              while(rs.next()){
                  cbo.addItem(new LoadWithObjects(rs.getString(1),rs.getString(2)));
                  n++;
              }
              return n;
          } catch (SQLException e) {
              Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Cargar Lista (Non-Thread Cbo)", JOptionPane.ERROR_MESSAGE ); 
//     Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     shutdown();
     System.exit(1);
     return 0;
  }finally{
              close_objects(2);
          } 
}

public final void CargarCombo3Data(String sql,JComboBox cbo){
            System.out.print("\n -- NON-THREAD CARGAR_COMBO -- SQL# "+sql); 
          try {
              cbo.removeAllItems();
              cbo.addItem("Elegir");
              pst=con.prepareStatement(sql);
              rs=pst.executeQuery();
              while(rs.next())cbo.addItem(new LoadWithObjects3Data(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
          } catch (SQLException e) {
              Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Cargar Lista (Non-Thread Cbo)", JOptionPane.ERROR_MESSAGE ); 
//     Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     shutdown();
     System.exit(1);
  }finally{
              close_objects(2);
          } 
}

public final void CargarCombo2Data(String sql,JComboBox cbo){
            System.out.print("\n -- NON-THREAD CARGAR_COMBO -- SQL# "+sql); 
          try {
              cbo.removeAllItems();
              cbo.addItem("Elegir");
              pst=con.prepareStatement(sql);
              rs=pst.executeQuery();
              while(rs.next())cbo.addItem(new LoadWithObjects2Data(rs.getString(1),rs.getString(2),rs.getString(3)));
          } catch (SQLException e) {
              Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Cargar Lista (Non-Thread Cbo)", JOptionPane.ERROR_MESSAGE ); 
//     Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     shutdown();
     System.exit(1);
  }finally{
              close_objects(2);
          } 
}

public final int getInt(String str){
    if(str==null || str.equals("")){
        return 0;
    }else{
     try {
        return Integer.parseInt(str);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"No ingresó un valor entero válido\n"+e.getMessage(), "Error al parsear entero", JOptionPane.ERROR_MESSAGE ); 
        return 0;
    }   
    }
}

public final double getDouble(String str){
    if(str==null || str.equals("")){
        return 0;
    }else{
     try {
        return Double.parseDouble(str);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),"No ingresó un valor decimal válido\n"+e.getMessage(), "Error al parsear decimal", JOptionPane.ERROR_MESSAGE ); 
        return 0;
    }   
    }
}

public final void CargarComboSimple(String sql,JComboBox cbo){
            System.out.print("\n -- NON-THREAD CARGAR_COMBO_SIMPLE -- SQL# "+sql); 
          try {
              cbo.removeAllItems();
              cbo.addItem("Elegir");
              pst=con.prepareStatement(sql);
              rs=pst.executeQuery();
              while(rs.next())cbo.addItem(rs.getString(1));
          } catch (SQLException e) {
              Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(), "Error al Cargar Lista (Non-Thread Cbo)", JOptionPane.ERROR_MESSAGE ); 
//     Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
     shutdown();
     System.exit(1);
  }finally{
              close_objects(2);
          } 
}

public final void EmitirMensaje(JInternalFrame p,int bandera){//sacar jinternalframe del parametro
    String msj="";
    if(bandera==1){
        msj="Registro ingresado exitosamente";
         }
    if(bandera==2){ 
        msj="Registro editado exitosamente";
    }
    if(bandera==3){ 
        msj="Registro anulado exitosamente";
    } 
   new JoptionPaneContimeOut().visualizaDialogo(Menu.jDesktopPane1.getSelectedFrame(), msj,"Mensaje del Sistema",1350,2);
}

public final void btnAlign(final JButton[] btn,final JTabbedPane p,final JComboBox cbo,final int estado){
    //btn[0]---->btnAdd
    //btn[1]---->btnEdit--->puede ser null
    //btn[2]---->btnNull
    //btn[3]---->btnSave
    //btn[4]---->btnCancelar
    //btn[5]---->btnSee---->pueder ser null
  
     if(estado==1){//boot,estado inicial, al abrir el frame
         btn[0].setEnabled(true);
         btn[0].requestFocusInWindow();
         if(btn[1]!=null)btn[1].setEnabled(false);
         btn[2].setEnabled(false);
         btn[3].setEnabled(false);
         btn[4].setEnabled(false);
         if(btn[5]!=null)btn[5].setEnabled(false);
         p.setSelectedIndex(1);
     }
          if(estado==2){//pressed, estado al presionar en el jtable
         btn[0].setEnabled(false);
         if(cbo.getSelectedIndex()==0){
             btn[1].setEnabled(true);
             btn[2].setEnabled(true);
         }else{
             btn[1].setEnabled(false);
             btn[2].setEnabled(false);
         }
         btn[3].setEnabled(false);
         btn[4].setEnabled(true);
           if(btn[5]!=null)btn[5].setEnabled(true);
     }
               if(estado==3){//editing, estado al estar en la pestaña edicion
         btn[0].setEnabled(false);
         if(btn[1]!=null)btn[1].setEnabled(false);
         btn[2].setEnabled(false);
         btn[3].setEnabled(true);
         btn[4].setEnabled(true);
           if(btn[5]!=null)btn[5].setEnabled(false);
         p.setSelectedIndex(0);
     }
                    if(estado==4){//ESPECIAL - Ver Detalles de un registro, solo ciertos frames lo tienen
         btn[0].setEnabled(false);
         btn[1].setEnabled(false);
         btn[2].setEnabled(false);
         btn[5].setEnabled(false);
         btn[3].setEnabled(false);
         btn[4].setEnabled(true);
         p.setSelectedIndex(0);
     }
 
}

public final ResultSet consultar(String sql)throws Exception{
    st= con.createStatement();
    rs= st.executeQuery(sql);
    return rs;   
}

public final void rollback(){
    try { 
if(!con.getAutoCommit()){
    con.rollback();
      System.out.print("\nROLLBACK");
    }    
        } catch (SQLException e) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), e.getMessage(),"Error al ejcutar un Rollback", JOptionPane.ERROR_MESSAGE ); 
//            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
        }
}

public final boolean commit(){
    try { 
if(!con.getAutoCommit()){
    con.commit();
      System.out.print("\nCOMMIT");
      return true;
    }    
        } catch (SQLException e) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), e.getMessage(),"Error al ejecutar un Commit", JOptionPane.ERROR_MESSAGE ); 
//            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
            return false;
        }
    return false;
}

public final void close_objects(int tipo){
    String mensaje=null;
        try { 
                switch(tipo){
                        case 1://st y rs
                            if(st!=null)st.close();
                            if(rs!=null)rs.close();
                            mensaje="CERRANDO STATEMENT Y RESULTSET";
                            break;
                        case 2://pst y rs
                            if(pst!=null)pst.close();
                            if(rs!=null)rs.close();
                            mensaje="CERRANDO PREPARED_STATEMENT Y RESULTSET";
                            break;
                        case 3://cst
                           if(cst!=null)cst.close();
                           mensaje="CERRANDO CALLABLE_STATEMENT";
                            break;
                    }
                System.out.print("\n"+mensaje+"\n");
        } catch (SQLException e) {
            Menu.jDesktopPane1.getSelectedFrame().getToolkit().beep();
             JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(),"Error al Cerrar Objetos",JOptionPane.ERROR_MESSAGE);
        }
}

public final void getYears(JComboBox cboYear ){
      cboYear.removeAllItems();
      cboYear.addItem("Este Año");
      cboYear.addItem("Año Pasado");
      int current_year=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
      if(current_year>=2016){
             while(current_year!=2017){
          cboYear.addItem(String.valueOf(current_year));
          current_year--;
      }   
      }
}

public final Date obtenerUltimoDiaMes () {
Calendar cal=Calendar.getInstance();
cal.setTime(new Date());
//cal.set(anio, mes-1, 1);
cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
return cal.getTime();
//return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
}

public final Date obtenerPrimerDiaMes () {
Calendar cal = Calendar.getInstance();
//cal.setTime(date);
cal.setTime(new Date());
//cal.set(anio, mes-1, 1);
// Set the day of the month to the first day of the month
cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
return cal.getTime();
// Extract the Date from the Calendar instance
//Date firstDayOfTheMonth = cal.getTime();
}
}
