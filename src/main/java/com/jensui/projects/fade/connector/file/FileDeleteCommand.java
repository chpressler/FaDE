package com.jensui.projects.fade.connector.file;

import com.jensui.projects.fade.IDeleteCommand;
import com.jensui.projects.fade.IFile;
import org.apache.commons.io.FileUtils;

public class FileDeleteCommand implements IDeleteCommand {

    @Override
    public void delete(IFile file) throws Exception {
        if(file.isDir()) {
            FileUtils.deleteDirectory(file.getFile());
        } else {
            file.getFile().delete();
        }
    }

}
