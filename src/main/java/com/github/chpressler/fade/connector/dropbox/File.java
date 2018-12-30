package com.github.chpressler.fade.connector.dropbox;

import com.github.chpressler.fade.IConnector;
import com.github.chpressler.fade.IFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class File implements IFile {

    DropBoxConnector connector;
    boolean isDir;
    String name;
    URI uri;
    long modified;
    long size;

    List<IFile> children;
    IFile parent;

    public File(String name, DropBoxConnector c, boolean isDir, URI uri, long lastModified, long size) {
        this.connector = c;
        this.name = name;
        this.isDir = isDir;
        this.uri = uri;
        children = new ArrayList<>();
        this.modified = lastModified;
        this.size = size;
    }

    @Override
    public int getChildCount() {
        //return connector.getChildren(this).size();
        if(children.size() == 0) {
            children = connector.getChildren(this);
        }
        return children.size();
    }

    @Override
    public List<IFile> getChildren() {
        //return connector.getChildren(this);
        if(children.size() == 0) {
            children = connector.getChildren(this);
        }
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public boolean isDir() {
        return isDir;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public long getTotalSpace() {
        return 0;
    }

    @Override
    public long getFreeSpace() {
        return 0;
    }

    @Override
    public long getLastChanged() {
        return modified;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public java.io.File getFile() {
        return null;
    }

    private String getFileName(String path) {
        return path.split("/")[path.split("/").length-1];
    }

    private String getParentPath(String path) {
        if(path == null || path.trim().isEmpty()) {
            return "";
        }
        return path.substring(0, path.lastIndexOf("/"));
    }

    @Override
    public IFile getParent() {
        String name = getFileName(getParentPath(uri.getPath()));
        URI u = null;
        try {
            u = new URI(getParentPath(uri.getPath()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (getName().trim().isEmpty() || getName().equals("/")) {
            return null;
        }
        return new File( name, connector, true, u , 0, 0);
    }

    @Override
    public IConnector getConnector() {
        return connector;
    }

    @Override
    public void setConnector(IConnector connector) {
        this.connector = (DropBoxConnector) connector;
    }

    @Override
    public boolean mkDir() {
        try {
            return connector.getCreateCommand().createDirectory(name, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createNewFile() {
        try {
            return connector.getCreateCommand().createFile(name, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setWritable(boolean b) {
        return false;
    }
}
