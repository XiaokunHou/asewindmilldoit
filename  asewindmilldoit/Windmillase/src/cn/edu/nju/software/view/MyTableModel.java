package cn.edu.nju.software.view;

import javax.swing.table.DefaultTableModel;


public class MyTableModel extends DefaultTableModel{
	
	public MyTableModel(){
		
	}
	
	public MyTableModel(Object[][] data, Object[] columnNames){
		super(data, columnNames);
	}
	
	public Class getColumnClass(int column){
		return getValueAt(0, column).getClass();
	}
	
	public boolean isCellEditable(int row, int column){
		Class columnClass = getColumnClass(column);
		return columnClass == Boolean.class;
	}

}
