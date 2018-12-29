package com.github.chpressler.fade.components;

import com.github.chpressler.fade.IFile;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class URLComponent extends JPanel implements ExplorerComponentListener {
	
	private static final long serialVersionUID = 1L;

	private FaDEComponent fadeComponent;
	
	private JTextField textField;

    public URLComponent(FaDEComponent fc) {
		setOpaque(false);
		fadeComponent = fc;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
        LayoutManager layout = new MigLayout("", "[grow,fill][pref!]", "0[]");
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
					IFile f = fadeComponent.getIExplorerComponent().getCurrentDirectory().getConnector().getFile(textField.getText());
					if (f != null) {
						if(!getRoot(f).getURI().getPath().equals(fadeComponent.getIExplorerComponent().getRoot().getURI().getPath())) {
							fadeComponent.getIExplorerComponent().setRoot(f);
						}
						fadeComponent.getIExplorerComponent().setCurrentDirectory(f);
					} else {
						textField.setText(fadeComponent.getIExplorerComponent().getCurrentDirectory().getURI().getPath());
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}});
        JButton button = new JButton("go");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IFile f = fadeComponent.getIExplorerComponent().getCurrentDirectory().getConnector().getFile(textField.getText());
				if (f != null) {
					if(!getRoot(f).getURI().getPath().equals(fadeComponent.getIExplorerComponent().getRoot().getURI().getPath())) {
						fadeComponent.getIExplorerComponent().setRoot(f);
					}
					fadeComponent.getIExplorerComponent().setCurrentDirectory(f);
				} else {
					textField.setText(fadeComponent.getIExplorerComponent().getCurrentDirectory().getURI().getPath());
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
	
	private IFile getRoot(IFile f) {
		if(f.getParent() == null) {
			return f;
		} else {
			return getRoot(f.getParent());
		}
	}

	@Override
	public void currentDirectoryPathChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getCurrentDirectory().getURI().getPath());
	}

	@Override
	public void rootChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getRoot().getURI().getPath());
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
		textField.setText(((IExplorerComponent) e.getSource()).getLastSelected().getURI().getPath());
	}

}
