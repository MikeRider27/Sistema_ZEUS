
package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import config.LoadWithObjects2Data;

public class CboThreadGT2_GetConcat extends Thread{

 private boolean continuar = true;
   String sql=null;
   JComboBox cbo=null;
   Connection con = null;
    PreparedStatement st;
   ResultSet rs;
   //conectarDB DB=;

  public CboThreadGT2_GetConcat(String sql,JComboBox cbo,Connection con){
       this.sql=sql;
       this.cbo=cbo;
       this.con=con;
   }
   
   public void stop_(){
      continuar=false;
   }
   
   public String getCod(String dato){
       String res="";boolean guion=false;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    char c = vector_de_char[i];
    if(Character.isDigit(c) && !guion){
        res=res+c;
    }else{
        if(c=='-')guion=true;
    }
}
System.out.print("\n -  FINDCOD  - ("+dato+").getCodOnlyPrevius="+res+" (Thread)");
return res;
}
   
   public String getDes(String dato){
    String res="";boolean guion=false;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    char c = vector_de_char[i];
    if(c=='-'){
        guion=true;
    }else{
        if(guion){
                 if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isDigit(c) || c=='.'){
        res=res+c;
    }  
        }else{
               if(Character.isLetter(c) || Character.isWhitespace(c) || c=='.'){
        res=res+c;
    }    
        } 
    }
}
System.out.print("\n -  FINDDES  - ("+dato+").getDes="+res.trim()+" (Thread)");
return res.trim();
}

 @Override
   public void run(){
        System.out.print("\n -- THREAD -- "+sql);
      while (continuar){
          try {
              st=con.prepareStatement(sql);
              rs=st.executeQuery();//--------------------cod----------concat----------seg--------
              while(rs.next()){//---------------------------cod-------------des----------cant-------
                  cbo.addItem(new LoadWithObjects2Data(getCod(rs.getString(1)),getDes(rs.getString(1)),rs.getString(2)));
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