package com.jensui.projects.fade.controller;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.components.FileTableComponent;
import com.jensui.projects.fade.components.IExplorerComponent;
import com.jensui.projects.fade.model.FileTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileTableController implements MouseListener, KeyListener, ListSelectionListener {
	
	private final IExplorerComponent c;
	
	private final Logger logger = Logger.getLogger(FileTableController.class.getName());
	
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
			IFile file = (IFile) ((JTable) e.getSource()).getModel().getValueAt(row, 0);
			if (!file.isDir()) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(file.getFile());
						} catch (IOException e) {
							logger.log(Level.SEVERE, e.toString(), new Throwable(e));
						}
					}
				});
			} else {
				c.setCurrentDirectory(file);
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
			IFile file = (IFile) ((JTable) e.getSource()).getModel().getValueAt(((JTable) e.getSource()).getSelectedRow(), 0);
			if(file.isDir()) {
				c.setCurrentDirectory(file);
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(file.getFile());
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
				IFile f = (IFile) ((FileTableModel) table.getModel()).getValueAt(selrow, 0);
				c.selectionChanged(f);
			}
		}
	}

}
