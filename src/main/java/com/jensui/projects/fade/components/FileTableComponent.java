package com.jensui.projects.fade.components;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.controller.FileTableController;
import com.jensui.projects.fade.model.FileTableModel;
import net.miginfocom.swing.MigLayout;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.util.ArrayList;

public class FileTableComponent extends JPanel implements IExplorerComponent {

	private static final long serialVersionUID = 1L;

	private JTable view;

    private IFile root;
	
	private IFile currentDir;
	
	private IFile lastSelected = null;

    private final ArrayList<ExplorerComponentListener> listeners = new ArrayList<ExplorerComponentListener>();
	
	public FileTableComponent(URLComponent urlc, DriveSelectComponent dsc) {
//		root = File.listRoots()[0];
		root = dsc.getSelectedRoot();
		currentDir = root;
        FileTableModel model = new FileTableModel(this);
		view = new JTable(model);
		view.setShowGrid(false);
		view.setDragEnabled(true);
		view.setDropMode(DropMode.INSERT_ROWS);
		view.setTransferHandler(new TableRowTransferHandler(view));
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

        FileTableController controller = new FileTableController(this);
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
        LayoutManager layout = new MigLayout("flowy", "0[grow,fill,center]",
                "[c,grow,fill]");
		this.setLayout(layout);
		this.add(jsp);
	}
	
	public JTable getView() {
        return view;
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
		IFile[] sel = new IFile[view.getSelectedRowCount()];
		int n = 0;
		for(int i : view.getSelectedRows()) {
			sel[n] = ((IFile) view.getModel().getValueAt(i, 0));
			n++;
		}
		return sel;
	}

	@Override
	public void setCurrentDirectory(IFile f) {
		if(!f.isDir()) {
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

	private IFile getRoot(IFile f) {
		if(f.getParent() == null) {
			return f;
		} else {
			return getRoot(f.getParent());
		}
	}

	@Override
	public void selectionChanged(IFile f) {
		lastSelected = f;
		for(ExplorerComponentListener l : listeners) {
			l.selectionChanged(new ExplorerComponentEvent(this));
		}
	}

	@Override
	public IFile getLastSelected() {
		return lastSelected;
	}

	@Override
	public ArrayList<ExplorerComponentListener> getExplorerComponentListeners() {
		return listeners;
	}
	
}

class TableRowTransferHandler extends TransferHandler {
	private final DataFlavor localObjectFlavor = new ActivationDataFlavor(Integer.class, "application/x-java-Integer;class=java.lang.Integer", "Integer Row Index");
	private JTable           table             = null;

	public TableRowTransferHandler(JTable table) {
		this.table = table;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		assert (c == table);
		return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport info) {
		boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
		table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
		return b;
	}

	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY_OR_MOVE;
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport info) {
		JTable target = (JTable) info.getComponent();
		JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
		int index = dl.getRow();
		int max = table.getModel().getRowCount();
		if (index < 0 || index > max)
			index = max;
		target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		try {
			Integer rowFrom = (Integer) info.getTransferable().getTransferData(localObjectFlavor);
			if (rowFrom != -1 && rowFrom != index) {
//				((Reorderable)table.getModel()).reorder(rowFrom, index);
//				if (index > rowFrom)
//					index--;
//				target.getSelectionModel().addSelectionInterval(index, index);
//				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected void exportDone(JComponent c, Transferable t, int act) {
		if ((act == TransferHandler.MOVE) || (act == TransferHandler.NONE)) {
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

}
