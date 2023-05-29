/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author Kuroryuu
 */
import java.awt.Color;
import java.awt.Component;
 
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
 
public class TableRowEx
{
 
	private static String[] columnNames =
	{ "Source", "Destination", "Val1", "Val2", "Order" };
 
	private static Object[][] data =
	{
	{ "a1", "a2", new Integer(5), new Integer(5), Boolean.TRUE },
	{ "a1", "a2", new Integer(5), new Integer(5), Boolean.FALSE },
	{ "a1", "a2", new Integer(5), new Integer(5), Boolean.TRUE },
	{ "a1", "a2", new Integer(5), new Integer(5), Boolean.TRUE },
	{ "a1", "a2", new Integer(5), new Integer(5), Boolean.FALSE } };
 
	public static void main(String args[])
	{
		MyTableModel model = new MyTableModel(data, columnNames);
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CheckBoxTableRenderer renderer = new CheckBoxTableRenderer();
		renderer.setBackground(Color.DARK_GRAY);
		table.getColumnModel().getColumn(4).setCellRenderer(renderer);
		
		JFrame frame = new JFrame();
		frame.add(table);
		frame.setSize(500, 200);
		frame.pack();
		frame.setVisible(true);
                
	}
}
 
class MyTableModel extends DefaultTableModel
{
 
	public MyTableModel(Object[][] pdata, String[] cols)
	{
		super(pdata, cols);
	}
 
	public Class<?> getColumnClass(int c)
	{
		return c == 4 ? Boolean.class : getValueAt(0, c).getClass();
	}
 
	public boolean isCellEditable(int row, int col)
	{
		if (col == 4)
		{
			return true;
		}
		return false;
	}
 
	public void setValueAt(Object value, int row, int col)
	{
            System.out.print("\nValue "+value+" row "+row+" col "+col);
		super.setValueAt(value, row, col);
	}
}
 
class CheckBoxTableRenderer extends JCheckBox implements TableCellRenderer
{
	protected CheckBoxTableRenderer()
	{
		setHorizontalAlignment(JCheckBox.CENTER);
		setVerticalAlignment(JCheckBox.CENTER);
	}
 
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	        boolean isSelected, boolean hasFocus, int row, int col)
	{
		setForeground(table.getForeground());
		setBackground(table.getBackground());
		setFont(table.getFont());
		setSelected((value != null && ((Boolean) value)));
		return this;
	}
 
}
