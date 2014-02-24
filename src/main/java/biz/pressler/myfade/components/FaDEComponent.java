package biz.pressler.myfade.components;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class FaDEComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private DriveSelectComponent dsc = null;
	
	private ViewSwitcherComponent vsc = null;
	
	private URLComponent urlc = null;
	
	private IExplorerComponent fileComponent = null;
	
	private LayoutManager layout = null;
	
	private boolean isSelected = false;
	
	private ArrayList<FaDEComponentSelectionListener> listeners = new ArrayList<FaDEComponentSelectionListener>();
	
	public void addFaDEComponentSelectionListener(FaDEComponentSelectionListener listener) {
		listeners.add(listener);
	}
	
	public void removeFaDEComponentSelectionListener(FaDEComponentSelectionListener listener) {
		listeners.remove(listener);
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
		if (isSelected) {
			for(FaDEComponentSelectionListener l : listeners) {
				l.selected(this);
			}
//			setBorder(BorderFactory.createEtchedBorder(Color.black, Color.gray));
//			setBackground(new Color(getBackground().getRed() + 20, getBackground().getGreen(), getBackground().getBlue()));
			urlc.setSelected(true);
		} else {
//			setBorder(null);
//			setBackground(new Color(getBackground().getRed() - 20, getBackground().getGreen(), getBackground().getBlue()));
			urlc.setSelected(false);
		}
//		repaint();
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public FaDEComponent() {
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setSelected(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				setSelected(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		urlc = new URLComponent(this);
		vsc = new ViewSwitcherComponent(this);
		dsc = new DriveSelectComponent(this);
		fileComponent = new FileTableComponent(urlc, dsc);
		layout = new MigLayout("flowy", "0[grow,fill,center]",
				"[][][][grow,fill]");
		setLayout(layout);
		add(dsc);
		add(vsc);
		add(urlc);
		add((Component) fileComponent);
	}
	
	public void setFileComponent(IExplorerComponent fc) {
		this.remove((Component) fileComponent);
		this.fileComponent = fc;
		add((Component) fileComponent, "cell 0 3");
		this.revalidate();
	}
	
	public IExplorerComponent getIExplorerComponent() {
		return fileComponent;
	}
	
	public URLComponent getURLComponent() {
		return urlc;
	}
	
	public ViewSwitcherComponent getViewSwitcherComponent() {
		return vsc;
	}
	
	public DriveSelectComponent getDriveSelectComponent() {
		return dsc;
	}
	
}
