package com.github.chpressler.fade.connector.file;

import com.github.chpressler.fade.IFile;
import com.github.chpressler.fade.IMoveCommand;

import java.io.File;

public class FileMoveCommand implements IMoveCommand {

    @Override
    public boolean move(IFile source, IFile target) throws Exception {
        String name = source.getName();
        if(target.isDir()) {
           // if(source.getOrigin().equals(FileConnector.class.getCanonicalName()) && target.getOrigin().equals(FileConnector.class.getCanonicalName())) {
                return source.getFile().renameTo(new File(target.getFile().getAbsolutePath() + File.separator + name));
           // } else {
            // }
        } else {
            throw new Exception("target file is not a directory");
        }
    }

}
