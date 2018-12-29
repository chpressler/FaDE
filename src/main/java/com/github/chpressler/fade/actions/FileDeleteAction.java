package com.github.chpressler.fade.actions;

import com.github.chpressler.fade.FaDE;
import com.github.chpressler.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FileDeleteAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (IFile file : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            try {
                file.getConnector().getDeleteCommand().delete(file);
            } catch (Exception e1) {
                e1.printStackTrace();
                final JDialog jd = new JDialog();
                JOptionPane.showMessageDialog(jd,
                        e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE, new ImageIcon("about.jpg"));
            }
        }
        FaDE.getInstance().repaint();
    }

}
