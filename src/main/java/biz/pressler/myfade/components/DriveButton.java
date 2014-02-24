package biz.pressler.myfade.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JToggleButton;

public class DriveButton extends JToggleButton {
	
	private static final long serialVersionUID = 1L;
	
	private File f;
	
	private boolean isPressed = false;
	
	private boolean mouseOver = false;
	
	private int arcHeight = 10;
	
	private Color freeSpaceUpperSelected;
	
	private Color freeSpaceLowerSelected;
	
	private Color freeSpaceUpper;
	
	private Color freeSpaceLower;
	
	private Color occSpaceUpperSelected;
	
	private Color occSpaceLowerSelected;
	
	private Color occSpaceUpper;
	
	private Color occSpaceLower;
	
	public DriveButton(File f) {
		super(f.getAbsolutePath());
		this.f = f;
		
		freeSpaceUpper = new Color(200, 200, 200);
		freeSpaceLower = new Color(180, 180, 180);
		freeSpaceUpperSelected = new Color(10, 255, 10);
		freeSpaceLowerSelected = new Color(10, 200, 10);
		occSpaceUpper = new Color(150, 150, 150);
		occSpaceLower = new Color(130, 130, 130);
		occSpaceUpperSelected = new Color(255, 10, 10);
		occSpaceLowerSelected = new Color(200, 10, 10);
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOver = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				isPressed = true;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				isPressed = false;
				repaint();
			}});
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}});
	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if(isSelected()) {
			long total = f.getTotalSpace();
			long free = f.getFreeSpace();
			long used = total - free;
			long percent = 0;
			if(total > 0) {
				percent = (used * 100) / total;
			}
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		    g2d.setColor(mouseOver ? new Color(freeSpaceUpperSelected.getRed() - 10, freeSpaceUpperSelected.getGreen() - 10, freeSpaceUpperSelected.getBlue() - 10) : freeSpaceUpperSelected);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight()/4, getWidth(), getHeight()/4);
			g2d.setColor(mouseOver ? new Color(freeSpaceLowerSelected.getRed() - 10, freeSpaceLowerSelected.getGreen() - 10, freeSpaceLowerSelected.getBlue() - 10) : freeSpaceLowerSelected);
			g2d.fillRoundRect(0, getHeight()/2, getWidth(), getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight()/4);
			g2d.setColor(mouseOver ? new Color(occSpaceUpperSelected.getRed() - 10, occSpaceUpperSelected.getGreen() - 10, occSpaceUpperSelected.getBlue() - 10) : occSpaceUpperSelected);
			g2d.fillRoundRect(0, 0, (getWidth() * (int) percent) / 100, getHeight() / 2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight()/4, (getWidth() * (int) percent) / 100, getHeight()/4);
			g2d.setColor(mouseOver ? new Color(occSpaceLowerSelected.getRed() - 10, occSpaceLowerSelected.getGreen() - 10, occSpaceLowerSelected.getBlue() - 10) : occSpaceLowerSelected);
			g2d.fillRoundRect(0, getHeight()/2, (getWidth() * (int) percent) / 100, getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight() / 2, (getWidth() * (int) percent) / 100, getHeight()/4);
			g2d.setColor(Color.black);
//			g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcHeight, arcHeight);
			Font font = new Font("Arial", Font.BOLD, 15);
			g2d.setFont(font);
			float fl = (getHeight() - g2d.getFontMetrics(font).getHeight() / 2f);
			g2d.drawString(f.getAbsolutePath(), 10f, fl);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));	
		} else {
			long total = f.getTotalSpace();
			long free = f.getFreeSpace();
			long used = total - free;
			long percent = 0;
			if(total > 0) {
				percent = (used * 100) / total;
			}
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g2d.setColor(mouseOver ? new Color(freeSpaceUpper.getRed() - 10, freeSpaceUpper.getGreen() - 10, freeSpaceUpper.getBlue() - 10) : freeSpaceUpper);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight()/4, getWidth(), getHeight()/4);
			g2d.setColor(mouseOver ? new Color(freeSpaceLower.getRed() - 10, freeSpaceLower.getGreen() - 10, freeSpaceLower.getBlue() - 10) : freeSpaceLower);
			g2d.fillRoundRect(0, getHeight()/2, getWidth(), getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight()/4);
			g2d.setColor(mouseOver ? new Color(occSpaceUpper.getRed() - 10, occSpaceUpper.getGreen() - 10, occSpaceUpper.getBlue() - 10) : occSpaceUpper);
			g2d.fillRoundRect(0, 0, (getWidth() * (int) percent) / 100, getHeight() / 2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight()/4, (getWidth() * (int) percent) / 100, getHeight()/4);
			g2d.setColor(mouseOver ? new Color(occSpaceLower.getRed() - 10, occSpaceLower.getGreen() - 10, occSpaceLower.getBlue() - 10) : occSpaceLower);
			g2d.fillRoundRect(0, getHeight()/2, (getWidth() * (int) percent) / 100, getHeight()/2, arcHeight, arcHeight);
			g2d.fillRect(0, getHeight() / 2, (getWidth() * (int) percent) / 100, getHeight()/4);
			g2d.setColor(Color.black);
//			g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcHeight, arcHeight);
			Font font = new Font("Arial", Font.BOLD, 15);
			g2d.setFont(font);
			float fl = (getHeight() - g2d.getFontMetrics(font).getHeight() / 2f);
			g2d.drawString(f.getAbsolutePath(), 10f, fl);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		}
		if(mouseOver) {
			
		}
	}

	public File getFile() {
		return f;
	}

	public void setFile(File f) {
		this.f = f;
	}
	
}
