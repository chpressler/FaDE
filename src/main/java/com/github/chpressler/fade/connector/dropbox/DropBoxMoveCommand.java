package com.github.chpressler.fade.connector.dropbox;

import com.github.chpressler.fade.IFile;
import com.github.chpressler.fade.IMoveCommand;

public class DropBoxMoveCommand implements IMoveCommand {

    @Override
    public boolean move(IFile source, IFile target) throws Exception {
        return false;
    }

}
