package com.github.chpressler.fade.connector.dropbox;

import com.github.chpressler.fade.ICreateCommand;
import com.github.chpressler.fade.IFile;

public class DropBoxCreateCommand implements ICreateCommand {

    @Override
    public boolean createFile(String name, IFile dir) throws Exception {
        return false;
    }

    @Override
    public boolean createDirectory(String name, IFile dir) throws Exception {
        return false;
    }

}
