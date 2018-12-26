package com.jensui.projects.fade;

import com.jensui.projects.fade.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class FaDE extends JFrame implements FaDEComponentSelectionListener {

	private static final long serialVersionUID = 1L;
	private JSplitPane jsp;
	private final FaDEComponent left;
	private final FaDEComponent right;

	public enum OSType {MS, UNIX, UNKNOWN}

	private static volatile FaDE instance;
	
	public static synchronized FaDE getInstance() {
		if(instance == null) {
			synchronized (FaDE.class) {
				if(instance == null) {
					instance = new FaDE();
				}
			}
		}
		return instance;
	}
	
	private FaDE() {
		super("FaDE");
		LayoutManager layout = new BorderLayout();
		setLayout(layout);
//		SystemTray tray = SystemTray.getSystemTray();
//		TrayIcon ti = new TrayIcon(image, "FaDE");
//		ti.setToolTip("FaDE");
//		ti.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				this.setVisible(true);
//			}}); {
//		}
//		try {
//			tray.add(ti);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		PopupMenu menu = new PopupMenu();
//		MenuItem close = new MenuItem("close");
//		close.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}});
//		menu.add(close);
//		ti.setPopupMenu(menu);
		
		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
//			UIManager.setLookAndFeel();
//			UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
//			SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
		} catch (Exception e1) {
			final JDialog jd = new JDialog();
			JOptionPane.showMessageDialog(jd, e1.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
		}
		setSize(800, 600);
		setExtendedState(MAXIMIZED_BOTH);
		
		addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				
			}
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			@Override
			public void componentResized(ComponentEvent e) {
				jsp.setDividerLocation(getWidth() / 2);
			}
			@Override
			public void componentShown(ComponentEvent e) {
				
			}});
		
		add(new FaDEToolBar(), BorderLayout.NORTH);
		this.getRootPane().setJMenuBar(new FaDEMenu(this));
		right = new FaDEComponent();
		right.addFaDEComponentSelectionListener(this);
		left = new FaDEComponent();
		left.addFaDEComponentSelectionListener(this);
		left.setSelected(true);
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		add(jsp, BorderLayout.CENTER);
		ActionsComponent actionscomponent = new ActionsComponent();
//		add(actionscomponent, BorderLayout.SOUTH);
		FaDEStatusBar statusbar = new FaDEStatusBar(left.getIExplorerComponent(), right.getIExplorerComponent());
//		add(statusbar, BorderLayout.SOUTH);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(actionscomponent, BorderLayout.NORTH);
        southPanel.add(statusbar, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
		jsp.setBorder(BorderFactory.createEmptyBorder()); 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = dim.width;
		jsp.setDividerLocation(w / 2);
		jsp.setOneTouchExpandable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
		
		/*try {
			setIconImage(ImageIO.read(new File(FaDE.class.getClassLoader().getResource("myFaDE.jpg").toString())));
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	}
	
	public FaDEComponent getSelectedFaDEComponent() {
		if(left.isSelected()) {
			return left;
		} else {
			return right;
		}
	}
	
	public FaDEComponent getUnselectedFaDEComponent() {
		if(left.isSelected()) {
			return right;
		} else {
			return left;
		}
	}
	
	public static void main(String[] args) {

//		Thread.setDefaultUncaughtExceptionHandler(new FaDEUncaughtExceptionHandler()); 

//		final JWindow jw = new JWindow();
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		int h = dim.height;
//		int w = dim.width;
//		JLabel label = new JLabel(new ImageIcon(FaDE.class.getClassLoader().getResource("myFaDE.jpg")));
//		jw.getContentPane().add(label);
//		jw.setBounds(w / 2 - 250, h / 2 - 150, 500, 300);
//		jw.setVisible(true);
////		JFrame.setDefaultLookAndFeelDecorated(true);
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				FaDE.getInstance();
//				jw.setVisible(false);
//				jw.dispose();
//			}});

		FaDE.getInstance();
	}

	@Override
	public void selected(FaDEComponent fc) {
		if(left == fc) {
			right.setSelected(false);
		} else {
			left.setSelected(false);
		}
	}

    public static OSType getOSType() {
        String os = System.getProperty("os.name", "").toLowerCase();
        if (os.startsWith("windows")) {
            return OSType.MS;
        } else if (os.startsWith("linux") || os.startsWith("mac") || os.startsWith("darwin")) {
            return OSType.UNIX;
        } else {
            return OSType.UNKNOWN;
        }
    }
	
}
