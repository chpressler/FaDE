package com.github.chpressler.fade.connector.file;

import com.github.chpressler.fade.IDeleteCommand;
import com.github.chpressler.fade.IFile;
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
