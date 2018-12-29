package com.github.chpressler.fade.components;

import javax.swing.*;
import java.awt.*;

public class FaDEStatusBar extends JPanel implements ExplorerComponentListener {

	private static final long serialVersionUID = 1L;
	
	private final JLabel leftroot;
	
	private final JLabel rightroot;

    private final IExplorerComponent left;
	private final IExplorerComponent right;

	public FaDEStatusBar(IExplorerComponent left, IExplorerComponent right) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new FlowLayout());
		this.left = left;
		this.right = right;
		left.addExplorerComponentListener(this);
		right.addExplorerComponentListener(this);
		leftroot = new JLabel();
        JLabel middle = new JLabel("<->");
		rightroot = new JLabel();
		add(leftroot);
		add(middle);
		add(rightroot);
	}
	
	@Override
	public void paint(Graphics g) {
		if(getParent() != null) {
			Dimension d = getParent().getSize();
			d.height = 20;
			setPreferredSize(d);
		}
		super.paint(g);
	}

	@Override
	public void currentDirectoryPathChanged(ExplorerComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rootChanged(ExplorerComponentEvent e) {
		leftroot.setText(left.getRoot().getURI().getPath());
		rightroot.setText(right.getRoot().getURI().getPath());
		repaint();
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
	}
	
}
