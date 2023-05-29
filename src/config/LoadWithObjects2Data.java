
package config;

import java.util.Objects;

/**
 *
 * @author schafler92
 */

public class LoadWithObjects2Data {
  private String des,codigo,tercerDato; 

    public LoadWithObjects2Data(String codigo, String des,String tercerDato){
        this.codigo=codigo;
        this.des=des;
        this.tercerDato=tercerDato;
      }
    public String getDes(){
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCod(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public String getCant(){
    return tercerDato;
    }
    
    public void setCant(String cant){
        this.tercerDato=cant;
    }
    
  @Override
    public String toString()
    {
     return des;
    }
    
       @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        
        if (!(o instanceof LoadWithObjects2Data)) {
            return false;
        }

        LoadWithObjects2Data c = (LoadWithObjects2Data) o;
        
        return codigo.equals(c.codigo) && des.equals(c.des) ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.des);
        hash = 97 * hash + Objects.hashCode(this.codigo);
        hash = 97 * hash + Objects.hashCode(this.tercerDato);
        return hash;
    }

}
  