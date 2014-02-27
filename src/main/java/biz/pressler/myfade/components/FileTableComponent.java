package biz.pressler.myfade.components;

import biz.pressler.myfade.FaDE;
import biz.pressler.myfade.controller.FileTableController;
import biz.pressler.myfade.model.FileTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.miginfocom.swing.MigLayout;

public class FileTableComponent extends JPanel implements IExplorerComponent {

	private static final long serialVersionUID = 1L;

	private JTable view = null;

	private FileTableModel model = null;

	private FileTableController controller = null;
	
	private File root = null;
	
	private File currentDir = null;
	
	private File lastSelected = null;
	
	private LayoutManager layout = null;
	
	private volatile ArrayList<ExplorerComponentListener> listeners = new ArrayList<ExplorerComponentListener>();
	
	public FileTableComponent(URLComponent urlc, DriveSelectComponent dsc) {
//		root = File.listRoots()[0];
		root = dsc.getSelectedRoot();
		currentDir = root;
		model = new FileTableModel(this);
		view = new JTable(model);
		view.setShowGrid(false);
//		view.setShowHorizontalLines(false);
//		view.setShowVerticalLines(true);
		view.setIntercellSpacing(new Dimension(0, 0));
		view.setBackground(Color.white);
		addExplorerComponentListener(urlc);
		addExplorerComponentListener(dsc);
//		TableRowSorter<FileTableModel> sorter = new TableRowSorter<FileTableModel>(model);
//		sorter.setComparator(0, new Comparator<File>() {
//			public int compare(File o1, File o2) {
//				char c1 = o1.getName().toLowerCase().charAt(0);
//				char c2 = o2.getName().toLowerCase().charAt(0);
//				if(c1 > c2) {
//					return 1;
//				}
//				if(c1 < c2) {
//					return -1;
//				}
//				return 0;
//			}});
//		sorter.setComparator(1, new Comparator<File>() {
//			public int compare(File o1, File o2) {
//				String ext1 = "";
//				int i = (o1).getName().lastIndexOf('.');
//				if (i > 0 &&  i < o1.length() - 1) {
//					ext1 = o1.getName().toLowerCase().substring(i + 1).toLowerCase();
//				}
//				String ext2 = "";
//				int i2 = (o2).getName().lastIndexOf('.');
//				if (i2 > 0 &&  i2 < o2.length() - 1) {
//					ext2 = o2.getName().toLowerCase().substring(i2 + 1).toLowerCase();
//				}
//				if(ext1 == "" && ext2 != "") {
//					return 1;
//				}
//				if(ext1 != "" && ext2 == "") {
//					return -1;
//				}
//				if(ext1 == "" && ext2 == "") {
//					return 0;
//				}
//				if(ext1.charAt(0) > ext2.charAt(0)) {
//					return 1;
//				}
//				if(ext1.charAt(0) < ext2.charAt(0)) {
//					return -1;
//				}
//				return 0;
//			}});
//		sorter.setComparator(2, new Comparator<File>() {
//			public int compare(File o1, File o2) {
//				if(o1.length() > o2.length()) {
//					return 1;
//				}
//				if(o1.length() < o2.length()) {
//					return -1;
//				}
//				return 0;
//			}});
//		sorter.setComparator(3, new Comparator<File>() {
//			public int compare(File o1, File o2) {
//				if(o1.lastModified() > o2.lastModified()) {
//					return 1;
//				}
//				if(o1.lastModified() < o2.lastModified()) {
//					return -1;
//				}
//				return 0;
//			}});
//		view.setRowSorter(sorter);
		
		view.setFillsViewportHeight(true);
	    view.setAutoCreateRowSorter(true);
	    
		controller = new FileTableController(this);
		view.addMouseListener(controller);
		view.addKeyListener(controller);
		view.getSelectionModel().addListSelectionListener(controller);

		TableColumnModel tcm = view.getColumnModel();
//		tcm.getColumn(0).setCellEditor(new FileTableCellEditor(null));
		for (int i = 0; i < view.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(new FileTableCellRenderer(this));
			int maxwidth = 0;
			for (int row = 0; row < view.getRowCount(); row++) {
				TableCellRenderer tcr = view.getCellRenderer(row, i);
				Object value = view.getValueAt(row, i);
				Component comp = tcr.getTableCellRendererComponent(view,
						value, false, false, row, i);
				maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
			}
			TableColumn tc = tcm.getColumn(i);
			tc.setPreferredWidth(maxwidth);
		}
		
		JScrollPane jsp = new JScrollPane();
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getViewport().add(view);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		layout = new MigLayout("flowy", "0[grow,fill,center]",
		"[c,grow,fill]");
		this.setLayout(layout);
		this.add(jsp);
	}
	
	public JTable getView() {
        return view;
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
		File[] sel = new File[view.getSelectedRowCount()];
		int n = 0;
		for(int i : view.getSelectedRows()) {
			sel[n] = ((File) ((FileTableModel) view.getModel()).getValueAt(i, 0));
			n++;
		}
		return sel;
	}

	@Override
	public void setCurrentDirectory(File f) {
		if(!f.isDirectory()) {
			return;
		}
		currentDir = f;
		view.clearSelection();
		for(ExplorerComponentListener l : listeners) {
			l.currentDirectoryPathChanged(new ExplorerComponentEvent(this));
		}	
		repaint();
		revalidate();
		validate();
	}

	@Override
	public void setSelectedFiles(File[] selection) {
	}

	private File getRoot(File f) {
		if(f.getParentFile() == null) {
			return f;
		} else {
			return getRoot(f.getParentFile());
		}
	}

	@Override
	public void selectionChanged(File f) {
		lastSelected = f;
		for(ExplorerComponentListener l : listeners) {
			l.selectionChanged(new ExplorerComponentEvent(this));
		}
	}

	@Override
	public File getLastSelected() {
		return lastSelected;
	}

	@Override
	public ArrayList<ExplorerComponentListener> getExplorerComponentListeners() {
		return listeners;
	}
	
}
