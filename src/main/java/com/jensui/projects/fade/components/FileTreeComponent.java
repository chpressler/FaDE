package com.jensui.projects.fade.components;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IConnector;
import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.controller.FileTreeController;
import com.jensui.projects.fade.model.FileTreeModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileTreeComponent extends JPanel implements IExplorerComponent {

	private static final long serialVersionUID = 1L;

	private IConnector connector;
	
	private JTree view;
	
	private TreeModel model = null;
	
	private FileTreeController controller;
	
	private IFile root;
	
	private IFile currentDir;
	
	private IFile lastSelected;

    private final ArrayList<ExplorerComponentListener> listeners = new ArrayList<ExplorerComponentListener>();

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
        LayoutManager layout = new MigLayout("flowy", "0[grow,fill,center]",
                "[c,grow,fill]");
		this.setLayout(layout);
		add(jsp);
	}
	
	private Object[] getPath(IFile f, ArrayList<IFile> al) {
		al.add(f);
		if(f.getParent() != null) {
			getPath(f.getParent(), al);
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
	public IFile getRoot() {
		return root;
	}

	@Override
	public void setRoot(IFile f) {
		if(!f.isDir()) {
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
	public IFile getCurrentDirectory() {
		return currentDir;
	}

	@Override
	public IFile[] getSelectedFiles() {
		//TODO -> return selected Files[]
		return null;
	}

	@Override
	public void setCurrentDirectory(IFile f) {
		if(!f.isDir()) {
			return;
		}
		currentDir = f;
		view.clearSelection();
		ArrayList<IFile> al = new ArrayList<>();
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

	private IFile getRoot(IFile f) {
		if(f.getParent() == null) {
			return f;
		} else {
			return getRoot(f.getParent());
		}
	}

	@Override
	public IFile getLastSelected() {
		return lastSelected;
	}

	@Override
	public void selectionChanged(IFile lastSelected) {
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
