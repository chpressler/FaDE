package com.jensui.projects.myfade.components;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FileTableCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;
	
	public FileTableCellEditor(JTextField textField) {
		super(textField);
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		value = new File(value.toString()).getName();
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
