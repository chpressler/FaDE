package com.jensui.projects.fade.model;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.components.IExplorerComponent;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.File;

public class FileTableModel implements TableModel {

	private final IExplorerComponent c;
	
	private final String[] headers = { "File Name", "ext.", "Size", "Date Modified" };
	
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
			IFile f = c.getCurrentDirectory();
			if(f.getParent() != null) {
				return f.getChildCount() + 1;
			}
			return f.getChildCount();
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		IFile f = c.getCurrentDirectory();
		if(f.getParent() != null && rowIndex == 0) {
			return f.getParent();
		} else if(f.getParent() != null && rowIndex > 0) {
			return f.getChildren().get(rowIndex - 1);
		} else {
			return f.getChildren().get(rowIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 0;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		System.out.println(value.toString());
	}

}
