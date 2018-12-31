package com.github.chpressler.fade.connector.dropbox;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.github.chpressler.fade.ICopyCommand;
import com.github.chpressler.fade.IFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DropBoxCopyCommand implements ICopyCommand {

    DbxClientV2 client;

    public DropBoxCopyCommand(DbxClientV2 client) {
        this.client = client;
    }

    @Override
    public void copy(IFile source, IFile target) throws Exception {
        try (InputStream in = new FileInputStream(source.getFile())) {
            FileMetadata metadata = client.files().uploadBuilder(target.getURI().getPath()+ File.separator +source.getName()).uploadAndFinish(in);
        }
    }

}
