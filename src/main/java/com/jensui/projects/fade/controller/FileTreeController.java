package com.jensui.projects.fade.controller;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.components.ChartComponent;
import com.jensui.projects.fade.components.IExplorerComponent;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileTreeController implements MouseListener, KeyListener, TreeModelListener, TreeSelectionListener {
	
	private final IExplorerComponent c;
	
	public FileTreeController(IExplorerComponent c) {
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (SwingUtilities.isLeftMouseButton(arg0)) {
			final IFile f = (IFile) ((JTree) arg0.getSource())
					.getClosestPathForLocation(arg0.getX(), arg0.getY())
					.getLastPathComponent();
			if (!f.isDir() && arg0.getClickCount() == 2) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(f.getFile());
						} catch (IOException e) {
							Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
						}
					}
				});
			} else {
				
			}
		}
		if(SwingUtilities.isMiddleMouseButton(arg0)) {
			new ChartComponent();
		}
		if(SwingUtilities.isRightMouseButton(arg0)) {
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent arg0) {
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent arg0) {
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
//		((FileTreeComponent) c).getPathTextField().setText(e.getPath().getLastPathComponent().toString());
		c.selectionChanged((IFile) e.getPath().getLastPathComponent());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			IFile file = (IFile) ((JTree) e.getSource()).getSelectionPath().getLastPathComponent();
			if(file.isDir()) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							Desktop.getDesktop().open(file.getFile());
						} catch (IOException e) {
							Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
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

}
