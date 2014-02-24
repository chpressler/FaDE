package biz.pressler.myfade.components;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class DriveSelectComponent extends JPanel implements ExplorerComponentListener {

	private static final long serialVersionUID = 1L;
	
	private FaDEComponent c;
	
	private ArrayList<DriveButton> buttons = new ArrayList<DriveButton>();
	
	private ButtonGroup bg;
	
	private DriveButton currentSelected;
	
	private LayoutManager layout = null;
	
	private void init() {
		this.removeAll();
		setOpaque(false);
		layout = new GridLayout(2, 0, 5, 5);
		setLayout(layout);
		buttons.clear();
		bg = new ButtonGroup();
		for(File f : File.listRoots()) {
			DriveButton b = new DriveButton(f);
			buttons.add(b);
			bg.add(b);
			b.setToolTipText(FileSystemView.getFileSystemView().getSystemDisplayName(f));
			b.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!((DriveButton) e.getSource()).getFile().exists()) {
						currentSelected.setSelected(true);
						return;
					}
					currentSelected = (DriveButton) e.getSource();
					c.getIExplorerComponent().setCurrentDirectory(((DriveButton) e.getSource()).getFile());	
					c.getIExplorerComponent().setRoot(((DriveButton) e.getSource()).getFile());	
				}});
			add(b);
		}
		for(DriveButton tb : buttons) {
			if(tb.getFile().exists()) {
				tb.setSelected(true);
				currentSelected = tb;
				break;
			}
		}
		
	}
	
	public File getSelectedRoot() {
		for(DriveButton tb : buttons) {
			if(tb.isSelected()) {
			return tb.getFile();
			}
		}
		return null;
	}
	
	public DriveSelectComponent(FaDEComponent c) {
//		setLayout(new FlowLayout());
		this.c = c;
		init();
	}

	@Override
	public void currentDirectoryPathChanged(ExplorerComponentEvent e) {
		setDriveButtonSelected();
	}

	@Override
	public void rootChanged(ExplorerComponentEvent e) {
		setDriveButtonSelected();
	}
	
	private void setDriveButtonSelected() {
		for(DriveButton b : buttons) {
			if(b.getFile().equals(c.getIExplorerComponent().getRoot())) {
				b.setSelected(true);
				repaint();
				return;
			}
		}
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
	}
	
}