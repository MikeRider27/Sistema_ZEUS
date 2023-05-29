
package threads;
 
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class JoptionPaneContimeOut {
    JDialog dialogo = null;
    
    public void visualizaDialogo( Component padre, String texto,String titulo, final int timeout,int tipo_de_mensaje){
        JOptionPane msj = null;
    switch(tipo_de_mensaje){
            case 1://error
                msj=new JOptionPane("",JOptionPane.ERROR_MESSAGE);
                break;
            case 2://information
                msj=new JOptionPane("",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3://warning
                msj=new JOptionPane("",JOptionPane.WARNING_MESSAGE);
                break;
        }        
        
        msj.setMessage(texto);
        if ( null == dialogo ) {
            dialogo = msj.createDialog(padre, titulo);
        }else{
            dialogo.setTitle(titulo);
        }
 
        Thread hilo = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(timeout);
                    if (dialogo.isVisible()){
                        dialogo.setVisible(false);
                    }
                }
                catch ( InterruptedException e ){
                }
            }
        };
        hilo.start();
        dialogo.setVisible(true);
    }
}
