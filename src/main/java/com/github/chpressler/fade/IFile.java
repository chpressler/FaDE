package com.github.chpressler.fade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public interface IFile {

    public default String getOrigin() {
        return getConnector().getClass().getCanonicalName();
    }

    public int getChildCount();

    public List<IFile> getChildren();

    public String getName();

    public URI getURI();

    public boolean isDir();

    public String getDisplayName();

    public long getSize();

    public long getTotalSpace();

    public long getFreeSpace();

    public long getLastChanged();

    public boolean exists();

    public InputStream readFile() throws IOException;

    public IFile getParent();

    public IConnector getConnector();

    public void setConnector(IConnector connector);

    public boolean mkDir();

    public boolean createNewFile();

    public boolean setWritable(boolean b);

    public File getFile();

}
