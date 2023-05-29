package config;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer_stock extends DefaultTableCellRenderer{
   // private Font bold = new Font( "Arial",Font.BOLD ,12 );
    int cant,cant_min,veinte;
  //  JLabel e=new JLabel();
  //  Font f = new Font("VIVALDI", Font.PLAIN, 12);
  //  Font fbold = new Font("TAHOMA", Font.BOLD, 12);
        
    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ){
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        
        
       //this.setFont(f);
       // this.setHorizontalAlignment(JLabel.CENTER);
        
       cant=(int)Double.parseDouble(value.toString());//cantidad existente columna 3
       cant_min=(int)Double.parseDouble(table.getValueAt(row, 4).toString());//cantidad minima columna 4
       veinte=(int) ((int)cant_min*0.2);//20% de la cantidad minima (rojo)
         
//-------------se calcula el % faltante para alcanzar el minimo y se pinta la celda de acuerdo a ello-----------
      // if(selected){//Focuseado
          /* this.setBackground( Color.lightGray );//gris over
                   if((cant-cant_min)<=veinte){//20%
 this.setBackground( Color.lightGray );//gris over
        }
        
        if((cant-cant_min)>veinte && (cant-cant_min)<=(veinte*2)){//40%                
 this.setBackground(Color.lightGray);//gris over
        }
        
        if((cant-cant_min)>(veinte*2) && (cant-cant_min)<=(veinte*4)){//80%
 this.setBackground( Color.lightGray);//gris over
        }*/
            //   e.setBackground(Color.ORANGE);
    //   }else{//Fuera de Focus
              //  e.setBackground(Color.WHITE);
                  if((cant-cant_min)<=veinte){//20%
 //this.setBackground(new Color(229,0,0) );//rojo
 this.setForeground(new Color(229,0,0));
        }
        
        if((cant-cant_min)>veinte && (cant-cant_min)<=(veinte*2)){//40%                
 //this.setBackground(Color.yellow);//amarillo
 this.setForeground(Color.yellow);
        }
        
        if((cant-cant_min)>(veinte*2) && (cant-cant_min)<=(veinte*4)){//80%
// this.setBackground( new Color(0,255,0) );//verde 
 this.setForeground(new Color(0,255,0) );
        } 
     //  }  
        //colores de la letra comenzando por el selected y luego por el default
       // this.setForeground( (selected)? new Color(0,0,0):new Color(160, 160, 160));//new Color(0,110,10) );            
       this.setText(value.toString());
        this.setForeground( (selected)? new Color(0,0,0):new Color(0,0,0) );             
        return this;
    }
}
