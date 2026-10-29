package config;

/*
    ** Autor Prof. Ing. Juan Cáceres
    ** Autor de las Actualizaciones Ing.** Helmut Schafler
*/

import frames.Menu;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;


public class PGa_v2 {
   
    public DefaultListModel modelo = new DefaultListModel();//modelo default para la lista
    public boolean show_jlist = false;//booleano para determinar que se vea la lista
    public ResultSet rs;//resultset
    public JTextField campo,//jtextfield al cual se coloca el jlist que esta dentro del popup
            campoSecundario,//campo en el cual se coloca un dato si se desea
            campoTerciario;//campo en el cual se coloca un dato si se desea
    public Component componente;//next focusable component
    public PreparedStatement pst;//prepared statement
    public Popup popup = null;//se agrega el jlist a este popup, y este a su vez al jtextfield
    public final JList lista = new JList(modelo);//jlist segun modelo default que luego cargamos
    public int jlist_lenght,//cuenta la cantidad de rows que trae el resultset
            matches,//cuenta el numero de coincidencias encontradas segun lo ingresado en el campo
    nro_de_datosSQL;//campos de la consulta en Pga_v2.load
    public boolean added,//controlar los listeners para agregarlos solo 1 vez
            loaded=false;//controla que se conecte a la BD solo 1 vez
            //THREE_data=false;//si es true indica que son 3 campos los del SQL en Pga_v2.load
    public ArrayList<Object> arrayList = new ArrayList<>();//obtiene el resultset (cod)
    public ArrayList<Object> arrayList2 = new ArrayList<>();//obtiene el resultset (des)
    public ArrayList<Object> arrayList3 = new ArrayList<>();//obtiene el resultset (algo mas)
    public ArrayList<Object> arrayList4 = new ArrayList<>();//obtiene el resultset (algo mas)
    public Object[] data;//recibe el resultset a travez del arrayList
    public Object[] data2;//recibe el resultset a travez del arrayList
    public Object[] data3;//recibe el resultset a travez del arrayList
    public Object[] data4;//recibe el resultset a travez del arrayList
    public JInternalFrame frame;//para determinar el frame y asi verificar cuando pierde el foco y cerrar el popup
    
    public void autocomplete(final JTextField campo,final JTextField campoSecundario,JTextField campoTerciario,final Component componente,final JInternalFrame frame){
        //System.out.print("\n   -- LOOKING FOR "+campo.getText().trim().toUpperCase()+"\n");
        matches=0;
        modelo.removeAllElements();
        lista.setOpaque(true);
        this.campo=campo;
        this.campoSecundario=campoSecundario;
        this.campoTerciario=campoTerciario;
        this.componente=componente;
        this.frame=frame;
//        for (int i = 0; i < arrayList.size(); i++) {
//    System.out.print("\n ANTES arraylist data "+arrayList.get(i));
//             }
        data=new Object[jlist_lenght];//cod
        data2=new Object[jlist_lenght];//des
        data3=new Object[jlist_lenght];//algo mas
        data4=new Object[jlist_lenght];//algo mas
        data=arrayList.toArray();//cod 
        data2=arrayList2.toArray();//des
        data3=arrayList3.toArray();//algo mas
        data4=arrayList4.toArray();//algo mas
        show_jlist=true;      
//        for (int i = 0; i < data.length; i++) {
//    System.out.print("\n data data "+data[i]);
        
        switch(nro_de_datosSQL){
            case 2:
                                        for (int i = 0; i < jlist_lenght; i++) {
                             if(data2[i].toString().contains(campo.getText().trim().toUpperCase())){
                    // modelo.addElement(data[i]);
                                modelo.addElement(new LoadWithObjects(data[i].toString(),data2[i].toString()));//:O
                                matches++;
                 }     
             }
                break;
            case 3:
                                        for (int i = 0; i < jlist_lenght; i++) {
                             if(data2[i].toString().contains(campo.getText().trim().toUpperCase())){
                    // modelo.addElement(data[i]);
                                modelo.addElement(new LoadWithObjects2Data(data[i].toString(),data2[i].toString(),data3[i].toString()));//:O
                                matches++;
                 }     
             }
                break;
            case 4:
                                        for (int i = 0; i < jlist_lenght; i++) {
                             if(data2[i].toString().contains(campo.getText().trim().toUpperCase())){
                    // modelo.addElement(data[i]);
                                modelo.addElement(new LoadWithObjects3Data(data[i].toString(),data2[i].toString(),data3[i].toString(),data4[i].toString()));//:O
                                matches++;
                 }     
             }
                break;
        }
//        if(THREE_data){
//                        for (int i = 0; i < jlist_lenght; i++) {
//                             if(data2[i].toString().contains(campo.getText().trim().toUpperCase())){
//                    // modelo.addElement(data[i]);
//                                modelo.addElement(new LoadWithObjects2Data(data[i].toString(),data2[i].toString(),data3[i].toString()));//:O
//                                matches++;
//                 }     
//             } 
//        }else{
//                        for (int i = 0; i < jlist_lenght; i++) {
//                             if(data2[i].toString().contains(campo.getText().trim().toUpperCase())){
//                    // modelo.addElement(data[i]);
//                                modelo.addElement(new LoadWithObjects(data[i].toString(),data2[i].toString()));//:O
//                                matches++;
//                 }     
//             } 
//        }
            
//             }
 
    System.out.print("\n   -- LOOKING FOR "+campo.getText().trim().toUpperCase()+" FINDED "+matches+" MATCHES\n");
            AddListeners();//agrega los listeners
            
//------------------------Agrega un borde a la lsita------------------------------------------
        //lista.setBorder(BorderFactory.createEtchedBorder());
        //lista.setBorder(BorderFactory.createBevelBorder(1));
        //lista.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        lista.setBorder(BorderFactory.createRaisedBevelBorder());
        //lista.setBorder(BorderFactory.createLoweredSoftBevelBorder());
 //------------------------------------------------------------------------------------------       
PopupFactory factory = PopupFactory.getSharedInstance();
popup = factory.getPopup(campo, lista, campo.getLocationOnScreen().x, campo.getLocationOnScreen().y+campo.getHeight());  
refresh_();
 } 
    
