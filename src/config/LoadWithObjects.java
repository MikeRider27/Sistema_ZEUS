
package config;

import java.util.Objects;

public class LoadWithObjects {
  private String des;
  private String codigo; 

    public LoadWithObjects(String codigo, String des){
        this.codigo=codigo;
        this.des=des;
      }
    public String getDes(){
        return des;
    }

    public void setNombreDeEjemplo(String des) {
        this.des = des;
    }

    public String getCod(){
                 return codigo;   
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
  @Override
    public String toString(){
     return des;
    }

       @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof LoadWithObjects)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        LoadWithObjects c = (LoadWithObjects) o;
         
        // Compare the data members and return accordingly 
        return codigo.equals(c.codigo) && des.equals(c.des) ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.des);
        hash = 17 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

}
  