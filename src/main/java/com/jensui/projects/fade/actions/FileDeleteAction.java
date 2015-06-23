package com.jensui.projects.fade.actions;

import com.jensui.projects.fade.FaDE;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import org.apache.commons.io.FileUtils;

public class FileDeleteAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (File source : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            try {
                if (source.isDirectory()) {
                    FileUtils.deleteDirectory(source);
                } else {
                    source.delete();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        FaDE.getInstance().repaint();
    }

}
