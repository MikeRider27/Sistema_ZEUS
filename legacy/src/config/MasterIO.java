/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

/**
 *
 * @author sch
 */
public class MasterIO {
    String ln = System.getProperty("line.separator");
    
    public File create(String name,String ext,String path){
        File file = null;
        if(path.isEmpty())path="C:\\Users\\sch\\Dropbox\\Sistema_ZEUS\\";
        System.out.print(path+name+"."+ext);
        try{
file=new File(path+name+"."+ext);
//file=new File("C:\\Users\\sch\\Dropbox\\Sistema_ZEUS\\scxçh001.txt");
if(file.createNewFile())System.out.print("\nFile created in "+file.getAbsolutePath());
//                //si existe
//                System.out.println(file.exists());
//		// para saber si directorio, devuelve true o false
//		System.out.println(file.isDirectory());
//		// para saber la fecha de modificación
//		System.out.println(file.lastModified());
//		// para saber el nombre del archivo
//		System.out.println(file.getName());
//		// para saber la ruta
//		System.out.println(file.getPath());
//		// para saber el tamaño en bytes que ocupa en disco
//		System.out.println(file.length());
//		// para saber si es de lectura, devuelve true o false
//		System.out.println(file.canRead());
//		// para saber si es de escritura, devuelve true o false
//		System.out.println(file.canWrite());
//		// crea un directorio en disco, devuelve true o false, para esto la ruta no debe ser .txt
//		// ejemplo: "C:\\archivos\\estudiantes" y crea el directorio estudiantes
//                 System.out.println(file.mkdir());
}
catch(IOException e){
System.out.println("Error al crear"+e.getMessage());
}
        return file;
    }
    
    public File find(String path,String name,String ext){
        File file=new File(path+name+"."+ext);
            if(file.exists())return file;
       return null;
    }
    
    
    public void write(File file,String[] data){
            int i=0;
try{
FileWriter writer=new FileWriter(file,true);//el true hace que todo se escriba subsecuentemente sin sobreescribir
while(i!=data.length){
  writer.write(data[i]+ln);  
  i++;
}
writer.close();
}
catch(IOException e){
System.out.println("Error al escribir \n"+e.getMessage());
}
}
    
    public String[] read(File file){
        Object []content=null;
        String texto;
try{
//Creamos un archivo FileReader que obtiene lo que tenga el archivo
FileReader lector=new FileReader(file);
//El contenido de lector se guarda en un BufferedReader
BufferedReader contenido=new BufferedReader(lector);

Stream g =contenido.lines();
content=g.toArray();

//Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
int i=0;
while((texto=contenido.readLine())!=null){
System.out.println("\n -"+texto);
System.out.println("\n SS"+content[i]);
}
}

//Si se causa un error al leer cae aqui
catch(IOException e){
System.out.println("Error al leer\n"+e.getMessage());
}
return null;
    }
    
        public static void main(String args[]) {
MasterIO io=new MasterIO();

io.write(io.create("sch001", "txt","C:\\Users\\sch\\Dropbox\\Sistema_ZEUS\\"),new String[]{"[MAIN]"," - DIR"," - THEME"," ","[USER]"});
io.read(io.find("C:\\Users\\sch\\Dropbox\\Sistema_ZEUS\\","sch001","ini"));
    }
}
