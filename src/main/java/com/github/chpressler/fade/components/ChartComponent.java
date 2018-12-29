package com.github.chpressler.fade.components;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ChartComponent extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChartComponent() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		File f1 = new File("C:/");
		pieDataset.setValue("free", f1.getFreeSpace());
		pieDataset.setValue("occupied", f1.getTotalSpace()-f1.getFreeSpace());
//		pieDataset.setValue("useable", f1.getUsableSpace());
		JFreeChart chart = ChartFactory.createPieChart3D("Disk Usage", pieDataset, true, true, true);
		
		chart.getPlot().setForegroundAlpha(0.5f);
				
		ChartPanel panel = new ChartPanel(chart);
		add(panel);
		
//		BufferedImage image = chart.createBufferedImage(800,600);
//		File f = new File("chart.jpg");
//		f.createNewFile();
//		OutputStream os = new FileOutputStream(f);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//		encoder.encode(image);
		
		try {
			ChartUtilities.saveChartAsJPEG(new File("test.jpg"), chart, 100, 100);
		} catch (IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
		}
		setVisible(true);
	}
	
}
