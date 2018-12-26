package com.jensui.projects.fade.components;

import com.jensui.projects.fade.IFile;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

class FileTableCellRenderer extends JLabel implements
		TableCellRenderer, Serializable {
	static final long serialVersionUID = 7878911414715528324L;

	protected static Border noFocusBorder = new EmptyBorder(0, 0, 0, 0);
	
	private final IExplorerComponent c;

	private static class UIResource extends DefaultTableCellRenderer implements
			javax.swing.plaf.UIResource {

		private static final long serialVersionUID = 1L;

		public UIResource() {
			super();
		}
	}

	public FileTableCellRenderer(IExplorerComponent c) {
		super();
		this.c = c;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		
		if(column == 0) {
			setValue(((IFile) value).getName());
		} else if(column == 1) {
			String ext = "";
			   int i = ((IFile) value).getName().lastIndexOf('.');
			   if (i > 0 &&  i < ((IFile) value).getSize() - 1) {
			      ext = ((IFile) value).getName().substring(i + 1).toLowerCase();
			   }
			setValue(ext);
		} else if(column == 2) {
			long length = ((IFile) value).getSize();
			String size;
			if(length > 1024 && length < (1024 * 1024)) {
				length /= 1024;
				size = length + " KB";
			}
			else if(length > (1024 * 1024) && length < (1024 * 1024 * 1024)) {
				length /= (1024 * 1024);
				size = length + " MB";
			}
			else if(length > (1024 * 1024 * 1024)) {
				length /= (1024 * 1024 * 1024);
				size = length + " GB";
			}
			else if(!((IFile) value).isDir()) {
				size = length + " Byte";
			}
			else {
				size = "";
			}
			setValue(size);
//			setValue(((File) value).length() / 1024);
		} else if(column == 3) {			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(((IFile)value).getLastChanged());
			String DATE_FORMAT = "dd.MM.yyyy hh:mm";
		    SimpleDateFormat sdf =
		          new SimpleDateFormat(DATE_FORMAT);
		   setValue( sdf.format(gc.getTime()));
//			setValue(((File)value).lastModified());
//			setValue(gc.getTime());
		} else {
			setValue("");
		}
		
		setOpaque(true);

		if (table == null)
			return this;

		if (isSelected) {
			super.setBackground(Color.BLACK);
			super.setForeground(Color.WHITE);
		} else {
			super.setBackground(Color.WHITE);
			super.setForeground(Color.BLACK);
		}

//		Border b = null;
//		if (hasFocus) {
//			if (isSelected) {
////				b = UIManager.getBorder("Table.focusSelectedCellHighlightBorder");
//			}
//			if (b == null) {
////				b = UIManager.getBorder("Table.focusCellHighlightBorder");
//			}
//		} else
//			b = noFocusBorder;
		
//		setBorder(BorderFactory.createLineBorder(Color.WHITE));

		setFont(table.getFont());

		// If the current background is equal to the table's
		// background, then we
		// can avoid filling the background by setting the
		// renderer opaque.
		// // Color back = getBackground();
		// // setOpaque(back != null
		// // && back.equals(table.getBackground()));
		
		if(column == 0) {
			setIcon(FileSystemView.getFileSystemView().getSystemIcon(((IFile) value).getFile()));
		}
		
		if(c.getCurrentDirectory().getParent() != null && row == 0 && column == 0) {
			setValue("..");
//			setIcon();
		} else if(c.getCurrentDirectory().getParent() != null && row == 0 && column > 0) {
			setValue("");
		}

		return this;
	}

	public boolean isOpaque() {
		return true;
	}

	public void validate() {
		// Does nothing.
	}

	public void revalidate() {
		// Does nothing.
	}

	public void repaint(long tm, int x, int y, int width, int height) {
		// Does nothing.
	}

	public void repaint(Rectangle r) {
		// Does nothing.
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		// Does nothing.
	}

	public void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
		// Does nothing.
	}

	private void setValue(Object value) {
		if (value != null)
			setText(value.toString());
		else
			// null is rendered as an empty cell.
			setText("");
	}

}
