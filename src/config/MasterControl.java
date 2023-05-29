package config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MasterControl {
    
    //fichero TMP
   // private final String appPath = System.getProperties().getProperty("user.dir");
     private final String appPath =System.getProperty("java.io.tmpdir") ;
     //private final String appPath ="C:\\sys\\";
   
  //  private final File archivo = new File( appPath + "\\temp.tmp");   
        private final File archivo = new File( appPath + System.getProperty("file.separator")+"temp.sch"); //gg   

    //tiempo en que se actualiza el fichero TMP
    private final int segundos = 60;
    
    /** Constructor de clase */
    public MasterControl(){
     //System.out.print(appPath);
    }
    
    /**
     * Comprueba que archivo TMP exista, sino lo crea e inicia valores
     * @return 
     */
    public boolean comprobar(){           
        if (archivo.exists()){ 
            long tiempo = leer();//
            long res = restarTiempo(tiempo);           
            if( res < segundos){              
//                JOptionPane.showMessageDialog(null,"El Sistema ya se encuentra en ejecución.\n"
//  + "Esta instancia va a cerrarse","",JOptionPane.WARNING_MESSAGE);
//                System.exit(0);
                return false;
            }else{        
                launchThread();
                return true;
            }
        }else{//no existe el temp
            crearTMP();   
            launchThread();
            return true;
        }            
    }
    
    /**
     * Lee el archivo TMP y retorna su valor 
     * @return LONG cantidad de milisegundos 
     */
    public long leer()
    {
        String linea = "0";        
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(archivo));            
            while(bufferedReader.ready()){
                linea = bufferedReader.readLine();            
        }
            
        }catch (IOException e) {
            System.err.println( e.getMessage() );
        }
        //return Long.valueOf(linea).longValue();
        return Long.parseLong(linea);
    }
    
    /**
     * Programa un proceso que se repite cada cierto tiempo
     */
    public void launchThread(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() 
            {
                @Override
                public void run() {                   
                    crearTMP(); 
                }
              }, 1000, segundos * 1000 , TimeUnit.MILLISECONDS ); //comienza dentro de 1 segundo y luego se repite cada N segundos           
    }
    
    /**
     * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
     */
    public void crearTMP(){
        Date fecha=new Date();   
        try {            
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));       
            System.out.print("\n//--Se creo el archivo TMP "+archivo);
            writer.write(String.valueOf(fecha.getTime()));//escribe la fecha en el archivo en formato long
            writer.close();            
            //-------------
            //locate the full path to the file e.g. c:\a\b\Log.txt
    //  Path p = Paths.get(appPath + System.getProperty("file.separator")+"temp.txt");

       //link file to DosFileAttributes
     //  DosFileAttributes dos = Files.readAttributes(p, DosFileAttributes.class);
       //hide the Log file
     //  Files.setAttribute(p, "dos:hidden", true);

      //System.out.println("\n//--Se escribio en el archivo y paso a estado oculto"+dos.isHidden());
        } catch (IOException e) {
            System.err.println("\nCreate "+ e.getMessage() );
        } 
        
    }
 
    /**
     * Resta el tiempo expresado en milisegundos
     * @param tiempoActual el tiempo actual del sistema expresado en milisegundos
     * @return tiempo el resultado expresado en segundos
     */
    public long restarTiempo(long tiempoActual){  
        long tiempoTMP = new Date().getTime();        
        long tiempo = tiempoTMP - tiempoActual;        
        tiempo = tiempo /1000;        
        return tiempo;
    }
    
    /**
     * Elimina el fichero TMP si es que existe
     */
    public void borrarTemp(){   
        if (archivo.exists())archivo.delete();
        System.out.print("\n//--Se elimino el archivo TMP "+archivo);
        System.exit(0);
    }
    
}//--> fin clase
