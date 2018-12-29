package com.github.chpressler.fade.components;

import com.github.chpressler.fade.IFile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.UIResource;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileTreeCellRenderer extends JLabel implements TreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private boolean selected;

	private boolean hasFocus;

	private final boolean drawsFocusBorderAroundIcon;

	private transient Icon closedIcon;

	private transient Icon leafIcon;

	private transient Icon openIcon;

	private Color textSelectionColor;

	private Color textNonSelectionColor;

	private Color backgroundSelectionColor;

	private Color backgroundNonSelectionColor;

	private Color borderSelectionColor;

	public FileTreeCellRenderer() {
		setLeafIcon(getDefaultLeafIcon());
		setOpenIcon(getDefaultOpenIcon());
		setClosedIcon(getDefaultClosedIcon());

		setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
		setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
		setBackgroundNonSelectionColor(UIManager
				.getColor("Tree.textBackground"));
		setBackgroundSelectionColor(UIManager
				.getColor("Tree.selectionBackground"));
		setBorderSelectionColor(UIManager.getColor("Tree.selectionBorderColor"));
		Object val = UIManager.get("Tree.drawsFocusBorderAroundIcon");
		drawsFocusBorderAroundIcon = val != null
				&& (Boolean) val;
	}

	private Icon getDefaultOpenIcon() {
		return UIManager.getIcon("Tree.openIcon");
	}

	private Icon getDefaultClosedIcon() {
		return UIManager.getIcon("Tree.closedIcon");
	}

	private Icon getDefaultLeafIcon() {
		return UIManager.getIcon("Tree.leafIcon");
	}

	private void setOpenIcon(Icon icon) {
		openIcon = icon;
	}

	private Icon getOpenIcon() {
		return openIcon;
	}

	private void setClosedIcon(Icon icon) {
		closedIcon = icon;
	}

	private Icon getClosedIcon() {
		return closedIcon;
	}

	private void setLeafIcon(Icon icon) {
		leafIcon = icon;
	}

	public Icon getLeafIcon() {
		return leafIcon;
	}

	private void setTextSelectionColor(Color c) {
		textSelectionColor = c;
	}

	private Color getTextSelectionColor() {
		return textSelectionColor;
	}

	private void setTextNonSelectionColor(Color c) {
		textNonSelectionColor = c;
	}

	private Color getTextNonSelectionColor() {
		return textNonSelectionColor;
	}

	private void setBackgroundSelectionColor(Color c) {
		backgroundSelectionColor = c;
	}

	private Color getBackgroundSelectionColor() {
		return backgroundSelectionColor;
	}

	private void setBackgroundNonSelectionColor(Color c) {
		backgroundNonSelectionColor = c;
	}

	private Color getBackgroundNonSelectionColor() {
		return backgroundNonSelectionColor;
	}

	private void setBorderSelectionColor(Color c) {
		borderSelectionColor = c;
	}

	private Color getBorderSelectionColor() {
		return borderSelectionColor;
	}

	public void setFont(Font f) {
		if (f != null && f instanceof UIResource)
			f = null;
		super.setFont(f);
	}

	public void setBackground(Color c) {
		if (c != null && c instanceof UIResource)
			c = null;
		super.setBackground(c);
	}

	public Component getTreeCellRendererComponent(JTree tree, Object val,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (leaf)
//			setIcon(getLeafIcon());
			setIcon( FileSystemView.getFileSystemView().getSystemIcon( ((IFile) val).getFile() ));
		else if (expanded)
			setIcon(getOpenIcon());
		else
			setIcon(getClosedIcon());
		
		try {
			setText( ((IFile) val).getName());
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
		}
		
		this.selected = selected;
		this.hasFocus = hasFocus;
		setHorizontalAlignment(LEFT);
		setOpaque(false);
		setVerticalAlignment(CENTER);
		setEnabled(true);
		super.setFont(UIManager.getFont("Tree.font"));

		if (selected) {
			super.setBackground(getBackgroundSelectionColor());
			setForeground(getTextSelectionColor());

			if (hasFocus)
				setBorderSelectionColor(UIManager.getLookAndFeelDefaults()
						.getColor("Tree.selectionBorderColor"));
			else
				setBorderSelectionColor(null);
		} else {
			super.setBackground(getBackgroundNonSelectionColor());
			setForeground(getTextNonSelectionColor());
			setBorderSelectionColor(null);
		}
		
		return this;
	}

	public void paint(Graphics g) {

		Color bgColor;
		if (selected)
			bgColor = getBackgroundSelectionColor();
		else {
			bgColor = getBackgroundNonSelectionColor();
			if (bgColor == null)
				bgColor = getBackground();
		}
		// Paint background.
		int xOffset = -1;
		if (bgColor != null) {
			//@SuppressWarnings("unused")
			Icon i = getIcon();
			xOffset = getXOffset();
			g.setColor(bgColor);
			g.fillRect(xOffset, 0, getWidth() - xOffset, getHeight());
		}

		if (hasFocus) {
			if (drawsFocusBorderAroundIcon)
				xOffset = 0;
			else if (xOffset == -1)
				xOffset = getXOffset();
			paintFocus(g, xOffset, getWidth() - xOffset, getHeight());
		}
		super.paint(g);
	}

	private void paintFocus(Graphics g, int x, int w, int h) {
		Color col = getBorderSelectionColor();
		if (col != null) {
			g.setColor(col);
			g.drawRect(x, 0, w - 1, h - 1);
		}
	}

	private int getXOffset() {
		Icon i = getIcon();
		int offs = 0;
		if (i != null && getText() != null)
			offs = i.getIconWidth() + Math.max(0, getIconTextGap() - 1);
		return offs;
	}

	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.width += 3;
		return size;
	}

	public void validate() {

	}

	public void revalidate() {

	}

	public void repaint(long tm, int x, int y, int width, int height) {

	}

	public void repaint(Rectangle area) {

	}

	protected void firePropertyChange(String name, Object oldValue,
			Object newValue) {

	}

	public void firePropertyChange(String name, byte oldValue, byte newValue) {

	}

	public void firePropertyChange(String name, char oldValue, char newValue) {

	}

	public void firePropertyChange(String name, short oldValue, short newValue) {

	}

	public void firePropertyChange(String name, int oldValue, int newValue) {

	}

	public void firePropertyChange(String name, long oldValue, long newValue) {

	}

	public void firePropertyChange(String name, float oldValue, float newValue) {

	}

	public void firePropertyChange(String name, double oldValue, double newValue) {
		//  Overridden for performance reasons.
	}

	public void firePropertyChange(String name, boolean oldValue,
			boolean newValue) {
		//  Overridden for performance reasons.
	}

}