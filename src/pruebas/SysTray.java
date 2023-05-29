/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author schafler92
 */
public class SysTray {
   public static void main(String[] args) throws Exception {
      TrayIcon icon = new TrayIcon(getImage(),"Sistema AALLCAR",createPopupMenu());
      icon.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {//accion del globo o doble click icono
            JOptionPane.showMessageDialog(null, "Hey, you activated me!");
            
         }
      });
      SystemTray.getSystemTray().add(icon);

      Thread.sleep(3000);

     // icon.displayMessage("AALL CAR System", "Se ha ingresado un nuevo vehiculo, Presione AQUI para redirigirlo al formulario",TrayIcon.MessageType.INFO);
      //System.exit(0);
   }

   private static Image getImage() throws HeadlessException {
      Icon defaultIcon = MetalIconFactory.getTreeHardDriveIcon();
      Image img = new BufferedImage(defaultIcon.getIconWidth(), 
            defaultIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
      defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);
      return img;
   }

   private static PopupMenu createPopupMenu() throws HeadlessException {
      PopupMenu menu = new PopupMenu();
      
      MenuItem open = new MenuItem("Abrir Sistema");
      open.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
//Login l =new Login();
//l.setVisible(true);
         }
      });
      menu.add(open);
      //------------------------

      MenuItem exit = new MenuItem("Cerrar Sistema");
      exit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
              int msj=JOptionPane.showConfirmDialog(null,"¿Desea Cerrar el Sistema?", "Confirmar",JOptionPane.YES_NO_OPTION);
        if (msj==JOptionPane.YES_OPTION) {
           System.exit(0);
        }
         }
      });
      menu.add(exit);

      return menu;
   }
}