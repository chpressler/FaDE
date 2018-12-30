package com.github.chpressler.fade.components;

import com.github.chpressler.fade.ConnectorManager;
import com.github.chpressler.fade.IConnector;
import com.github.chpressler.fade.IFile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DriveSelectComponent extends JPanel implements ExplorerComponentListener {

	private static final long serialVersionUID = 1L;
	private final FaDEComponent c;
	private final List<DriveButton> buttons;
    private int rootCount;
    private DriveButton currentSelected;

    private int getRootCount() {
       return getRootFiles().size();
    }

    private boolean rootCountChanged() {
        if(rootCount < 0) {
            rootCount = getRootCount();
            return false;
        }
        int rc = getRootCount();
        if(rootCount != rc) {
           rootCount = rc;
           return true;
        }
        return false;
    }

    private List<IFile> getRootFiles() {
        List<IFile> roots = new ArrayList<>();
        for(IConnector c : ConnectorManager.getInstance().getConnectors()) {
            roots.addAll(c.getRootFiles());
        }
        return roots;
    }

    private void init() {
		this.removeAll();
		setOpaque(false);
        LayoutManager layout = new GridLayout(2, 0, 5, 5);
		setLayout(layout);
		buttons.clear();
        ButtonGroup bg = new ButtonGroup();
		for(IFile f : getRootFiles()) {
			DriveButton b = new DriveButton(f);
			buttons.add(b);
			bg.add(b);
			b.setToolTipText(FileSystemView.getFileSystemView().getSystemDisplayName(f.getFile()));
			b.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f.getFile()));
			b.addActionListener(e -> {
                if(!((DriveButton) e.getSource()).getFile().exists()) {
                    currentSelected.setSelected(true);
                    return;
                }
                currentSelected = (DriveButton) e.getSource();
                c.getIExplorerComponent().setCurrentDirectory(((DriveButton) e.getSource()).getFile());
                c.getIExplorerComponent().setRoot(((DriveButton) e.getSource()).getFile());
            });
			add(b);
		}
		for(DriveButton tb : buttons) {
			if(tb.getFile().exists()) {
				tb.setSelected(true);
				currentSelected = tb;
				break;
			}
		}
        updateUI();
	}
	
	public IFile getSelectedRoot() {
		for(DriveButton tb : buttons) {
			if(tb.isSelected()) {
			    return tb.getFile();
			}
		}
		return null;
	}
	
	public DriveSelectComponent(FaDEComponent c) {
        buttons = new ArrayList<DriveButton>();
        rootCount = -1;
//		setLayout(new FlowLayout());
		this.c = c;
		init();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(rootCountChanged()) {
                    init();
                }
            }
        }).start();
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
				b.repaint();
				return;
			}
		}
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
	}
	
}