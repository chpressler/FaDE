package com.jensui.projects.myfade.components;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class URLComponent extends JPanel implements ExplorerComponentListener {
	
	private static final long serialVersionUID = 1L;

	private FaDEComponent fadeComponent = null;
	
	private JTextField textField = null;
	
	private JButton button = null;
	
	private LayoutManager layout = null;
	
	public URLComponent(FaDEComponent fc) {
		setOpaque(false);
		fadeComponent = fc;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		layout = new MigLayout("", "[grow,fill][pref!]", "0[]");
//		fc.getIExplorerComponent().addExplorerComponentListener(this);
//		fadeComponent.getIExplorerComponent().getCurrentDirectory().getAbsolutePath()
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					File f = new File(textField.getText());
					if (f.exists()) {
						if(getRoot(f).getAbsolutePath() != fadeComponent.getIExplorerComponent().getRoot().getAbsolutePath()) {
							fadeComponent.getIExplorerComponent().setRoot(f);
						}
						fadeComponent.getIExplorerComponent().setCurrentDirectory(f);
					} else {
						textField.setText(fadeComponent.getIExplorerComponent().getCurrentDirectory().getAbsolutePath());
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}});
		button = new JButton("go");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = new File(textField.getText());
				if (f.exists()) {
					if(getRoot(f).getAbsolutePath() != fadeComponent.getIExplorerComponent().getRoot().getAbsolutePath()) {
						fadeComponent.getIExplorerComponent().setRoot(f);
					}
					fadeComponent.getIExplorerComponent().setCurrentDirectory(f);
				} else {
					textField.setText(fadeComponent.getIExplorerComponent().getCurrentDirectory().getAbsolutePath());
				}
			}});
		setLayout(layout);
		add(textField, "growx");
		add(button);
//		panel.add(comp, "w 40!"); // w is short for width.
//		add(textField);
//		add(button);
	}
	
	public void setSelected(boolean b) {
		if(b) {
			textField.setBackground(new Color(255, 212, 85));
		} else {
			textField.setBackground(Color.white);
		}
		textField.repaint();
	}
	
	public JTextField getURLTextField() {
		return textField;
	}
	
	private File getRoot(File f) {
		if(f.getParentFile() == null) {
			return f;
		} else {
			return getRoot(f.getParentFile());
		}
	}

	@Override
	public void currentDirectoryPathChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getCurrentDirectory().getAbsolutePath());
	}

	@Override
	public void rootChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getRoot().getAbsolutePath());
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getLastSelected().getAbsolutePath());
	}

}
