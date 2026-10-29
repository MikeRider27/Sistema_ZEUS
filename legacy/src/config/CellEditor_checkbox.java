package config;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellEditor_checkbox extends DefaultCellEditor implements TableCellRenderer{
    private final JComponent component = new JCheckBox();    
    private boolean value = false; // valor de la celda
    
    /** Constructor de clase */
    public CellEditor_checkbox() {
        super( new JCheckBox() );
    }

    @Override
    public Object getCellEditorValue() {
        return ((JCheckBox)component).isSelected();        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //Color de fondo en modo edicion
     //   ( (JCheckBox) component).setBackground( new Color(200,200,0) );
        //obtiene valor de celda y coloca en el JCheckBox
        boolean b = ((Boolean) value);
        ( (JCheckBox) component).setSelected( b );
        System.out.print("\nNOPE");
        return ( (JCheckBox) component);     
    }

    /** cuando termina la manipulacion de la celda
     * @return  */
    @Override
    public boolean stopCellEditing() {        
        value = ((Boolean)getCellEditorValue()) ;
        ((JCheckBox)component).setSelected( value );
        return super.stopCellEditing();
    }

    /** retorna componente
     * @return  */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;         
         return ( (JCheckBox) component );
    }
}
