package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FileCopyAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for(IFile f : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            IFile target = FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory();
            try {
                target.getConnector().getCopyCommand().copy(f, target);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        FaDE.getInstance().repaint();
    }

}
