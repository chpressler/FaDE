package biz.pressler.myfade.components;

import java.awt.Graphics;

import javax.swing.JComponent;

public class FaDEButton extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public FaDEButton() {
		this("");
	}
	
	public FaDEButton(String s) {
		this.text = s;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(text, 0, 0);
	}
	
}
