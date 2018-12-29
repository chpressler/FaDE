package com.github.chpressler.fade;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface IFile {

    public int getChildCount();

    public List<IFile> getChildren();

    public String getName();

    public URI getURI();

    public boolean isDir();

    public String getDescription();

    public long getSize();

    public long getTotalSpace();

    public long getFreeSpace();

    public long getLastChanged();

    public boolean exists();

    public File getFile();

    public IFile getParent();

    public IConnector getConnector();

    public void setConnector(IConnector connector);

    public boolean mkDir();

    public boolean createNewFile();

    public boolean setWritable(boolean b);

}
