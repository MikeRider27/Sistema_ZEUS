/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

/**
 *
 * @author schafler92
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class ScreenCapture{
    
public static void captureScreen(String fileName) throws Exception {
Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
Rectangle screenRectangle = new Rectangle(screenSize);
Robot robot = new Robot();
BufferedImage image = robot.createScreenCapture(screenRectangle);
ImageIO.write(image, "png", new File(fileName));
}
//----
public static void main(String[] args) {
try{
System.out.println("[ Captura iniciada ]");
//sleep 1.5 seg
//Thread.currentThread().sleep(1*2500);
String FILENAME="E:\\"+new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()).toString()+".png";
ScreenCapture.captureScreen(FILENAME);
System.out.println("[ Captura finalizada ]");
}
catch(Exception e){
JOptionPane.showMessageDialog( null, e.getMessage(),"Error al Capturar la Pantalla", JOptionPane.ERROR_MESSAGE );
}
}
//----
}
