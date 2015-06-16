package com.jensui.projects.myfade.components;

import java.awt.Color;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jensui.projects.myfade.FaDE;
import com.jensui.projects.myfade.model.FileTreeModel;
import net.miginfocom.swing.MigLayout;

import com.jensui.projects.myfade.controller.FileTreeController;

public class FileTreeComponent extends JPanel implements IExplorerComponent {

	private static final long serialVersionUID = 1L;
	
	private JTree view = null;
	
	private TreeModel model = null;
	
	private FileTreeController controller = null;
	
	private File root;
	
	private File currentDir;
	
	private File lastSelected;
	
	private LayoutManager layout = null;
	
	private ArrayList<ExplorerComponentListener> listeners = new ArrayList<ExplorerComponentListener>();

	public JTree getView() {
		return view;
	}

	public void setView(JTree view) {
		this.view = view;
	}

	public TreeModel getModel() {
		return model;
	}

	public void setModel(FileTreeModel model) {
		this.model = model;
	}

	public FileTreeController getController() {
		return controller;
	}

	public void setController(FileTreeController controller) {
		this.controller = controller;
	}
	
	public FileTreeComponent(URLComponent urlc, DriveSelectComponent dsc) {
		try {
			model = new FileTreeModel(this);
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
		}
//		root = File.listRoots()[0];
		root = dsc.getSelectedRoot();
		addExplorerComponentListener(urlc);
		addExplorerComponentListener(dsc);
		
		view = new JTree(model);
		controller = new FileTreeController(this);
		view.setCellRenderer(new FileTreeCellRenderer());
		view.addMouseListener(controller);
		view.addKeyListener(controller);
		view.addTreeSelectionListener(controller);
		view.setBackground(Color.white);
//		new TreeDragSource(view, DnDConstants.ACTION_COPY);
//	    new TreeDropTarget(view);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane jsp = new JScrollPane();
		jsp.setAutoscrolls(true);
		jsp.getViewport().add(view);
		layout = new MigLayout("flowy", "0[grow,fill,center]",
		"[c,grow,fill]");
		this.setLayout(layout);
		add(jsp);
	}
	
	private Object[] getPath(File f, ArrayList<File> al) {
		al.add(f);
		if(f.getParent() != null) {
			getPath(f.getParentFile(), al);
		}
		Object[] oa = new Object[al.size()];
		int oi = 0; 
		for(int i = al.size()-1; i >= 0; i--) {
			oa[oi] = al.get(i);
			oi++;
		}
		return oa;
	}

	@Override
	public File getRoot() {
		return root;
	}

	@Override
	public void setRoot(File f) {
		if(!f.isDirectory()) {
			return;
		}
        if(FaDE.getInstance().getOSType().equals(FaDE.OSType.UNIX)) {
            root = f;
        } else {
            root = getRoot(f);
        }
		view.clearSelection();
		view.updateUI();
		for(ExplorerComponentListener l : listeners) {
			l.rootChanged(new ExplorerComponentEvent(this));
		}
		repaint();
	}

	@Override
	public void addExplorerComponentListener(ExplorerComponentListener l) {
		listeners.add(l);
	}

	@Override
	public void removeExplorerComponentListener(ExplorerComponentListener l) {
		listeners.remove(l);
	}

	@Override
	public File getCurrentDirectory() {
		return currentDir;
	}

	@Override
	public File[] getSelectedFiles() {
		//TODO -> return selected Files[]
		return null;
	}

	@Override
	public void setCurrentDirectory(File f) {
		if(!f.isDirectory()) {
			return;
		}
		currentDir = f;
		view.clearSelection();
		ArrayList<File> al = new ArrayList<File>();
		TreePath path = new TreePath(getPath(f, al));
		view.setSelectionPath(path);
		view.expandPath(path);
		view.updateUI();
		for(ExplorerComponentListener l : listeners) {
			l.currentDirectoryPathChanged(new ExplorerComponentEvent(this));
		}
		repaint();
		revalidate();
		validate();
	}

	@Override
	public void setSelectedFiles(File[] selection) {
		// TODO Auto-generated method stub
		
	}
	
	private File getRoot(File f) {
		if(f.getParentFile() == null) {
			return f;
		} else {
			return getRoot(f.getParentFile());
		}
	}

	@Override
	public File getLastSelected() {
		return lastSelected;
	}

	@Override
	public void selectionChanged(File lastSelected) {
		this.lastSelected = lastSelected;
		for(ExplorerComponentListener l : listeners) {
			l.selectionChanged(new ExplorerComponentEvent(this));
		}
	}

	@Override
	public ArrayList<ExplorerComponentListener> getExplorerComponentListeners() {
		return listeners;
	}

}
