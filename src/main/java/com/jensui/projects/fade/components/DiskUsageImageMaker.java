package com.jensui.projects.fade.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class DiskUsageImageMaker {
	
	private static final long serialVersionUID = 1L;

	private static volatile DiskUsageImageMaker instance = null;
	
	public static synchronized DiskUsageImageMaker getInstance() {
		if(instance == null) {
			synchronized (DiskUsageImageMaker.class) {
				if(instance == null) {
					instance = new DiskUsageImageMaker();
				}
			}
		}
		return instance;
	}
	
	public Icon getIcon(Dimension d, File f) throws IOException {
		
		long total = f.getTotalSpace();
		long free = f.getFreeSpace();
		long used = total - free;
		long percent = 0;
		if(total > 0) {
			percent = (used * 100) / total;
		}
		
//		Dimension d = new Dimension(dim.width/2 , dim.height - ((dim.height * 30) / 100));
		
	    BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = bi.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, d.width, d.height/2);
		g2d.setColor(new Color(0, 200, 0));
		g2d.fillRect(0, d.height/2, d.width, d.height/2);
		g2d.setColor(Color.red);
		g2d.fillRect(0, 0, (d.width * (int) percent) / 100, d.height);
		g2d.setColor(new Color(200, 0, 0));
		g2d.fillRect(0, d.height/2, (d.width * (int) percent) / 100, d.height/2);
		g2d.setColor(Color.black);
//		g2d.drawRoundRect(0, 0, d.width-1, d.height-1, 50, 50);
		Font font = new Font("Arial", Font.BOLD, 10);
		g2d.setFont(font);
		float fl = (d.height - g2d.getFontMetrics(font).getHeight() / 2f);
		g2d.drawString(f.getAbsolutePath(), 10f, fl);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		Icon icon = new ImageIcon(bi);
		
		return icon;
	}
	
	public BufferedImage getImage(Dimension dim, File f) throws IOException {
		
		long total = f.getTotalSpace();
		long free = f.getFreeSpace();
		long used = total - free;
		long percent = 0;
		if(total > 0) {
			percent = (used * 100) / total;
		}
		
		Dimension d = new Dimension(dim.width/2 , dim.height - ((dim.height * 30) / 100));
		
	    BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = bi.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, d.width, d.height/2);
		g2d.setColor(new Color(0, 200, 0));
		g2d.fillRect(0, d.height/2, d.width, d.height/2);
		g2d.setColor(Color.red);
		g2d.fillRect(0, 0, (d.width * (int) percent) / 100, d.height);
		g2d.setColor(new Color(200, 0, 0));
		g2d.fillRect(0, d.height/2, (d.width * (int) percent) / 100, d.height/2);
		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, d.width-1, d.height-1);
		Font font = new Font("Arial", Font.BOLD, 10);
		g2d.setFont(font);
		float fl = (d.height - g2d.getFontMetrics(font).getHeight() / 2f);
		g2d.drawString(f.getAbsolutePath(), 10f, fl);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		return bi;
	}
	
}