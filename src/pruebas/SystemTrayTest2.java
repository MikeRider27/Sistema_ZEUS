/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

/**
 *
 * @author schafler92*/
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitorInputStream;

public class SystemTrayTest2 {
  public static void main(String[] args) {
    // create a test frame with a "press me" button
    final JFrame f = new JFrame("Sample");
    f.getContentPane().setLayout(new FlowLayout());
    JButton b = new JButton("Press me");
    f.getContentPane().add(b);
    f.pack();

    // set up the file read action
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // when button is pressed, start a new thread
        //   to read the file. A new thread is needed because we
        //   need to free the GUI update thread to paint the
        //   progress monitor
        new Thread() {
          @Override
          public void run() {
            try {
              // open the file, wrapping it in a ProgressMonitorInputStream
              InputStream in = new FileInputStream("c:Bigfile.bin");
                try (ProgressMonitorInputStream pm = new ProgressMonitorInputStream(f,"Reading the big file",in)) {
                    int c;
                    while((c=pm.read()) != -1) {
                        // do something
                    } }
            }
            catch(IOException ex) {
            }
          }
        }.start();
      }});  
  
    // display the frame
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
