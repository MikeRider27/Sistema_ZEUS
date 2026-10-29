/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author schafler92
 */
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
 
public class TrayIconBasic {
 
    public static void main(String[] args) {
 
        if (!SystemTray.isSupported()) {
            // Go directory to the task;
            return;
        }
 
        Image icon = Toolkit.getDefaultToolkit().getImage("src/img/omt.gif");        
      //  Image icon = createIcon("src/img/omt.gif", "Sample tray icon");
        if (icon == null) {
            // Go directory to the task;
            return;
        }
 
        // create the trayIcon itself.
        final TrayIcon trayIcon = new TrayIcon(icon);
 
        // access the system tray. If not supported 
        // or if notification area is not present (Ubuntu)  
        // a NotSupportedException exception is trown;
 
        final SystemTray tray = SystemTray.getSystemTray();
 
        // Create popup menu
        PopupMenu popup = new PopupMenu();
        MenuItem exit = new MenuItem("Exit");
 
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do some cleanup
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
 
        popup.add(exit);
 
         
        // Add tooltip and menu to trayicon
        trayIcon.setToolTip("Tray icon demo");
        trayIcon.setPopupMenu(popup);
 
        // Add the trayIcon to system tray/notification
        // area
 
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Could not load tray icon !");
        }
 
        // Sleep/Simulate long running application code
        try {
            java.lang.Thread.sleep(1000 * 5);
        } catch (Exception e) {
        }
 
        // Update icon.  Visually notify user that something was done or
        // needs attention
 
        Image updateIcon = createIcon("rss.gif", "Updated icon");
        if (updateIcon != null) {
            trayIcon.setImage(updateIcon);
        }
         
        // Just to show how to add an alert/error message
        trayIcon.displayMessage("Alert", "The message", 
            TrayIcon.MessageType.ERROR);
 
         
        // Continue/Simulate long running application code
        try {
            java.lang.Thread.sleep(1000 * 5);
        } catch (Exception e) {
        }
 
        // remove the icon.  Task is done.
        tray.remove(trayIcon);
    }
 
 
    // A handy method to create an Image instance.
    protected static Image createIcon(String path, String description) {
        URL imageURL = TrayIconBasic.class.getResource(path);
        if (imageURL == null) {
            System.err.println(path + " not found");
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}