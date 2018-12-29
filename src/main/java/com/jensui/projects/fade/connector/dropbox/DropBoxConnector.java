package com.jensui.projects.fade.connector.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.jensui.projects.fade.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DropBoxConnector implements IConnector {

    private static final String ACCESS_TOKEN = "3S6a37tHco8AAAAAAAAZSk84YXGd0UNeSTBzTcyfxFcPuXbUPw4dajES-EWZKtOT";

    private String getName(String path) {
        return path.split("/")[path.split("/").length-1];
    }

    private boolean isDir(String metadataJSON) {
        JSONTokener t = new JSONTokener(metadataJSON);
        JSONObject root = new JSONObject(t);
        return root.get(".tag").equals("folder");
    }

    public List<IFile> getChildren(IFile f) {
        List<IFile> list = new ArrayList<>();
        try {
            DbxRequestConfig config = DbxRequestConfig.newBuilder("fade").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            ListFolderResult result = client.files().listFolder(f.getURI().getPath());
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                    boolean isDir = isDir(metadata.toString());
                    list.add(new com.jensui.projects.fade.connector.dropbox.File(getName(metadata.getPathLower()), this, isDir, new URI(metadata.getPathLower())));
                }

                if (!result.getHasMore()) {
                    break;
                }
                result = client.files().listFolderContinue(result.getCursor());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public IFile getFile(URI uri) {
        return null;
    }

    @Override
    public IFile getFile(String path) {
        return null;
    }

    @Override
    public File convert(IFile f) {
        return null;
    }

    @Override
    public List<IFile> getRootFiles() {
        List<IFile> files = new ArrayList<>();
        try {
            files.add(new com.jensui.projects.fade.connector.dropbox.File("", this, true, new URI("")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public IRenameCommand getRenameCommand() {
        return null;
    }

    @Override
    public ICopyCommand getCopyCommand() {
        return null;
    }

    @Override
    public IDeleteCommand getDeleteCommand() {
        return null;
    }

    @Override
    public IMoveCommand getMoveCommand() {
        return null;
    }

    @Override
    public ICreateCommand getCreateCommand() {
        return null;
    }

}
