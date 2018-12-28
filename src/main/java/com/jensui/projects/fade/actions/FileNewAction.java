package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FileNewAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent e) {
		IFile f = FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getCurrentDirectory();

		String fileName = JOptionPane.showInputDialog("FileName");

		try {
			if(!FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getRoot().getConnector().getCreateCommand().createFile(fileName, f)) {
				throw new Exception("File could not be created");
			}
		} catch (Exception e1) {
			final JDialog jd = new JDialog();
			JOptionPane.showMessageDialog(jd,
					e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("about.jpg"));
		}
		FaDE.getInstance().repaint();
	}

}
