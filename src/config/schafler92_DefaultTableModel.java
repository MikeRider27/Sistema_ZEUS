
package config;

import javax.swing.table.DefaultTableModel;

public class schafler92_DefaultTableModel extends DefaultTableModel{
   @Override
   public boolean isCellEditable (int row, int column){
       return false;
   }
   public boolean is (int row, int column){
       return false;
   }
}

