package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.IFile;

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
            try {
                f.getConnector().getRenameCommand().rename(f, "bla");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        FaDE.getInstance().repaint();

    }

}