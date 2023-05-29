
package threads;

import static frames.Menu.jDesktopPane1;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

public class Open_InternalFrame_Thread extends Thread{

   private boolean continuar = true;
    JInternalFrame frame;
    long start=System.currentTimeMillis();
    
  public Open_InternalFrame_Thread (JInternalFrame frame){
       this.frame=frame;
   }
   
   public void stop_(){
      continuar=false;
      frame=null;
   }

   @Override
    public void run(){
      while (continuar){
 jDesktopPane1.add(frame); 
Dimension desktopSize = jDesktopPane1.getSize();
Dimension jInternalFrameSize = frame.getSize();
frame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2); 
frame.setVisible(true);
System.out.print("\n -- THREAD -- Abriendo el Formulario "+frame.getTitle()+" en "+(System.currentTimeMillis()-start)+"ms");
stop_();
} 
} 
}