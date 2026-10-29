package config;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellRenderer_checkbox extends JCheckBox implements TableCellRenderer{
    private final JComponent component = new JCheckBox();

    /** Constructor de clase */
    public CellRenderer_checkbox() {
        setOpaque(true);
    }

    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      //Color de fondo de la celda
      //( (JCheckBox) component).setBackground( new Color(167,218,251) );//[167,218,251]
      
      //obtiene valor boolean y coloca valor en el JCheckBox
      boolean b = ((Boolean) value);
      ( (JCheckBox) component).setSelected( b );
      this.setEnabled(false);
      return ( (JCheckBox) component);
  }
}
