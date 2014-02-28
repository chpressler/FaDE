package biz.pressler.myfade.components;

import biz.pressler.myfade.FaDE;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class DriveSelectComponent extends JPanel implements ExplorerComponentListener {

	private static final long serialVersionUID = 1L;
	private FaDEComponent c;
	private List<DriveButton> buttons;
    private int rootCount;
	private ButtonGroup bg;
	private DriveButton currentSelected;
	private LayoutManager layout = null;

    private int getRootCount() {
        FaDE.OSType os = FaDE.getOSType();
        switch (os) {
            case UNIX:
                try {
                    int count = 0;
                    Process mountProcess = Runtime.getRuntime().exec("mount");
                    BufferedReader mountOutput = new BufferedReader(new InputStreamReader(mountProcess.getInputStream()));
                    while (true) {
                        count++;
                        String line = mountOutput.readLine();
                        if (line == null) {
                            break;
                        }
                    }
                    return count;
                } catch (Exception e) {
                    return File.listRoots().length;
                }
            case MS:
                // fall through
            default:
                return File.listRoots().length;
        }
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

    private File[] getRootFiles() {
        FaDE.OSType os = FaDE.getOSType();
        switch (os) {
            case UNIX:
                try {
                    Process mountProcess = Runtime.getRuntime().exec("mount");
                    BufferedReader mountOutput = new BufferedReader(new InputStreamReader(mountProcess.getInputStream()));
                    List<File> roots = new ArrayList<File>();
                    while (true) {
                        String line = mountOutput.readLine();
                        if (line == null) {
                            break;
                        }
                        String[] sa = line.split(" ");
                        File f = new File(sa[2]);
                        if(f.exists()) {
                            roots.add(f);
                        }
                    }
                    mountOutput.close();
                    File[] ra = new File[roots.size()];
                    return roots.toArray(ra);
                } catch (Exception e) {
                    Logger.getLogger(DriveSelectComponent.class.getName()).log(Level.SEVERE, e.getMessage());
                    return File.listRoots();
                }
            case MS:
                // fall through
            default:
                return File.listRoots();
        }
    }

    private void init() {
		this.removeAll();
		setOpaque(false);
		layout = new GridLayout(2, 0, 5, 5);
		setLayout(layout);
		buttons.clear();
		bg = new ButtonGroup();
		for(File f : getRootFiles()) {
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
        updateUI();
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
        buttons = new ArrayList<DriveButton>();
        rootCount = -1;
//		setLayout(new FlowLayout());
		this.c = c;
		init();
        new Thread(new Runnable() {
            @Override
            public void run() {
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