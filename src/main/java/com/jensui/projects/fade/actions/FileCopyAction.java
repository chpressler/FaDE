package com.jensui.projects.fade.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.AbstractAction;

import com.jensui.projects.fade.FaDE;
import org.apache.commons.io.FileUtils;

public class FileCopyAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (File source : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            System.out.println( FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().setWritable(true) );
            try {
                File destination = new File(FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().getAbsolutePath() + "/" + source.getName());
                int x = 0;
                while (destination.exists()) {
                    x++;
                    destination = new File(FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().getAbsolutePath() + "/" + "(" + x + ") " + source.getName());
                }
                if (source.isDirectory()) {
                    destination.mkdir();
                    FileUtils.copyDirectory(source, destination);
                } else {
                    destination.createNewFile();
                    FileUtils.copyFile(source, destination);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        FaDE.getInstance().repaint();
    }

}
