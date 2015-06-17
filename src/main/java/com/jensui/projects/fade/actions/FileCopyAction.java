package com.jensui.projects.fade.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.commons.io.FileUtils;

import javax.swing.AbstractAction;

import com.jensui.projects.fade.FaDE;

public class FileCopyAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (File inputFile : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            try {
                File outputFile = new File(FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().getAbsolutePath() + "/" + inputFile.getName());
                int x = 0;
                while (outputFile.exists()) {
                    x++;
                    outputFile = new File(FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().getAbsolutePath() + "/" + "(" + x + ") " + inputFile.getName());
                }
                outputFile.createNewFile();
                if (inputFile.isDirectory()) {
                    FileUtils.copyDirectory(inputFile, outputFile);
                } else {
                    FileReader in = null;
                    FileWriter out = null;
                    try {
                        in = new FileReader(inputFile);
                        out = new FileWriter(outputFile);
                        int c;
                        while ((c = in.read()) != -1) {
                            out.write(c);
                        }
                    } finally {
                        in.close();
                        out.close();
                    }
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

}
