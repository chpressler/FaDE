package com.github.chpressler.fade.connector.dropbox;

import com.github.chpressler.fade.IFile;
import com.github.chpressler.fade.IRenameCommand;

public class DropBoxRenameCommand implements IRenameCommand {

    @Override
    public boolean rename(IFile file, String newName) throws Exception {
        return false;
    }

}