    public void load(final String[] fields,String tabla, String condicion,Connection con){
        String sql = null;
            if(condicion==null){//:V
                condicion="";
            }else{
                condicion="WHERE "+condicion;
            }
//        if(fields.length>2){//3 campos (cod, des y algo mas)
//            THREE_data=true;
//            sql="SELECT "+fields[0]+","+fields[1]+","+fields[2]+" \n"
//                    + "      FROM "+tabla+" "+condicion+" ORDER BY 1 ASC";
//        }else{//solo 2 campos (cod y des)
//            sql="SELECT "+fields[0]+","+fields[1]+" \n"
//                    + "      FROM "+tabla+" "+condicion+" ORDER BY 1 ASC";
//        }
       nro_de_datosSQL=fields.length;
        switch(fields.length){
            case 2:
                sql="SELECT "+fields[0]+","+fields[1]+" \n"//-- (cod y des)
                    + "      FROM "+tabla+" "+condicion+" ORDER BY 1 ASC";
                break;
            case 3:
                sql="SELECT "+fields[0]+","+fields[1]+","+fields[2]+" \n"//-- (cod y des)+algo mas
                    + "      FROM "+tabla+" "+condicion+" ORDER BY 1 ASC";
                break;
            case 4:
                sql="SELECT "+fields[0]+","+fields[1]+","+fields[2]+","+fields[3]+" \n"//-- (cod y des)+ 2x algo mas
                    + "      FROM "+tabla+" "+condicion+" ORDER BY 1 ASC";
                break;
        }
        if(!loaded){

          try {//-------------------------------cod---------des o concat----algo mas---algo mas**
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            System.out.print("\n   -- PGa_v2 EXECUTED IN\n"
                    + "     - SQL# "+sql);
            jlist_lenght=0;
            if(rs.isBeforeFirst()){
       switch(nro_de_datosSQL){
           case 2: 
                       while (rs.next()){
               arrayList.add(rs.getObject(1));
               arrayList2.add(rs.getObject(2));
                 jlist_lenght++;
            }
               break;
           case 3:
                       while (rs.next()){
               arrayList.add(rs.getObject(1));
               arrayList2.add(rs.getObject(2));
               arrayList3.add(rs.getObject(3));
                 jlist_lenght++;
            } 
               break;
           case 4:
                       while (rs.next()){
               arrayList.add(rs.getObject(1));
               arrayList2.add(rs.getObject(2));
               arrayList3.add(rs.getObject(3));
               arrayList4.add(rs.getObject(4));
                 jlist_lenght++;
            } 
               break;
       }         
               loaded=true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), ex.getMessage(), "Error al Cargar Lista (PGa)",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }finally{
            try {
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();   
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(), e.getMessage(), "Error al Cerrar Objetos", JOptionPane.ERROR_MESSAGE);
            }
        }  
        }
    }
    
    public void refresh_(){
        if(popup!=null){
         if (show_jlist) {
            popup.show();
        }else{
            popup.hide();
   //         lista.setOpaque(true);
        }    
        }
}
    
