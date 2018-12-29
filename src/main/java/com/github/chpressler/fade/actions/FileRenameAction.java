package com.github.chpressler.fade.actions;

import com.github.chpressler.fade.FaDE;
import com.github.chpressler.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by PRESSLER-1 on 6/16/2015.
 */
public class FileRenameAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {

        if(FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles().length == 1) {
            IFile f = FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()[0];
            String fileName = JOptionPane.showInputDialog("FileName", f.getName());
            try {
                if(!f.getConnector().getRenameCommand().rename(f, fileName)) {
                    throw new Exception("File could not be renamed");
                }
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