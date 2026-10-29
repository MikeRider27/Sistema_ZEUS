
package pruebas;

import java.io.File;


public class ListarContenidoDirectorio {

        public static void main(String[] args) {
                String sDirectorio = "C:\\";
                File f = new File(sDirectorio);
                
                if (f.exists()){
                        // Recuperamos la lista de ficheros
                        File[] ficheros = f.listFiles();
                        for (int x=0;x<ficheros.length;x++){
                                System.out.println(ficheros[x].getName()+"  "+ficheros[x].getAbsoluteFile());
                        }
                } else{
                        System.out.println("No existe ese directorio");
                }
        }
}