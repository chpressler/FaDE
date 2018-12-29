package com.github.chpressler.fade.actions;

import com.github.chpressler.fade.FaDE;
import com.github.chpressler.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by PRESSLER-1 on 6/16/2015.
 */
public class FileMKDIRAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {

        IFile f = FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getCurrentDirectory();
        String dirName = JOptionPane.showInputDialog("DirectoryName");
        try {
            if(!FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getRoot().getConnector().getCreateCommand().createDirectory(dirName, f)) {
                throw new Exception("Directory could not be created");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            final JDialog jd = new JDialog();
            JOptionPane.showMessageDialog(jd,
                    e1.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon("about.jpg"));
        }
        FaDE.getInstance().repaint();
    }

}