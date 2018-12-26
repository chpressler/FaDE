package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;

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
            }
        }
        FaDE.getInstance().repaint();
    }

}
