

package threads;

import frames.Login;
import java.io.File;

public class Controlar_Medios_Extraibles extends Thread{

boolean continuar = true,root=false;

  public Controlar_Medios_Extraibles(){

   }
   
   public void stop_(){
      continuar=false;
   }

 @Override
   public void run(){
      while (continuar){
          File[] roots = File.listRoots();
                for (int i=0; i<roots.length; i++) {
                    System.out.println(roots[i]+" "+roots.length);
                }
                //stop_();
                if(roots.length>1){//1= c/ 2= particion de c/ 3= unidad virtual
                   System.out.println("Hay Unidades adicionales Conectadas"); 
                    if(Login.per==0 || Login.per==1){
                        System.out.println("\nAccess Granted"); 
                    }else{
                    //     try { 
         //   Runtime.getRuntime().exec("shutdown -s 1");
     //  // }  
     //   catch (IOException e) { 
//JOptionPane.showMessageDialog(null, e.getMessage(),"Error al Ejecutar Comandos", JOptionPane.ERROR_MESSAGE );      
       // }
                         System.out.println("\nAccess Denied"); 
                    }
                }
 } 
} 
   
 public static void main(String args[]) {
Controlar_Medios_Extraibles elHilo = new Controlar_Medios_Extraibles();
elHilo.start();
}
}
