/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author schafler92
 */
import java.awt.*; 
import java.awt.event.*; 
import java.util.Vector; 

public class ThrSystemTray { 
public ThrSystemTray(Vector vMenuActionListener, Vector vMenuActionListenerNames, String sToolTip, ActionListener al) { 

//se verifica que el SystemTray sea soportado 
if (SystemTray.isSupported()) { 
//se obtiene una instancia estática de la clase SystemTray 
SystemTray tray = SystemTray.getSystemTray(); 
//esta es la Imagen de icono 
Image ImagenIcono = Toolkit.getDefaultToolkit().getImage("C:\\Users\\schafler92\\Desktop\\temp%\\resources\\customize\\u_7imate_rc2_by_rflfn-d3fbuoo\\U-7imate RC2\\U-7imate RC2\\Icons\\Aplications\\gg.ico"); 
//este listener nos permite capturar cualquier tipo de evento 
//que se haga con el mouse sobre el icono 
MouseListener mouseListener = new MouseListener() { 
public void mouseClicked(MouseEvent e) { 
System.out.println("Icono del System Tray - Mouse clicked!" ); 
} 
public void mouseEntered(MouseEvent e) { 
System.out.println("Icono del System Tray - Mouse entered!" ); 
} 
public void mouseExited(MouseEvent e) { 
System.out.println("Icono del System Tray - Mouse exited!" ); 
} 
public void mousePressed(MouseEvent e) { 
System.out.println("Icono del System Tray - Mouse pressed!" ); 
} 
public void mouseReleased(MouseEvent e) { 
System.out.println("Icono del System Tray - Mouse released!" ); 
} 
}; 
//Se agregan los listeners que se obtuvieron por parametro 
//menu que aparece al hacer click derecho 
PopupMenu popup = new PopupMenu(); 
MenuItem item ; 
for (int i = 0 ; i < vMenuActionListener.size() ; i++){ 
item = new MenuItem(vMenuActionListenerNames.get(i).toString()); 
item.addActionListener((ActionListener) vMenuActionListener.get(i)); 
popup.add(item); 
} 
//iniciamos el objeto TrayIcon 
iconoSystemTray = new TrayIcon(ImagenIcono, sToolTip, popup); 
iconoSystemTray.setImageAutoSize(true); 
//este tipo de listener captura el doble click sobre el icono 
iconoSystemTray.addActionListener(al); 
iconoSystemTray.addMouseListener(mouseListener); 

//se debe capturar una excepción en caso que falle la adicion de un icono 
try { 
tray.add(iconoSystemTray); 
} catch (AWTException e) { 
System.err.println("No es posible agregar el icono al System Tray"); 
} 
} 
else 
System.err.println("Tu sistema no soporta el System Tray"); 
} 

public void setToolTip(){ 
    
iconoSystemTray.setToolTip("foiwejgujhwqioghqogwhoi"); 
} 

//se declara el objeto tipo icono 
private TrayIcon iconoSystemTray; 
}