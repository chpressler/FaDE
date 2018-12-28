package com.jensui.projects.fade.connector.file;

import com.jensui.projects.fade.IFile;
import com.jensui.projects.fade.IMoveCommand;

import java.io.File;

public class FileMoveCommand implements IMoveCommand {

    @Override
    public boolean move(IFile source, IFile target) throws Exception {
        String name = source.getName();
        if(target.isDir()) {
            return target.getFile().renameTo(new File(target.getFile().getAbsolutePath() + File.separator + name));
        } else {
            throw new Exception("target file is not a directory");
        }
    }

}
