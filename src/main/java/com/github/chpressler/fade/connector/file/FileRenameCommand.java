package com.github.chpressler.fade.connector.file;

import com.github.chpressler.fade.IFile;
import com.github.chpressler.fade.IRenameCommand;

import java.io.File;

public class FileRenameCommand implements IRenameCommand {

    @Override
    public boolean rename(IFile file, String newName) throws Exception {
        File newFile = new File(file.getURI().getPath().substring(0, file.getURI().getPath().lastIndexOf(File.separator)+1) + newName);
        return file.getFile().renameTo(newFile);
    }

}
