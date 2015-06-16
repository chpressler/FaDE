package com.jensui.projects.myfade.model;

import java.io.File;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jensui.projects.myfade.components.IExplorerComponent;

public class FileTreeModel implements TreeModel {
	
	private IExplorerComponent c;
	
	public FileTreeModel(IExplorerComponent c) {
		this.c = c;
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		
	}
	
	@Override
	public Object getChild(Object arg0, int arg1) {
		return ((File) arg0).listFiles()[arg1];
	}
	
	@Override
	public int getChildCount(Object arg0) {
		if(((File) arg0).listFiles() == null) {
			return 0;
		}
		return ((File) arg0).listFiles().length;
	}

	@Override
	public int getIndexOfChild(Object arg0, Object arg1) {
		for(int i = 0; i < ((File) arg0).listFiles().length; i++ ) {
			if(((File) arg0).listFiles()[i].equals((File) arg1)) {
				return i;
			}
		}
		return 0;
	}
	
	@Override
	public Object getRoot() {
		return c.getRoot();
	}
	
	
	@Override
	public boolean isLeaf(Object arg0) {
		return ((File) arg0).isFile();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
