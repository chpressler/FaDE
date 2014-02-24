package biz.pressler.myfade.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FaDEStatusBar extends JPanel implements ExplorerComponentListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel leftroot;
	
	private JLabel rightroot;
	
	private JLabel middle;
	
	private IExplorerComponent left, right;

	public FaDEStatusBar(IExplorerComponent left, IExplorerComponent right) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new FlowLayout());
		this.left = left;
		this.right = right;
		leftroot = new JLabel();
		middle = new JLabel("<->");
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
		leftroot.setText(left.getRoot().getAbsolutePath());
		rightroot.setText(right.getRoot().getAbsolutePath());
		repaint();
	}

	@Override
	public void selectionChanged(ExplorerComponentEvent e) {
	}
	
}
