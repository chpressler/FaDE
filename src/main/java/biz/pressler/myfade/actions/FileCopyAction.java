package biz.pressler.myfade.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.AbstractAction;

import biz.pressler.myfade.FaDE;

public class FileCopyAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
        for (File f : FaDE.getInstance().getSelectedFaDEComponent().getIExplorerComponent().getSelectedFiles()) {
            try {
                File outputFile = new File(FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().getAbsolutePath() + "/" + f.getName());
                if (outputFile.isDirectory()) {

                } else {
                    FileReader in = new FileReader(f);
                    FileWriter out = new FileWriter(outputFile);
                    int c;
                    while ((c = in.read()) != -1) {
                        out.write(c);
                    }
                    in.close();
                    out.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

}
