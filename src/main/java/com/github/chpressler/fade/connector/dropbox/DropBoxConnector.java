package com.github.chpressler.fade.connector.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.github.chpressler.fade.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DropBoxConnector implements IConnector {

    public static String CONNECTOR_NAME = "DropBox";

    private static final String ACCESS_TOKEN = "3S6a37tHco8AAAAAAAAZTeOPgFfQn4civQ0I7h4gLtunQS5SbJoTq4i-PxdaeIBr";

    private DbxRequestConfig config;
    private DbxClientV2 client;

    IRenameCommand renameCommand;
    ICreateCommand createCommand;
    IMoveCommand moveComamnd;
    IDeleteCommand deleteCommand;
    ICopyCommand copyCommand;

    public DropBoxConnector() {
        renameCommand = new DropBoxRenameCommand();
        createCommand = new DropBoxCreateCommand();
        moveComamnd = new DropBoxMoveCommand();
        deleteCommand = new DropBoxDeleteCommand();
        copyCommand = new DropBoxCopyCommand();
        config = DbxRequestConfig.newBuilder("fade").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    private String getName(String path) {
        return path.split("/")[path.split("/").length-1];
    }

    private boolean isDir(String metadataJSON) {
        JSONTokener t = new JSONTokener(metadataJSON);
        JSONObject root = new JSONObject(t);
        return root.get(".tag").equals("folder");
    }

    private long getSize(String metadataJSON) {
        JSONTokener t = new JSONTokener(metadataJSON);
        JSONObject root = new JSONObject(t);
        try {
            return (long) (Integer) root.get("size");
        } catch(Exception e) {
            return 0L;
        }
    }

    public long getUsedSpace() {
        try {
            return client.users().getSpaceUsage().getUsed();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public long getTotalPace() {
        try {
            return client.users().getSpaceUsage().getAllocation().getIndividualValue().getAllocated();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private long lastModified(String metadataJSON) {
        JSONTokener t = new JSONTokener(metadataJSON);
        JSONObject root = new JSONObject(t);
        //DateFormat df = new SimpleDateFormat("yyyy-MM-ddTHH:mm:SSZ");
        try {
            //return df.parse((String) root.get("client_modified")).getTime();
            return Instant.parse((String) root.get("client_modified")).toEpochMilli();
        } catch (Exception e) {
            return 0L;
        }
    }

    public List<IFile> getChildren(IFile f) {
        List<IFile> list = new ArrayList<>();
        try {
            ListFolderResult result = client.files().listFolder(f.getURI().getPath().replaceAll("\\+", " "));
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    String metaDataString = metadata.toString();
                    boolean isDir = isDir(metaDataString);
                    IFile file =  new com.github.chpressler.fade.connector.dropbox.File(getName(metadata.getPathLower()), this, isDir, new URI(URLEncoder.encode(metadata.getPathLower(), "UTF-8")), lastModified(metaDataString), getSize(metaDataString));
                    list.add(file);
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
        try {
            if(uri.getPath().trim().isEmpty()) {
                return new com.github.chpressler.fade.connector.dropbox.File("", this, true, new URI(""), 0, getUsedSpace());
            }
            Metadata md = client.files().getMetadata(uri.getPath());
            String mds = md.toString();
            String name = md.getName();
            boolean isDir = isDir(mds);
            long modified = lastModified(mds);
            long size = getSize(mds);
            return new com.github.chpressler.fade.connector.dropbox.File(name, this, isDir, uri, modified, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IFile getFile(String path) {
        try {
            return getFile(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File convert(IFile f) {
        return null;
    }

    @Override
    public List<IFile> getRootFiles() {
        List<IFile> files = new ArrayList<>();
        files.add(getFile(""));
        return files;
    }

    @Override
    public IRenameCommand getRenameCommand() {
        return renameCommand;
    }

    @Override
    public ICopyCommand getCopyCommand() {
        return copyCommand;
    }

    @Override
    public IDeleteCommand getDeleteCommand() {
        return deleteCommand;
    }

    @Override
    public IMoveCommand getMoveCommand() {
        return moveComamnd;
    }

    @Override
    public ICreateCommand getCreateCommand() {
        return createCommand;
    }

}