public  void seleccion(){
    if(lista.getSelectedIndex() != -1){
        switch(nro_de_datosSQL){
            case 2:
            LoadWithObjects objLista1=(LoadWithObjects)lista.getSelectedValue(); 
                    //campo.setText(lista.getSelectedValue().toString());
                       // campo.setText(new LoadWithObjects2Data(objLista.getCod(),objLista.getDes(),objLista.getCant()));    :(
            campo.setText(objLista1.getCod()+" - "+objLista1.getDes());//escribe en el txt madre del PGAv2
            //if(THREE_data && campoSecundario!=null)campoSecundario.setText(objLista.getCant());//
                show_jlist= false;//hide popup
                modelo.removeAllElements();
                break;
            case 3:
                                    LoadWithObjects2Data objLista=(LoadWithObjects2Data)lista.getSelectedValue(); 
                    //campo.setText(lista.getSelectedValue().toString());
                       // campo.setText(new LoadWithObjects2Data(objLista.getCod(),objLista.getDes(),objLista.getCant()));    :(
            campo.setText(objLista.getCod()+" - "+objLista.getDes());//escribe en el txt madre del PGAv2
            if( campoSecundario!=null)campoSecundario.setText(objLista.getCant());//
                show_jlist= false;//hide popup
                modelo.removeAllElements();
                break;
            case 4:
                                    LoadWithObjects3Data objLista3=(LoadWithObjects3Data)lista.getSelectedValue(); 
                    //campo.setText(lista.getSelectedValue().toString());
                       // campo.setText(new LoadWithObjects2Data(objLista.getCod(),objLista.getDes(),objLista.getCant()));    :(
            campo.setText(objLista3.getCod()+" - "+objLista3.getDes());//escribe en el txt madre del PGAv2
            if( campoSecundario!=null)campoSecundario.setText(objLista3.getCant());//
            if( campoTerciario!=null)campoTerciario.setText(objLista3.getAlgomas());//
                show_jlist= false;//hide popup
                modelo.removeAllElements();
                break;
        }
//        if(THREE_data){//3 campos (cod, des y algo mas)
//                    LoadWithObjects2Data objLista=(LoadWithObjects2Data)lista.getSelectedValue(); 
//                    //campo.setText(lista.getSelectedValue().toString());
//                       // campo.setText(new LoadWithObjects2Data(objLista.getCod(),objLista.getDes(),objLista.getCant()));    :(
//            campo.setText(objLista.getCod()+" - "+objLista.getDes());//escribe en el txt madre del PGAv2
//            if(THREE_data && campoSecundario!=null)campoSecundario.setText(objLista.getCant());//
//                show_jlist= false;//hide popup
//                modelo.removeAllElements();
//        }else{//solo 2 campos (cod y des)
//                    LoadWithObjects objLista=(LoadWithObjects)lista.getSelectedValue(); 
//                    //campo.setText(lista.getSelectedValue().toString());
//                       // campo.setText(new LoadWithObjects2Data(objLista.getCod(),objLista.getDes(),objLista.getCant()));    :(
//            campo.setText(objLista.getCod()+" - "+objLista.getDes());//escribe en el txt madre del PGAv2
//            //if(THREE_data && campoSecundario!=null)campoSecundario.setText(objLista.getCant());//
//                show_jlist= false;//hide popup
//                modelo.removeAllElements();
//        }           
    }
            if(componente != null)componente.requestFocus();
            lista.setOpaque(true);
            popup.hide();
}
    
    public void ocultarauto(){
        if(popup!=null)popup.hide();
    }
    
    public void AddListeners(){
        if(!added){
            lista.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               // System.out.print("\nPRESSED");
                if(e.getKeyCode() == 10){
                    seleccion();
                }
                if(e.getKeyCode() == 27){
                    campo.requestFocus();
                    campo.setText("");
                    ocultarauto();
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                   // System.out.print("\nPRESSED up");
                    if(lista.getSelectedIndex()==0){
                        e.consume();
                        lista.setSelectedIndex(matches-1);  
                    }
                }
                
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                //    System.out.print("\nPRESSED down "+matches+" "+lista.getSelectedIndex());
                    if(lista.getSelectedIndex()==matches-1){
                         e.consume();
                        lista.setSelectedIndex(0);
                    }
                }
                
            }
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
                //evento foco
        lista.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
             //   System.out.print("\n LA LISTA GANO EL FOCUS");
            }
            @Override
            public void focusLost(FocusEvent e) {
               // System.out.print("\n LA LISTA PERDIO EL FOCUS");
                if(Menu.jDesktopPane1.getSelectedFrame()!=frame)ocultarauto();
            }
        });
        
                        //evento foco
       campo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
              //  System.out.print("\n EL CAMPO GANO EL FOCUS ");
            }
            @Override
            public void focusLost(FocusEvent e) {
             //   System.out.print("\n EL CAMPO PERDIO EL FOCUS ");
                if(Menu.jDesktopPane1.getSelectedFrame()!=frame)ocultarauto();
            }
        });
        
        lista.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                seleccion();
            }

            @Override
            public void mousePressed(MouseEvent e) {
             //   seleccion(campo,componente);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        campo.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 27){
                    ocultarauto();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    lista.requestFocus();
                    lista.setSelectedIndex(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(campo.getText().trim().isEmpty()){
                    ocultarauto();
                }
            }
        });
        added=true;//pone true para no volver a agregar los listeners una y otra vez
        }
    }   
    
public static final String getCod(String dato){
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
System.out.print("\n -  FINDCOD  - ("+dato+").getCod="+res+"");
return res;
}

public static final String getDes(String dato){
    String res="";boolean guion=false;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    char c = vector_de_char[i];
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
}
