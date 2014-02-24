package biz.pressler.myfade.model;

import java.io.File;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import biz.pressler.myfade.components.IExplorerComponent;

public class FileTableModel implements TableModel {

	private IExplorerComponent c;
	
	String[] headers = { "File Name", "ext.", "Size", "Date Modified" };
	
	public FileTableModel(IExplorerComponent c) {
		this.c = c;
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return File.class;
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	@Override
	public int getRowCount() {
		try {
			File f = c.getCurrentDirectory();
			if(f.getParent() != null) {
				return f.listFiles().length + 1;
			}
			return f.listFiles().length;
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		File f = c.getCurrentDirectory();
		if(f.getParent() != null && rowIndex == 0) {
			return f.getParentFile();
		} else if(f.getParent() != null && rowIndex > 0) {
			return f.listFiles()[rowIndex - 1]; 
		} else {
			return f.listFiles()[rowIndex];	
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		System.out.println(value.toString());
	}

}
