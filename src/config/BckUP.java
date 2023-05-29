

package config;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class BckUP {
    private final int BUFFER = 10485760;  
    //para guardar en memoria
    private StringBuffer temp = null;
    //para guardar el archivo SQL
    private FileWriter  fichero = null;
    private PrintWriter pw = null;
    
 public boolean CrearBackup(String password,String PGDir,String dir){
    boolean ok=false;
    
    try{    
	        ProcessBuilder pb;    
	        Process p;    
	        pb = new ProcessBuilder(PGDir,//C:/Program Files (x86)/PostgreSQL/9.4/bin/pg_dump.exe
                        "-i", "-h","localhost", "-p","5432","-U","postgres","-F","c","-b","-v" ,"-f", 
	        dir,//"C:\\Users\\sch\\Desktop\\backufp.backup",
	        "despensa");    
	        pb.environment().put("PGPASSWORD", "wasd");    
	        pb.redirectErrorStream(true);    
	        p = pb.start();     
        }catch(Exception ex){    
            JOptionPane.showMessageDialog(null, ex.getMessage());    
        }

    return ok; 
 }  
 
//     public static void main(String args[]) {
//new BckUP().CrearBackup("127.0.0.1", "5432","postgres","wasd","despensa","C:/Program Files (x86)/PostgreSQL/9.4/bin/pg_dump.exe");
//    }
}