package com.jensui.projects.fade.connector.file;

import com.jensui.projects.fade.FaDE;
import com.jensui.projects.fade.ICopyCommand;
import com.jensui.projects.fade.IFile;
import org.apache.commons.io.FileUtils;

public class FIleCopyCommand implements ICopyCommand {

    @Override
    public void copy(IFile source, IFile target) throws Exception {
        FaDE.getInstance().getUnselectedFaDEComponent().getIExplorerComponent().getCurrentDirectory().setWritable(true);
        String name = source.getName();
        java.io.File t = new java.io.File(target.getURI().getPath() + java.io.File.separator + name);
        int x = 0;
                while (t.exists()) {
                    x++;
                    t = new java.io.File(target.getURI().getPath() + java.io.File.separator + name + "/" + "(" + x + ") " + source.getName());
                }
                if (source.isDir()) {
                    target.mkDir();
                    FileUtils.copyDirectory(source.getFile(), t);
                } else {
                    target.createNewFile();
                    FileUtils.copyFile(source.getFile(), t);
                }
    }

}
