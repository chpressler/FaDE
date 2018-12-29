package com.github.chpressler.fade.components;

import javax.swing.*;
import java.awt.*;

class FaDEButton extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private final String text;
	
	public FaDEButton() {
		this("");
	}
	
	private FaDEButton(String s) {
		this.text = s;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(text, 0, 0);
	}
	
}
