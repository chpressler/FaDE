package com.jensui.projects.fade.controller;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jensui.projects.fade.components.FileTableComponent;
import com.jensui.projects.fade.components.IExplorerComponent;
import com.jensui.projects.fade.model.FileTableModel;

public class FileTableController implements MouseListener, KeyListener, ListSelectionListener {
	
	private IExplorerComponent c;
	
	private Logger logger = Logger.getLogger(FileTableController.class.getName());
	
	public FileTableController(IExplorerComponent c) {
		this.c = c;
		try {
			logger.addHandler(new FileHandler("FaDEErrorLog.xml.txt"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			int row = ((JTable) e.getSource()).getSelectedRow();
			String file = ((JTable) e.getSource()).getModel().getValueAt(row, 0).toString();
			final File f = new File(file);
			if (f.isFile()) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(f);
						} catch (IOException e) {
							logger.log(Level.SEVERE, e.toString(), new Throwable(e));
						}
					}
				});
			} else {
				c.setCurrentDirectory(f);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String file = ((JTable) e.getSource()).getModel().getValueAt(((JTable) e.getSource()).getSelectedRow(), 0).toString();
			final File f = new File(file);
			if(f.isDirectory()) {
				c.setCurrentDirectory(f);
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(f);
						} catch (IOException e) {
							logger.log(Level.FINEST, e.toString(), new Throwable(e));
						}
					}
				});
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			JTable table = ((FileTableComponent) c).getView();
			int selrow = table.getSelectedRow();
			if(selrow > -1) {
				File f = (File) ((FileTableModel) table.getModel()).getValueAt(selrow, 0);
				c.selectionChanged(f);
			}
		}
	}

}
