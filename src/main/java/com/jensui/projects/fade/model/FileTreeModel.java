package com.jensui.projects.fade.model;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.components.IExplorerComponent;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {
	
	private final IExplorerComponent c;
	
	public FileTreeModel(IExplorerComponent c) {
		this.c = c;
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		
	}
	
	@Override
	public Object getChild(Object arg0, int arg1) {
		return ((IFile) arg0).getChildren().get(arg1);
	}
	
	@Override
	public int getChildCount(Object arg0) {
		if(((IFile) arg0).getChildren() == null) {
			return 0;
		}
		return ((IFile) arg0).getChildCount();
	}

	@Override
	public int getIndexOfChild(Object arg0, Object arg1) {
		for(int i = 0; i < ((IFile) arg0).getChildCount(); i++ ) {
			if(((IFile) arg0).getChildren().get(i).equals((IFile) arg1)) {
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
		return !((IFile) arg0).isDir();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
