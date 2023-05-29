package config;

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
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;


public class PGa_vStandard {
    
    public DefaultListModel modelo = new DefaultListModel();
    public boolean show_jlist = false;
    public ResultSet rs;
    public JTextField campo;
    public Component componente;
    public PreparedStatement pst;
    public Popup popup = null;
    public final JList lista = new JList(modelo);
    public static int jlist_lenght,focus;
    public boolean added;
    public JInternalFrame frame;
    
    public void autocomplete(final JTextField campo,final String concat,String tabla, String condicion,final JInternalFrame frame,final Component componente,Connection con){
        modelo.removeAllElements();
        this.campo=campo;
        this.frame=frame;
        this.componente=componente;
        if(condicion==null)condicion="";
              System.out.print("\n -- AUTOCOMPLETE -- \n"
                      + "SQL# SELECT concat("+concat+") FROM "+tabla+" WHERE concat("+concat+") LIKE '%" + campo.getText().trim().toUpperCase() + "%' "+condicion+" ORDER BY 1 ASC LIMIT 10");
        try {
            pst=con.prepareStatement("SELECT concat("+concat+") FROM "+tabla+" WHERE concat("+concat+") LIKE '%"+campo.getText().trim().toUpperCase()+"%' "+condicion+" ORDER BY 1 ASC LIMIT 10");
            rs=pst.executeQuery();
            jlist_lenght=0;
            while (rs.next()){
               modelo.addElement(rs.getString(1).trim());
                 jlist_lenght++;
            }
            show_jlist = true;
            System.out.print("\n -- AUTOCOMPLETE -- \n");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error al Cargar Lista (PGa_Standard)",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }finally{
            try {
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();   
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error al Cerrar Objetos", JOptionPane.ERROR_MESSAGE);
            }
        }   
        lista.setOpaque(true);
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
    
    public void refresh_(){
                if (show_jlist == true) {
            popup.show();
        } else {
            popup.hide();
            lista.setOpaque(true);
        } 
}
    
    public void seleccion(final JTextField campo,final Component componente){
                    if (lista.getSelectedIndex() != -1) {
                    campo.setText(lista.getSelectedValue().toString());
                    show_jlist= false;//hide popup
                    modelo.removeAllElements();
                    if (componente != null)componente.requestFocus();
                 }
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
                if(e.getKeyCode() == 10){
                    seleccion(campo,componente);
                }
                if(e.getKeyCode() == 27){
                    campo.requestFocus();
                    campo.setText("");
                    ocultarauto();
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    if(lista.getSelectedIndex()==0){
                        e.consume();
                        lista.setSelectedIndex(jlist_lenght-1);
                        
                    }
                }
                
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    if(lista.getSelectedIndex()==jlist_lenght-1){
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
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(Menu.jDesktopPane1.getSelectedFrame()!=frame)ocultarauto();
            }
        });
        
                        //evento foco
       campo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(Menu.jDesktopPane1.getSelectedFrame()!=frame)ocultarauto();
            }
        });
        
        lista.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                seleccion(campo,componente);
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
        added=true;
        }
    }          
}
