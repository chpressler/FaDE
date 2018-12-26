package com.jensui.projects.fade.connector.file;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.IRenameCommand;

import java.io.File;

public class FileRenameCommand implements IRenameCommand {

    @Override
    public void rename(IFile file, String newName) throws Exception {
        File newFile = new File(file.getURI().getPath().substring(0, file.getURI().getPath().lastIndexOf(File.separator)+1) + newName);
        file.getFile().renameTo(newFile);
    }

}
