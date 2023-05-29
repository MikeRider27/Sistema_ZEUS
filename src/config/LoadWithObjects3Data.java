
package config;

import java.util.Objects;

/**
 *
 * @author schafler92
 */
public class LoadWithObjects3Data {
  private String des,codigo,cant,algomas; 

    public LoadWithObjects3Data(String codigo, String des,String cant,String algomas){
        this.codigo=codigo;
        this.des=des;
        this.cant=cant;
        this.algomas=algomas;
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
    return cant;
    }
    
    public void setCant(String cant){
        this.cant=cant;
    }
    
    public String getAlgomas(){
    return algomas;
    }
    
    public void setAlgomas(String algomas){
        this.algomas=algomas;
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
        
        if (!(o instanceof LoadWithObjects3Data)) {
            return false;
        }

        LoadWithObjects3Data c = (LoadWithObjects3Data) o;
        
        return codigo.equals(c.codigo) && des.equals(c.des) ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.des);
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.cant);
        hash = 79 * hash + Objects.hashCode(this.algomas);
        return hash;
    }

}
  