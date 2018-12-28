package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FileMoveAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (IFile source : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            try {
                IFile target = FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory();
                if(!target.getConnector().getMoveCommand().move(source, target)) {
                    throw new Exception("File could not be moved");
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
