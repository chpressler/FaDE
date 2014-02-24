package biz.pressler.myfade.components;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.UIResource;
import javax.swing.tree.TreeCellRenderer;

public class FileTreeCellRenderer extends JLabel implements TreeCellRenderer {

	private static final long serialVersionUID = 1L;

	protected boolean selected;

	protected boolean hasFocus;

	private boolean drawsFocusBorderAroundIcon;

	protected transient Icon closedIcon;

	protected transient Icon leafIcon;

	protected transient Icon openIcon;

	protected Color textSelectionColor;

	protected Color textNonSelectionColor;

	protected Color backgroundSelectionColor;

	protected Color backgroundNonSelectionColor;

	protected Color borderSelectionColor;

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
				&& ((Boolean) val).booleanValue();
	}

	public Icon getDefaultOpenIcon() {
		return UIManager.getIcon("Tree.openIcon");
	}

	public Icon getDefaultClosedIcon() {
		return UIManager.getIcon("Tree.closedIcon");
	}

	public Icon getDefaultLeafIcon() {
		return UIManager.getIcon("Tree.leafIcon");
	}

	public void setOpenIcon(Icon icon) {
		openIcon = icon;
	}

	public Icon getOpenIcon() {
		return openIcon;
	}

	public void setClosedIcon(Icon icon) {
		closedIcon = icon;
	}

	public Icon getClosedIcon() {
		return closedIcon;
	}

	public void setLeafIcon(Icon icon) {
		leafIcon = icon;
	}

	public Icon getLeafIcon() {
		return leafIcon;
	}

	public void setTextSelectionColor(Color c) {
		textSelectionColor = c;
	}

	public Color getTextSelectionColor() {
		return textSelectionColor;
	}

	public void setTextNonSelectionColor(Color c) {
		textNonSelectionColor = c;
	}

	public Color getTextNonSelectionColor() {
		return textNonSelectionColor;
	}

	public void setBackgroundSelectionColor(Color c) {
		backgroundSelectionColor = c;
	}

	public Color getBackgroundSelectionColor() {
		return backgroundSelectionColor;
	}

	public void setBackgroundNonSelectionColor(Color c) {
		backgroundNonSelectionColor = c;
	}

	public Color getBackgroundNonSelectionColor() {
		return backgroundNonSelectionColor;
	}

	public void setBorderSelectionColor(Color c) {
		borderSelectionColor = c;
	}

	public Color getBorderSelectionColor() {
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
			setIcon( FileSystemView.getFileSystemView().getSystemIcon( ((File) val) ));
		else if (expanded)
			setIcon(getOpenIcon());
		else
			setIcon(getClosedIcon());
		
		try {
			setText( ((File) val).getAbsoluteFile().getName());
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

	public Font getFont() {
		return super.getFont();
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
			paintFocus(g, xOffset, 0, getWidth() - xOffset, getHeight());
		}
		super.paint(g);
	}

	private void paintFocus(Graphics g, int x, int y, int w, int h) {
		Color col = getBorderSelectionColor();
		if (col != null) {
			g.setColor(col);
			g.drawRect(x, y, w - 1, h - 1);
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