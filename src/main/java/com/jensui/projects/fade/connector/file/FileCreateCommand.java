package com.jensui.projects.fade.connector.file;

import com.jensui.projects.fade.ICreateCommand;
import com.jensui.projects.fade.IFile;

import java.io.File;

public class FileCreateCommand implements ICreateCommand {

    @Override
    public boolean createFile(String name, IFile dir) throws Exception {
        return new File(dir.getFile().getAbsolutePath() + File.separator + name).createNewFile();
    }

    @Override
    public boolean createDirectory(String name, IFile dir) throws Exception {
        return new File(dir.getFile().getAbsolutePath() + File.separator + name).mkdir();
    }

}
